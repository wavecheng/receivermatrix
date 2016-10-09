package com.citrix.matrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citrix.matrix.model.Data;

public interface DataRepository extends JpaRepository<Data, Long>{
	
}
