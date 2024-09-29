package ru.vpt.constructorapp.api.commercial.employee.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeePaginationDto {
    private List<EmployeeDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
