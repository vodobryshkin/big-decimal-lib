package ru.ifmo.se.gmt.request.implementations.messages;

import ru.ifmo.se.gmt.request.interfaces.AreaRequest;
import ru.ifmo.se.gmt.request.interfaces.Request;

import java.util.List;

/**
 * Класс для реализации запроса с распарсенными запросами.
 *
 * @param areaRequests список с распарсенными запросами.
 */
public record AreasRequest(List<AreaRequest> areaRequests) implements Request {}
