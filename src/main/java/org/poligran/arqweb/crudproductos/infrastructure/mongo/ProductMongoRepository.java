package org.poligran.arqweb.crudproductos.infrastructure.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductMongoRepository extends ReactiveMongoRepository<ProductDocument, String> {
}
