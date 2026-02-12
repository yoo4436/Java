package tw.brad.spring04.dto;

import java.util.List;

/* 
@Getter
@AllArgsConstructor
public class CustomerDto {
    private String CustomerId;
    private String CustomerName;
    private List<OrderDto> orders;
}
*/

public record CustomerDto(String customerId, String companyName, List<OrderDto> orders) {
    
}