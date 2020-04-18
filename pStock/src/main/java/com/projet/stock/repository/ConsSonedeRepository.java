package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.ConsSonede;
@Repository
public interface ConsSonedeRepository extends JpaRepository<ConsSonede, Long>{

}