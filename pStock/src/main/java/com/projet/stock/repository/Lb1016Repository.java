package com.projet.stock.repository;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.projet.stock.model.Lb1016;
@Repository
public interface Lb1016Repository extends JpaRepository<Lb1016, Long>{


	@Modifying
    @Transactional
    @Query("delete from Lb1016 e where numero = ?1")
    void deleteByNumero(int numero);
	Iterable<Lb1016> findAllByNumero(int numero);
}
