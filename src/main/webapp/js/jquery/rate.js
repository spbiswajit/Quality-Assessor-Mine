function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}
var score = 0;
var key;
var assessmentId = 0;
var requestedUserId = "null";

$(function() {
	var title = getURLParameter('title');
	key = getURLParameter('key');
	assessmentId = getURLParameter('assessmentId');
	score = getURLParameter('score');
	requestedUserId =getURLParameter('requestedUserId');
	
	$(".heading").html("Provide Rating for " + title);
	for (i = 0; i < score; i++) {
		$('#ratingContainer').append(
				$('<img class="toggle" alt="" src="images/yellowstar.png">'));
	}
	var noOfWhiteStars = 5 - score;
	for (i = 0; i < noOfWhiteStars; i++) {
		$('#ratingContainer').append(
				$('<img class="toggle" alt="" src="images/whitestar.gif">'));
	}
	$("img")
			.click(
					function() {
						if ($(this).attr('src') == 'images/whitestar.gif') {
							$(this).attr('src', "images/yellowstar.png");
							score = $(this).index() + 1;
							// alert($(this).index());
							$(this).prevAll().attr('src',
									"images/yellowstar.png");
						} else {
							if (($(this).next().length == 0)
									|| ($(this).next().attr('src') == 'images/whitestar.gif')) {
								$(this).attr('src', "images/whitestar.gif");
								score = $(this).index();
							}
						}
					});
});

function saveRating() {
	// alert(score);
	var data = {
		key : key,
		score : score,
		id : assessmentId,
		requestedUserId:requestedUserId
	};
	var url = 'rate';
	$.ajax({
		type : 'POST',
		url : url,
		data : data,
		success : function(assessmentId) {
			parent.updateNode(score, assessmentId);
			parent.$.fn.colorbox.close();

		},
		error : function(jqXHR, textStatus, errorThrown) {
			// showErrorMessage(jqXHR.responseText, "450", "300");
		},
		dataType : 'text'
	});
}