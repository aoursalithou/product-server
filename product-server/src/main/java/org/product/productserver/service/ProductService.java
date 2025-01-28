package org.product.productserver.service;


import org.product.productserver.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ProductService {

    List<ProductDto> fetchProducts();

    List<ProductDto> fetchProductsSeriesCodes();

    Page<ProductDto> getProductsList(Pageable pageable, String name, Date purchaseDateFrom, Date purchaseDateTo, Collection<String> productSeriesCodesList);

    long createProduct(ProductDto dto);

    boolean updateProduct(ProductDto dto);

    ProductDto getProductById(long id);

    boolean deleteProduct(Long id);
}



