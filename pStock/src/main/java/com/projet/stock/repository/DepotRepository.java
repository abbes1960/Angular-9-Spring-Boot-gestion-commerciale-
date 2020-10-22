package com.projet.stock.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Depot;
@Repository
public interface DepotRepository extends JpaRepository<Depot, Long>{

	List<Depot> findByCode(int code);

}
