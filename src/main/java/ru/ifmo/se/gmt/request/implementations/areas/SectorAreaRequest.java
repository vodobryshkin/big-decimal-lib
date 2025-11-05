package ru.ifmo.se.gmt.request.implementations.areas;

import lombok.Data;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для реализации запроса на создание области в виде сектора.
 */
@Data
public class SectorAreaRequest implements AreaRequest {
    private final BigDecimal xC;
    private final BigDecimal yC;
    private final BigDecimal radiusK;
    private final BigDecimal startAngleK;
    private final BigDecimal endAngleK;
}
