package dev.yassiraitelghari.domain;
import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name ;

    @JoinColumn( name = "task_id" , nullable = false)
    @ManyToOne
    private Task task ;
}
