package cl.ecotouch.bff.service.impl;

import cl.ecotouch.bff.client.LocationClient;
import cl.ecotouch.bff.dto.LocationDTO;
import cl.ecotouch.bff.dto.LocationRequestDTO;
import cl.ecotouch.bff.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationClient locationClient;

    @Override
    public List<LocationDTO> getAllLocation(LocationRequestDTO location) {
        //TODO HACER CLIENTE QUE VAYA POR API DEL GOB

        List<LocationDTO> locationMongo = locationClient.getMongoPoints();

        return locationMongo;
    }
}
