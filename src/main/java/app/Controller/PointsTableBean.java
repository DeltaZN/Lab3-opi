package app.Controller;

import app.Entities.Point;
import app.Entities.User;
import app.Model.Graphic;
import lombok.Data;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data

public class PointsTableBean implements Serializable {

    private BigDecimal XCanvas;
    private BigDecimal YCanvas;
    private BigDecimal x;
    private BigDecimal y;
    private double r = 1;
    private int timezoneOffset;

    @PersistenceContext(unitName = "hibernate")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    private User user;

    public String init() throws Exception {
        user = new User(userBean.getName());
        userBean.logIn();
        userTransaction.begin();
        em.merge(user);
        userTransaction.commit();
        return "toMain";
    }

    public PointsTableBean() {
        setR(1);
        setX(new BigDecimal(0));
    }

    private void addPoint(Point point) {
        point.setUser(user);
        try {
            userTransaction.begin();
            em.persist(point);
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPointFromForm()  {
        Point point = new Point(x, y, r, timezoneOffset);
        point.setCurrentTime(timezoneOffset);
        addPoint(point);
    }

    public void updatePoint(Point point) throws Exception {
        point.setR(r);
        point.setHit(Graphic.isHit(point, r));
        userTransaction.begin();
        em.merge(point);
        userTransaction.commit();
    }

    public void addPointFromCanvas() {
        addPoint(new Point(XCanvas, YCanvas, r, timezoneOffset));
    }


    public List<Point> getPoints() {
        Query query = em.createQuery("select p from Point p WHERE p.user = :id");
        query.setParameter("id", user);
        List<Point> points = (List<Point>) query.getResultList();
        points.sort((p1, p2) -> p1.getId() > p2.getId() ? 1 : -1);
        return points;
    }

}


