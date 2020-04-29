package app.Model;

import app.Entities.Point;

import java.math.BigDecimal;

public class Graphic {
    public static boolean isHit(Point point, double r) {

        BigDecimal x = point.getX();
        BigDecimal y = point.getY();

        boolean circle = (x.multiply(x).add(y.multiply(y)).compareTo(new BigDecimal(Math.pow(r / 2, 2))) <= 0
                && y.compareTo(new BigDecimal("0")) <= 0 && x.compareTo(new BigDecimal("0")) >= 0);
        boolean square = (x.compareTo(new BigDecimal("0")) <= 0 && y.compareTo(new BigDecimal(r)) <= 0
                && y.compareTo(new BigDecimal("0")) >= 0 && x.compareTo(new BigDecimal(-r / 2)) >= 0);
        BigDecimal sum = new BigDecimal(r).add(x.multiply(new BigDecimal(-1)));
        boolean triangle = ((y.compareTo(sum) <= 0 && y.compareTo(new BigDecimal("0")) >= 0
                && x.compareTo(new BigDecimal("0")) >= 0 && x.compareTo(new BigDecimal(r)) <= 0));

        return square || triangle || circle;
    }
}
