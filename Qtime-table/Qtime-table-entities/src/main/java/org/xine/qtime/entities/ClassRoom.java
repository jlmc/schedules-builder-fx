package org.xine.qtime.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "ClassRoom")
public class ClassRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String code;
    private Integer capacity;
    private Boolean computed;
    private ClassRoomCategory category;

    public ClassRoom() {
        super();
    }

    public ClassRoom(final Long id, final String code, final Integer capacity,
            final Boolean computed, final ClassRoomCategory category) {
        super();
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.computed = computed;
        this.category = category;
    }

    public ClassRoom(final String code, final Integer capacity, final Boolean computed,
            final ClassRoomCategory category) {
        super();
        this.code = code;
        this.capacity = capacity;
        this.computed = computed;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "Code", nullable = false, unique = true, length = 10)
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    @Column(name = "capacity", nullable = false)
    public Integer getCapacity() {
        return this.capacity;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    @Column(name = "computed")
    public Boolean getComputed() {
        return this.computed;
    }

    public void setComputed(final Boolean computed) {
        this.computed = computed;
    }

    @Enumerated(EnumType.STRING)
    public ClassRoomCategory getCategory() {
        return this.category;
    }

    public void setCategory(final ClassRoomCategory category) {
        this.category = category;
    }

}
