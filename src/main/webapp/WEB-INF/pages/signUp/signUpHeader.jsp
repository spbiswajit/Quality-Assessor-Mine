<link href="css/common.css" rel="Stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/logincss/reset.css">
<link rel="stylesheet" type="text/css" href="css/logincss/structure.css">
<link rel="stylesheet" type="text/css" href="css/error.css">
<script src="js/jquery/jquery.min.js"></script>
<script src="js/jquery/jquery.validate.min.js"></script>
<script>
  $(document).ready(function(){
    $("#signUpForm").validate();
  });
  
  jQuery.validator.addMethod("confirmPassword", function(value, element) { 
	  var password=$('#password').val();
	  var confirmPassword =$('#confirmPassword').val();
	  if(password!=confirmPassword){
		  return false;
	  }
	   return true; 
	}, "Password doesn\'t match with confirm password");
</script> 