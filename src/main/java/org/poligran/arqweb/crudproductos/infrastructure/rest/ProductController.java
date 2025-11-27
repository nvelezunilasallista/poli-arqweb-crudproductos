package org.poligran.arqweb.crudproductos.infrastructure.rest;

import org.poligran.arqweb.crudproductos.application.ProductService;
import org.poligran.arqweb.crudproductos.domain.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductResponse> getAll() {
        return productService.getAll()
                .map(ProductRestMapper::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<ProductResponse> getById(@PathVariable String id) {
        return productService.getById(id)
                .map(ProductRestMapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product product = ProductRestMapper.toDomain(request);
        return productService.create(product)
                .map(ProductRestMapper::toResponse);
    }

    @PutMapping("/{id}")
    public Mono<ProductResponse> update(@PathVariable String id,
                                        @Valid @RequestBody ProductRequest request) {
        Product product = ProductRestMapper.toDomain(request);
        return productService.update(id, product)
                .map(ProductRestMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return productService.delete(id);
    }
}
