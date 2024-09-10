package ru.vpt.constructorapp.api.product.option.dto;

import lombok.*;

import java.util.List;
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ProductOptionPaginationDto {
    List<ProductOptionDto> productOptionDtos;
    long totalCount;
    int currentPage;
    int totalPages;
}
