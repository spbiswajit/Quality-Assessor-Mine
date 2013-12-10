<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Assessments</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/table.css" type="text/css" rel="stylesheet">
<link href="css/button.css" type="text/css" rel="stylesheet">
<link href="css/common.css" type="text/css" rel="stylesheet">
<link type='text/css' rel='stylesheet' href='css/style.css' />
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		if ($("#messageDiv").is(':visible')) {
			setTimeout(function() {
				$("#messageDiv").hide('blind');
			}, 3000);
		}	;
	});
</script>
</head>
<body>
<%-- 	<%@include file="/WEB-INF/pages/header.jsp"%> --%>
<%-- 	<%@include file="/WEB-INF/pages/mainHeader.jsp"%> --%>

<div>
	<div style='color: white;
    text-align: center;height:40px;background:white;margin-top:2%;margin-left:2%;
    text-shadow: none;float:left;color:white;width:7%'><div><a style='text-decoration:none'  href="">Product Domains</a></div></div>
	<div class="background container">
	
		<c:if test="${not empty requestScope.message}">
			<div id="messageDiv" style="padding: 11px 84px;">
				<div
					style="color: windowtext; z-index: -1; padding: 7px; width: 750px; background: OliveDrab">${requestScope.message}</div>
			</div>
		</c:if>
		<table
			style="margin-top: 4%; width: 80%; margin-right: auto; margin-left: 80px; float: left;margin-bottom:20px"
			class="imagetable">
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<td -->
<!-- 						style="font-size: 15px; font-weight: bold; background: #30576E; color: white; text-align: center;" -->
<!-- 						colspan="4">Domain</td> -->

<!-- 				</tr> -->
<!-- 			</thead> -->

			<c:forEach items="${listOfRootDomains}" var="domain"
				varStatus="status">
				<tr>
					<td><span class='spanTitle'>${domain.name}</span></td>
					<td><a class='button-default'
						style="margin: auto; width: 130px; padding: 4px 13px; text-align: center"
						href='assessments/${domain.id}'><span class='spanTitle'>Self Assessment</span></a></td>
					<td><a class='button-default'
						style="margin: auto; width: 130px; padding: 4px 13px; text-align: center"
						href='assessments/${domain.id}/chart'>
						<span class='spanTitle'>View Assessment</span></a></td>
				</tr>
			</c:forEach>
		</table>
		

		<div style="width: 500px; margin-left:80px">
			<a  style="color:#30576E" href="assessmentRequests">Click here to assess others</a>
		</div>
	</div>
	<div style='float:left;color:white'>users</div>
	</div>
	<%@include file="/WEB-INF/pages/common/footer.jsp"%>
</body>
</html>