package dev.ewm.stock.adapter.out.persistence;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
