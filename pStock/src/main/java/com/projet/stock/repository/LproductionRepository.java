package com.projet.stock.repository;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.projet.stock.model.Lproduction;
@Repository
public interface LproductionRepository extends JpaRepository<Lproduction, Long>{


	@Modifying
    @Transactional
    @Query("delete from Lproduction e where numero = ?1")
    void deleteByNumero(int numero);
	Iterable<Lproduction> findAllByNumero(int numero);
}
