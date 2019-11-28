/**
 * 
 */
package com.mm.app.model;

import javax.persistence.*;
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
@Table(name = "`user`")
public @Data class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", unique=true, nullable = false)
	@NotEmpty
	private String name;
	
	@Column(name = "password", nullable = false)
	@NotEmpty
	private String password;

	@Column(name = "is_active", nullable = false)
	@NotNull
	private boolean isActive;

}
