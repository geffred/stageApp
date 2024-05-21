package com.stage.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stage.app.Entity.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    @Query("SELECT i FROM Inscription i WHERE  (i.stage.denom LIKE CONCAT('%', :denom, '%') AND i.paye = :paye )  ")
    List<Inscription> findByInscription(@Param("denom") String denom, @Param("paye") Boolean paye);

    @Query("SELECT i FROM Inscription i WHERE  i.stage.denom LIKE CONCAT('%', :denom, '%')")
    List<Inscription> findByStageDenom(@Param("denom") String denom);

    public List<Inscription> findByEnfantId(Integer id);

    public List<Inscription> findByStageId(Integer id);

}
