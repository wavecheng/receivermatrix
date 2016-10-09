package com.citrix.matrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citrix.matrix.model.Cell;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {

	Cell findByRowAndColumn(int row, int column);
	
}
