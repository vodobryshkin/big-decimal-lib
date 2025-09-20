package geometry.model;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Класс-абстракция над точкой на координатной плоскости.
 * Нужен для обработки данных на бэкенде.
 */
@Getter @Data
public class Point {
    private final BigDecimal x;
    private final BigDecimal y;
}
