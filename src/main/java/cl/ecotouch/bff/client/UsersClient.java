package cl.ecotouch.bff.client;

import cl.ecotouch.bff.dto.LocationDTO;
import cl.ecotouch.bff.dto.UsersDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsersClient {

    private final RestTemplate request;

    public List<UsersDto> getUsers(String urlIgDbUsers) throws Exception {
        ResponseEntity<?> response = request.exchange(urlIgDbUsers, HttpMethod.GET, null, UsersDto[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            UsersDto[] puntos = (UsersDto[]) response.getBody();
            log.info("UsersClient - getUsers -> status code {}",response.getStatusCode());
            return Arrays.asList(puntos != null ? puntos : new UsersDto[0]);
        }else{
            log.info("UsersClient - getUsers -> error:  {}",response.getBody());
            throw new Exception("Internal server error");
        }
    }
}
