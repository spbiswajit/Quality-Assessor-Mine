
<script>
$(function(){

	$("#addNewProductDomain").click(function(){
		var tableHeading = $("#productTableHeadId");
		var row = "<tr><td><input class = 'productId' value='' type = 'hidden' value=''><img class = 'saveOrUpdateImage' src = 'images/save.png'></td>"+
		"<td><img class = 'deleteImage' src = 'images/cross.png'></td><td><input class ='productName' type='text'></td><td><input class = 'productDescription' type='text'></td><td><a id ='linkId'  href='' style='padding:3px 13px;display:none' class='button-default'>Get this product to be reviewed</a></td></tr>";
		tableHeading.after(row);
	});
	
	$(".saveOrUpdateImage").live('click', function(){
		var currentObj =  $(this);
		var row = $(this).closest("tr");
		var productIdObj = row.find(".productId")
		var productId = productIdObj.val();
		var productNameObj = row.find(".productName");
		var productName = productNameObj.val();
		var productDescriptionObj = row.find(".productDescription");
		var productDescription = productDescriptionObj.val();
		var editImage = row.find(".editImage");
		$.ajax({
			url : 'saveOrUpdateProduct',
			data : { id : productId,
					productName : productName ,
					productDescription : productDescription },
			dataType : 'text',
			type : 'POST',
			success : function(productId){
					row.find(".productId").val(productId);	
					currentObj.attr("src","images/edit.png");
					currentObj.removeClass("saveOrUpdateImage");
					currentObj.addClass("editImage");
					editImage.show();
					productNameObj.attr("readonly","readonly");
					productNameObj.css("border","none");
					productDescriptionObj.attr("readonly","readonly");
					productDescriptionObj.css("border", "none");
					var linkToReview = row.find("#linkId"); 
					linkToReview.show();
					linkToReview.attr("href","templateSelectionView?productId="+productId);
					},
			error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
			}	
		});
	});
	
	$(".editImage").live('click', function(){
		var currentObj =  $(this);
		var row = $(this).closest("tr");
		var productNameObj = row.find(".productName");
		var productDescriptionObj = row.find(".productDescription");
		productNameObj.removeAttr("readonly");
		productNameObj.css("border","");
		productDescriptionObj.removeAttr("readonly");
		productDescriptionObj.css("border","");
		currentObj.attr('src','images/save.png');
		currentObj.removeClass("saveImage");
		currentObj.addClass("saveOrUpdateImage");
	});

	$(".deleteImage").live('click',function(){
		var currentObj =  $(this);
		var row = $(this).closest("tr");
		var productIdObj = row.find(".productId")
		var productId = productIdObj.val();
		$.ajax({
			url : 'deleteProduct',
			data : { id : productId},
			dataType : 'text',
			type : 'POST',
			success : function(productId){
					row.remove();
					},
			error : function(jqXHR, textStatus, errorThrown) {
						alert(jqXHR.responseText);
			}	
		});
	})
	
	
});
</script>
