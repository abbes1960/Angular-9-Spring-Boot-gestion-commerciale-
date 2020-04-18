package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Fournisseur;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>{

}
