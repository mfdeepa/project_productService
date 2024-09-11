package agg.deepa.productservices.services;

import agg.deepa.productservices.clients.fakeStoreapi.FakeStoreClient;
import agg.deepa.productservices.clients.fakeStoreapi.FakeStoreProductDto;
import agg.deepa.productservices.dtos.ProductDto;
import agg.deepa.productservices.models.Category;
import agg.deepa.productservices.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static jdk.javadoc.internal.tool.Main.execute;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor,uriVariables);
    }
    private Product convertFakeStroeProductDtoToProduct(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }
    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();


   //    this below lines are written in fakeStoreClient
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto[]> l =restTemplate.getForEntity(
//                "https://fakestoreapi.com/products",
//                FakeStoreProductDto[].class
//        );
        List<Product> answer = new ArrayList<>();
        for (FakeStoreProductDto productDto: fakeStoreProductDtos){
            answer.add(convertFakeStroeProductDtoToProduct(productDto));

//            Product product = new Product();
//            product.setId(productDto.getId());
//            product.setTitle(productDto.getTitle());
//            product.setPrice(productDto.getPrice());
//            Category category = new Category();
//            category.setName(productDto.getCategory());
//            product.setCategory(category);
//            product.setImageUrl(productDto.getImage());
//            answer.add(product);

        }
//        for (Object object : l.getBody()){
//            ProductDto productDto = (ProductDto) object;
//            Product product = new Product();
//            product.setId(productDto.getId());
//            product.setTitle(productDto.getTitle());
//            product.setPrice(productDto.getPrice());
//            Category category = new Category();
//            category.setName(productDto.getCategory());
//            product.setCategory(category);
//            product.setImageUrl(productDto.getImage());
//            answer.add(product);
//        }
        return answer;
    }

    /*
    Return a product object with all the details of the fetched product.
    The ID of the category will be null but the name of the category shall
    be correct.
     */
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =  restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = response.getBody();

        // getbody will return the ProductDto object what are the object, we are trying to convert.

//        Product product = new Product();
//        product.setId(productDto.getId());
//        product.setTitle(productDto.getTitle());
//        product.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
//        product.setImageUrl(productDto.getImage());
        if (productDto == null){
            return Optional.empty();
        }
        // if condition is too check whether the product id is available or not.
        return Optional.of(convertFakeStroeProductDtoToProduct(productDto));

//        return product;

        // this getForEntity need parameter -> URL, return type, params_in_url...
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class);
        FakeStoreProductDto productDto = response.getBody();
//        Product product1 = new Product();
//        product1.setId(productDto.getId());
//        product1.setTitle(productDto.getTitle());
//        product1.setPrice(productDto.getPrice());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product1.setCategory(category);
//        product1.setImageUrl(productDto.getImage());

        return convertFakeStroeProductDtoToProduct(productDto);

        //return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStroeProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }
    public Product replaceProduct(Long productId, Product product) {


        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return true;
    }

    @Override
    public String getLimitProductResult(Long productId) {
        return null;
    }

    @Override
    public String getProductBySorting(Long productId) {
        return null;
    }

    @Override
    public String updateProductPartially(Long productId) {
        return null;
    }
}
