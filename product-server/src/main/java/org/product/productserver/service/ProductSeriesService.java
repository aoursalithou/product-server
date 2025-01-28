package org.product.productserver.service;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.dto.ProductSeriesDto;
import org.product.productserver.response.ProductSeriesResponse;

import java.util.List;

public interface ProductSeriesService {

    List<ProductSeriesResponse> getProductSeriesList();

    List<ProductDto> fetchProductsSeriesCodes();

    List<ProductSeriesDto> fetchProductsByProductSeriesCode(String qrCode);

    List<ProductDto> fetchAvailableProducts();

    long createProductSeries(ProductSeriesDto dto);

    boolean updateProductSeries(ProductSeriesDto dto);

    List<ProductDto> getProductSeriesById(long id);

    boolean deleteProductSeries(Long id);
}
