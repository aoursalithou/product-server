package org.product.productserver.dto;

import org.product.productserver.request.GenericRequest;
import org.product.productserver.request.ProductSeriesRequest;

import java.util.Collection;

public class ProductSeriesDto extends GenericRequest {

    private String ProductSeriesCode;

    private String ProductLot;

    private Long ProductsCount;

    private Collection<String> connectedProductsIds;

    private Collection<String> unconnectedProductsIds;

    public ProductSeriesDto() {
    }

    public String getProductSeriesCode() {
        return ProductSeriesCode;
    }

    public void setProductSeriesCode(String productSeriesCode) {
        ProductSeriesCode = productSeriesCode;
    }

    public String getProductLot() {
        return ProductLot;
    }

    public void setProductLot(String productLot) {
        ProductLot = productLot;
    }

    public Long getProductsCount() {
        return ProductsCount;
    }

    public void setProductsCount(Long productsCount) {
        ProductsCount = productsCount;
    }

    public Collection<String> getConnectedProductsIds() {
        return connectedProductsIds;
    }

    public void setConnectedProductsIds(Collection<String> connectedProductsIds) {
        this.connectedProductsIds = connectedProductsIds;
    }

    public Collection<String> getUnconnectedProductsIds() {
        return unconnectedProductsIds;
    }

    public void setUnconnectedProductsIds(Collection<String> unconnectedProductsIds) {
        this.unconnectedProductsIds = unconnectedProductsIds;
    }

    public ProductSeriesDto(ProductSeriesRequest request, Long id, boolean isUpdate) {
        this.setProductSeriesCode(request.getProductSeriesCode());
        this.setConnectedProductsIds(request.getConnectedProductsIds());
        if (isUpdate) {
            this.setId(id);
            this.setUnconnectedProductsIds(request.getUnconnectedProductsIds());
        }
    }
}
