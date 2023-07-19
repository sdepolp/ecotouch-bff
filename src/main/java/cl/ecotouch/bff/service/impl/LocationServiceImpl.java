package cl.ecotouch.bff.service.impl;

import cl.ecotouch.bff.client.LocationClient;
import cl.ecotouch.bff.dto.LocationDTO;
import cl.ecotouch.bff.dto.LocationRequestDTO;
import cl.ecotouch.bff.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.stream.Location;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationClient locationClient;
    @Value("${url.mma.puntoslimpios}")
    String urlMmaPuntosLimpios;

    @Value("${url.ig-mongo}")
    String urlIgMongo;

    @Override
    public List<LocationDTO> getAllLocation(LocationRequestDTO location) throws Exception {
        String urlMongo = urlIgMongo+"/v1/location/get-all-points";

        URIBuilder builder = new URIBuilder(urlMmaPuntosLimpios+"/api/points/geo");
        builder.setParameter("lat", location.getLat())
                .setParameter("lng", location.getLng())
                .setParameter("distance","7");
        URI uri = builder.build();

        List<LocationDTO> locationMma = locationClient.getPuntosLimpios(uri);

        List<LocationDTO> locationMongo = locationClient.getMongoPoints(urlMongo);

        //TODO HACER CLIENTE QUE VAYA POR API DEL GOBLocationDTO
        List<LocationDTO> concatenatedList = new ArrayList<>();
        concatenatedList.addAll(locationMma);
        concatenatedList.addAll(locationMongo);

        return concatenatedList;
    }
}
