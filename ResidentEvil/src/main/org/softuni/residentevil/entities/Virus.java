package org.softuni.residentevil.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.residentevil.entities.enums.VirusMagnitude;
import org.softuni.residentevil.entities.enums.VirusMutation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "viruses")
public class Virus {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String sideEffects;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private boolean isDeadly;

    @Column(nullable = false)
    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VirusMutation mutation;

    @Column(nullable = false)
    private int turnoverRate;

    @Column(nullable = false)
    private int hoursUntilTurn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VirusMagnitude magnitude;

    @Column(nullable = false)
    private LocalDate releasedOn;

    @ManyToMany
    private List<Capital> capitals;

    public Virus() {

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return this.isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public VirusMutation getMutation() {
        return this.mutation;
    }

    public void setMutation(VirusMutation mutation) {
        this.mutation = mutation;
    }

    public int getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(int turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public int getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(int hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public VirusMagnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(VirusMagnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(LocalDate releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}