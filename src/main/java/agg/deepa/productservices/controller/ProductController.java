package agg.deepa.productservices.controller;

import agg.deepa.productservices.dtos.ErrorResponseDto;
import agg.deepa.productservices.dtos.ProductDto;
import agg.deepa.productservices.exception.NotFoundException;
import agg.deepa.productservices.models.Category;
import agg.deepa.productservices.models.Product;
import agg.deepa.productservices.repository.ProductRepository;
import agg.deepa.productservices.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess4whenthey");
        Optional<Product> productOptional = productService.getSingleProduct(productId);

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);

        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );
        // add headers and token
        return response;
    }

    //    public GetSingleProductResponseDto getSingleProduct(@PathVariable("productId") Long productId){
//
//        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
//        responseDto.setProduct(
//                productService.getSingleProduct(productId)
//        );
//        return responseDto;
//    }
    @GetMapping("/products/limit")
    public String getLimitProductResult(@RequestParam(defaultValue = "3") int limit) {
        return "Returning total products with id" + limit;
    }

    @GetMapping("/products/sort")
    public String getProductBySorting(@RequestParam(defaultValue = "2") String sort) {
        return "returning result with sorting order" + sort;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product) {
        Product newProduct = productService.addNewProduct(product);


        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);
        return response;
    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());


        return productService.updateProduct(productId, product);
    }

    @PatchMapping("/products/{productId}")
    public String updateProductPartially(@PathVariable("productId") Long productId) {
        return "Updating product with id" + productId;
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting product with id " + productId;
    }
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> handleNotFoundException(Exception exception){
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    //}

}
