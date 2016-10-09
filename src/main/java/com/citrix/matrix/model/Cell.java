package com.citrix.matrix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="Matrix")
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"row" , "column"})})
public class Cell {
	@Id
	@GeneratedValue
	private int id;
	
	private int row;
	private int column;
	private String value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("%1$d [%2$d,%3$d]=%4$s", id,row,column,value);
	}
}
