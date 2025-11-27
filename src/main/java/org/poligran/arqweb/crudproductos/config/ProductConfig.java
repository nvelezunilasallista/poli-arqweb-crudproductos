package org.poligran.arqweb.crudproductos.config;

import org.poligran.arqweb.crudproductos.application.ProductService;
import org.poligran.arqweb.crudproductos.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}
