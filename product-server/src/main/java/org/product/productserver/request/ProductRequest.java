package org.product.productserver.request;

import java.util.Date;

public class ProductRequest extends GenericRequest {

    private String productRef;

    private String productLot;

    private String productManufacturer;

    private Date productPurchaseDate;

    private String productNotes;

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getProductLot() {
        return productLot;
    }

    public void setProductLot(String productLot) {
        this.productLot = productLot;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public Date getProductPurchaseDate() {
        return productPurchaseDate;
    }

    public void setProductPurchaseDate(Date productPurchaseDate) {
        this.productPurchaseDate = productPurchaseDate;
    }

    public String getProductNotes() {
        return productNotes;
    }

    public void setProductNotes(String productNotes) {
        this.productNotes = productNotes;
    }
}