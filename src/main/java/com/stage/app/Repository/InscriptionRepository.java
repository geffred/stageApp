package com.stage.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stage.app.Entity.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

}
