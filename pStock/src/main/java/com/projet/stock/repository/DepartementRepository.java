package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.projet.stock.model.Departement;
public interface DepartementRepository extends JpaRepository<Departement, Long>{
	@Query("delete from Departement b where b.code=:code")
	void deletedeprat(@Param("code") String deco);
	

}
