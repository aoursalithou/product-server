package org.product.productserver.dto;


import org.product.productserver.request.GenericRequest;
import org.product.productserver.request.ProductRequest;

import java.util.Collection;
import java.util.Date;

public class ProductDto extends GenericRequest {

    private Long productSeriesId;

    private String productRef;

    private String productLot;

    private String productManufacturer;

    private Date productPurchaseDate;

    private String productNotes;

    private String productSeriesCode;

    private Collection<String> productSeriesCodesList;

    private Date purchaseDateFrom;

    private Date purchaseDateTo;

    private String productSeriesQrCode;

    private Long productsCount;


    public ProductDto() {
    }

    public ProductDto(String name, Date purchaseDateFrom, Date purchaseDateTo, Collection<String> productSeriesCodesList) {
        this.name = name;
        this.purchaseDateFrom = purchaseDateFrom;
        this.purchaseDateTo = purchaseDateTo;
        this.productSeriesCodesList = productSeriesCodesList;
    }

    public ProductDto(ProductRequest request, Long id, boolean isUpdate) {
        this.setName(request.getName());
        this.setDescription(request.getDescription());
        this.setProductRef(request.getProductRef());
        this.setProductLot(request.getProductLot());
        this.setProductManufacturer(request.getProductManufacturer());
        this.setProductPurchaseDate(request.getProductPurchaseDate());
        this.setProductNotes(request.getProductNotes());
        if (isUpdate) {
            this.setId(id);
        }
    }

    public Long getProductSeriesId() {
        return productSeriesId;
    }

    public void setProductSeriesId(Long productSeriesId) {
        this.productSeriesId = productSeriesId;
    }

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

    public String getProductSeriesCode() {
        return productSeriesCode;
    }

    public void setProductSeriesCode(String productSeriesCode) {
        this.productSeriesCode = productSeriesCode;
    }

    public Collection<String> getProductSeriesCodesList() {
        return productSeriesCodesList;
    }

    public void setProductSeriesCodesList(Collection<String> productSeriesCodesList) {
        this.productSeriesCodesList = productSeriesCodesList;
    }

    public Date getPurchaseDateFrom() {
        return purchaseDateFrom;
    }

    public void setPurchaseDateFrom(Date purchaseDateFrom) {
        this.purchaseDateFrom = purchaseDateFrom;
    }

    public Date getPurchaseDateTo() {
        return purchaseDateTo;
    }

    public void setPurchaseDateTo(Date purchaseDateTo) {
        this.purchaseDateTo = purchaseDateTo;
    }

    public String getProductSeriesQrCode() {
        return productSeriesQrCode;
    }

    public void setProductSeriesQrCode(String productSeriesQrCode) {
        this.productSeriesQrCode = productSeriesQrCode;
    }

    public Long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(Long productsCount) {
        this.productsCount = productsCount;
    }
}
