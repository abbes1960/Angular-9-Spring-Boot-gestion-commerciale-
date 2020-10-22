package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projet.stock.model.Lbs1016;

@Repository
public interface Lbs1016Repository extends JpaRepository<Lbs1016, Long>{
	Iterable<Lbs1016> findAllByNumero(int numero);
}
