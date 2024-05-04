package com.stage.app.Entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String denom;

    @Column
    @Pattern(regexp = "[0-9]+")
    @Min(value = 3, message = "l'age minimal est de 3 an")
    private Integer ageMin;

    @Column
    @Min(value = 18, message = "l'age maximal est de 18 ans")
    @Pattern(regexp = "[0-9]+")
    private Integer ageMax;

    @DateTimeFormat(pattern = "dd-MM-yy")
    @NotEmpty(message = "La date de debut ne doit pas être vide")
    @NotEmpty(message = "La date de debut ne doit pas être null")
    LocalDate dateDeb;

    @DateTimeFormat(pattern = "dd-MM-yy")
    @PastOrPresent(message = "la date de naissaince doit être antérieur")
    @NotEmpty(message = "La date de fin ne doit pas être vide")
    @NotEmpty(message = "La date de fin ne doit pas être null")
    LocalDate dateFin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id", unique = true, updatable = false)
    @Autowired
    List<Inscription> listInscription;

    public Stage(String denom, Integer ageMin, Integer ageMax, LocalDate dateDeb, LocalDate dateFin) {

        this.denom = denom;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public Stage() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public LocalDate getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(LocalDate dateDeb) {
        this.dateDeb = dateDeb;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

}
