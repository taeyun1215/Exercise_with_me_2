package dev.ewm.product.adapter.out.persistence;

import dev.ewm.global.annotation.PersistenceAdapter;
import dev.ewm.product.application.port.out.SaveProductPort;
import dev.ewm.product.domain.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ProductPersistenceAdapter implements SaveProductPort {

    private final ProductJpaRepo productJpaRepo;
    private final ProductPersistenceMapper productPersistenceMapper;

    @Override
    public void saveProduct(Product product) {
        productJpaRepo.save(productPersistenceMapper.mapToJpaEntity(product));
    }

}
