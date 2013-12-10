
<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>
	<div class="background container"
		style="background: white; width: 70%;">
<%-- 		<%@include file="/WEB-INF/pages/header.jsp"%> --%>
		<div style="margin-left:50px">
<!-- 		<div style="font-size:18px;margin-bottom:30px;margin-top:30px"><label><strong>Select Domain to Assess</strong></label></div> -->
<!-- 		<table style='width: 68%; margin-left: 10%'> -->
<!-- 			<tr> -->
<!-- 				<td style='width: 250px;'><b>Application Domains</b><br /> <select -->
<!-- 				size="10"	 multiple="multiple" id='domainList' style="width: 100%;"> -->
<%-- 						<c:forEach var='domain' items="${domainList}"> --%>
<%-- 							<option value="${domain.id}">${domain.name}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<!-- 				<td style='width: 50px; text-align: center; vertical-align: middle;'> -->
<!-- 					<input type='button' id='domainRight' value='  >  ' /> <br /> <input -->
<%-- 					type='button' id='domainLeft' value='  <  ' /> --%>
<!-- 				</td> -->
<!-- 				<td style='width: 250px;'><b>Selected Domains</b><br /> <select -->
<!-- 				size='10'	multiple="multiple" id='selectedDomainList' style="width: 100%;"> -->

<!-- 				</select></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
		<div style="font-size:18px;margin-bottom:30px;margin-top:30px"><label><strong>Select Users</strong></label></div>
		<table style='width: 68%; margin-left: 10%;'>
			<tr>
				<td style='width: 250px;'><b>Application Users</b><br /> <select
				size='10'	style="width: 100%" multiple="multiple" id='userList'>
						<c:forEach var='user' items="${userList}">
							<option value="${user.userId}">${user.firstName}
								${user.lastName}</option>
						</c:forEach>
				</select></td>
				<td style='width: 50px; text-align: center; vertical-align: middle;'>
					<input type='button' id='userRight' value='  >  ' /> <br /> <input
					type='button' id='userLeft' value='  <  ' />
				</td>
				<td style='width: 250px;'><b>Selected Users</b><br /> <select
				 size='10'	style="width: 100%" multiple="multiple" id='selectedUserList'>

				</select></td>
			</tr>
		</table>



		<div style="width: 170px; text-align: center; right: 50%; margin-top: 40px;margin-left:36%">
			<a  class='button-default' href="javascript:sendInvitation();">Request a Review</a>
		</div></div>
	</div>
