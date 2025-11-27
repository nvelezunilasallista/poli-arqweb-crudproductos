package org.poligran.arqweb.crudproductos.infrastructure.rest;

import org.poligran.arqweb.crudproductos.domain.Product;

public class ProductRestMapper {

    private ProductRestMapper() {
    }

    public static Product toDomain(ProductRequest request) {
        Product product = new Product();
        product.setNombre(request.getNombre());
        product.setDescripcion(request.getDescripcion());
        product.setPrecio(request.getPrecio());
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getNombre(),
                product.getDescripcion(),
                product.getPrecio()
        );
    }
}
