<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>

	<div >

		<form:form id="profileForm" action="profile" method="POST"
			commandName="userProfileDTO">
			<form:errors path="" cssClass="objectError" />

			<table class="zebra-striped"
				style="width: 80%; margin: 30px auto auto; border: 1px solid #CCCCCC;">
				<thead>
					<tr>
						<td style="text-align: center; border-bottom: 1px solid #CCCCCC;"
							colspan="4"><strong class="headerStrong">Personal
								Details</strong></td>
					</tr>
				</thead>

				<tr style="background-color: white;">
					<td><strong>Name*</strong></td>
					<td class="span3"><form:input id="firstName" path="firstName"
							type="text" class="required" /> <form:errors path="firstName"
							cssClass="error" /></td>
					<td class="span3"><form:input id="middleName"
							path="middleName" type="text" /></td>
					<td class="span3"><form:input id="lastName" path="lastName"
							type="text" /></td>
				</tr>
				<tr style="background-color: white;">
					<td class="span3"><strong>Email*</strong></td>
					<td class="span3"><form:input path="username" type="text"
							class="required email" /></td>
					<td colspan="2"><form:errors path="username" cssClass="error" /></td>

				</tr>
			</table>

			<table class="zebra-striped"
				style="width: 80%; margin: 30px auto auto; border: 1px solid #CCCCCC;">
				<thead>
					<tr>
						<td style="text-align: center; border-bottom: 1px solid #CCCCCC;"
							colspan="4"><strong class="headerStrong">Address
								Details</strong></td>
					</tr>
				</thead>

				<tr>
					<td class="span3"><strong>Address Line1</strong></td>
					<td class="span3"><form:input path="addressLine1" type="text" /></td>
					<td class="span3"><strong>Address Line2</strong></td>
					<td class="span3"><form:input path="addressLine2" type="text" /></td>
				</tr>
				<tr>
					<td class="span3"><strong>City</strong></td>
					<td class="span3"><form:input path="city" type="text" /></td>
					<td class="span3"><strong>State</strong></td>
					<td class="span3"><form:input path="state" type="text" /></td>
				</tr>
				<tr>
					<td class="span3"><strong>Country</strong></td>
					<td class="span3"><form:input path="country" type="text" /></td>
					<td class="span3"><strong>Zip</strong></td>
					<td class="span3"><form:input path="zipCode" type="text" /></td>
				</tr>

			</table>


			<table id="workExpTable1" class="zebra-striped"
				style="width: 80%; margin: 30px auto auto; border: 1px solid #CCCCCC;">

				<thead>
					<tr>
						<td style="text-align: center; border-bottom: 1px solid #CCCCCC;"
							colspan="5"><strong class="headerStrong">Work
								Experience</strong></td>
					</tr>
				</thead>

				<tr style="background-color: #F9F9F9;" id="workExpLabels1"
					class="clonedInput">
					<td><strong>Title</strong></td>
					<td><strong>Area Of Expertise</strong></td>
					<td><strong>Role Description</strong></td>
					<td><strong>From Date</strong></td>
					<td><strong>To Date</strong></td>

				</tr>
				<c:forEach items="${userProfileDTO.workExperiences}"
					varStatus="stat">
					<tr id="workExpTexts${stat.index}" class="cloneWorkExpTexts">
						<td style="width: 25%"><form:input type="hidden"
								path="workExperiences[${stat.index}].workExperienceId" /> <form:input
								path="workExperiences[${stat.index}].title" class="title"
								type="text" style="width:90%" /></td>
						<td style="width: 25%"><form:input
								path="workExperiences[${stat.index}].areaOfExpertise"
								class="areaOfExperience" type="text" style="width:90%" /></td>
						<td style="width: 25%"><form:input
								path="workExperiences[${stat.index}].roleDescription"
								class="roleDescription" type="text" style="width:90%" /></td>
						<td style="width: 13%"><form:input class="fromDatepicker"
								path="workExperiences[${stat.index}].fromDate" type="text"
								style="width:90%" /></td>
						<td style="width: 12%"><form:input class="toDatepicker"
								path="workExperiences[${stat.index}].toDate" type="text"
								style="width:90%" /></td>
					</tr>
				</c:forEach>

				<tr id="workExpBtnTr">
					<td colspan="2" style="border-top: none"><input
						class="btn primary" id="addMoreWorkExpbtn" type="button"
						value="Add More Work Experience"></td>
				</tr>

			</table>




			<table id="socialNetwork1" class="zebra-striped"
				style="width: 80%; margin: 30px auto auto; border: 1px solid #CCCCCC;">

				<thead>
					<tr>
						<td style="text-align: center; border-bottom: 1px solid #CCCCCC;"
							colspan="5"><strong class="headerStrong">Social
								Network</strong></td>
					</tr>
				</thead>

				<tr style="background-color: #F9F9F9;">

					<td style="width: 35%"><strong>SocialNetwork</strong></td>
					<td style="width: 35%"><strong>SocialNetwork-Id</strong></td>
					<td><a style="height: 25px" class='btn primary invitationbtn'
						href='invitationlist'><span class='spanTitle'>Invite
								Application Colleagues</span></a></td>
				</tr>
				<tr>
					<td><input name="socialSiteName" type="hidden"
						value="LinkedIn" /><strong>LinkedIn</strong></td>
					<td><form:input path="linkedInId" /></td>
					<td><a style="height: 25px" class='btn primary invitationbtn'
						href='invitationlist'><span class='spanTitle'>Invite
								LinkedIn Colleagues</span></a></td>
				</tr>
				<tr>
					<td><input name="socialSiteName" type="hidden"
						value="Facebook" /><strong>Facebook</strong></td>
					<td><form:input path="facebookId" /></td>
					<td><a style="height: 25px" class='btn primary invitationbtn'
						href='invitationlist'><span class='spanTitle'>Invite
								Facebook Colleagues</span></a></td>
				</tr>
				<tr>
					<td><input name="socialSiteName" type="hidden"
						value="Twitter" /><strong>Twitter</strong></td>
					<td><form:input path="twitterId" /></td>
					<td><a style="height: 25px" class='btn primary invitationbtn'
						href='invitationlist'><span class='spanTitle'>Invite
								Twitter Colleagues</span></a></td>
				</tr>
				<tr>
					<td><input name="socialSiteName" type="hidden"
						value="Googleplus" /><strong>Googleplus</strong></td>
					<td><form:input path="googleplusId" /></td>
					<td><a style="height: 25px" class='btn primary invitationbtn'
						href='invitationlist'><span class='spanTitle'>Invite
								Googleplus Colleagues</span></a></td>
				</tr>
				<!-- 					<tr id="socialButtonRow"> -->
				<!-- 						<td style="border-top: none"><input id="addMoreSocialbtn" class="btn primary" -->
				<!-- 							type="button" value="Add More Social Network"></td> -->
				<!-- 					</tr> -->
			</table>

			<div align="center">
				<input style="margin-top: 20px; margin-bottom: 10px" type="submit"
					class="btn primary" value="Save">
			</div>
		</form:form>
	</div>


