<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<form:form id="signUpForm" class="box login" style="top: 36%;"
		action="signUp" method="post" commandName="userProfileDTO">
		<fieldset class="boxBody" style="height: 75%">
			<label
				style="margin: 2px 9px 2px 2px; font-size: 12px; position: absolute; right: 0;">*
				fields are required</label> <label class="userform-box-name">First
				Name*</label>
			<form:input path="firstName" type="text" value="" class="required" />
			<form:errors path="firstName" cssClass="error" />

			<label class="userform-box-name">Last Name*</label>
			<form:input path="lastName" type="text" value="" class="required" />

			<form:errors path="lastName" cssClass="error" />

			<label class="userform-box-name">Email*</label>
			<form:input path="username" type="text" value="" size="30"
				maxlength="2048" class="required email" />
			<form:errors path="username" cssClass="error" />

			<label class="userform-box-name">Password*</label>
			<form:input path="password" type="password" value="" size="30"
				maxlength="2048" class="required" minlength="6" />
			<form:errors path="password" cssClass="error" />

			<label class="userform-box-name">Confirm Password*</label>
			<form:input id="confirmPassword" path="confirmPassword"
				type="password" value="" size="30" maxlength="2048"
				class="required confirmPassword" minlength="6" />
			<form:errors path="confirmPassword" cssClass="error" />
			<form:errors path="" cssClass="error" />
		</fieldset>
		<fieldset class="formFooter" style="height: 25%; padding: 20px 26px;">
			<input type="submit" class="btnLogin" value="Sign Up">
		</fieldset>
	</form:form>


</div>
