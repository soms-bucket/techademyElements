package com.pack;

import javax.persistence.*;

@Entity
@Table(name = "customer")	//Add Table annotation with table name
public class Customer {
	@Id // Add annotation for primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	// Add column annotation
	@Column(name = "id")
	private int id;
	
	// Add column annotation
	@Column(name = "name")
	private String csName;
	
	// Add column annotation
	@Column(name = "email")
	private String csEmail;
	
	@Column(name = "phone")
	private String csPhone;
	
	// Constructors, getters, and setters
	public Customer() {
		super();
	}

	public Customer( String csName, String csEmail, String csPhone) {
		super();

		this.csName = csName;
		this.csEmail = csEmail;
		this.csPhone = csPhone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCsName() {
		return csName;
	}

	public void setCsName(String csName) {
		this.csName = csName;
	}

	public String getCsEmail() {
		return csEmail;
	}

	public void setCsEmail(String csEmail) {
		this.csEmail = csEmail;
	}

	public String getCsPhone() {
		return csPhone;
	}

	public void setCsPhone(String csPhone) {
		this.csPhone = csPhone;
	}
	
	
	
	
}
