package cl.ecotouch.bff.service.impl;

import cl.ecotouch.bff.client.OrderClient;
import cl.ecotouch.bff.client.UsersClient;
import cl.ecotouch.bff.dto.OrderRequestDto;
import cl.ecotouch.bff.dto.RequestPickupDTO;
import cl.ecotouch.bff.dto.UsersDto;
import cl.ecotouch.bff.service.OrderService;
import cl.ecotouch.bff.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Value("${url.ig-mongo}")
    String urlIgMongo;

    @Value("${url.ms-auth}")
    String urlDbEcotouch;

    private final OrderClient orderClient;
    private final UsersClient usersClient;

    @Override
    public List<RequestPickupDTO> getDailyOrders(OrderRequestDto orderRequestDto) throws Exception {
        String urlMongo = urlIgMongo+"/v1/pickup/get-daily-orders";
        String urlIgDbUsers = urlDbEcotouch+"/v1/users/get-users";

        List<UsersDto> lstUsers = usersClient.getUsers(urlIgDbUsers);

        List<RequestPickupDTO> allMarkers = orderClient.getRequestPickup(urlMongo);

        List<RequestPickupDTO> markersWithinRadius = allMarkers.stream()
                .filter(marker -> {
                    double distance = Utils.calculateDistance(orderRequestDto.getLat(), orderRequestDto.getLng(), marker.getLatitude(), marker.getLongitude());
                    return distance <= orderRequestDto.getDistance();
                })
                .peek(marker -> {
                    lstUsers.stream()
                            .filter(user -> user.getUsername().equals(marker.getUsername()))
                            .findFirst()
                            .ifPresent(user -> marker.setMobile(user.getMobile()));
                })
                .collect(Collectors.toList());

        return markersWithinRadius;

    }

    @Override
    public List<RequestPickupDTO> getOrdersByUser(String username) throws Exception {
        String urlMongo = urlIgMongo+"/v1/pickup/get-all-orders";
        return orderClient.getRequestPickup(urlMongo).stream()
                .filter(requestPickupDTO -> Objects.nonNull(requestPickupDTO.getUsername()))
                .filter(requestPickupDTO -> requestPickupDTO.getUsername().equals(username))
                .collect(Collectors.toList());
    }
}
