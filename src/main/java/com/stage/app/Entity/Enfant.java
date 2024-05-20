package com.stage.app.Entity;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Enfant")
public class Enfant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Pattern(regexp = "^([a-zA-Z][ ]*)+$", message = "Seules les lettres sont acceptées")
    @NotEmpty(message = "Ce champ ne doit pas être vide")
    private String nom;

    @Column
    @NotEmpty(message = "Ce champ ne doit pas être vide")
    @Pattern(regexp = "^([a-zA-Z][ ]*)+$", message = "Seules les lettres sont acceptées")
    private String prenom;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Ce champs ne doit pas être null")
    private Gender sexe;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")

    @NotNull(message = "ce champs ne doit pas être null")
    @Past(message = "vous devez entrer une date dans le passé")
    private LocalDate dateNaiss;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enfant_id", unique = true, updatable = false)
    @Autowired
    List<Inscription> listInscription;

    public Enfant(String nom, String prenom, Gender sexe, LocalDate dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
    }

    public Enfant() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Gender getSexe() {
        return sexe;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public List<Inscription> getListInscription() {
        return listInscription;
    }

    public void setListInscription(List<Inscription> listInscription) {
        this.listInscription = listInscription;
    }
}
