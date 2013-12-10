$(function(){

	var subClicked = $.cookie('sub-clicked');
	$.cookie('sub-clicked',false);
	//alert(subClicked);
	if($.cookie('top-nav')==null){
		//alert('dd');
		$("#cssmenu > ul > li:nth-child(1)").css("background","grey");
		$("#nav-1").removeClass("not-active");
		$("#nav-1 div:nth-child(1)").css("background","grey");
	}
	else{
		var topNavValue = $.cookie('top-nav');
		$("#cssmenu > ul > li:nth-child("+topNavValue+")").css("background","grey");
		$("#nav-"+topNavValue).removeClass("not-active");
		var subNavValue = $.cookie('sub-nav');
		if(subNavValue == null){
			subNavValue = 1; 
		}
		if(subClicked == "true"){
			$("#nav-"+topNavValue+" div:nth-child("+subNavValue+")").css("background","grey");
		}
		if(subClicked == "false"){
			$("#nav-"+topNavValue+" div:nth-child(1)").css("background","grey");
		}
//		$.cookie('top-nav', null);
//		$.cookie('sub-nav', null);
	}
	$('#cssmenu > ul > li').click(function(){
		$.cookie('top-nav', $(this).index()+1);
		
		//alert($(this).index());
	});
	
	$('#cssmenu > ul > li > ul > li').click(function(){
		$.cookie('sub-nav', $(this).index()+1);
		$.cookie('sub-clicked',true);
//		alert($(this).index());
//		alert("ddf");
	});
	
	$('.leftNavigation .divNavigation').click(function(){
		 $.cookie('sub-nav',$(this).index()+1);
		 $.cookie('top-nav',$(this).parent().attr("id").split("nav-")[1]);
		 $.cookie('sub-clicked',true);
		
	});
	
});