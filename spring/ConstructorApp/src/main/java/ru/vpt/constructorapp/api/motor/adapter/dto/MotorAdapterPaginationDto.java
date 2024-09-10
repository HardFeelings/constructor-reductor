package ru.vpt.constructorapp.api.motor.adapter.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MotorAdapterPaginationDto {
    private List<MotorAdapterTypeDto> content;
    long totalCount;
    int currentPage;
    int totalPages;
}
