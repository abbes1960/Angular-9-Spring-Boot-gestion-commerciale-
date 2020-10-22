package com.projet.stock.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.projet.stock.model.Residence;
import com.projet.stock.model.Scategorie;
@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long>{
 @Query("SELECT t FROM Residence t where t.coddir = :code")
	    public List<Residence> findByCoddir(@Param("code") int code);
 @Query("SELECT r.code,r.libelle as libr,d.libelle as libd  FROM Residence r LEFT JOIN Direction d WHERE r.coddir = d.code")
 public  List<Residence> getAll();

}