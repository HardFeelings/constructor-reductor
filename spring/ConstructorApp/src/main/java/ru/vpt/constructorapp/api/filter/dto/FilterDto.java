package ru.vpt.constructorapp.api.filter.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FilterDto {
    // product param
    private Long productTypeId;
    private List<Long> productOptions;
    private Double rpm;

    // motor param
    private Long motorTypeId;
    private Long motorAdapterTypeId;
    private Double power;
    private Integer polucesNumber;

    // reducer param

    private Long idReducerType;
    private Long idReducerSize;
    private Integer diamOutput;
    private Integer diamOutputAllowance;
    private Long idReducerInputType;
    private Long idReducerOutputShaftType;
    private Double torqueMoment;
    private Double ratio;
    private Long idReducerInstallationType;
    private Long idReducerMounting;
}
