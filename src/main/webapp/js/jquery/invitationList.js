function moveUserToSelectedList(e) {
	var selectedOpts = $('#userList option:selected');
	if (selectedOpts.length == 0) {
		alert("Nothing to move.");
		e.preventDefault();
	}

	$('#selectedUserList').prepend($(selectedOpts).clone());
	$(selectedOpts).remove();
	e.preventDefault();
}

function moveSelectedUserToUserList(e) {
	var selectedOpts = $('#selectedUserList option:selected');
	if (selectedOpts.length == 0) {
		alert("Nothing to move.");
		e.preventDefault();
	}

	$('#userList').append($(selectedOpts).clone());
	$(selectedOpts).remove();
	e.preventDefault();
}

function moveDomainToSelectedDomains(e) {
	var selectedOpts = $('#domainList option:selected');
	if (selectedOpts.length == 0) {
		alert("Nothing to move.");
		e.preventDefault();
	}

	$('#selectedDomainList').prepend($(selectedOpts).clone());
	$(selectedOpts).remove();
	e.preventDefault();
}

function moveSelectedDomainToDomains(e) {
	var selectedOpts = $('#selectedDomainList option:selected');
	if (selectedOpts.length == 0) {
		alert("Nothing to move.");
		e.preventDefault();
	}

	$('#domainList').append($(selectedOpts).clone());
	$(selectedOpts).remove();
	e.preventDefault();
}

$(document).ready(function() {
	$('#userRight').click(function(e) {
		moveUserToSelectedList(e);
	});

	$('#userList option').live('dblclick', function(e) {
		moveUserToSelectedList(e)
	});

	$('#userLeft').click(function(e) {
		moveSelectedUserToUserList(e);
	});

	$('#selectedUserList option').live('dblclick', function(e) {
		moveSelectedUserToUserList(e);
	});

	$('#domainRight').click(function(e) {
		moveDomainToSelectedDomains(e);
	});

	$('#domainList option').live('dblclick', function(e) {
		moveDomainToSelectedDomains(e);
	});

	$('#domainLeft').click(function(e) {
		moveSelectedDomainToDomains(e);
	});
	$('#selectedDomainList option').live('dblclick', function(e) {
		moveSelectedDomainToDomains(e);
	});

});

function sendInvitation() {
	var userIds = [];
	var domainIds = [];
	var userList = $('#selectedUserList option');
	//var domainList = $('#selectedDomainList option');

//	if (domainList.length == 0) {
//		alert("Please select atleast one domain");
//	} else if (userList.length == 0) {
//		alert("Please select atleast one user");
//	} 
//else {

		userList.each(function(index, id) {

			var userId = {};
			userId['id'] = $(id).val();

			userIds.push(JSON.stringify(userId));

		});

//		domainList.each(function(index, id) {
//
//			var domainId = {};
//			domainId['id'] = $(id).val();
//			domainIds.push(JSON.stringify(domainId));
//
//		});

//		data = '[{"userIds":[' + userIds + ']},{"domainIds":[' + domainIds
//				+ ']}]';
		
		data = '[{"userIds":[' + userIds + ']}]';

		$.ajax({
			type : 'POST',
			url : 'invitation',
			data : data,
			dataType : 'text',
			contentType : 'application/json',
			success : function(data) {
				window.location.href = data;
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},

		});
	

} 