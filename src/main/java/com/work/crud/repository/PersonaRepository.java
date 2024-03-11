
package com.work.crud.repository;

import com.work.crud.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author linux
 */
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    
}
