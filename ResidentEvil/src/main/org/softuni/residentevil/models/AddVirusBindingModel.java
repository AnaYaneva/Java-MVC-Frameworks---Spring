package org.softuni.residentevil.models;

import org.softuni.residentevil.customValidations.BeforeToday;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class AddVirusBindingModel {
    @NotNull
    @Size(min = 3, max = 10)
    private String name;

    @NotNull
    @Size(min = 5, max = 100)
    private String description;

    @NotNull
    @Size(max = 50)
    private String sideEffect;

    @NotNull
    @Pattern(regexp = "^Corp|corp\\.$")
    private String creator;

    private String isDeadly;

    private String isCurable;

    @NotNull
    @Pattern(regexp = "^ZOMBIE|T_078_TYRANT|GIANT_SPIDER$")
    private String mutation;

    @NotNull
    @Min(0)
    @Max(100)
    private int turnoverRate;

    @NotNull
    @Min(1)
    @Max(12)
    private int hoursUntilTurn;

    @NotNull
    @Pattern(regexp = "^Low|Medium|High$")
    private String magnitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @BeforeToday
    private LocalDate releaseDate;

    @NotNull
    @Size(min = 1)
    private List<String> affectedCapitals;

    public AddVirusBindingModel() {

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

    public String getSideEffect() {
        return this.sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getIsDeadly() {
        return this.isDeadly;
    }

    public void setIsDeadly(String isDeadly) {
        this.isDeadly = isDeadly;
    }

    public String getIsCurable() {
        return this.isCurable;
    }

    public void setIsCurable(String isCurable) {
        this.isCurable = isCurable;
    }

    public String getMutation() {
        return this.mutation;
    }

    public void setMutation(String mutation) {
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

    public String getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getAffectedCapitals() {
        return this.affectedCapitals;
    }

    public void setAffectedCapitals(List<String> affectedCapitals) {
        this.affectedCapitals = affectedCapitals;
    }
}