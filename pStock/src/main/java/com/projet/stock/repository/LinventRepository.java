package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Linvent;

@Repository
public interface LinventRepository extends JpaRepository<Linvent, Long>{
	Iterable<Linvent> findAllByNumero(int numero);
}
