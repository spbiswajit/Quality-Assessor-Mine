package com.qaitdevlabs.qualityassessor.domain.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.assessment.dao.AssessmentDao;
import com.qaitdevlabs.qualityassessor.assessment.dao.impl.AssessmentDaoImpl;
import com.qaitdevlabs.qualityassessor.assessment.service.AssessmentService;
import com.qaitdevlabs.qualityassessor.common.exception.GenericException;
import com.qaitdevlabs.qualityassessor.domain.dao.DomainDao;
import com.qaitdevlabs.qualityassessor.domain.service.DomainService;
import com.qaitdevlabs.qualityassessor.dto.DomainDTO;
import com.qaitdevlabs.qualityassessor.dto.RadarChartInfo;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;
import com.qaitdevlabs.qualityassessor.model.Assessment;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.DomainMapping;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.util.CustomDomainComparator;

/**
 * 
 * @author anujchhabra
 * 
 */
@Service
public class DomainServiceImpl implements DomainService {

	private DomainDao domainDao;

	@Autowired
	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}

	private AssessmentDao assessmentDao;

	@Autowired
	public void setAssessmentDao(AssessmentDao assessmentDao) {
		this.assessmentDao = assessmentDao;
	}

	@Override
	public List<TreeNodeDTO> getDomainList(String key ,User user ,String domainType) {
		Long id = Long.valueOf(key);
		List<TreeNodeDTO> nodeList = null;
		if (id == 0) {
			nodeList = getTreeNodeDTO(domainDao.getRootDomainListOnUserBasis(user, domainType));
			System.out.println("service"+nodeList);
		} else {
			nodeList = getTreeNodeDTO(domainDao.getSubDomainList(id));
		}
		if(nodeList !=null){
			Collections.sort(nodeList, new CustomDomainComparator());
		}
		System.out.println("service"+nodeList);
		return nodeList;
	}

		
	@SuppressWarnings("rawtypes")
	private List<TreeNodeDTO> getTreeNodeDTO(List object) {
		
		if(object == null || object.size()<1){
			return null;
		}

		List<TreeNodeDTO> domainsDto = new ArrayList<TreeNodeDTO>();
		if (object.get(0) instanceof Domain) {
			@SuppressWarnings("unchecked")
			List<Domain> domains = object;
			Iterator<Domain> it = domains.iterator();

			while (it.hasNext()) {

				Domain domain = (Domain) it.next();
				TreeNodeDTO dto = getTreeNodeDTO(domain);
//				int noOfChildren = domainDao.getSubDomainList(
//						domain.getDomainId()).size();
//				System.out.println("domainid and children"
//						+ domain.getDomainId() + " " + noOfChildren);
//
//				if (noOfChildren > 0) {
////					dto.setIsLazy("true");
//					System.out.println("lazy" + true);
//				}
				domainsDto.add(dto);

			}
		} else {

			@SuppressWarnings("unchecked")
			List<DomainMapping> domainMappings = object;
			Iterator<DomainMapping> it = domainMappings.iterator();

			while (it.hasNext()) {

				DomainMapping domainMapping = (DomainMapping) it.next();
				Domain subDomain = domainMapping.getSubDomain();
				Domain domain = domainMapping.getDomain();
				TreeNodeDTO dto = getTreeNodeDTO(subDomain);

				int noOfChildren = domainDao.getSubDomainList(
						subDomain.getDomainId()).size();
				System.out.println("domainid and children"
						+ subDomain.getDomainId() + " " + noOfChildren);

				if (noOfChildren > 0) {
//					dto.setIsLazy("true");
					System.out.println("lazy" + true);
				}

				dto.setParentKey(String.valueOf(domain.getDomainId()));
				dto.setWeightage(String.valueOf(domainMapping.getWeightage()));
				domainsDto.add(dto);

			}
		}
		return domainsDto;
	}

	private TreeNodeDTO getTreeNodeDTO(Domain domain) {
		if (domain != null) {
			TreeNodeDTO dto = new TreeNodeDTO();
			dto.setKey(String.valueOf(domain.getDomainId()));
			dto.setTitle(domain.getDomainName());
			dto.setType(domain.getDomainType());
			//dto.setWikipediaLink(domain.getWikipediaLink());
			dto.setCreationDate(domain.getCreationDate());
			// dto.setCreationUserName(domain.getCreationUser().getUsername());
			// dto.setModificationUserName(domain.getModificationUser().getUsername());
			dto.setModificationDate(domain.getModificationDate());
			dto.setRoot(domain.getIsParent());
			return dto;
		} else
			return null;

	}

	@Override
	public Long updateDomain(TreeNodeDTO dto, User user) {

		Long key = Long.valueOf(dto.getKey());
		String parent = dto.getParentKey();
		Long domainId = null;
		if (parent.equals("0")) {
			Domain domain = domainDao.get(key);
			if (domain == null) {
				domain = new Domain();
				domain.setCreationDate(dto.getModificationDate());
				domain.setCreationUser(user);
				domain.setDomainType(dto.getType());
				domain.setIsParent(true);
				domain.setIsActive(true);
			}
			domain.setDomainName(dto.getTitle());
			domain.setModificationDate(dto.getModificationDate());
			domainDao.saveOrUpdateDomain(domain);
			domainId = domain.getDomainId();
		} else {

			Long parentKey = Long.valueOf(parent);
			String domainName = dto.getTitle();
			int weightage = Integer.valueOf(dto.getWeightage());
			Date modificationDate = dto.getModificationDate();

			DomainMapping domainMapping = domainDao.getDomainMapping(parentKey,
					key);
			Domain subDomain = null;
			if (domainMapping == null) {
				domainMapping = new DomainMapping();
				subDomain = new Domain();
				subDomain.setDomainName(dto.getTitle());
				subDomain.setCreationDate(dto.getCreationDate());
				subDomain.setModificationDate(dto.getCreationDate());
				subDomain.setCreationUser(user);
				subDomain.setIsActive(true);
				subDomain.setIsParent(false);
				subDomain.setDomainType(dto.getType());
				Domain domain = domainDao.get(parentKey);
				domainMapping.setDomain(domain);
				domainMapping.setSubDomain(subDomain);
				domainMapping.setCreationDate(modificationDate);
			} else {
				subDomain = domainMapping.getSubDomain();
				subDomain.setModificationDate(modificationDate);
				subDomain.setDomainName(domainName);
				domainDao.saveOrUpdateDomain(subDomain);
			}
			domainMapping.setWeightage(weightage);
			domainMapping.setModificationDate(modificationDate);
			domainDao.saveOrUpdateDomainMapping(domainMapping);
			domainId = subDomain.getDomainId();
		}
		return domainId;
	}

	@Override
	public TreeNodeDTO saveDomain(TreeNodeDTO dto, User user) {

		String parentKey = dto.getParentKey();
		Domain subDomain = new Domain();
		subDomain.setDomainName(dto.getTitle());
		subDomain.setCreationDate(dto.getCreationDate());
		subDomain.setModificationDate(dto.getCreationDate());
		// domain.setWikipediaLink(wikipediaLink);
		subDomain.setCreationUser(user);
		// domain.setModificationUser(modificationUser);
		subDomain.setIsActive(true);
		if (parentKey.equals("null")) {
			System.out.println("null parent");
			subDomain.setIsParent(true);
			domainDao.saveOrUpdateDomain(subDomain);
		} else {
			subDomain.setIsParent(false);
			Long parentId = Long.valueOf(parentKey);
			int weightage = Integer.valueOf(dto.getWeightage());
			Domain domain = domainDao.get(parentId);
			DomainMapping domainMapping = new DomainMapping();
			domainMapping.setSubDomain(subDomain);
			domainMapping.setDomain(domain);
			domainMapping.setCreationDate(dto.getCreationDate());
			domainMapping.setModificationDate(dto.getCreationDate());
			domainMapping.setWeightage(weightage);
			domainDao.saveOrUpdateDomainMapping(domainMapping);
		}
		dto.setKey(String.valueOf(subDomain.getDomainId()));
		return dto;
	}

	@Override
	public boolean deleteDomain(String parentKey, String key) {
		boolean success = false;
		Long domainId = Long.valueOf(key);
		Long parentDomainId = null;

		if (parentKey.equals("0")) {

			Domain domain = domainDao.get(domainId);
			domain.setIsParent(false);
			domainDao.saveOrUpdateDomain(domain);
			success = true;
		} else {

			parentDomainId = Long.valueOf(parentKey);
			success = domainDao.deleteDomainMapping(parentDomainId, domainId);
		}
		return success;

	}

	@Override
	public int getRemainingWeightageInDomain(String domainKey) {
		Long domainId = Long.valueOf(domainKey);
		List<DomainMapping> domainMappings = domainDao
				.getSubDomainList(domainId);
		Iterator<DomainMapping> it = domainMappings.iterator();
		int totalSubDomainWeightage = 0;
		int remainingWeightage = 0;
		while (it.hasNext()) {
			DomainMapping domainMapping = (DomainMapping) it.next();
			totalSubDomainWeightage += domainMapping.getWeightage();
		}
		remainingWeightage = 100 - totalSubDomainWeightage;
		return remainingWeightage;
	}

	@Override
	public List<DomainDTO> getSubDomains(String key) {
		Long id = Long.valueOf(key);
		List<DomainDTO> list = new ArrayList<DomainDTO>();
		List<DomainMapping> domainMappings = domainDao.getSubDomainList(id);
		Iterator<DomainMapping> it = domainMappings.iterator();
		while (it.hasNext()) {
			DomainDTO dto = new DomainDTO();
			DomainMapping domainMapping = (DomainMapping) it.next();
			String domainName = domainMapping.getSubDomain().getDomainName();
			String weightage = String.valueOf(domainMapping.getWeightage());
			dto.setName(domainName);
			dto.setWeightage(weightage);
			list.add(dto);
		}
		return list; 
	}

	
	//GET All root domains
	
	@Override
	public List<DomainDTO> getListOfRootDomains() {
		List<DomainDTO> listOfRootDomains = null;
		List<Domain> domains = domainDao.getRootDomainList();
		if (domains.size() > 0) {
			listOfRootDomains = new ArrayList<DomainDTO>();
			Iterator<Domain> domainList = domains.iterator();
			while (domainList.hasNext()) {
				Domain domain = (Domain) domainList.next();
				DomainDTO dto = new DomainDTO();
				dto.setId(String.valueOf(domain.getDomainId()));
				dto.setName(domain.getDomainName());
				listOfRootDomains.add(dto);
			}

		}
		return listOfRootDomains;
	}

	public TreeNodeDTO getDomainHierarchy(Long id ) {
		Domain domain = (Domain) domainDao.get(id);
		TreeNodeDTO node = getTreeNodeDTO(domain);
		
		List<DomainMapping> subDomainMappingList = domainDao
				.getSubDomainList(id);
		Iterator<DomainMapping> it = subDomainMappingList.iterator();
		List<TreeNodeDTO> childList = new ArrayList<TreeNodeDTO>();

		float score1 = 0;
		while (it.hasNext()) {
			DomainMapping domainMapping = (DomainMapping) it.next();
			TreeNodeDTO dto = getDomainHierarchy(domainMapping.getSubDomain()
					.getDomainId());
			Integer weightage = domainMapping.getWeightage();
			float childScore = dto.getScore();
			score1 += childScore * weightage / 100;
			System.out.println("childScore " + childScore + "score" + score1
					+ dto.getTitle() + "we" + weightage);
			dto.setWeightage(weightage.toString());
			childList.add(dto);
		}
		if (childList.size() > 0) {
			Collections.sort(childList, new CustomDomainComparator());
			node.setChildren(childList);
			node.setScore(score1);
			System.out.println(node.getTitle() + " " + score1);
			score1 = 0;
		}
		return node;
	}

	
	public TreeNodeDTO getDomainHierarchyWithAssessment(Long id, User assessor, AssessmentInvitation invitation, Product product) {
		Domain domain = (Domain) domainDao.get(id);
		TreeNodeDTO node = getTreeNodeDTO(domain);
		
			Assessment assessment = assessmentDao.getAssessment(assessor, product,
					domain,invitation);
			if (assessment != null) {
				node.setScore(assessment.getScore());
				node.setAssessmentId(assessment.getAssessmentId());
			}
		
		
		List<DomainMapping> subDomainMappingList = domainDao
				.getSubDomainList(id);
		Iterator<DomainMapping> it = subDomainMappingList.iterator();
		List<TreeNodeDTO> childList = new ArrayList<TreeNodeDTO>();

		float score1 = 0;
		while (it.hasNext()) {
			DomainMapping domainMapping = (DomainMapping) it.next();
			TreeNodeDTO dto = getDomainHierarchyWithAssessment(domainMapping.getSubDomain()
					.getDomainId(), assessor, invitation, product );
			Integer weightage = domainMapping.getWeightage();
			float childScore = dto.getScore();
			score1 += childScore * weightage / 100;
			System.out.println("childScore " + childScore + "score" + score1
					+ dto.getTitle() + "we" + weightage);
			dto.setWeightage(weightage.toString());
			childList.add(dto);
		}
		if (childList.size() > 0) {
			Collections.sort(childList, new CustomDomainComparator());
			node.setChildren(childList);
			node.setScore(score1);
			System.out.println(node.getTitle() + " " + score1);
			score1 = 0;
		}
		return node;
	}

	
	
	@Override
	public Domain getDomain(String key) {
		Long id = null;
		try {
			id = Long.valueOf(key);
		} catch (Exception e) {
			System.out.println(e);
			throw new GenericException("Requested url is incorrect!!!");
		}
		return domainDao.get(id);
	}

	public void getExtremeChildDomains(Long id, List<Domain> extrmeChilds) {
		System.out.println("getExtremeChildDomains");
		Domain domain = (Domain) domainDao.get(id);
		List<DomainMapping> subDomainMappingList = domainDao
				.getSubDomainList(id);
		if (subDomainMappingList.size() < 1) {
			extrmeChilds.add(domain);
			System.out.println(domain.getDomainName());
		} else {
			Iterator<DomainMapping> it = subDomainMappingList.iterator();

			while (it.hasNext()) {
				DomainMapping domainMapping = (DomainMapping) it.next();
				getExtremeChildDomains(domainMapping.getSubDomain()
						.getDomainId(), extrmeChilds);
			}
		}
	}

	public Domain getCloneDomain(Domain domain, Date date, User user){
		Domain cloneDomain = new Domain();
		cloneDomain.setDomainName(domain.getDomainName());
		cloneDomain.setIsParent(false);
		cloneDomain.setIsActive(domain.getIsActive());
		cloneDomain.setDomainType(domain.getDomainType());
		cloneDomain.setCreationDate(date);
		cloneDomain.setModificationDate(date);
		cloneDomain.setCreationUser(user);
		return cloneDomain;
	}
	
	public void copyDomainHierarchy(Long id, Domain cloneDomain, Date date,
			User user, int orgCounter ,int counter) {
		List<DomainMapping> domainMappings = domainDao.getSubDomainList(id);
		if (domainMappings.size() == 0) {
			counter = orgCounter;
		} else {
			Iterator<DomainMapping> itr = domainMappings.iterator();
			while (itr.hasNext()) {
				DomainMapping domainMapping = itr.next();
				Domain subDomain = domainMapping.getSubDomain();
				Domain cloneSubDomain = getCloneDomain(subDomain, date, user);
				DomainMapping cloneDomainMapping = new DomainMapping();
				cloneDomainMapping.setDomain(cloneDomain);
				cloneDomainMapping.setSubDomain(cloneSubDomain);
				cloneDomainMapping.setWeightage(domainMapping.getWeightage());
				cloneDomainMapping.setCreationDate(date);
				cloneDomainMapping.setModificationDate(date);
				domainDao.saveOrUpdateDomainMapping(cloneDomainMapping);
				System.out.println(cloneDomain.getDomainId() + " "
						+ cloneSubDomain.getDomainId());
				counter--;
				if (counter > 1) {
					copyDomainHierarchy(subDomain.getDomainId(),
							cloneSubDomain, date, user, orgCounter, counter);
					counter = orgCounter;
				} 
			}
		}

	}

	@Override
	public Long importDomainHierarchy(String key, String parentKey,
			String weightage, User user) {
		Long rootDomainId = null;
		Long domainId = Long.valueOf(key);
		Domain domain = domainDao.get(domainId);
		Date date = new Date();
		Domain cloneDomain = getCloneDomain(domain, date, user);
		int counter = 2 ;
		if (parentKey.equals("0")) {
			cloneDomain.setIsParent(true);
			domainDao.saveOrUpdateDomain(cloneDomain);
			counter = 3;
	
		} else {
			Long parentDomainId = Long.valueOf(parentKey);
			Domain parentDomain = domainDao.get(parentDomainId);
			DomainMapping domainMapping = new DomainMapping();
			domainMapping.setDomain(parentDomain);
			domainMapping.setSubDomain(cloneDomain);
			int weighage = Integer.valueOf(weightage);
			domainMapping.setWeightage(weighage);
			domainMapping.setCreationDate(date);
			domainMapping.setModificationDate(date);
			domainDao.saveOrUpdateDomainMapping(domainMapping);
			
		}
		rootDomainId = cloneDomain.getDomainId();
		copyDomainHierarchy(domainId, cloneDomain, date, user, counter ,counter);
		return rootDomainId;
		
	}
	
	// public void getExtremeChildDomains(Long id, User user, User assessor,
	// List<RadarChartInfo> extrmeChilds) {
	// List<Domain> leafDomains = new ArrayList<Domain>();
	// getExtremeChildDomains(id, leafDomains);
	// Iterator<Domain> it = leafDomains.iterator();
	// while (it.hasNext()) {
	// Domain domain = it.next();
	// Assessment assessment = assessmentDao.getAssessment(assessor, user,
	// domain);
	// if (null != assessment) {
	// RadarChartInfo info = new RadarChartInfo();
	// info.setTitle(domain.getDomainName());
	// System.out
	// .println("*******************************************");
	// System.out.println(info.getTitle());
	//
	// info.setScore(assessment.getScore());
	//
	// System.out.println(info.getScore());
	// System.out
	// .println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	// info.setCategory("self");
	// extrmeChilds.add(info);
	// }
	// double score = assessmentDao.getAverageAssessment(user, assessor,
	// domain);
	// RadarChartInfo info1 = new RadarChartInfo();
	// info1.setTitle(domain.getDomainName());
	// info1.setScore(score);
	// info1.setCategory("Average");
	// extrmeChilds.add(info1);
	//
	// }
	// }

	public void getExtremeChildDomains(Long id, Product product, User assessor,
			List<RadarChartInfo> extrmeChilds) {
		List<DomainMapping> domainMappings = domainDao.getSubDomainList(id);
		for (int i = 0; i < domainMappings.size(); i++) {
			DomainMapping domainMapping = domainMappings.get(i);
			Domain domain = domainMapping.getSubDomain();
			List<DomainMapping> secondLevelDomainMappings = domainDao
					.getSubDomainList(domain.getDomainId());
			double selfScore = 0, combineScore = 0;
			if (secondLevelDomainMappings.size() < 1) {
				// int weightage = domainMapping.getWeightage();
				Assessment assessment = assessmentDao.getAssessment(assessor, product,
						domain,null);
				if (assessment != null) {
					selfScore = assessment.getScore();
				}
				combineScore = assessmentDao.getAverageAssessment(product,
						assessor, domain);

			} else {
				for (int j = 0; j < secondLevelDomainMappings.size(); j++) {
					DomainMapping thirdLeveldomainMapping = secondLevelDomainMappings
							.get(j);
					int weightage = thirdLeveldomainMapping.getWeightage();
					Domain thirdLeveldomain = thirdLeveldomainMapping
							.getSubDomain();
					Assessment assessment = assessmentDao.getAssessment(assessor,
							product, thirdLeveldomain,null);
					if (assessment != null) {
						int score = assessment.getScore();
						double actualScore = (float) score * weightage / 100;
						selfScore += actualScore;
						System.out.println(score + " " + actualScore + " "
								+ weightage + " " + selfScore + " "
								+ thirdLeveldomain.getDomainName());
					}
					double avgScore = assessmentDao.getAverageAssessment(product,
							assessor, thirdLeveldomain);
					avgScore = (float) avgScore * weightage / 100;
					combineScore += avgScore;
				}
			}
			String domainName = domain.getDomainName();
			System.out.println("domain -name" + domainName);
			int weightage = domainMapping.getWeightage();
			selfScore = selfScore * weightage / 100;
			RadarChartInfo info1 = new RadarChartInfo();
			info1.setTitle(domainName);
			info1.setScore(selfScore);
			info1.setCategory("Self");
			combineScore = combineScore * weightage / 100;
			RadarChartInfo info2 = new RadarChartInfo();
			info2.setTitle(domainName);
			info2.setScore(combineScore);
			info2.setCategory("Combine");
			extrmeChilds.add(info1);
			extrmeChilds.add(info2);
		}
	}

	@Override
	public List<DomainDTO> findDomainsWithProperty(String property, String value) {
		List<DomainDTO> listOfDomainDTO = null;
		List<Domain> listOfDomains = domainDao.findDomainsWithProperty(
				property, value);
		if (listOfDomains.size() > 0) {
			listOfDomainDTO = new ArrayList<DomainDTO>();
			Iterator<Domain> domainItr = listOfDomains.iterator();
			while (domainItr.hasNext()) {
				Domain domain = domainItr.next();
				DomainDTO dto = new DomainDTO();
				dto.setId(String.valueOf(domain.getDomainId()));
				dto.setName(domain.getDomainName());
				listOfDomainDTO.add(dto);
			}
		}
		return listOfDomainDTO;
	}

	public List<TreeNodeDTO> getExistingDomainHierarchy(String domainName, String domainType) {
		List<TreeNodeDTO> listOfDTO = null;
		List<Domain> listOfDomains = domainDao.getDomainByNameAndType(domainName, domainType);
		if(listOfDomains == null){
			return null;
		}
		else{
			listOfDTO = new ArrayList<TreeNodeDTO>();
		}
		Iterator<Domain> itr = listOfDomains.iterator();
		while (itr.hasNext()) {
			Domain domain = itr.next();
			long domainId = domain.getDomainId();
			TreeNodeDTO dto = getDomainHierarchy(domainId );
			listOfDTO.add(dto);
		}
		return listOfDTO;
	}

	@Override
	public boolean hasUpdateOrDeletePermission(String key, Long userId) {
		Long domainId = Long.valueOf(key);
		Domain domain = domainDao.get(domainId);
		User user = domain.getCreationUser();
		Long creationUserId = null;
		if(user != null){
			creationUserId = domain.getCreationUser().getUserId();
		}
		System.out.println(creationUserId + " " + userId);
		if (creationUserId!=null && creationUserId.equals(userId))
			return true;
		else
			return false;
	}

	@Override
	public List<TreeNodeDTO> getMatchingRootDomains(String name) {
		List<Domain> listOfDomains = domainDao.getMatchingRootDomains(name);
		List<TreeNodeDTO> list = null;
		if(listOfDomains!=null){
		list = new ArrayList<TreeNodeDTO>();
		}
		Iterator<Domain> it = listOfDomains.iterator();
		while(it.hasNext()){
			Domain domain = it.next();
			TreeNodeDTO dto =  getTreeNodeDTO(domain);
			list.add(dto);
		}
		return list;
	}

	
	// public void getExtremeChildDomains(Long id, User user, User assessor,
	// List<TreeNodeDTO> extrmeChilds) {
	// Domain domain = (Domain) domainDao.get(id);
	// TreeNodeDTO node = getTreeNodeDTO(domain);
	// Assessment assessment = assessmentDao.getAssessment(assessor, user,
	// domain);
	// if (assessment != null) {
	// node.setScore(assessment.getScore());
	// node.setAssessmentId(assessment.getAssessmentId());
	// }
	//
	// List<DomainMapping> subDomainMappingList = domainDao
	// .getSubDomainList(id);
	// if (subDomainMappingList.size() < 1) {
	// System.out.println(node.getTitle());
	// extrmeChilds.add(node);
	// }
	// Iterator<DomainMapping> it = subDomainMappingList.iterator();
	//
	// while (it.hasNext()) {
	// DomainMapping domainMapping = (DomainMapping) it.next();
	// getExtremeChildDomains(domainMapping.getSubDomain().getDomainId(),
	// assessor, user, extrmeChilds);
	// }
	//
	// }

	// public List<TreeNodeDTO> getChildNodes(TreeNodeDTO node, Long id) {
	//
	// List<DomainMapping> subDomainMappingList = domainDao
	// .getSubDomainList(id);
	// Iterator<DomainMapping> it = subDomainMappingList.iterator();
	// while (it.hasNext()) {
	// List children = new ArrayList();
	// DomainMapping domainMapping = (DomainMapping) it.next();
	// TreeNodeDTO child = getTreeNodeDTO(domainMapping.getDomain());
	// node.setChildren(getChildNodes(child, domainMapping.getDomain()
	// .getDomainId()));
	// }
	//
	// return chidNodes;
	// }

	/*
	 * @Override public Domain get(Long id) { return domainDao.get(id); }
	 */
	/*
	 * private List<TreeNodeDTO> getTreeNodeDTO( List<DomainMapping>
	 * domainsMapping) { Iterator<DomainMapping> it = domainsMapping.iterator();
	 * List<TreeNodeDTO> domainsDto = new ArrayList<TreeNodeDTO>(); while
	 * (it.hasNext()) { DomainMapping domainMapping = (DomainMapping) it.next();
	 * TreeNodeDTO dto = new TreeNodeDTO(); Domain domain =
	 * domainMapping.getSubDomain();
	 * dto.setKey(String.valueOf(domain.getDomainId()));
	 * dto.setTitle(domain.getDomainName());
	 * dto.setWikipediaLink(domain.getWikipediaLink());
	 * dto.setCreationDate(domain.getCreationDate());
	 * dto.setCreationUserName(domain.getCreationUser().getUsername());
	 * dto.setModificationUserName(domain.getModificationUser().getUsername());
	 * dto.setModificationDate(domain.getModificationDate());
	 * domainsDto.add(dto); } return domainsDto; }
	 */
}
