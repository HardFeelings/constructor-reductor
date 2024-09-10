package ru.vpt.constructorapp.api.reducer.size.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerSizePaginationDto {
    private List<ReducerSizeDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
