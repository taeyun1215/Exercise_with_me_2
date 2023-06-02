package dev.ewm.order.adapter.in.request;

import dev.ewm.global.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderItemRequest extends SelfValidating<OrderItemRequest> {
}
