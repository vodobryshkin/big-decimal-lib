package ru.ifmo.se.gmt.request.implementations.messages;

import lombok.Data;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;
import ru.ifmo.se.gmt.request.interfaces.Request;

import java.util.List;

/**
 * Класс для реализации запроса с распарсенными запросами.
 */
@Data
public class AreasRequest implements Request {
    private final List<AreaRequest> areaRequests;
}