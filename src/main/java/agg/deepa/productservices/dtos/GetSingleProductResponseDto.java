package agg.deepa.productservices.dtos;

import agg.deepa.productservices.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;

}
