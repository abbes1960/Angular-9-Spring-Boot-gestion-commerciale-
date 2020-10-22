package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Production;
@Repository
public interface ProductionRepository extends JpaRepository<Production, Long>{

}

