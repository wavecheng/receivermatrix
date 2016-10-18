package com.citrix.matrix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Data {
	@Id
	private long id = 1;
	
	@Column
	@Lob
	private String tableData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableData() {
		return tableData;
	}

	public void setTableData(String tableData) {
		this.tableData = tableData;
	}
	
	
}
