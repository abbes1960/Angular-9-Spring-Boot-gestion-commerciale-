package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Flivr;
@Repository
public interface FlivrRepository extends JpaRepository<Flivr, Long>{

}