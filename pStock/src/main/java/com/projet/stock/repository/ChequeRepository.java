package com.projet.stock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.stock.model.Cheque;
@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long>{

}
