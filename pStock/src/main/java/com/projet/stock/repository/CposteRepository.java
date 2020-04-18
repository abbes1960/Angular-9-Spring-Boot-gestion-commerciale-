package com.projet.stock.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Cposte;
@Repository
public interface CposteRepository extends JpaRepository<Cposte, Long>{
	Optional<Cposte> findByAnnee(int annee);

}
