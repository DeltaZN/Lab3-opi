package app.Model;

import app.Entities.Point;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GraphicTest extends TestCase {

    ArrayList<Point> points = new ArrayList<>();

    @Before
    public void setUpPoints(){
        points.add(new Point(new BigDecimal(1), new BigDecimal(1),2,180));
        points.add(new Point(new BigDecimal(2), new BigDecimal(2),2,180));

        points.add(new Point(new BigDecimal(-1), new BigDecimal(1),3,180));
        points.add(new Point(new BigDecimal(-2), new BigDecimal(2),3,180));

        points.add(new Point(new BigDecimal(-1), new BigDecimal(-1),4,180));
        points.add(new Point(new BigDecimal(-2), new BigDecimal(-2),4,180));

        points.add(new Point(new BigDecimal(1), new BigDecimal(-1),5,180));
        points.add(new Point(new BigDecimal(2), new BigDecimal(-2),5,180));
    }

    @Test
    public void testPointsIn1stQuarter(){
        assertFalse(Graphic.isHit(points.get(1),2));
        assertTrue(Graphic.isHit(points.get(0),2));
    }

    @Test
    public void testPointsIn2ndQuarter(){
        assertFalse(Graphic.isHit(points.get(3),3));
        assertTrue(Graphic.isHit(points.get(2),3));
    }

    @Test
    public void testPointsIn3rdQuarter(){
        assertFalse(Graphic.isHit(points.get(4),2));
        assertFalse(Graphic.isHit(points.get(5),2));
    }

    @Test
    public void testPointsIn4thQuarter(){
        assertFalse(Graphic.isHit(points.get(7),5));
        assertTrue(Graphic.isHit(points.get(6),5));
    }
}
