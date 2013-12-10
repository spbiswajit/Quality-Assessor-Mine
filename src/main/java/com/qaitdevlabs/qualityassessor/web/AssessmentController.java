package com.qaitdevlabs.qualityassessor.web;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qaitdevlabs.qualityassessor.assessment.service.AssessmentService;
import com.qaitdevlabs.qualityassessor.assessmentinvitation.service.AssessmentInvitationService;
import com.qaitdevlabs.qualityassessor.common.exception.GenericException;
import com.qaitdevlabs.qualityassessor.domain.service.DomainService;
import com.qaitdevlabs.qualityassessor.dto.DomainDTO;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;
import com.qaitdevlabs.qualityassessor.model.Assessment;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.ProductTemplateMap;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.product.service.ProductService;
import com.qaitdevlabs.qualityassessor.productTemplateMap.service.ProductTemplateMapService;
import com.qaitdevlabs.qualityassessor.service.UserService;

@Controller
@RequestMapping("/assessments")
public class AssessmentController {

	private AssessmentService assessmentService;

	@Autowired
	public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private DomainService domainService;

	@Autowired
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
	
	private AssessmentInvitationService assessmentInvitationService;

	@Autowired
	public void setAssessmentInvitationService(
			AssessmentInvitationService assessmentInvitationService) {
		this.assessmentInvitationService = assessmentInvitationService;
	}

	private ProductTemplateMapService productTemplateMapService;

	@Autowired
	public void setProductTemplateMapService(
			ProductTemplateMapService productTemplateMapService) {
		this.productTemplateMapService = productTemplateMapService;
	}
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getListOfRootDomains(ModelMap map) {
		List<DomainDTO> listOfRootDomains = domainService
				.getListOfRootDomains();
		map.addAttribute("listOfRootDomains", listOfRootDomains);
		return "assessments";
	}

	

	@RequestMapping(value = "/{productTemplateKey}", method = RequestMethod.GET)
	public String showDomainPage(ModelMap map, @PathVariable String productTemplateKey, @RequestParam(required = false) Long invitationId, HttpServletRequest request) {
		System.out.println("key"+productTemplateKey);
		Long productTemplateMapId = Long.valueOf(productTemplateKey);
		
		ProductTemplateMap productTemplateMap = productTemplateMapService.getProductTemplateMapById(productTemplateMapId);
		
		Product product = productTemplateMap.getProduct();
		Domain domain = productTemplateMap.getDomain();
		
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);
		AssessmentInvitation invitation = null;
		
		if( invitationId != null ){
			invitation = assessmentInvitationService.getAssessmentInvitation(invitationId);
		}
		
		TreeNodeDTO dto = domainService.getDomainHierarchyWithAssessment(
				domain.getDomainId(), user, invitation, product);
		System.out.println(dto.getTitle());
		request.setAttribute("productId", product.getProductId());
		request.setAttribute("productName", product.getProductName());
		request.setAttribute("templateDTO",dto);
		return "reviewPage";
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	long saveRating(
			@RequestParam String key,
			@RequestParam String id,
			@RequestParam String productId,
			@RequestParam(value = "requestedUserId", required = false) String requestedUserId,
			@RequestParam(value = "invitationId", required = false) String invitationKey,
			@RequestParam String score, ModelMap map, HttpServletRequest request) {
		System.out.println("id" + id);

		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User assessor = userService.getUser(userId);
		AssessmentInvitation invitation = null;
//		User user = null;
//		user = userService.getUser(Long.valueOf(requestedUserId));
		if(!invitationKey.equals("null"))
			invitation = assessmentInvitationService.getAssessmentInvitation(Long.valueOf(invitationKey));
	
		
		Domain domain = domainService.getDomain(key);
		Long assessmentId = Long.valueOf(id);
		if (assessmentId == 0) {
			assessmentId = null;
		}
		
		Product product = productService.getProductById(Long.valueOf(productId));
		Date assessmentDate = new Date();
		Assessment assessment = new Assessment();
		assessment.setAssessmentId(assessmentId);
	//	assessment.setUser(user);
		assessment.setAssessor(assessor);
		assessment.setDomain(domain);
		assessment.setScore(Integer.valueOf(score));
		assessment.setAssessmentDate(assessmentDate);
		assessment.setInvitation(invitation);
		assessment.setProduct(product);
		assessment = assessmentService.saveAssessment(assessment);
		assessmentId = assessment.getAssessmentId();
		return assessmentId;
	}

}
