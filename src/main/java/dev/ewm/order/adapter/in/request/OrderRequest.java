package dev.ewm.order.adapter.in.request;

import dev.ewm.global.common.SelfValidating;
import dev.ewm.order.domain.Order;
import dev.ewm.order.domain.OrderItem;
import dev.ewm.order.domain.constant.OrderStatus;
import dev.ewm.user.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderRequest extends SelfValidating<OrderRequest> {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String receiverName;

    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String receiverPhone;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String receiverAddress;

    public OrderRequest(
            String receiverName,
            String receiverPhone,
            String receiverAddress
    ) {
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.validateSelf();
    }

    public Order toEntity(List<OrderItem> orderItems, User user) {
        return Order.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverAddress(receiverAddress)
                .userId(user.getUserId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .orderItems(orderItems)
                .build();
    }

}
