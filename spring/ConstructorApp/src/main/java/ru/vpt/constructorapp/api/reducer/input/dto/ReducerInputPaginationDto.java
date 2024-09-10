package ru.vpt.constructorapp.api.reducer.input.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerInputPaginationDto {
    private List<ReducerInputTypeDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
