package ru.vpt.constructorapp.api.motor.common.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class MotorPaginationDto {
    private List<MotorDto> motorDtos;
    long totalCount;
    int currentPage;
    int totalPages;
}
