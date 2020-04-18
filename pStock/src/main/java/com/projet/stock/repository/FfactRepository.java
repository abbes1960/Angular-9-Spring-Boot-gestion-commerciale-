package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Ffact;
@Repository
public interface FfactRepository extends JpaRepository<Ffact, Long>{

}
