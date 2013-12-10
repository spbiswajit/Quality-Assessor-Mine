function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}

$(function() {
	var isIgnoreInvitation = getURLParameter("ignoreInvitation");
	if (isIgnoreInvitation != undefined && isIgnoreInvitation == 'true') {
		var viewInvitationHref = $('#viewIgnoreRequestDiv a');
		viewInvitationHref.html("View latest invitations");
		viewInvitationHref.attr("href",
				"assessmentRequests?ignoreInvitation=false");
	}
});