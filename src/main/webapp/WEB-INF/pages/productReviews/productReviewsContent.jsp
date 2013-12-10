<%@include file="/WEB-INF/pages/common/common-taglibs.jsp"%>
<div class = "topDivLabel" style="font-size: 17px; ">
		<label class = "topLabel"><strong>Product Reviews</strong></label>
	</div>
<div style="padding-left: 20px;width:450px;margin-bottom:50px">
<table>
	<c:forEach items="${list}" var="item">

		<div style='border-top: 1px solid #ccc; margin-top: 15px;'>
			<div
				style='margin-bottom: 12px; margin-top: 6px; font-weight: bold; font-size: 13px'>
				<div>${item.product.productName} on ${item.domain.domainName}</div>
			</div>
			<div style='display: inline;margin-right:20px'>
				<a href="assessments/${item.productTemplateMapId}"
					class="button-default" style="display: inline; padding: 3px 13px">Self
					Review</a>
			</div>
			<div style='display: inline;margin-right:20px'>
				<a href="assessments/${item.productTemplateMapId}/invitation" class="button-default"
					style="display: inline; padding: 3px 13px">Send Invitation</a>
			</div>
			<div style='display: inline;margin-right:20px'>
				<a href="assessments/${item.productTemplateMapId}/chart"
					class="button-default" style="display: inline; padding: 3px 13px">View
					Assessment</a>
			</div>
		</div>

	</c:forEach>
</table>
</div>