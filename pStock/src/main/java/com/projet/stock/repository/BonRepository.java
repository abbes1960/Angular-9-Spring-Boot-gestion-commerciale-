package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Bon;
@Repository
public interface BonRepository extends JpaRepository<Bon, Long>{

}
