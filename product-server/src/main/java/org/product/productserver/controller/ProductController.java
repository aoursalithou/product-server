package org.product.productserver.controller;

import org.product.productserver.dto.ProductDto;
import org.product.productserver.request.ProductRequest;
import org.product.productserver.response.ProductResponse;
import org.product.productserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/get-products-list")
    public ResponseEntity<Page<ProductDto>> getProductList(
            Pageable pageable,
            @RequestParam Optional<String> name,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> purchaseDateFrom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> purchaseDateTo,
            @RequestParam Optional<Collection<String>> productSeriesCodesList

    ) {
        Page<ProductDto> retVal = productService.getProductsList(pageable, name.orElse(null), purchaseDateFrom.orElse(null), purchaseDateTo.orElse(null), productSeriesCodesList.orElse(null));
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/fetch-products")
    public ResponseEntity<List<ProductDto>> fetchproducts() {
        List<ProductDto> retVal = productService.fetchProducts();
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/fetch-products-series-codes")
    public ResponseEntity<List<ProductDto>> fetchProductsSeriesCodes() {
        List<ProductDto> retVal = productService.fetchProductsSeriesCodes();
        return ResponseEntity.status(HttpStatus.OK).body(retVal);
    }

    @GetMapping("/get-product-by-id")
    public ResponseEntity<ProductResponse> getById(@RequestParam Long id) {
        ProductDto productDto = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse(productDto));
    }

    @RequestMapping(value = "/create-product", method = RequestMethod.POST)
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequest request) {
        ProductDto productDto = new ProductDto(request, null, false);
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(productDto));
    }

    @PutMapping("/update-product")
    public ResponseEntity<Boolean> update(@RequestParam long id, @RequestBody ProductRequest request) {
        ProductDto productDto = new ProductDto(request, id, true);
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productDto));
    }

    @RequestMapping("/delete-product")
    public ResponseEntity<Boolean> deleteProduct(@RequestBody Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }

}
