package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.ConsSteg;
@Repository
public interface ConsStegRepository extends JpaRepository<ConsSteg, Long>{

}

