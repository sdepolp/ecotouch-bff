package cl.ecotouch.bff.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequestDTO {
    String lat;
    String lng;
}
