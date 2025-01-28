package org.product.productserver.service;


import org.product.productserver.dto.ProductDto;
import org.product.productserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> fetchProducts() {
        return productRepository.fetchProducts();
    }

    @Override
    public List<ProductDto> fetchProductsSeriesCodes() {
        return productRepository.fetchProductsSeriesCodes();
    }

    @Override
    public Page<ProductDto> getProductsList(Pageable pageable, String name,
                                            Date purchaseDateFrom,
                                            Date purchaseDateTo, Collection<String> productSeriesCodesList) {
        return productRepository.getProductsList(pageable, new ProductDto(name, purchaseDateFrom, purchaseDateTo, productSeriesCodesList));
    }

    @Override
    public long createProduct(ProductDto dto) {

        return productRepository.createProduct(dto);
    }

    @Override
    public boolean updateProduct(ProductDto dto) {

        return productRepository.updateProduct(dto);
    }

    @Override
    public ProductDto getProductById(long id) {

        return productRepository.getProductById(id);
    }
    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.deleteProduct(id);
    }
}
