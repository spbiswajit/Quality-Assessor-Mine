function domainSettings() {
var domainId=$(this).attr("id");
	$.colorbox({
		href : 'domainSettings.jsp?domainId=' + domainId,
		open : true,
		iframe : true,
		width : "400px",
		height : "280px",
		opacity : 0.9,
	});

};

function addParentDomain() {
		$.colorbox({
			href : 'addParentDomain.jsp',
			open : true,
			iframe : true,
			width : "400px",
			height : "280px",
			opacity : 0.9,
		});

	};

function showDomainSettings(){
	$('#main').show();
	$('#addDomain').hide();
	$('#editDomain').hide();
}
	
function addDomain(){
	$('#addDomain').show();
	$('#main').hide();
	$('#editDomain').hide();
}

function updateDomain(){
	getDomainInfo(key,parentKey);
	$('#editDomain').show();
	$('#main').hide();
	$('#addDomain').hide();
}

function deleteDomain(){
	confirm("Do you want to delete Domain");
}