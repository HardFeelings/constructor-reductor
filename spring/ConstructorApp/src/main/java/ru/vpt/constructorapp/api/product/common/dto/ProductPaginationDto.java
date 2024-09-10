package ru.vpt.constructorapp.api.product.common.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductPaginationDto {
    List<ProductDto> productDtoList;
    long totalCount;
    int currentPage;
    int totalPages;
}
