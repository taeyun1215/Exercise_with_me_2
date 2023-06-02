package dev.ewm.product.adapter.out.persistence;

import dev.ewm.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {

    public ProductJpaEntity mapToJpaEntity(Product product) {
        return product.toJpaEntity();
    }
}
