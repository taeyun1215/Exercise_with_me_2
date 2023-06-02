package dev.ewm.product.adapter.in.request;

import com.sun.istack.NotNull;
import dev.ewm.global.common.SelfValidating;
import dev.ewm.product.domain.Product;
import dev.ewm.product.domain.constant.Status;
import dev.ewm.user.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterProductRequest extends SelfValidating<RegisterProductRequest> {

    @NotBlank(message = "제품 이름을 입력해주세요")
    private String name;

    @Range(min = 10, message = "제품가격은 최소 10원 이상이어야 합니다")
    private int price;

    @NotNull
    private Status status;

    @NotBlank(message = "상품 설명을 입력해주세요")
    private String description;

    @NotBlank(message = "카테고리를 선택해주세요.")
    private Long categoryId;

    public RegisterProductRequest(
            String name,
            int price,
            Status status,
            String description,
            Long categoryId
    ) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.description = description;
        this.categoryId = categoryId;
        this.validateSelf();
    }

    public Product toEntity(User user) {
        return Product.builder()
                .name(name)
                .price(price)
                .status(status)
                .description(description)
                .categoryId(categoryId)
                .userId(user.getUserId())
                .build();
    }

}
