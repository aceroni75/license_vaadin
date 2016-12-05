package com.genedata.expressionist.data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alessio Ceroni
 */
@Entity
public class License {

	@Id
	@GeneratedValue
	private Long id;

	private String recipient;

	@Temporal(TemporalType.DATE)
	private Date expiration;

// --------------------------- Constructors ---------------------------

	public License() {
		this("", new Date());
	}

	public License(String recipient, Date expiration) {
		this.recipient = recipient;
		this.expiration = expiration;
	}

// -------------------------- Public --------------------------

	public Date getExpiration() {
		return expiration;
	}

	public Long getId() {
		return id;
	}

	public String getRecipient() {
		return recipient;
	}

	public boolean isPersisted() {
		return id != null;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
}
