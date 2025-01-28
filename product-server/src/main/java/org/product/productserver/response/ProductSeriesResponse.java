package org.product.productserver.response;

import org.product.productserver.dto.ProductSeriesDetails;
import org.product.productserver.dto.ProductSeriesDto;
import org.product.productserver.request.GenericRequest;
import java.util.Collection;
import java.util.List;

public class ProductSeriesResponse extends GenericRequest {

    private Long productSeriesId;

    private Long productsCount;

    private String productSeriesCode;

    private String productLot;

    private List<ProductSeriesDetails> productSeriesDetails;

    private Collection<String> connectedProductsIds;

    public ProductSeriesResponse() {
    }

    public ProductSeriesResponse(Long id, String productSeriesCode, List<ProductSeriesDetails> productSeriesDetails) {
        this.id = id;
        this.productSeriesCode = productSeriesCode;
        this.productSeriesDetails = productSeriesDetails;
    }

    public Long getProductSeriesId() {
        return productSeriesId;
    }

    public void setProductSeriesId(Long productSeriesId) {
        this.productSeriesId = productSeriesId;
    }

    public Long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Long productsCount) {
        this.productsCount = productsCount;
    }

    public String getProductSeriesCode() {
        return productSeriesCode;
    }

    public void setProductSeriesCode(String productSeriesCode) {
        this.productSeriesCode = productSeriesCode;
    }

    public String getProductLot() {
        return productLot;
    }

    public void setProductLot(String productLot) {
        this.productLot = productLot;
    }

    public List<ProductSeriesDetails> getProductSeriesDetails() {
        return productSeriesDetails;
    }

    public void setProductSeriesDetails(List<ProductSeriesDetails> productSeriesDetails) {
        this.productSeriesDetails = productSeriesDetails;
    }

    public Collection<String> getConnectedProductsIds() {
        return connectedProductsIds;
    }

    public void setConnectedProductsIds(Collection<String> connectedProductsIds) {
        this.connectedProductsIds = connectedProductsIds;
    }

    public ProductSeriesResponse(ProductSeriesDto productSeriesDto) {
        this.setProductSeriesCode(productSeriesDto.getProductSeriesCode());
        this.setConnectedProductsIds(productSeriesDto.getConnectedProductsIds());
        this.setName(productSeriesDto.getName());
        this.setDescription(productSeriesDto.getDescription());
        this.setProductLot(productSeriesDto.getProductLot());
        this.setProductsCount(productSeriesDto.getProductsCount());
    }
}
