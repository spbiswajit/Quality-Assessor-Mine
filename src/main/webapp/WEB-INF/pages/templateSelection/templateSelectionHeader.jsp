 <link rel="stylesheet" href="/qualityassessor/css/jquery-ui.css" />
 <script src="js/jquery/jquery.watermark.min.js"></script>
<link type='text/css' rel='stylesheet' href='css/colorbox.css' />
<link rel="stylesheet" href="css/jquery.ui.autocomplete.css">
<script type = "text/javascript" src = "/qualityassessor/js/jquery/domainManager.js"></script>
<script type="text/javascript" src="/qualityassessor/js/jquery/jquery.colorbox-min.js"></script>
<script src="/qualityassessor/js/jquery/jquery.ui.autocomplete.min.js"></script>
<script src='/qualityassessor/js/jquery/autoCompleteScript.js'></script>
<style>
.ui-autocomplete {
        max-height: 100px;
        overflow-y: auto;
        /* prevent horizontal scrollbar */
        overflow-x: hidden;
    }
</style>
     <script  type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
   
    <script type="text/javascript">
    $(function() {
       
    	$("#exist-radio-button").attr('checked', true);
    	
        var search_opts = {
    			source: function( request, response ) {
    				$.ajax({
    					url: "getMatchingProducts",
    					dataType: "json",
    					data: {
//     						name : request.term,
    						term : $("#productName").val()
    					},
    					success: function( data ) {
    						
    						response( $.map(data, function( item ) {
    							return {
    								label: item.productName,
    								value: item.productName,
    								
    							}
    						}));
    					}
    				});
    			},
    			minLength: 2,
    			
    			select: function( event, ui ) {
    			//	var row = $(this).closest('tr');
    			//	title = ui.item.label.trim();
    				//$(".imagetable").hide();
    			//	makeTable(ui.item.domain);
    				
    			},
    			open: function() {
    				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
    			},
    			close: function() {
    				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
    			}
    		};
  
       // $("#productName").live('click',function(){$("#productName").autocomplete(search_opts)});
    
        
        
        var search_opts1 = {
    			source: function( request, response ) {
    				$.ajax({
    					url: "getMatchingRootDomains",
    					dataType: "json",
    					data: {
//     						name : request.term,
    						name : $("#domainName").val()
    					},
    					success: function( data ) {
    						
    						response( $.map(data, function( item ) {
    							return {
    								label: item.title,
    								value: item.title,
    								key : item.key 
    							}
    						}));
    					}
    				});
    			},
    			minLength: 2,
    			
    			select: function( event, ui ) {
    			//	var row = $(this).closest('tr');
    			//	title = ui.item.label.trim();
    				//$(".imagetable").hide();
    			//	makeTable(ui.item.domain);
    			//alert(ui.item.key);
    				$("#domainId").val(ui.item.key);
    			},
    			open: function() {
    				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
    			},
    			close: function() {
    				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
    			}
    		};
  
        $("#domainName").live('click',function(){$("#domainName").autocomplete(search_opts1)});
    
        
        
        
        $("#createTemplateBtnId").click(function(){
        	$('#showTemplateTypes').show();
        	$('#selectTemplateSecId').hide();
        });
        
        $("input[name='chooseTemplate']").change(function(){
        	var selectedValue = $(this).val();
        	if(selectedValue == 'new'){
        		 $(".newTemp").show();
        		 $("#existTempId").hide();
        	}
        	else{
        		 $(".newTemp").hide();
        		 $("#existTempId").show();
        	}
           
        });
        

    });
    </script>
    
    

<!-- <link href="css/common.css" rel="Stylesheet" type="text/css" /> -->
<!-- Include the required JavaScript libraries: -->
<!-- <script src='js/jquery/jquery.min.js' type="text/javascript"></script> -->
<!-- <script src='js/jquery/jquery-ui.custom.min.js' type="text/javascript"></script> -->

<!-- <script type="text/javascript" src="js/jquery/homePageScript.js"></script> -->
<!-- <script src='js/json2/json2.js' type="text/javascript"></script> -->
<!-- <link rel="stylesheet" href="css/jquery.ui.all.css"> -->
<style type="text/css">
.inputWeightage{
width:30px;
}

.addBorder{
border:1px;
}

.iconWidth{
width:2%;
}

img{
cursor:pointer;
}

.titleClass{

width:25%;
}

.spanTitle{
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 13px;
    font-weight: normal;
    line-height: 18px;

}

.rootTitle:hover{
color:black;
text-decoration:underline;
}

.rootTitle{
cursor:pointer;
}

a{
color:black;
}

</style>

<script>
function checkChooseTemplate(){
	if($('#new_radio_button').is(':checked')){
		row = $("#newTempId").find('tr');
		domainName = row.find(".titleClass .rootTitle").html();
		newDomainId = row.attr("id");
		if(domainName == '' || newDomainId ==''){
			alert("Please select template");
			return false;
		}
		//alert($("#newTempId").find('tr').attr("id"));
		$("#domainId").val(newDomainId);
	}
	else{
		var existingDomainId = $("#domainId").val();
		var domainName = $("#domainName").val();
		if(existingDomainId == '' || domainName == ''){
			alert("Please select template");
			return false;
		}
	}
}

</script>