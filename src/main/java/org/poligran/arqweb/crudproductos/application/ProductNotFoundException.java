package org.poligran.arqweb.crudproductos.application;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
        super("Producto con id '" + id + "' no existe");
    }
}
