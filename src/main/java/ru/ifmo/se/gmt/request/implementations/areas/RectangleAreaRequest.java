package ru.ifmo.se.gmt.request.implementations.areas;

import lombok.Data;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для реализации запроса на создание прямоугольной области.
 */
@Data
public class RectangleAreaRequest implements AreaRequest {
    private final BigDecimal x;
    private final BigDecimal y;
    private final String format;
    private final BigDecimal widthK;
    private final BigDecimal heightK;
}
