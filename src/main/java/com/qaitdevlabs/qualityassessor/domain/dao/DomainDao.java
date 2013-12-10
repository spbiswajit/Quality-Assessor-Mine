package com.qaitdevlabs.qualityassessor.domain.dao;

/**
 * This service interface is used to handle request from service layer for saving,
 * updating ,deleting domains and to get child domains.
 * 
 * @author anujchhabra
 * 
 */
import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.GenericDao;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.DomainMapping;
import com.qaitdevlabs.qualityassessor.model.User;

public interface DomainDao extends GenericDao<Domain, Long> {

	/**
	 * This method get list of root domains
	 * 
	 * @return
	 */
	public List<Domain> getRootDomainList();

	/**
	 * This method get list of child domains corresponding to parent id from
	 * database
	 * 
	 * @param id
	 *            parent domain id
	 * @return domain list list of root domains
	 */
	public List<DomainMapping> getSubDomainList(Long id);

	/**
	 * This method get domain mapping object corresponding to domain id and
	 * subdomain id from database
	 * 
	 * @param id
	 *            domain id
	 * @param parentId
	 *            parent domain id
	 * @return list of domain mappings
	 */
	public DomainMapping getDomainMapping(Long id, Long parentId);

	/**
	 * This method delete domain mapping corresponding to domain id and
	 * subdomain id from database
	 * 
	 * @param parentDomainId
	 *            parent domain id
	 * @param domainId
	 *            domain id
	 * @return true if deleted successfully otherwise return false
	 */
	public boolean deleteDomainMapping(Long parentDomainId, Long domainId);

	/**
	 * This method is used to save or update domain mapping corresponding to
	 * domain id and subdomain id in database
	 * 
	 * @param domainMapping
	 *            domain mapping
	 * @return domain mapping object
	 */
	public DomainMapping saveOrUpdateDomainMapping(DomainMapping domainMapping);

	/**
	 * This method is used to save or update domain
	 * 
	 * @param domain
	 *            given domain object
	 * @return domain
	 */
	public Domain saveOrUpdateDomain(Domain domain);

	public List<Domain> findDomainsWithProperty(String property, String value);

	List<Domain> getRootDomainListOnUserBasis(User user, String domainType);

	public List<Domain> getMatchingRootDomains(String name);

	List<Domain> getDomainByNameAndType(String name, String domainType);

}
