package ru.vpt.constructorapp.api.reducer.installation.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ReducerInstallationPaginationDto {
    private List<ReducerInstallationTypeDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
