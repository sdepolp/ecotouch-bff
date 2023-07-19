package cl.ecotouch.bff.client;

import cl.ecotouch.bff.dto.LocationDTO;
import cl.ecotouch.bff.dto.RequestPickupDTO;
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
public class OrderClient {
    private final ObjectMapper objectMapper;
    private final RestTemplate request;

    public List<RequestPickupDTO> getRequestPickup(String url) throws Exception {
        ResponseEntity<?> response = request.exchange(url, HttpMethod.GET, null, RequestPickupDTO[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            RequestPickupDTO[] orders = (RequestPickupDTO[]) response.getBody();
            log.info("OrderClient - getRequestPickup -> status code {}",response.getStatusCode());
            return Arrays.asList(orders != null ? orders : new RequestPickupDTO[0]);
        }else{
            log.info("OrderClient - getRequestPickup -> error:  {}",response.getBody());
            throw new Exception("Internal server error");
        }
    }
}
