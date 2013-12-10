<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>

<div style="background: white; margin: auto;">
		
		<form id="loginForm" class="box login" name='f'
			action="<c:url value='j_spring_security_check' />" method='POST'>
			<fieldset class="boxBody" style="height: 70%;">
				<label>Email</label> <input id="userId" type="text"
					name='username' tabindex="1" class="required email"> <label>Password</label>
				<input id="passwordId" name='password' type="password"
					tabindex="2" class="required" minlength="6"">
				<c:if test="${not empty error}">
					<div class="error">
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
				</c:if>
			</fieldset>
			<fieldset class="formFooter" style="height: 30%;">
				<div>
					<input type="submit" class="btnLogin" value="Login">
				</div>
				<div
					style="margin: 5px 5px 5px 247px; font-weight: bold; font-size: 14px; font-style: italic">
					<a href="signUp"><font color="#333333"><u>SignUp!</u></font></a>
				</div>
			</fieldset>

		</form>

	</div>