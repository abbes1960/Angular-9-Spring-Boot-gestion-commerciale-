package com.projet.stock.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.TypePatrimoine;
@Repository
public interface TypePatrimoineRepository extends JpaRepository<TypePatrimoine, Long>{

	Optional<TypePatrimoine> findByCode(String typep);

}