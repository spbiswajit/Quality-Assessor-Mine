package com.qaitdevlabs.qualityassessor.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;
import com.qaitdevlabs.qualityassessor.validator.FieldMatch;

/**
 * Created with IntelliJ IDEA. User: anujchhabra Date: 28/8/12 Time: 2:35 PM To
 * change this template use File | Settings | File Templates.
 */

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "Password doesn\'t match with confirm password")})
public class UserProfileDTO {

	@Email(message = "Please enter a valid email address.")
	@NotEmpty(message = "This field is required.")
	private String username;

	@Length(min = 6, message = "Please enter atleast 6 characters")
	private String password;

	@Length(min = 6, message = "Please enter atleast 6 characters")
	private String confirmPassword;

	@NotEmpty(message = "This field is required.")
	private String firstName;

	private String middleName;
	private String suffix;

	@NotEmpty(message = "This field is required.")
	private String lastName;
	private String country;
	private String city;
	private String state;
	private String zipCode;
	private String addressLine1;
	private String addressLine2;
	private String facebookId;
	private String linkedInId;
	private String twitterId;
	private String googleplusId;

	private String address;

	private String education;
	private List<String> title;
	private List<String> areaOfExpertise;
	private List<Date> fromDate;
	private List<Date> toDate;

	
	private List<WorkExperience> workExperiences;
	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getAreaOfExpertise() {
		return areaOfExpertise;
	}

	public void setAreaOfExpertise(List<String> areaOfExpertise) {
		this.areaOfExpertise = areaOfExpertise;
	}

	public List<Date> getFromDate() {
		return fromDate;
	}

	public void setFromDate(List<Date> fromDate) {
		this.fromDate = fromDate;
	}

	public List<Date> getToDate() {
		return toDate;
	}

	public void setToDate(List<Date> toDate) {
		this.toDate = toDate;
	}

	public List<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getGoogleplusId() {
		return googleplusId;
	}

	public void setGoogleplusId(String googleplusId) {
		this.googleplusId = googleplusId;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
