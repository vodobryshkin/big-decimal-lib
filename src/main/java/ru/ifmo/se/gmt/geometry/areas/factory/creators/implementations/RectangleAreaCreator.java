package ru.ifmo.se.gmt.geometry.areas.factory.creators.implementations;

import ru.ifmo.se.gmt.geometry.areas.factory.creators.interfaces.AreaCreator;
import ru.ifmo.se.gmt.geometry.areas.implementations.RectangleArea;
import ru.ifmo.se.gmt.geometry.areas.interfaces.Area;
import ru.ifmo.se.gmt.geometry.model.Point;
import ru.ifmo.se.gmt.request.implementations.areas.RectangleAreaRequest;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для автоматического создания прямоугольных областей.
 */
public class RectangleAreaCreator implements AreaCreator {
    /**
     * Метод для создания области по переданному запросу.
     * Параметры x, y идут в точку.
     *
     * @param areaRequest переданный запрос на создание прямоугольной области.
     * @param radius радиус из условия лабораторной.
     * @return созданную область.
     */
    @Override
    public Area createArea(AreaRequest areaRequest, BigDecimal radius) {
       if (areaRequest instanceof RectangleAreaRequest rectangleAreaRequest) {
           BigDecimal x = rectangleAreaRequest.x();
           BigDecimal y = rectangleAreaRequest.y();
           String format = rectangleAreaRequest.format();
           BigDecimal width = rectangleAreaRequest.widthK().multiply(radius);
           BigDecimal height = rectangleAreaRequest.heightK().multiply(radius);

           return new RectangleArea(new Point(x, y), format, width, height);
       }
       return null;
    }
}
