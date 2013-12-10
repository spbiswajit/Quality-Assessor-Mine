package com.qaitdevlabs.qualityassessor.model;

import java.io.Serializable;
import java.util.Date;

public class DomainMapping implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long domainMappingId;
	private Domain domain;
	private Domain subDomain;
	private Integer weightage;
	private Date creationDate;
	private Date modificationDate;

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Domain getSubDomain() {
		return subDomain;
	}

	public void setSubDomain(Domain subDomain) {
		this.subDomain = subDomain;
	}

	public Integer getWeightage() {
		return weightage;
	}

	public void setWeightage(Integer weightage) {
		this.weightage = weightage;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Long getDomainMappingId() {
		return domainMappingId;
	}

	public void setDomainMappingId(Long domainMappingId) {
		this.domainMappingId = domainMappingId;
	}
}
