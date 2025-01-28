package org.product.productserver.service;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.dto.ProductSeriesDetails;
import org.product.productserver.dto.ProductSeriesDto;
import org.product.productserver.repository.ProductSeriesRepository;
import org.product.productserver.response.ProductSeriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProductSeriesServiceImpl implements ProductSeriesService {


    @Autowired
    private ProductSeriesRepository productSeriesRepository;

    @Override
    public List<ProductDto> fetchProductsSeriesCodes() {

        return productSeriesRepository.fetchProductsSeriesCodes();
    }

    @Override
    public List<ProductDto> fetchAvailableProducts() {
        return productSeriesRepository.fetchAvailableProducts();
    }

    @Override
    public long createProductSeries(ProductSeriesDto dto) {
        return productSeriesRepository.createProductSeries(dto);
    }

    @Override
    public boolean updateProductSeries(ProductSeriesDto dto) {
        return productSeriesRepository.updateProductSeries(dto);
    }

    @Override
    public List<ProductSeriesDto> fetchProductsByProductSeriesCode(String qrCode) {
        return productSeriesRepository.fetchProductsByProductSeriesCode(qrCode);
    }

    @Override
    public List<ProductSeriesResponse> getProductSeriesList() {
        List<ProductSeriesResponse> productSeriesResponseList = new ArrayList<>();
        List<ProductDto> productDtos = this.fetchProductsSeriesCodes();
        List<ProductSeriesDto> productSeriesDtos = productSeriesRepository.getProductSeriesList();

        // loop list with productCodes
        for (ProductDto tempProductDto : productDtos) {
             Long id = tempProductDto.getId();
             String productSeriesCode = tempProductDto.getProductSeriesCode(); // get product code
             List<ProductSeriesDetails> productSeriesDetailsList = new ArrayList<>(); // create product details list
            for (ProductSeriesDto tempProductSeriesDto : productSeriesDtos) {
                if (!tempProductSeriesDto.getProductSeriesCode().equals(productSeriesCode)) {
                    continue;
                } else {
                    ProductSeriesDetails productSeriesDetails = new ProductSeriesDetails();
                    productSeriesDetails.setProductName(tempProductSeriesDto.getName());
                    productSeriesDetails.setProductDescription(tempProductSeriesDto.getDescription());
                    productSeriesDetails.setProductLot(tempProductSeriesDto.getProductLot());
                    productSeriesDetails.setProductsCount(tempProductSeriesDto.getProductsCount());
                    productSeriesDetailsList.add(productSeriesDetails);
                }
            }
            ProductSeriesResponse productSeriesResponse = new ProductSeriesResponse(id,productSeriesCode, productSeriesDetailsList);
            productSeriesResponseList.add(productSeriesResponse);
        }
        return productSeriesResponseList;
    }

    @Override
    public List<ProductDto> getProductSeriesById(long id) {
        return productSeriesRepository.getProductSeriesById(id);
    }

    @Override
    public boolean deleteProductSeries(Long id) {
        return productSeriesRepository.deleteProductSeries(id);
    }
}
