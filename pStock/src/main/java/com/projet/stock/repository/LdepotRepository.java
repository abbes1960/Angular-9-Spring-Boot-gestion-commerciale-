package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.projet.stock.model.Ldepot;
@Repository
public interface LdepotRepository extends JpaRepository<Ldepot, Long>{

	Iterable<Ldepot> findAllByNumero(int numero);

}
