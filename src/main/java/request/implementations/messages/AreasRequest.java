package request.implementations.messages;

import request.interfaces.AreaRequest;
import request.interfaces.Request;

import java.util.List;

/**
 * Класс для реализации запроса с распарсенными запросами.
 *
 * @param areaRequests список с распарсенными запросами.
 */
public record AreasRequest(List<AreaRequest> areaRequests) implements Request {}
