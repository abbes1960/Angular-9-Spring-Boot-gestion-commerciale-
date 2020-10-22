package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.stock.model.Llivrs;

@Repository
public interface LlivrsRepository extends JpaRepository<Llivrs, Long>{
	Iterable<Llivrs> findAllByNumero(int numero);
}
