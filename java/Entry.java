import javax.persistence.*;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(nullable = false)
    private Double X;
    @Column(nullable = false)
    private Double Y;
    @Column(nullable = false)
    private Double R;
    @Column(nullable = false)
    private boolean hit;
    @Column(nullable = false)
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
        // TODO
    }
}
