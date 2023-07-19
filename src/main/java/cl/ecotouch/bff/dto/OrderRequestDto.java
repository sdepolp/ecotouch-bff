package cl.ecotouch.bff.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    double lat;
    double lng;
    int distance;
}
