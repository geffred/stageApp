package com.stage.app.Entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "stage")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @Pattern(regexp = "^([a-zA-Z][ ]*)+$", message = "Seules les lettres sont acceptées")
    private String denom;

    @Column
    @Min(value = 3, message = "l'age minimal est de 3 ans")
    @Max(value = 18, message = "l'age maximal est de 18 ans")
    @NotNull(message = "Ce champs ne doit pas être null")
    private Integer ageMin;

    @Column
    @NotNull(message = "Ce champs ne doit pas être null")
    @Min(value = 3, message = "l'age minimal est de 3 ans")
    @Max(value = 18, message = "l'age maximal est de 18 ans")

    private Integer ageMax;

    @DateTimeFormat(pattern = "dd-MM-yy")
    @NotNull(message = "La date de debut ne doit pas être null")
    LocalDate dateDeb;

    @DateTimeFormat(pattern = "dd-MM-yy")
    @Future(message = "la date de fin doit être dans le future")
    @NotNull(message = "La date de fin ne doit pas être null")
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

    public List<Inscription> getListInscription() {
        return listInscription;
    }

    public void setListInscription(List<Inscription> listInscription) {
        this.listInscription = listInscription;
    }

}
