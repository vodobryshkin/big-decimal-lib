package ru.ifmo.se.gmt.geometry.areas.factory;

import ru.ifmo.se.gmt.geometry.areas.factory.creators.implementations.RectangleAreaCreator;
import ru.ifmo.se.gmt.geometry.areas.factory.creators.implementations.SectorAreaCreator;
import ru.ifmo.se.gmt.geometry.areas.factory.creators.implementations.TriangleAreaCreator;
import ru.ifmo.se.gmt.geometry.areas.factory.creators.interfaces.AreaCreator;
import ru.ifmo.se.gmt.geometry.areas.interfaces.Area;
import ru.ifmo.se.gmt.request.implementations.areas.RectangleAreaRequest;
import ru.ifmo.se.gmt.request.implementations.areas.SectorAreaRequest;
import ru.ifmo.se.gmt.request.implementations.areas.TriangleAreaRequest;
import ru.ifmo.se.gmt.request.implementations.messages.AreasRequest;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для автоматического создания и управления областями.
 */
public class AreaFactory {
    @Setter
    private AreaCreator areaCreator;

    /**
     * Метод для создания списка областей из запроса на создание областей.
     *
     * @param areasRequest запрос на создание областей.
     * @param radius радиус, по которому задаются области.
     * @return список созданных областей.
     */
    public List<Area> createAreas(AreasRequest areasRequest, BigDecimal radius) {
        List<Area> areaList = new ArrayList<>();

        for (AreaRequest areaRequest: areasRequest.getAreaRequests()) {
            if (areaRequest instanceof RectangleAreaRequest) {
                setAreaCreator(new RectangleAreaCreator());
            } else if (areaRequest instanceof TriangleAreaRequest) {
                setAreaCreator(new TriangleAreaCreator());
            } else if (areaRequest instanceof SectorAreaRequest) {
                setAreaCreator(new SectorAreaCreator());
            }

            areaList.add(areaCreator.createArea(areaRequest, radius));
        }

        return areaList;
    }
}
