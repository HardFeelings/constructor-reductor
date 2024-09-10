package ru.vpt.constructorapp.api.commercial.prop.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CommercialPropPaginationDto {
    private List<CommercialPropDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
