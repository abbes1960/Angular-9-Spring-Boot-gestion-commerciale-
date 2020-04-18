package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Invent;
@Repository
public interface InventRepository extends JpaRepository<Invent, Long>{

}
