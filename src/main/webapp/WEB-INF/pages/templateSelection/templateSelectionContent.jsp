<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>
<c:if test="${not empty productTemplateMapList}">
	<div class="topDivLabel" style="font-size: 17px;">
		<label class="topLabel"><strong>Already Mapped
				Templates with ${requestScope.productName}</strong></label>
	</div>
	<div
		style='margin: 30px; margin-top: 15px; width: 470px; max-height: 250px; overflow: auto'>
		<c:forEach var="item" items="${productTemplateMapList}">
			<div style="border-top: 1px solid #ccc; margin-bottom: 10px">
				<div
					style='margin-bottom: 12px; margin-top: 6px; font-weight: bold; font-size: 13px'>
					<div>${item.domain.domainName}</div>
				</div>
				<div style='display: inline; margin-right: 20px'>
					<a href="assessments/${item.productTemplateMapId}"
						class="button-default" style="display: inline; padding: 3px 13px">Self
						Review</a>
				</div>
				<div style='display: inline; margin-right: 20px'>
					<a href="assessments/${item.productTemplateMapId}/invitation" class="button-default"
						style="display: inline; padding: 3px 13px">Send Invitation</a>
				</div>
				<div style='display: inline; margin-right: 20px'>
					<a href="assessments/${item.productTemplateMapId}/chart"
						class="button-default" style="display: inline; padding: 3px 13px">View
						Assessment</a>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>

<div class="topDivLabel" style="font-size: 17px;">
	<label class="topLabel"><strong>Map Template with
			${requestScope.productName}</strong></label>
</div>

<div style="margin: 20px">
	<form:form method="post" action="templateSelectionView"
		onsubmit="return checkChooseTemplate()" commandName="templateSelectionForm">
		<div style="margin: 10px">
			<div style="display: inline-block; width: 160px">Product</div>
			<div style="display: inline">
				<form:input readonly="true" path="productName" />
				<form:hidden path="productId" />
			</div>
		</div>
		<div style="margin: 10px">
			<div style="display: inline-block; width: 160px">Choose
				Template</div>


			<div style="display: inline">
				<input id="exist-radio-button" type="radio" name="chooseTemplate"
					value="existing" checked="checked"> <label>Existing</label>
			</div>

			<div style="display: inline">
				<input id="new_radio_button" type="radio" name="chooseTemplate"
					value="new"> <label>New</label>
			</div>
		</div>
		<div id="existTempId" style="margin: 10px">
			<div style="display: inline-block; width: 160px">
				<label>Type Template Name</label>
			</div>
			<div id="selectTemplateSecId"
				style="display: inline; font-size: 0.9em;" class="ui-widget">
				<form:input path="domainName" style="width:200px" id="domainName" />
				<form:errors path="domainName" style="width:200px;color:red" id="domainName" />
				<form:hidden path="domainId" id="domainId" />

			</div>

		</div>

		<div class="newTemp" style="margin: 10px; display: none">
			<div style="display: inline-block; width: 160px"
				id="showTemplateTypes">
				<label>Select type of template </label>
			</div>
			<div style="display: inline">
				<select>
					<option value="1">Product Template</option>
					<option value="2">Environment Template</option>
					<option value="3">Skill Template</option>
					<option value="4">Practice Template</option>
				</select>
			</div>
		</div>

		<div style="margin: 10px">
			<!-- 		<div  class = "newTemp"  style="display:inline;">Create New Template -->
			<!-- 		</div> -->
			<div id="newTempId" class="newTemp" style="display: none">

				<table>
					<tr pid="0" id="new" type="product" class="currentSelectedRow">
						<td class="iconWidth"><a href="http://en.wikipedia.org/wiki/"
							target="_blank" class="wikiLink" style="display: none"><img
								src="images/wiki.png" class="wikiLink"></a></td>
						<td class="iconWidth"><img src="images/new.png"
							class="newDomain" style="display: none"></td>
						<td class="iconWidth"><img src="images/save.png"
							class="saveDomain"></td>
<!-- 						<td class="iconWidth"><img src="images/cross.png" -->
<!-- 							class="deleteDomain"></td> -->
						<td class="titleClass"><span
							class="spanTitle rootTitle expandDomain"></span><input
							type="text" size="30" class="autoCompleteWiki"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>

		</div>
		<div style="margin-top:20px;margin-bottom:30px">
			<div style="display: inline">
				<input style="display: inline; padding: 3px 13px"
					class='button-default' type="submit" name="selfReview"
					value="Self Review">
			</div>
			<div style="display: inline">
				<input style="display: inline; padding: 3px 13px"
					class='button-default' type="submit" name="sendInvitation"
					value="Send Invitation">
			</div>
			<div style="display: inline">
				<input style="display: inline; padding: 3px 13px"
					class='button-default' type="submit" name="save&ReviewLater"
					value="Save & Review Later">
			</div>
		</div>
	</form:form>
</div>
<div style="display:none;margin-top:25px;margin-left:15px;margin-right:15px" id="existingDomainDiv">
		</div>