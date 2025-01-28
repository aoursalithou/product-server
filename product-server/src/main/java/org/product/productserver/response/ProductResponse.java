package org.product.productserver.response;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.request.GenericRequest;

import java.util.Date;

public class ProductResponse extends GenericRequest {

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

    public ProductResponse(ProductDto productDto) {
        this.setId(productDto.getId());
        this.setName(productDto.getName());
        this.setDescription(productDto.getDescription());
        this.setProductRef(productDto.getProductRef());
        this.setProductLot(productDto.getProductLot());
        this.setProductManufacturer(productDto.getProductManufacturer());
        this.setProductPurchaseDate(productDto.getProductPurchaseDate());
        this.setProductNotes(productDto.getProductNotes());
    }
}
