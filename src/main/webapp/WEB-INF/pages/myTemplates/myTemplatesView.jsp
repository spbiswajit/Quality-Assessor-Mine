<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>
<div style="margin: 3%">


		<table>
			<tbody><tr>
				<th class="iconwidth"></th>
				<th class="iconwidth"></th>
				<th class="iconwidth"></th>
				<th class="iconwidth"></th>
				<th class="iconwidth"></th>
				<th style="text-align:left;text-transform: capitalize;">My Templates</th>
			</tr>
		</tbody>
		</table>


<div id="domainDivId">
	<c:forEach items="${rootDomainList}" var="elem">
		<table style="margin-top: 15px; width: 100%;">
			<tr type="${domainType}" id="${elem.key}" pid="0">
				<td class="iconWidth"><a
					href="http://en.wikipedia.org/wiki/${elem.title}" target="_blank"
					class="wikiLink"><img src="images/wiki.png" class="wikiLink"></a></td>
				<td class="iconWidth"><img src="images/new.png"
					class="newDomain"></td>
				<td class="iconWidth"><img src="images/edit.png"
					class="editDomain"></td>
				<td class="iconWidth"><img src="images/cross.png"
					class="deleteDomain"></td>
				<td class="titleClass expandDomain"><input type="text"
					value="${elem.title}" class="autoCompleteWiki"
					style="display: none"><span class="spanTitle rootTitle">${elem.title}</span></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</c:forEach>
</div>

</div>