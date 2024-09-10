package ru.vpt.constructorapp.api.reducer.common.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerPaginationDto {
    private List<ReducerDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
