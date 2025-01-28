package org.product.productserver.repository;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.dto.ProductSeriesDto;

import java.util.List;

public interface ProductSeriesRepository {

    List<ProductSeriesDto> getProductSeriesList();

    List<ProductDto> fetchProductsSeriesCodes();

    List<ProductDto> fetchAvailableProducts();

    List<ProductSeriesDto> fetchProductsByProductSeriesCode(String qrCode);

    long createProductSeries(ProductSeriesDto ProductSeriesDto);

    boolean updateProductSeries(ProductSeriesDto ProductSeriesDto);

    List<ProductDto> getProductSeriesById(long id);

    boolean deleteProductSeries(Long id);
}
