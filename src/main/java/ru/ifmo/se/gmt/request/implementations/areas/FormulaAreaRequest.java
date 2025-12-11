package ru.ifmo.se.gmt.request.implementations.areas;

import lombok.Value;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;

/**
 * Класс для реализации запроса на создание области по формуле.
 */
@Value
public class FormulaAreaRequest implements AreaRequest {
    String formula;
}
