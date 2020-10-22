package com.projet.stock.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.projet.stock.model.UserPoste;
@Repository
public interface UserPosteRepository extends JpaRepository<UserPoste, Long>{

	Optional<UserPoste> findByMat(int mat);
	
	
	
}