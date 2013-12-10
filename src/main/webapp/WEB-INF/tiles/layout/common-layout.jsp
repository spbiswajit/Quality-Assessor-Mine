<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title></title>
<tiles:insertAttribute name="commonHeader" />
<tiles:insertAttribute name="header" />

</head>
<body>

	<tiles:insertAttribute name="main-header" />

	<div id="tabName" style="display:none"><tiles:getAsString  name="tabName" />
	</div>

	<div class="topNavigation">
		<tiles:insertAttribute name="topNavigation" />
	</div>

	<div class="body">
		<div class="leftNavigation">
			<tiles:insertAttribute name="leftNavigation" />
		</div>

		<div class="content">
			<tiles:insertAttribute name="content" />
		</div>

	</div>

	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>


</body>
</html>