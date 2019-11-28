package com.mm.app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="persistant_logins")
public @Data class PersistentLogin implements Serializable{

	@Id
	private String series;

	@Column(name="user_name", unique=true, nullable=false)
	private String username;
	
	@Column(name="token", unique=true, nullable=false)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;
}
