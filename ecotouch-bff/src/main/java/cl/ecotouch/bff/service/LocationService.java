package cl.ecotouch.bff.service;

import cl.ecotouch.bff.dto.LocationDTO;
import cl.ecotouch.bff.dto.LocationRequestDTO;

import java.util.List;

public interface LocationService {
    List<LocationDTO> getAllLocation(LocationRequestDTO location);
}
