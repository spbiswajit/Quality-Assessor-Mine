//fetching url parameter 

function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}

var requestedUserId = "null";

function makeRootRow(domain){
	var row = "<tr pid='0' id="
		+ domain.key+" type='"+domain.type
		+ "'><td class='iconWidth'><a class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/"
		+ domain.title
		+ "'><img class='wikiLink' src='images/wiki.png'/></a></td>"
		+ "<td class='iconWidth'><img class='newDomain' src='images/new.png'/></td>"
		+ "<td class='iconWidth'><img class='editDomain' src='images/edit.png'/></td>"
		+ "<td class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td>"
		+ "<td class='titleClass expandDomain' class='addBorder' ><input style='display:none' "
		+ "class='autoCompleteWiki' type='text' value='"
		+ domain.title + "'><span class='spanTitle rootTitle'>"+domain.title+"</span>"
		+ "</td><td></td><td></td><td></td></tr>";
	return row;
}


//Make new table for each root domain

function makeTable(domain){
	var table = "";
	table += "<table style='margin-top:15px;width:100%;'  id='tableId"
			+ i + "'>";
	table += makeRootRow(domain);
	$('#domainDivId').append(table);
	return table;
}

$(function() {
	
//	var domainType = getURLParameter('type');
	
	
//Show watermark on search text
	
	//$("#searchDomain").watermark("Search Domain");
	
	
//Get Root Domains
	
//	$
//			.ajax({
//				type : 'GET',
//				url : 'domains?key=0&domainType='+domainType,
//				success : function(data) {
//					data = JSON.parse(data);
//					for (i = 0; i < data.length; i++) {
//						makeTable(data[i]);
//					}
//				},
//				error : function(jqXHR, textStatus, errorThrown) {
//				},
//				dataType : 'text'
//			});

});

//Get Domain child tree

function showCompleteDomainTree(rootKey, table) {
	$
			.ajax({
				type : 'GET',
				url : 'domainHierarchy?key=' + rootKey + '&requestedUserId='
						+ requestedUserId+ '&fetchAssessment=false',
				success : function(data) {
					var ul = "";

					data = JSON.parse(data);

					children = data.children;

					// Second Level
					if (children != null) {

						var arr = children;
						for (i = 0; i < arr.length; i++) {
							obj = arr[i];

							children = obj.children;

							// Third Level
							if (children != null) {

							
								ul += "<tr pid='"
										+ rootKey
										+ "' id='"
										+ obj.key
										+ "' "
										+ "><td colspan='5' style='border-top:none;border-bottom:none;'></td><td class='iconWidth'><a class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/"
										+ obj.title
										+ "'><img class='wikiLink' src='images/wiki.png'/></a></td><td class='iconWidth'><img class='newDomain' src='images/new.png'/></td>" +
												"<td class='iconWidth'><img class='editDomain' src='images/edit.png'/></td>"
										+ "<td class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td><td class='titleClass'><span class='spanTitle'>"
										+ obj.title
										+ "</span>"
										+ "<input style='display:none'  class='autoCompleteWiki' type='text' value='"
										+ obj.title
										+ "'></td><td><input  readonly='readonly' class='inputWeightage addBorder' type='text' value="
										+ obj.weightage
										+ "></td><td></td><td score="
										+ obj.score + " id=p" + i + ">"
										+ "</td></tr>";
								
								var arr1 = children;
								for (j = 0; j < arr1.length; j++) {
									obj1 = arr1[j];

									score = obj1.score;
									weightage = obj1.weightage;
									key = obj1.key;

									ul += "<tr pid='"
											+ obj.key
											+ "' id='"
											+ obj1.key
											+ "'><td colspan='11' style='border-top:none;border-bottom:none;'></td><td class='iconWidth'><a class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/"
											+ obj1.title
											+ "'><img class='wikiLink' src='images/wiki.png'/></a></td><td class='iconWidth'><img class='editDomain' src='images/edit.png'/></td><td class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td><td class='titleClass'  i="
											+ i
											+ " j="
											+ j
											+ "><span class='spanTitle'>"
											+ obj1.title
											+ "</span><input style='display:none' class='autoCompleteWiki' type='text' value='"
											+ obj1.title
											+ "'></td><td><input  readonly='readonly' class='addBorder inputWeightage' type='text' value="
											+ obj1.weightage + "></td></tr>";

								}
							
							} else {

								ul += "<tr pid='"
										+ rootKey
										+ "' id='"
										+ obj.key
										+ "' childCount=0><td colspan='5' style='border-top:none;border-bottom:none;'></td><td class='iconWidth'><a class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/"
										+ obj.title
										+ "'><img class='wikiLink' src='images/wiki.png'/></a></td><td class='iconWidth'><img class='newDomain' src='images/new.png'/></td><td class='iconWidth'><img class='editDomain' src='images/edit.png'/></td>"
										+ "<td class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td><td class='titleClass'><span class='spanTitle'>"
										+ obj.title
										+ "</span><input style='display:none' class='autoCompleteWiki' type='text' value='"
										+ obj.title
										+ "'></td><td><input readonly='readonly'  class='inputWeightage addBorder'  type='text' value="
										+ obj.weightage
										+ "></td><td></td><td assessmentId="
										+ obj.assessmentId + " id=" + obj.key
										+ ">" + "</td></tr>";
							}

						}

					}
					// ul += '</table>'
					table.append(ul);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					// showErrorMessage(jqXHR.responseText, "450", "300");
				},
				dataType : 'text'
			});
}

$(function() {

//Binding edit functionality on edit button click
	
	$(".editDomain").live('click', function() {

		if (checkIfOperationPending() == true) {
			alert("Please save or close already open domain");
			return;
		}

		var row = $(this).closest('tr');
		row.addClass("currentSelectedRow");
		var key = row.attr("id");
		var data = {
			key : key
		};
		var currentObj = this;
		$.ajax({
			type : 'POST',
			url : 'hasUpdateOrDeletePermission',
			data : data,
			success : function(data) {
				if (data.success == false) {
					alert(data.message);
					return;
				} else {
					var titleInput = row.find('.autoCompleteWiki');
					titleInput.show();
					var spanTitle = row.find('.spanTitle');
					spanTitle.hide();
					var weightageInput = row.find('.inputWeightage');
					weightageInput.removeClass("addBorder");
					weightageInput.attr('readonly', false);
					$(currentObj).attr("src", "images/save.png");
					$(currentObj).parent().prev().children().hide();
					$(currentObj).removeClass("editDomain");
					$(currentObj).addClass("saveDomain");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			},
			dataType : 'json'
		});

	});

	
//Binding save functionality on save button click
	
	$(".saveDomain")
			.live(
					'click',
					function() {
						var row = $(this).closest('tr');
						var titleInput = row.find('.autoCompleteWiki');
						var title = titleInput.val();
						var currentObj = this;
						var weightageInput = row.find('.inputWeightage');
						var weightage = weightageInput.val();
						if (weightage == undefined) {
							weightage = '100';
						}
						
						if(title==''){
							alert("Domain name can't be blank");
							return;
						}
						if(weightage==''){
							alert("Weighing can't be blank and must be numeric");
							return;
						}
						
						var key = row.attr("id");
						if (key == 'new') {
							key = '0';
						}
						var parentKey = row.attr("pid");

						var table = $(this).closest('table');
						
						var rootRow = table.find('tr:first');
						
						var domainType = rootRow.attr('type');
						
						var rows = table.find("tr[pid='" + parentKey + "']");
						sum = 0;

						rows.each(function(index) {
							sum = sum
									+ parseInt($(this).find('.inputWeightage')
											.val());
						});

						if (sum > 100) {
							alert("Total sum of subdomain's Weighing under a domain can't be greater than 100");
							return;
						}
					
						if((row.attr('id')=="new")&&(row.find('.newDomain').length!=0)){
							checkIfDomainAlreadyExist(title, row, parentKey, weightage, domainType, currentObj);
						}
						else{
							
							saveDomain(key, parentKey, title, weightage, domainType, row,
								currentObj);
						}
					});


//Binding expand functionality on expand button click
	
	$(".expandDomain").live('click', function() {
		key = $(this).closest('tr').attr("id");
		var table = $(this).closest('table');
		$(this).append("<img id='loadingImg' style='width:20px' src='images/demo_wait.gif' />");
		showCompleteDomainTree(key, table);		
		$(this).attr("src", "images/collapse.png");
		$(this).removeClass("expandDomain");
		$(this).addClass("collapseDomain");
		$(this).find("#loadingImg").remove();
		
		
		

		
	});

//Binding collapse functionality on collapse button click
	
	$(".collapseDomain").live('click', function() {
		var table = $(this).closest('table');
		table.find("tr:gt(0)").remove();
		$(this).attr("src", "images/expand.png");
		$(this).removeClass("collapseDomain");
		$(this).addClass("expandDomain");

	});

//Binding adding new domain functionality on new button click
	
	$(".newDomain")
			.live(
					'click',
					function() {
						
						if (checkIfOperationPending() == true) {
							alert("Please save or close already open domain");
							return;
						}
						
						var row = $(this).closest('tr');
						var cell = $(this).closest('td');
						var ppid = row.attr("pid");
						var pid = row.attr("id");

						var currentObj = this;
						var data = {
							key : pid
						};
						$
								.ajax({
									type : 'POST',
									url : 'hasUpdateOrDeletePermission',
									data : data,
									success : function(data) {
										if (data.success == false) {
											alert(data.message);
											return;
										} else {
											if (ppid == '0') {
												expandDomain = row
														.find('.expandDomain');
												if (expandDomain.length > 0) { // if
																				// domain
																				// not
													// expanded then
													// first expand it
													var table = $(currentObj)
															.closest('table');
													showCompleteDomainTree(pid,
															table);
													expandDomain
															.attr("src",
																	"images/collapse.png");
													expandDomain
															.removeClass("expandDomain");
													expandDomain
															.addClass("collapseDomain");
												}
											}
											var cell = row.find('td').first();
									
											var colspan = cell.attr(
													"colspan");
											if (colspan == 5) {
												var tr = "<tr class='currentSelectedRow' id='new' pid='"
														+ pid
														+ "'><td class='iconWidth' colspan='11'></td><td><a style='display:none' class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/"
														+ "'><img class='wikiLink' src='images/wiki.png'/></a></td>"
														+ "<td class='iconWidth'><img class='saveDomain' src='images/save.png'></td>"
														+ "<td  class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td>"
														+ "<td class='titleClass'><span class='spanTitle'></span><input class='autoCompleteWiki' type='text'></td>"
														+ "<td><input class='inputWeightage' type='text'></td></tr>";
												row.after(tr);
											} else {
												var tr = "<tr class='currentSelectedRow' id='new' pid='"
														+ pid
														+ "'><td colspan='5'></td><td class='iconWidth'><a style='display:none' class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/"
														+ "'><img class='wikiLink' src='images/wiki.png'/></a></td><td  class='iconWidth'><img style='display:none'  class='newDomain' src='images/new.png'/></td>"
														+ "<td class='iconWidth'><img class='saveDomain' src='images/save.png'></td>"
														+ "<td  class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td>"
														+ "<td class='titleClass'><span class='spanTitle'></span><input class='autoCompleteWiki' type='text'></td>"
														+ "<td><input class='inputWeightage' type='text'></td></tr>";
												row.after(tr);
											}
										}
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										alert(jqXHR.responseText);
									},
									dataType : 'json'
								});

					});

	
//Binding delete functionality on delete button click	
	
	$(".deleteDomain").live('click', function() {

		var row = $(this).closest('tr');
		var table = $(this).closest('table');
		var key = row.attr("id");
		var data = {
			key : key
		};
		var parentKey = row.attr("pid");
		if (key != 'new') {
			$.ajax({
				type : 'POST',
				url : 'hasUpdateOrDeletePermission',
				data : data,
				success : function(data) {
					if (data.success == false) {
						alert(data.message);
						return;
					}
					if (!confirm('Are you sure you want to delete domain?')) {
						return;
					}
					deleteDomain(key, parentKey, table, row);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				},
				dataType : 'json'
			});
		} else {
			if (parentKey != '0') {
				row.remove();
				table.find("tr[pid='" + key + "']").remove();
			} else {
				table.remove();
				row.removeClass("currentSelectedRow");
			}

		}

	});

	
//Applying validaton check on input weightage like user can only enter numeric value
	
	$(".inputWeightage").live(
			'keydown',
			function(evt) {
				{
					var charCode = evt.keyCode;

					if ((charCode > 47 && charCode < 58)
							|| (charCode > 95 && charCode < 106)
							|| (charCode == 8) || (charCode == 9)
							|| (charCode == 12) || (charCode == 27)
							|| (charCode == 37) || (charCode == 39)
							|| (charCode == 46)) {
						return true;
					}

					return false;
				}
			});

	

});

//function for adding new root domain

function showAddRootDomainView(type) {
	var table = "";
	table += "<table style='margin-top:15px;width:100%;'  id='tableId'>";
	table += "<tr class='currentSelectedRow' type="+type+" id='new' pid='0'><td class='iconWidth'><a style='display:none' class='wikiLink' target='_blank' href='http://en.wikipedia.org/wiki/'"
			+ "><img class='wikiLink' src='images/wiki.png'/></a></td>"
			+ "<td class='iconWidth'><img style='display:none' class='newDomain' src='images/new.png'/></td>"
			+ "<td class='iconWidth'><img class='saveDomain' src='images/save.png'/></td>"
			+ "<td class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td>"
			+ "<td class='titleClass'><span class='spanTitle rootTitle expandDomain'></span><input class='autoCompleteWiki' size='30' type='text'></td>"
			+ "<td></td><td></td><td></td></tr>"
	$('#domainDivId').prepend(table);
}

//function for making ajax call on server to save or update domain

function saveDomain(key, parentKey, title, weightage, type, row, currentObj) {

	data = '0';
	var data = {
		key : key,
		parentKey : parentKey,
		title : title,
		weightage : weightage,
		type : type
	};
	var url = 'saveOrUpdateDomain';
	$.ajax({
		type : 'POST',
		url : url,
		data : data,
		success : function(data) {
			if (key == '0') {
				row.attr("id", data);
			}
			row.find('.newDomain').show();
			
			var spanTitle = row.find('.spanTitle');
			var wikiLink = row.find('.wikiLink');
			spanTitle.html(title);
			spanTitle.show();
			wikiLink.attr("href", "http://en.wikipedia.org/wiki/"
					+ title);
			wikiLink.show();
			var titleInput = row.find('.autoCompleteWiki');
			titleInput.hide();
			
			row.removeClass("currentSelectedRow");
			var weightageInput = row.find('.inputWeightage');
			weightageInput.addClass("addBorder");
			weightageInput.attr('readonly', 'readonly');
			$(currentObj).attr("src", "images/edit.png");
			$(currentObj).addClass("editDomain");
			$(currentObj).parent().prev().children().show();
			$(currentObj).removeClass("saveDomain");

//			var expandImg = row.find('.expandDomain');
//
//			if (expandImg.is(":hidden")) {
//				expandImg.show(); // hide button
//			}

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		},
		dataType : 'text'
	});
	return data;
}

//function for making ajax call for deleting domain

function deleteDomain(key, parentKey, table, row) {

	var data = {
		key : key,
		parentKey : parentKey
	}

	var url = 'deleteDomain';
	$.ajax({
		type : 'POST',
		url : url,
		data : data,
		success : function(data) {
			if (parentKey != '0') {
				row.remove();
				table.find("tr[pid='" + key + "']").remove();
			} else {
				table.remove();
			}
			row.removeClass("currentSelectedRow");
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		},
		dataType : 'text'
	});
}



function importHierarchy(parentKey, weightage){
	var key = $('input:radio[name=selectedDomain]:checked').val();
	var title = $('input:radio[name=selectedDomain]:checked').next().html();
	//alert(title);
	var data = {
			key : key,
			parentKey : parentKey,
			weightage : weightage
		}

		var url = 'domainHierarchy/import';
		$.ajax({
			type : 'GET',
			url : url,
			data : data,
			success : function(rootKey) {
				if(rootKey != null){
					//alert(rootKey);
					if(parentKey!="0"){
						row = $("#"+parentKey);
						table = row.closest('table');
						table.find("tr:gt(0)").remove();
						showCompleteDomainTree(parentKey, table);
					}
					else{
						row = $("#new");
						type = row.attr('type');
						table = row.closest('table');
						table.find("tr").remove();
						var domain ={};
						domain.key = rootKey;
						domain.title = title;
						domain.type = type;
						var row = makeRootRow(domain);
						table.append(row);
						expandDomain = table.find(".expandDomain");
						expandDomain.addClass("collapseDomain");
						expandDomain.removeClass("expandDomain");
						showCompleteDomainTree(rootKey, table);
						
					}
					$.fn.colorbox.close();
				}
//				if (parentKey != '0') {
//					row.remove();
//					table.find("tr[pid='" + key + "']").remove();
//				} else {
//					table.remove();
//				}
//				row.removeClass("currentSelectedRow");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			},
			dataType : 'text'
		});
}



function saveEnteredTitle(parentKey, domainName, weightage){
	row = $(".currentSelectedRow");
	saveLink = row.find('.saveDomain');
	var table = row.closest('table');
	var rootRow = table.find('tr:first');
	var domainType = rootRow.attr('type');
	saveDomain("0", parentKey, domainName, weightage, domainType, row, saveLink);
	$.fn.colorbox.close();
}

//function for check if domains having same name as user type already exist then
//show the user option to select one of them or have their own 

function checkIfDomainAlreadyExist(domainName, row, parentKey, weightage, domainType, currentObj) {
	var data = {
		name : domainName,
		domainType : domainType
	}

	var url = 'getExistingDomainHierachy';
	$
			.ajax({
				type : 'GET',
				url : url,
				data : data,
				success : function(data) {
					if (data == null || data.length == 0){
						
						saveDomain("0", parentKey, domainName, weightage, domainType, row, currentObj)
						return;
					}
					var existingDomainDiv = $("#existingDomainDiv");
					var headerDiv = $("<div><span style='font-weight:bold'>Following Domains already exist with specified name."
							+ "</span><br><span style='font-weight:bold'>If you want to use existing one then select the checkbox infront of domain.</span>"
							+ "</div>");
					existingDomainDiv.append(headerDiv);
					var domainDiv = $("<div style='margin-top:10px;max-height:380px;overflow-y:auto' id ='domainDiv'></div>");

					var html = "";
					
					for ( var n = 0; n < data.length; n++) {
					
						html += "<ol>";
						html += "<li style='list-style:none'>";
						html += "<input type='radio' name='selectedDomain' value='"
								+ data[n].key + "'>";
						html += "<span>" + data[n].title + "</span>";
						var children = data[n].children;
						if (children != null) {
							html += getDomainChildTreeHtml(children, "");
						}
						html += "</li>";
						html += "</ol>";
					
					}
					
					domainDiv.append(html);

					
					existingDomainDiv.append(domainDiv);
					var buttonDiv = $("<div id ='buttonDiv'></div>");
					buttonDiv.append("<br>");
					buttonDiv
							.append("<input type='button' class='btn primary' onclick='importHierarchy("+parentKey+","+weightage+")' id='selectedDomainBtn'  value='Import hierarchy'>");
					buttonDiv.append('<input type="button" class="btn primary" onclick="saveEnteredTitle('+parentKey+',\'' + domainName +'\','+weightage+')"  value="Choose my own">');
					existingDomainDiv.append(buttonDiv);
					$("#domainDiv > ol:even").css("background","gray");
					$("#domainDiv > ol:odd").css("background","yellowgreen");
					showExistingDomainTreeInColorbox();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				},
				dataType : 'json'
			});

}


//function addNewRows(row,id,pid){
//	var cell = row.find('td').first();
//	alert(cell);
//	var colspan = cell.attr(
//	"colspan");
//	alert(colspan);
//	if (colspan == 5) {
//		var tr = "<tr class='currentSelectedRow' id='new' pid='"
//				+ pid
//				+ "'><td class='iconWidth' colspan='11'></td>"
//				+ "<td class='iconWidth'><img class='saveDomain' src='images/save.png'></td>"
//				+ "<td  class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td>"
//				+ "<td class='titleClass'><span class='spanTitle'></span><input class='autoCompleteWiki' type='text'></td>"
//				+ "<td><input class='inputWeightage' type='text'></td></tr>";
//		
//	} else {
//		var tr = "<tr class='currentSelectedRow' id='new' pid='"
//				+ pid
//				+ "'><td colspan='5'></td><td  class='iconWidth'><img style='display:none'  class='newDomain' src='images/new.png'/></td>"
//				+ "<td class='iconWidth'><img class='saveDomain' src='images/save.png'></td>"
//				+ "<td  class='iconWidth'><img class='deleteDomain' src='images/cross.png'/></td>"
//				+ "<td class='titleClass'><span class='spanTitle'></span><input class='autoCompleteWiki' type='text'></td>"
//				+ "<td><input class='inputWeightage' type='text'></td></tr>";
//		
//	}
//	row.after(tr);
//}

//function updateIdOfDomain() {
//	var value = $('input:radio[name=selectedDomain]:checked').val();
//	$('.currentSelectedRow').attr('id', value);
//	$.fn.colorbox.close();
//	var row = $('.currentSelectedRow');
//	
//	addNewRows(row,"new","new");
//	// row.attr();
//}

// get domain child tree in form of html string

function getDomainChildTreeHtml(data, html) {

	for ( var j = 0; j < data.length; j++) {
		html += "<ol>";
		html += "<li style='list-style:none;margin-left:25px'>";
		html += "<span>" + data[j].title + "</span>";

		if (data[j].children != null) {
			html = getDomainChildTreeHtml(data[j].children, html);
		} else {
			html += "</li>";
		}
		html += "</ol>";

	}
	return html;
}

//show existing domain tree in colorbox

function showExistingDomainTreeInColorbox() {
	$.colorbox({
		href : '#existingDomainDiv',
		open : true,
		inline : true,
		width : "780px",
		height : "580px",
		opacity : 0.9,
		onOpen : function() { // triggers a callback when the lightbox opens
			$('#existingDomainDiv').show(); // when the lightbox opens, show the
			// content div
		},
		onCleanup : function() {
			var existingDomainDiv = $('#existingDomainDiv');
			existingDomainDiv.children().remove();
			existingDomainDiv.hide(); // hides the content div when the
			// lightbox closes

		}
	});
}

// check if user alreay has opened some domain then not allow him to another domain

function checkIfOperationPending() {
	if ($('.currentSelectedRow').length > 0) {
		return true;
	} else
		return false;
}
