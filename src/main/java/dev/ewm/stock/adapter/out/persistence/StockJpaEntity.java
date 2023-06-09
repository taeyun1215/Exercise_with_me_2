package dev.ewm.stock.adapter.out.persistence;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockJpaEntity extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @Column(name = "product_id")
    private Long productId;

//    @Version
//    private Long version; // 낙관락을 사용하기 위함

//    public StockJpaEntity(Long id, int quantity, Long productId) {
//        this.id = id;
//        this.productId = productId;
//        this.quantity = quantity;
//    }

}
