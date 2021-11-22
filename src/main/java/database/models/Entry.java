package database.models;

import javax.persistence.*;

@Entity
@Table (name = "entry")
public class Entry {
    public Entry() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "x", nullable = false)
    private Double X;
    @Column(name = "y", nullable = false)
    private Double Y;
    @Column(name = "r", nullable = false)
    private Double R;
    @Column(name = "hit", nullable = false)
    private boolean hit;
    @Column(name = "session_id", nullable = false)
    private String session_id;

    public Integer getID() {
        return ID;
    }

    public Double getX() {
        return X;
    }

    public Double getY() {
        return Y;
    }

    public Double getR() {
        return R;
    }

    public boolean isHit() {
        return hit;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setX(Double x) {
        X = x;
    }

    public void setY(Double y) {
        Y = y;
    }

    public void setR(Double r) {
        R = r;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void checkHit() {
        this.hit = ((X >= -R && X <= 0 && Y >= -R/2 && Y <= 0) ||
                (-2*Y <= (-X + R) && Y <= 0 && X >= 0) ||
                ((X*X + Y*Y) <= R*R && X <= 0 && Y >= 0));
    }
}
