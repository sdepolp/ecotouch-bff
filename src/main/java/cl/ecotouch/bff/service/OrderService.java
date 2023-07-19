package cl.ecotouch.bff.service;

import cl.ecotouch.bff.dto.OrderRequestDto;
import cl.ecotouch.bff.dto.RequestPickupDTO;

import java.util.List;

public interface OrderService {
    List<RequestPickupDTO> getDailyOrders(OrderRequestDto orderRequestDto) throws Exception;

    List<RequestPickupDTO> getOrdersByUser(String username) throws Exception;
}
