package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Livrs;
@Repository
public interface LivrsRepository extends JpaRepository<Livrs, Long>{
	
}