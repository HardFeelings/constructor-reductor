package ru.vpt.constructorapp.api.commercial.manager.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class ManagerPaginationDto {
    private List<ManagerDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
