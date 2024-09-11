package agg.deepa.productservices.clients.fakeStoreapi;

import agg.deepa.productservices.dtos.ProductDto;
import agg.deepa.productservices.exception.NotFoundException;
import agg.deepa.productservices.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.Optional;


@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l =restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        return Arrays.asList(l.getBody());
    };

    Optional<FakeStoreProductDto> getSingleProduct(Long productId) throws NotFoundException {
        return null;
    }

    FakeStoreProductDto addNewProduct(ProductDto product){return null;}

    FakeStoreProductDto updateProduct( Long productId, Product product){return null;};

    FakeStoreProductDto deleteProduct( Long productId){return null;};
    FakeStoreProductDto getLimitProductResult(Long productId){return null ;};
    FakeStoreProductDto getProductBySorting(Long productId){return null;};
    FakeStoreProductDto updateProductPartially(Long productId){return null;};
    FakeStoreProductDto replaceProduct(Long productId, Product product){return null;}
}
