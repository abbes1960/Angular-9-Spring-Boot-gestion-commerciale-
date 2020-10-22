package com.projet.stock.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Patrimoine;
import com.projet.stock.model.TypePatrimoine;
@Repository
public interface PatrimoineRepository extends JpaRepository<Patrimoine, Long>{

	Optional<TypePatrimoine> findByCode(String code);

}

