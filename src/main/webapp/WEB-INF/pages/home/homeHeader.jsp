<!-- Include the required JavaScript libraries: -->
<title>Home</title>
<script type="text/javascript">
$(function(){
	var topNavValue = $.cookie('top-nav');
	var subNavValue = $.cookie('sub-nav');
	if(topNavValue != null && topNavValue !=1){
		$("#nav-"+topNavValue).addClass("not-active");
		$("#nav-1").removeClass("not-active");
		$("#cssmenu > ul > li:nth-child("+topNavValue+")").css("background","");
		$("#cssmenu > ul > li:nth-child(1)").css("background","grey");
		if(subNavValue != null && subNavValue != 1){
			$("#nav-"+topNavValue+" div:nth-child("+subNavValue+")").css("background","");
			$("#nav-1 div:nth-child(1)").css("background","grey");
		}
		else{
			$("#nav-1 div:nth-child(1)").css("background","grey");
		}
	}
	else{
		$("#nav-1 div:nth-child(1)").css("background","grey");
	}
	
});
</script>