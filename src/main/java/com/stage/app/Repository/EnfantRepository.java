
package com.stage.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.app.Entity.Enfant;

public interface EnfantRepository extends JpaRepository<Enfant, Integer> {

}
