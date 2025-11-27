package org.poligran.arqweb.crudproductos.infrastructure.mongo;

import org.poligran.arqweb.crudproductos.domain.Product;
import org.poligran.arqweb.crudproductos.domain.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductMongoAdapter implements ProductRepository {

    private final ProductMongoRepository mongoRepository;

    public ProductMongoAdapter(ProductMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    private ProductDocument toDocument(Product product) {
        return new ProductDocument(
                product.getId(),
                product.getNombre(),
                product.getDescripcion(),
                product.getPrecio()
        );
    }

    private Product toDomain(ProductDocument doc) {
        return new Product(
                doc.getId(),
                doc.getNombre(),
                doc.getDescripcion(),
                doc.getPrecio()
        );
    }

    @Override
    public Flux<Product> findAll() {
        return mongoRepository.findAll()
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> findById(String id) {
        return mongoRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> save(Product product) {
        return mongoRepository.save(toDocument(product))
                .map(this::toDomain);
    }

    @Override
    public Mono<Product> update(String id, Product product) {
        return mongoRepository.findById(id)
                .flatMap(existing -> {
                    existing.setNombre(product.getNombre());
                    existing.setDescripcion(product.getDescripcion());
                    existing.setPrecio(product.getPrecio());
                    return mongoRepository.save(existing);
                })
                .map(this::toDomain);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoRepository.deleteById(id);
    }
}
