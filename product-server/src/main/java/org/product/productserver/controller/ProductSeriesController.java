package org.product.productserver.controller;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.dto.ProductSeriesDto;
import org.product.productserver.request.ProductSeriesRequest;
import org.product.productserver.response.ProductSeriesResponse;
import org.product.productserver.service.ProductSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products-series")
public class ProductSeriesController {

    @Autowired
    private ProductSeriesService productSeriesService;

    @RequestMapping("/get-product-series-list")
    public ResponseEntity<List<ProductSeriesResponse>> getproductSeriesList() {

        List<ProductSeriesResponse> dtos = productSeriesService.getProductSeriesList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/create-product-series")
    public ResponseEntity<Long> create(@RequestBody ProductSeriesRequest request) {
        ProductSeriesDto dto = new ProductSeriesDto(request, null, false);
        return ResponseEntity.status(HttpStatus.OK).body(productSeriesService.createProductSeries(dto));
    }

    @PutMapping("/update-product-series")
    public ResponseEntity<Boolean> update(@RequestParam Long id, @RequestBody ProductSeriesRequest request) {
        ProductSeriesDto productSeriesDto = new ProductSeriesDto(request, id, true);
        return ResponseEntity.status(HttpStatus.OK).body(productSeriesService.updateProductSeries(productSeriesDto));
    }

    @GetMapping("/fetch-available-products")
    public ResponseEntity<List<ProductDto>> fetchAvailableproducts() {
        List<ProductDto> retVal = productSeriesService.fetchAvailableProducts();
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/fetch-products-by-product-series-code")
    public ResponseEntity<List<ProductSeriesDto>> fetchproductsByproductSeriesCode(
            @RequestParam String qrCode) {
        List<ProductSeriesDto> retVal = productSeriesService.fetchProductsByProductSeriesCode(qrCode);
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/get-product-series-by-id")
    public ResponseEntity<List<ProductDto>> getById(@RequestParam Long id) {
        List<ProductDto> retVal = productSeriesService.getProductSeriesById(id);
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @RequestMapping("/delete-product-series")
    public ResponseEntity<Boolean> deleteproductSeries(@RequestBody Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productSeriesService.deleteProductSeries(id));
    }
}
