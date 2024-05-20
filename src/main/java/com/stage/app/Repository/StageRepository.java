package com.stage.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.app.Entity.Stage;

public interface StageRepository extends JpaRepository<Stage, Integer> {

}
