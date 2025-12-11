package ru.ifmo.se.gmt.request.implementations.messages;

import lombok.Value;
import ru.ifmo.se.gmt.geometry.model.Point;

import java.math.BigDecimal;

/**
 * Класс с данными для отправки на проверки на бизнес-логику.
 * Получается в результате обработки поступивших данных валидатором.
 */
@Value
public class CheckoutRequest {
    Point point;
    BigDecimal r;
}
