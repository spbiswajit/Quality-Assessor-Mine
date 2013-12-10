function getURLParameter(name) {
	return decodeURI((RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [
			, null ])[1]);
}

var domainName = '';
$(function() {
	var productTemplateKey = (window.location.pathname).split("/",4)[3];
//	var domainKey = getURLParameter("domainKey");
//	var domainName = getURLParameter("domainName");
//	// $('#radarChartId').attr("src", "radarChart?key=" + key);
//	$('#headingMsg').html(
//			"Assessment for " + domainName);

	$('#radarImage').attr('src','/qualityassessor/radarChart/'+productTemplateKey);
	// wrap our new image in jQuery, then:
//	$('#radarImage')
//	// once the image has loaded, execute this code
//	.load(function() {
//		// set the image hidden by default
//		$(this).hide();
//
//		// with the holding div #loader, apply:
////		$('#loader')
//		// remove the loading class (so no background spinner),
//		$('#backgroundImage').hide();
//		// then insert our image
////		$('#loader').append(this);
//
//		// fade our image in to create a nice effect
//		$(this).fadeIn();
//	})

	// if there was an error loading the image, react accordingly
//	.error(function() {
//			$('#backgroundImage').hide();
//			alert("image loading error occured");
//	})

	// *finally*, set the src attribute of the new image to our image

//	$("#radarImage").load(function(){
//        $("#backgroundImage").hide().fadeOut(function(){
//        	alert('ds');
//            $("#radarImage").fadeIn();
//        });
//    });
	 function preloader(){
         document.getElementById("radarImage").style.display = "block";
         document.getElementById("radarImage").style.background = "none";
     }//preloader
     window.onload = preloader;
	
	
});