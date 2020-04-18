package com.projet.stock.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.projet.stock.model.Residence;
@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long>{

	 @Query("SELECT t FROM Residence t where t.code_dir = :code_dir")
	    public List<Residence> findByResidence(@Param("code_dir") String code_dir);

}