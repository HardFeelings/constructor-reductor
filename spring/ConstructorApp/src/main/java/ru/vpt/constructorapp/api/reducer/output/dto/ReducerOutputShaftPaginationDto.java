package ru.vpt.constructorapp.api.reducer.output.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerOutputShaftPaginationDto {
    private List<ReducerOutputShaftTypeDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
