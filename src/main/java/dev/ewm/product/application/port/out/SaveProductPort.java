package dev.ewm.product.application.port.out;

import dev.ewm.product.domain.Product;

public interface SaveProductPort {

    void saveProduct(Product product);

}
