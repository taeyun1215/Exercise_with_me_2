package dev.ewm.order.adapter.out.persistence;

import dev.ewm.global.baseEntity.BaseTimeEntity;
import dev.ewm.order.domain.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderJpaEntity extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private Long userId;

    @Enumerated
    private OrderStatus orderStatus;

    @OneToMany
    private List<OrderItemJpaEntity> orderItemJpaEntities = new ArrayList<>();

}
