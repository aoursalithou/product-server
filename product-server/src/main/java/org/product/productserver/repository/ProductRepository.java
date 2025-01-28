package org.product.productserver.repository;

import org.product.productserver.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {

    List<ProductDto> fetchProducts();

    List<ProductDto> fetchProductsSeriesCodes();

    Page<ProductDto> getProductsList(Pageable pageable, ProductDto dto);

    long createProduct(ProductDto productDto);

    boolean updateProduct(ProductDto productDto);

    ProductDto getProductById(long id);

    boolean deleteProduct(Long id);
}
