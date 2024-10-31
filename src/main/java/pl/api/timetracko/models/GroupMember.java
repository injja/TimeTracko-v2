package pl.api.timetracko.models;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class GroupMember extends Base{
    @JoinColumn(name="user_id", nullable = false)
    @ManyToOne
    private User user;

    @JoinColumn(name="group_id", nullable = false)
    @ManyToOne
    private Group group;

    @Column
    private boolean active=true;

}
