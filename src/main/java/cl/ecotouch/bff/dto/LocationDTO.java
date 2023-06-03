package cl.ecotouch.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private String _id;
    String distance;
    String lat;
    String status;
    String lng;
    String owner;
    String type;
    String address_type;
    String manager;
    String address_number;
    String address_name;
    RegionDTO region;
    CommuneDTO commune;
    List<String> materials;
}

