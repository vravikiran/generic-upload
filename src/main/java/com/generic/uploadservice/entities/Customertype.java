package com.generic.uploadservice.entities;

import java.util.Objects;
import java.util.UUID;

import com.generic.uploadservice.validators.ConfigurableSize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * CustomerType entity
 */
@Entity
public class Customertype {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID bizcontacttypeid;
	@ConfigurableSize(maxProperty = "customertype.column.size")
	private String bizcontacttypedesc;

	public UUID getBizcontacttypeid() {
		return bizcontacttypeid;
	}

	public String getBizcontacttypedesc() {
		return bizcontacttypedesc;
	}

	public void setBizcontacttypedesc(String bizcontacttypedesc) {
		this.bizcontacttypedesc = bizcontacttypedesc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bizcontacttypedesc, bizcontacttypeid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customertype other = (Customertype) obj;
		return Objects.equals(bizcontacttypedesc, other.bizcontacttypedesc)
				&& Objects.equals(bizcontacttypeid, other.bizcontacttypeid);
	}

	@Override
	public String toString() {
		return "CustomerType [bizcontacttypeid=" + bizcontacttypeid + ", bizcontacttypedesc=" + bizcontacttypedesc
				+ "]";
	}
}
