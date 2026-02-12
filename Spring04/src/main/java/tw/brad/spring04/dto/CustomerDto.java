package tw.brad.spring04.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerDto {
    private String CustomerId;
    private String CustomerName;
    private List<OrderDto> orders;
}
