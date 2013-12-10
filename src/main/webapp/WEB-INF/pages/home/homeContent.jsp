<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>

<c:if test="${not empty requestScope.message}">
	<div id="messageDiv" style="padding: 11px 7px;">
		<div
			style="color: windowtext; z-index: -1; padding: 7px; width: 750px; background: OliveDrab">${requestScope.message}</div>
	</div>
</c:if>


<p style='margin-left: 50px'>
	<strong>Welcome ${sessionScope.userCompleteName}</strong>
</p>


