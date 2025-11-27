package org.poligran.arqweb.crudproductos.application;

import org.poligran.arqweb.crudproductos.domain.Product;
import org.poligran.arqweb.crudproductos.domain.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Product> getById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)));
    }

    public Mono<Product> create(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    public Mono<Product> update(String id, Product product) {
        return productRepository.update(id, product)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)));
    }

    public Mono<Void> delete(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException(id)))
                .then(productRepository.deleteById(id));
    }
}
