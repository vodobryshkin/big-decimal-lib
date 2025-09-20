package ru.ifmo.se.gmt.geometry.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Класс-абстракция над точкой на координатной плоскости.
 * Нужен для обработки данных на бэкенде.
 */
@Data
public class Point {
    private final BigDecimal x;
    private final BigDecimal y;
}
