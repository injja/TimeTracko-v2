package pl.api.timetracko.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

public class Group extends Base{
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "created-by")
    private User createdBy;




}
