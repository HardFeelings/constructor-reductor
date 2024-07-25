package ru.vpt.constructorapp.api.commercial.prop.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.vpt.constructorapp.api.commercial.item.dto.CommercialPropItemDto;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CommercialPropDto {
    private Long idCommercialProp;
    private String number;
    private String partner;
    private BigDecimal cost;
    private String timestamp;
    private Double marginRatio;
    private ManagerEntity manager;
    private List<CommercialPropItemDto> commercialPropItems;
}
