/**
 * 
 */
package com.mm.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author nahid
 *
 */
/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 */
@Entity
@Table(name = "address")
public @Data class Address {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "street", nullable = false)
	@NotEmpty
	private String street;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "zip", nullable = false)
	@NotEmpty
	private String zipCode;
	
	@Column(name = "city", nullable = false)
	@NotEmpty
	private String city;
	
	@Column(name = "country", nullable = false)
	@NotEmpty
	private String country;
	
	@Column(name = "latitude", nullable = false)
	@NotNull
	private Double latitude;
	
	@Column(name = "longitude", nullable = false)
	@NotNull
	private Double longitude;


}
