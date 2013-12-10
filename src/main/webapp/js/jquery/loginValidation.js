function checkValidations() {

	var userId = $('#userId').val();
	
	if (isValidEmailAddress(userId)==false) {
		
		return false;
	}
}