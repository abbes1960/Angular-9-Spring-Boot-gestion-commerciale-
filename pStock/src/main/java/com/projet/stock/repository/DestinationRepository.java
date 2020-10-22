package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Destination;
import com.projet.stock.model.Tarif;
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>{

	void save(Tarif tarif);

}