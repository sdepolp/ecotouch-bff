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
public class RequestPickupDTO {
    private String _id;
    String username;
    String datetimeRequest;
    List<String> materialType;
    double volume;
    double latitude;
    double longitude;
    String dateTimeScheduled;
    String textAddress;
    String statusRequest;
    String mobile;
}
