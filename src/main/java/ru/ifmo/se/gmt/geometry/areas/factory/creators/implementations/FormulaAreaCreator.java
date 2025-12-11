package ru.ifmo.se.gmt.geometry.areas.factory.creators.implementations;

import ru.ifmo.se.gmt.geometry.areas.factory.creators.interfaces.AreaCreator;
import ru.ifmo.se.gmt.geometry.areas.implementations.FormulaArea;
import ru.ifmo.se.gmt.geometry.areas.interfaces.Area;
import ru.ifmo.se.gmt.request.implementations.areas.FormulaAreaRequest;
import ru.ifmo.se.gmt.request.interfaces.AreaRequest;

import java.math.BigDecimal;

/**
 * Класс для автоматического создания областей по формулам.
 */
public class FormulaAreaCreator implements AreaCreator {
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
        if (areaRequest instanceof FormulaAreaRequest) {
            FormulaAreaRequest formulaAreaRequest = (FormulaAreaRequest) areaRequest;
            String formula = formulaAreaRequest.getFormula();

            return new FormulaArea(formula, radius);
        }
        return null;
    }
}
