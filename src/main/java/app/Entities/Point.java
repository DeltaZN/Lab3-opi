package app.Entities;

import app.Model.Graphic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@Data
@Table(name = "points")
public class Point implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Transient
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Column(precision = 34, scale = 32)
    private BigDecimal x;
    @Column(precision = 34, scale = 32)
    private BigDecimal y;
    private double r;
    private boolean hit;
    private String currentTime;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="username")
    private User user;

    public Point(BigDecimal x, BigDecimal y, double r, int timezoneOffset) {
        this.x = x;
        this.y = y;
        this.r = r;
        hit = Graphic.isHit(this,r);
        currentTime = LocalDateTime.now(ZoneOffset.UTC).minusMinutes(timezoneOffset).format(formatter);
    }

    public void setCurrentTime(int timezoneOffset) {
        this.currentTime = LocalDateTime.now(ZoneOffset.UTC).minusMinutes(timezoneOffset).format(formatter);
    }

    public BigDecimal getX() {
        if (x.toString().equals("0E-32"))
            return new BigDecimal(0);
        return x;
    }


    public String getDrawPoint(int r) {
        return String.format("drawPoint(%s, %s, %s)", x, y, Graphic.isHit(this,r));
    }


}
