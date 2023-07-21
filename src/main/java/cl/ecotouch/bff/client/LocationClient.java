package cl.ecotouch.bff.client;

import cl.ecotouch.bff.dto.LocationDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocationClient {
    private final ObjectMapper objectMapper;
    private final RestTemplate request;
    public List<LocationDTO> getMongoPoints(String url) throws Exception {

        log.info("[getMongoPoints]: Consultando puntos MongoDB a: {}",url);
        ResponseEntity<?> response = request.exchange(url, HttpMethod.GET, null, LocationDTO[].class);
        log.info("[getMongoPoints] Respuesta: {}",response.getStatusCode());
        if (response.getStatusCode().is2xxSuccessful()) {
            LocationDTO[] puntos = (LocationDTO[]) response.getBody();
            log.info("LocationClient - getMongoPoints -> status code {}",response.getStatusCode());
            return Arrays.asList(puntos != null ? puntos : new LocationDTO[0]);
        }else{
            log.info("LocationClient - getMongoPoints -> error:  {}",response.getBody());
            throw new Exception("Internal server error");
        }
    }

    public List<LocationDTO> getPuntosLimpios(URI uri)  throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial((chain, authType) -> true)
                .build();
        HttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))
                .build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);

        log.info("Response MMA: {}",response.getEntity().getContent());

        if (response.getStatusLine().getStatusCode() == 200) {
            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            return objectMapper.readValue(responseString, new TypeReference<>() {
            });
        }else{
            log.info("LocationClient - getPuntosLimpios -> error:  {}",response.getEntity().getContent());
            throw new Exception("Internal server error");
        }
    }
}
