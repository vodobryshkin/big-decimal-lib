package geometry.areas.factory.creators.implementations;

import geometry.areas.factory.creators.interfaces.AreaCreator;
import geometry.areas.implementations.RectangleArea;
import geometry.areas.interfaces.Area;
import geometry.model.Point;
import request.implementations.areas.RectangleAreaRequest;
import request.interfaces.AreaRequest;

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
           BigDecimal width = rectangleAreaRequest.widthK().multiply(radius);
           BigDecimal height = rectangleAreaRequest.heightK().multiply(radius);

           return new RectangleArea(new Point(x, y), width, height);
       }
       return null;
    }
}
