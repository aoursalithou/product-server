package org.product.productserver.request;

import java.util.Collection;

public class ProductSeriesRequest {

    private String productSeriesCode;

    private Collection<String> connectedProductsIds;

    private Collection<String> unconnectedProductsIds;

    public String getProductSeriesCode() {
        return productSeriesCode;
    }

    public void setProductSeriesCode(String productSeriesCode) {
        this.productSeriesCode = productSeriesCode;
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

    public void setUnconnectedproductsIds(Collection<String> unconnectedProductsIds) {
        this.unconnectedProductsIds = unconnectedProductsIds;
    }
}
