package tw.brad.spring04.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailDto {
    private BigDecimal price;
    private Integer qty;
    private String productName;
}
