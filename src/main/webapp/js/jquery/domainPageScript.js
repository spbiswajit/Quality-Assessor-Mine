function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}

$(function() {
	var key = getURLParameter("key");
	var requestedUserId = getURLParameter("requestedUserId");

	$("#tree")
			.dynatree(
					{
						initAjax : {
							url : "domainHierarchy",
							data : {
								key : key,
								requestedUserId : requestedUserId,
								mode : "all",
								
							}
						},

						onCustomRender : function(node) {
							var score = node.data.score;
							if ((score >= 0) && (node.getChildren() == null)) {
								title = node.data.title;
								var ratingContainerId = "ratingContainer"
										+ node.data.key;
								var ratingStarHtml = "";
								for (i = 0; i < score; i++) {
									ratingStarHtml = ratingStarHtml
											+ "<img class='toggle'  src='images/yellowstar.png'></img>";
								}

								var noOfWhiteStars = 5 - score;
								for (i = 0; i < noOfWhiteStars; i++) {
									ratingStarHtml = ratingStarHtml
											+ "<img class='toggle'  src='images/whitestar.gif'></img>";
								}
								var html = "<a style='color:green;cursor:pointer' class='dynatree-title'>"
										+ title + "</a>";
								var score = node.data.score;

								return html + "<span id=" + ratingContainerId
										+ ">" + ratingStarHtml + "</span>";
							}
						},
						onClick : function(node, event) {
							// alert(node.data.score);
							// alert(node.data.assessmentId);
							if ((node.getEventTargetType(event) == "title")
									&& (node.getChildren() == null)) {
								var parentKey = node.data.parentKey;
								var key = node.data.key;
								var title = node.data.title;
								var weightage = node.data.weightage;
								var score = node.data.score;
								var assessmentId = node.data.assessmentId;
								$.colorbox({
									href : 'rate?title=' + title + '&key='
											+ key + '&score=' + score
											+ '&assessmentId=' + assessmentId
											+ '&requestedUserId='
											+ requestedUserId,
									open : true,
									iframe : true,
									width : "480px",
									height : "300px",
									opacity : 0.9,

								});

							}
						}

					});

	$("span .toggle").live('click', function() {
		if ($(this).attr('src') == 'images/whitestar.gif') {
			$(this).attr('src', "images/yellowstar.png");
			score = $(this).index() + 1;
			$(this).prevAll().attr('src', "images/yellowstar.png");

			var node = $("#tree").dynatree("getActiveNode");
			saveRating(node, score, requestedUserId);
		} else {
				//alert($(this).index());
				$(this).attr('src', "images/whitestar.gif");
				$(this).nextAll().attr('src', "images/whitestar.gif");
				score = $(this).index() - 1;
				if (score == -1) {
					score = 0;
				}
				var node = $("#tree").dynatree("getActiveNode");
				saveRating(node, score, requestedUserId);
			
		}

		// else {
		// if (($(this).next().length == 0)
		// || ($(this).next().attr('src') == 'images/whitestar.gif')) {
		// $(this).attr('src', "images/whitestar.gif");
		// score = $(this).index();
		// }
		// }
	});

});

function updateNode(score, assessmentId) {
	var node = $("#tree").dynatree("getActiveNode");
	node.data.score = score;
	node.data.assessmentId = assessmentId;
	//node.render();
}

function saveRating(node, updatedScore, requestedUserId) {
	// alert(score);
	var data = {
		key : node.data.key,
		score : updatedScore,
		id : node.data.assessmentId,
		requestedUserId : requestedUserId
	};
	var url = 'rate';
	$.ajax({
		type : 'POST',
		url : url,
		data : data,
		success : function(assessmentId) {
			updateNode(score, assessmentId);
			// parent.$.fn.colorbox.close();

		},
		error : function(jqXHR, textStatus, errorThrown) {
			// showErrorMessage(jqXHR.responseText, "450", "300");
		},
		dataType : 'text'
	});
}