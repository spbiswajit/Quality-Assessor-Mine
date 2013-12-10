function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}

var key;
var parentKey;
var remainingWeightageForNewDomain;
$(function() {
	key = getURLParameter("key");
	if (key == "null") {
		$('#editWeightageDiv').hide();
		$('#backButtonId').hide();
		$('#newWeightageDiv').hide();
		showAddDomainView();
		parentKey = "null";
	} else {
		parentKey = getURLParameter("parentKey");
		var title = getURLParameter("title");
		var weightage;
		$('#editTitleId').val(title);
		if (parentKey == 'null') {
			$('#editWeightageDiv').hide();
		} else {
			weightage = getURLParameter("weightage");
			$('#editWeightageId').val(weightage);
		}
		$('#selectedDomainInfo').html(
				"<strong>Selected Domain : " + title + "</strong>");
	}
});

function getRemainingWeightage() {
	var url = 'remainingWeightage?domainKey=' + key;
	$.ajax({
		type : 'GET',
		url : url,
		success : function(data) {
			$('#remainingWeightageId').text(
					"(Remaining Weighing : " + data + ")");
			remainingWeightageForNewDomain = data;
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// showErrorMessage(jqXHR.responseText, "450", "300");
		},
		dataType : 'text'
	});

}

function showDomainSettings() {
	$('#main').show();
	$('#addDomain').hide();
	$('#editDomain').hide();
}

function showAddDomainView() {
	if ((key != "null") || (parentKey != null)) {
		getRemainingWeightage();
	}
	$('#addDomain').show();
	$('#main').hide();
	$('#editDomain').hide();
	$('.wikiLinkUpdate').attr("href", "");
	$('.wikiLinkUpdate').html("");
}

function showUpdateDomainView() {
	$('#editDomain').show();
	$('#main').hide();
	$('#addDomain').hide();
	var title = $('#editTitleId').val();
	title = title.trim();
	title = title.replace(" ", "_");
	var wikiLink = "http://en.wikipedia.org/wiki/" + title;
	$('.wikiLinkUpdate').attr("href", wikiLink);
	$('.wikiLinkUpdate').html(wikiLink);
}

function validateTitleField(title) {
	var res = {
		success : true,
		errorMsg : ""
	};
	if (title == "") {
		res.errorMsg = "Domain name can't be blank";
		res.success = false;
	}
	return res;
}

function validateWeightageField(weightage, weightageLimit) {
	var res = {
		success : true,
		errorMsg : ""
	};
	if (weightage == "") {
		res.errorMsg = "Weighing can't be blank";
		res.success = false;
	} else {
		var value = weightage.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
		var intRegex = /^\d+$/;
		if (!intRegex.test(value)) {
			res.errorMsg = "Weighing must be numeric";
			res.success = false;
			return res;
		}
	}
	if (parseInt(weightage) > parseInt(weightageLimit)) {
		res.errorMsg = "Weighing can't be more than remaining weighing";
		res.success = false;
	}
	return res;
}

function showErrorMessage(errorId, errorMsg) {
	$('#' + errorId).html("<font color='red'>" + errorMsg + "</font>");
}

function showSuccessMessage(elmId, msg) {
	$('#' + elmId).html("<font color='green'>" + msg + "</font>");
}

function hideMessage(errorId) {
	$('#' + errorId).html("");
}

function deleteDomain() {
	var flag = confirm("Do you want to delete Domain");
	if (flag == true) {
		var data = {
			key : key,
			parentKey : parentKey,
		};
		var url = 'deleteDomain';
		$.ajax({
			type : 'POST',
			url : url,
			data : data,
			success : function(data) {
				if (data == 'true') {
					parent.deleteNode();
					parent.$.fn.colorbox.close();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// showErrorMessage(jqXHR.responseText, "450", "300");
			},
			dataType : 'text'
		});
	}
}

function updateDomain() {
	hideMessage("editMsg");
	hideMessage("editTitleErrorId");
	hideMessage("editWeightageErrorId");

	var success = true;

	var title = $('#editTitleId').val();
	var weightage = "null";
	res = validateTitleField(title);

	if (res.success == false) {
		showErrorMessage("editTitleErrorId", res.errorMsg)
	}

	success = res.success;
	if (parentKey != 'null') {
		var weightage = $('#editWeightageId').val();
		res = validateWeightageField(weightage, 100);

		if (res.success == false) {
			showErrorMessage("editWeightageErrorId", res.errorMsg)
		}
		if (success == true) {
			success = res.success;
		}
	}

	if (success == true) {
		var data = {
			key : key,
			parentKey : parentKey,
			title : title,
			weightage : weightage
		};
		var url = 'updateDomain';
		$.ajax({
			type : 'POST',
			url : url,
			data : data,
			success : function(data) {
				if (data == "true") {
					parent.updateNode(title, weightage);
					hideMessage("editTitleErrorId");
					hideMessage("editWeightageErrorId");
					// showSuccessMessage("editMsg", "Updated Successfully");
					showDomainSettings();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			},
			dataType : 'text'
		});
	}
}

function saveDomain() {
	hideMessage("addMsg");
	hideMessage("newTitleErrorId");
	hideMessage("newWeightageErrorId");
	var weightage = "null";
	var success = true;
	var title = $('#newTitleId').val();

	res = validateTitleField(title);

	if (res.success == false) {
		showErrorMessage("newTitleErrorId", res.errorMsg)
	}

	success = res.success;
	if (key != 'null') {
		var weightage = $('#newWeightageId').val();
		var weightage = $('#newWeightageId').val();
		res = validateWeightageField(weightage, remainingWeightageForNewDomain);

		if (res.success == false) {
			showErrorMessage("newWeightageErrorId", res.errorMsg)
		}
		if (success == true) {
			success = res.success;
		}
	}

	if (success == true) {

		var data = {
			parentKey : key,
			title : title,
			weightage : weightage
		};
		var url = 'saveDomain';
		$.ajax({
			type : 'POST',
			url : url,
			data : data,
			success : function(data) {
				if (data != null) {
					if (key != 'null') {
						parent.addNode(data);
					} else {
						parent.addRootNode(data);
						parent.$.fn.colorbox.close();
					}
					hideMessage("newTitleErrorId");
					hideMessage("newWeightageErrorId");
					// showSuccessMessage("addMsg",
					// "Domain Created Successfully");
					$('#newTitleId').val("");
					$('#newWeightageId').val("");
					// $('#backButtonId').show();
					showDomainSettings();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// showErrorMessage(jqXHR.responseText, "450", "300");
			},
			dataType : 'json'
		});

	}
}
var showSubDomain = false;
function showSubDomainsWeightage() {
	if (showSubDomain == true) {
		// alert('ag');
		showSubDomain = false;
		$('#subDomainWeightageId').remove();
		$('#subDomainWeightageLink').html("Show Sub-Domain Weightage");
		return;
	}
	showSubDomain = true;
	var url = 'getSubDomains?key=' + key;
	$.ajax({
		type : 'GET',
		url : url,

		success : function(data) {
			var div = $("<div id='subDomainWeightageId'>");
			var table = $("<table class='imagetable'>");
			var tr = $("<tr><th>Domain Name</th><th>Weightage</th></tr>");
			table.append(tr);
			for (i = 0; i < data.length; i++) {
				var tr = $("<tr><td>" + data[i].name + "</td><td>"
						+ data[i].weightage + "</td></tr>");
				table.append(tr);
			}
			div.prepend(table);
			$('#subDomainWeightageDiv').append(div);
			$('#subDomainWeightageLink').html("Hide Sub-Domain Weightage");

		},
		error : function(jqXHR, textStatus, errorThrown) {
		},
		dataType : 'json'
	});
}

function checkIfDomainAlreadyExist(title) {
	var url = 'getDomain?name=' + title;
	$.ajax({
		type : 'GET',
		url : url,
		success : function(data) {
			for (i = 0; i < data.length; i++) {

				var radio = $("<input  type='radio' value=" + i + "><label style='font-family: tahoma,arial,helvetica;font-size: 13pt; white-space: nowrap;'>"
						+ data[i].name + "</lable></input><span id='tree'></span>");
				$('#existedDomainDiv').append(radio);
				getCompleteTree(data[i].id, data[i].name);
			}
		}
	});
}

function updateWikiLink(title) {
	var wikiTitle = title.replace(" ", "_");
	$('.wikiLinkUpdate').attr("href",
			"http://en.wikipedia.org/wiki/" + wikiTitle);
	$('.wikiLinkUpdate').html("http://en.wikipedia.org/wiki/" + wikiTitle);
	checkIfDomainAlreadyExist(title);
}

function getCompleteTree(key, title) {
	$("#tree").dynatree({
		initAjax : {
			url : "domains",
			data : {
				key : key, // Optional arguments to append to the url
				mode : "all"
			}
		},
		onLazyRead : function(node) {
			node.appendAjax({
				url : "domains",
				data : {
					"key" : node.data.key, // Optional url arguments
					"mode" : "all"
				}
			});
		},
		 classNames: {nodeIcon:null},

	// onClick : function(node, event) {
	// if (node.getEventTargetType(event) == "title") {
	// var parentKey = node.data.parentKey;
	// var key = node.data.key;
	// var title = node.data.title;
	// var weightage = node.data.weightage;
	// $.colorbox({
	// href : 'domainSettings?key=' + key + '&parentKey='
	// + parentKey + '&title=' + title
	// + '&weightage=' + weightage,
	// open : true,
	// iframe : true,
	// width : "600px",
	// height : "380px",
	// opacity : 0.9,
	//
	// });
	//
	// }
	// }

	});
//	var currentRootNode = $("#tree").dynatree("getRoot");
//	var rootnode = {
//		parentKey : key,
//		title : title,
//		children : currentRootNode.getChildren(),
//	};
//	currentRootNode.removeChildren();
//	currentRootNode.addChild(rootnode);

}
