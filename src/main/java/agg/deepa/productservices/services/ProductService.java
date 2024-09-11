package agg.deepa.productservices.services;

import agg.deepa.productservices.dtos.ProductDto;
import agg.deepa.productservices.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getSingleProduct(Long productId);

    Product addNewProduct(ProductDto product);

    Product updateProduct( Long productId, Product product);

    boolean deleteProduct( Long productId);
    String getLimitProductResult(Long productId);
    String getProductBySorting(Long productId);
    String updateProductPartially(Long productId);
    Product replaceProduct(Long productId, Product product);
}
