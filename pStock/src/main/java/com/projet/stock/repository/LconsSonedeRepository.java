package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.LconsSonede;

@Repository
public interface LconsSonedeRepository extends JpaRepository<LconsSonede, Long>{
	Iterable<LconsSonede> findAllByNumero(int numero);
}