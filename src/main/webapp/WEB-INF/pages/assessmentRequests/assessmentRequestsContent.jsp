<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>
<div>
	<div class="topDivLabel" style="font-size: 17px;">
		<label class = "topLabel"><strong>Requests For Assessments</strong></label>
	</div>
	<div id='viewIgnoreRequestDiv' align = 'right' style="margin-right: 100px;margin-top:18px;">
		<a class = 'button-default' style='text-align:center;color: white;width:170px' style="width:170px;background:none repeat scroll 0 0 #595959"
			href='assessmentRequests?ignoreInvitation=true'>View ignored
			invitations</a>
	</div>
	<c:if test = "${empty assessmentRequestList}">
		<div style="margin:20px">You have not got any assessment request!!!</div>
	</c:if>
	<div style="padding-left: 20px;width:400px">
		<c:forEach var="assessment" items="${assessmentRequestList}">
			<div style='border-top: 1px solid #ccc; margin-top: 0px; margin-bottom:10px'>
				<div
					style='margin-bottom: 12px; margin-top: 6px; font-weight: bold; font-size: 13px'>
					<div>${assessment.invitationDate}</div>
					<div>${assessment.productName} on
						${assessment.domainName}</div>
				</div>
				<div style='display: inline;'>
					<a style='display: inline; padding: 3px 13px'
						class='button-default'
						href="assessments/${assessment.productTemplateMapId}?invitationId=${assessment.invitationId}">Assess
						Now</a>
				</div>
				<div style='display: inline;'>
					<a style='display: inline; padding: 3px 13px'
						class='button-default'
						href="ignoreInvitation?invitationId=${assessment.invitationId}">Ignore</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>