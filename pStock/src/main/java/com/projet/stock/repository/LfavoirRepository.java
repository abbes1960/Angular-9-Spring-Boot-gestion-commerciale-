package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Lfavoir;

@Repository
public interface LfavoirRepository extends JpaRepository<Lfavoir, Long>{
	Iterable<Lfavoir> findAllByNumero(int numero);
}
