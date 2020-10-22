package com.projet.stock.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Grade;
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>{

	Optional<Grade> findByCode(String code);

}
