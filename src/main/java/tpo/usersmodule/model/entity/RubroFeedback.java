package tpo.usersmodule.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rubros")
public class RubroFeedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idRubro", nullable = false)
    private int idRubro;
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;
    @OneToMany(mappedBy = "rubro", cascade = CascadeType.ALL)
    private List<Feedback> feedbackList;

    public RubroFeedback(int idRubro, String descripcion) {
        this.idRubro = idRubro;
        this.descripcion = descripcion;
        this.feedbackList=new ArrayList<>() ;
    }
    public RubroFeedback() {
       this.feedbackList=new ArrayList<>() ;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }
}
