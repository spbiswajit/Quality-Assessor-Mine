<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>
<div style="font-size: 17px;margin-left:80px;margin-top:30px">
		<label class="topLabel"><strong>Product Reviews for ${requestScope.productName} on ${templateDTO.title}</strong></label>
	</div>
<div style = "margin-left:80px;margin-top:20px">
<input id="productId" type = "hidden" value = "${requestScope.productId}"> 
<table id="excelId" class="imagetable"
		style="margin-top: 15px; border-color: #999999; border-style: solid;">
	<tr><td>${templateDTO.title}</td><td colspan = "3"></td></tr>
	<c:forEach var="dto" items="${templateDTO.children}" varStatus="status">
				<tr><td style="border-top: none; border-bottom: none;"></td><td>${dto.title}</td><td></td>
				<td class = "p${status.index}" id="${dto.key}"  weightage = "${dto.weightage}" assessmentid="${dto.assessmentId}" score="${dto.score}">
					<c:forEach begin="1" end="${dto.score}">
						<c:choose>
							<c:when test="${empty dto.children}">
								<img   style='border:none' src="/qualityassessor/images/yellowstar.png" class="toggle"></img>
							</c:when>
							<c:otherwise>
								<img   style='border:none' src="/qualityassessor/images/greenstar.png"></img>
							</c:otherwise>
						</c:choose>
					</c:forEach> 
					<c:forEach begin="${dto.score}" end="4">
						<c:choose>
							<c:when test="${empty dto.children}">
								<img   style='border:none' src="/qualityassessor/images/whitestar.gif" class="toggle"></img>
							</c:when>
							<c:otherwise>
								<img   style='border:none' src="/qualityassessor/images/whitestar.gif"></img>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</tr>
				<c:forEach var="dto1" items="${dto.children}">
					<tr><td style="border-top: none; border-bottom: none;"></td><td></td><td>${dto1.title}</td>
					<td parentId = "p${status.index}" id="${dto1.key}" weightage = "${dto1.weightage}"  assessmentid="${dto1.assessmentId}" score="${dto1.score}">
					<c:forEach begin="1" end="${dto1.score}">
						<img  style='margin:0px' src="/qualityassessor/images/yellowstar.png" class="toggle"></img>
					</c:forEach> 
					<c:forEach begin="${dto1.score}" end="4">
						<img   style='margin:0px' src="/qualityassessor/images/whitestar.gif" class="toggle"></img>
					</c:forEach>
				</tr>
				</c:forEach>
		
	</c:forEach>
	</table>
</div>


<!-- <table id="excelId" class="imagetable" -->
<!-- 	style="margin-top: 15px; border-color: #999999; border-style: solid;"> -->
<!-- 	<tbody> -->
<!-- 		<tr> -->
<!-- 			<td style="border-bottom: none">Software testing</td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 		</tr> -->
<!-- 		<tr childcount="0"> -->
<!-- 			<td style="border-top: none; border-bottom: none;"></td> -->
<!-- 			<td>Heel (professional wrestling)</td> -->
<!-- 			<td></td> -->
<!-- 			<td id="79" assessmentid="26" score="3"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/whitestar.png" class="toggle"><img -->
<!-- 				src="/images/whitestar.png" class="toggle"> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr childcount="0"> -->
<!-- 			<td style="border-top: none; border-bottom: none;"></td> -->
<!-- 			<td>IUPAC</td> -->
<!-- 			<td></td> -->
<!-- 			<td id="62" assessmentid="27"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/whitestar.png" class="toggle"><img -->
<!-- 				src="/images/whitestar.png" class="toggle"><img -->
<!-- 				src="/images/whitestar.png" class="toggle"> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr childcount="0"> -->
<!-- 			<td style="border-top: none; border-bottom: none;"></td> -->
<!-- 			<td>Stress testing</td> -->
<!-- 			<td></td> -->
<!-- 			<td id="13" assessmentid="28"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/yellowstar.png" class="toggle"><img -->
<!-- 				src="/images/whitestar.png" class="toggle"> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</tbody> -->
<!-- </table> -->