<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<title></title>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="commonHeader" />
</head>
<body>

	<tiles:insertAttribute name="main-header" />

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