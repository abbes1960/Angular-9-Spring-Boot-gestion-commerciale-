package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Favoir;
@Repository
public interface FavoirRepository extends JpaRepository<Favoir, Long>{

}
