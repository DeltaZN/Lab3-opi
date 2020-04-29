package app.Controller;

import app.Entities.Point;
import app.Entities.User;
import app.Model.Graphic;
import app.Util.HibernateSessionFactoryUtil;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    private org.hibernate.Transaction tx;
    public Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    private User user;

    public String init() {
        user = new User(userBean.getName());
        userBean.logIn();
        tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        return "toMain";
    }

    public PointsTableBean() {
        setR(1);
        setX(new BigDecimal(0));
    }

    private void addPoint(Point point) {
        point.setUser(user);
        tx = session.beginTransaction();
        session.save(point);
        tx.commit();
    }

    public void addPointFromForm() {
        Point point = new Point(x, y, r, timezoneOffset);
        point.setCurrentTime(timezoneOffset);
        addPoint(point);
    }

    public void updatePoint(Point point) {
        point.setR(r);
        point.setHit(Graphic.isHit(point, r));
        tx = session.beginTransaction();
        session.merge(point);
        tx.commit();
    }

    public void addPointFromCanvas() {
        addPoint(new Point(XCanvas, YCanvas, r, timezoneOffset));
    }


    public List<Point> getPoints() {

        String hql = "from Point p WHERE p.user = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", user);
        List<Point> points = (List<Point>) query.list();
        points.sort((p1, p2) -> p1.getId() > p2.getId() ? 1 : -1);
        return points;
    }

}


