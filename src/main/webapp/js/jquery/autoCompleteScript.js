$(function() {
	
	//AutoComplete functionality for getting domains from wikipedia using jsonp response
	
	$(".autoCompleteWiki").live('click',function(){$(".autoCompleteWiki").autocomplete(autocomplete_opts)});
	
		function log( message ) {
			$( "<div/>" ).text( message ).prependTo( "#log" );
			$( "#log" ).scrollTop( 0 );
		}

		var autocomplete_opts = {
			source: function( request, response ) {
				$.ajax({
					url: "http://en.wikipedia.org/w/api.php",
					dataType: "jsonp",
					data: {
						action: "opensearch",
						search: "api",
						namespace: "0",
						limit : "10",
						search: request.term
					},
					success: function( data ) {
						console.log(data[1]);
						response( $.map(data[1], function( item ) {
							return {
								label: item,
								value: item
							}
						}));
					}
				});
			},
			minLength: 2,
			select: function( event, ui ) {
				var row = $(this).closest('tr');
				title = ui.item.label.trim();
				$(this).val(title);
				//checkIfDomainAlreadyExist( title, row);
				
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		};
		
		//Making search domain textfield blank initially
		
		$("#searchDomain").val("");
		
		//AutoComplete functionality for search domains from application database and getting json response
		
		$("#searchDomain").live('click',function(){$("#searchDomain").autocomplete(search_opts)});
	
		var search_opts = {
			source: function( request, response ) {
				$.ajax({
					url: "getMatchingDomains",
					dataType: "json",
					data: {
						name : request.term,
						domainType : $("#searchDomainType").val()
					},
					success: function( data ) {
						
						response( $.map(data, function( item ) {
							return {
								label: item.title,
								value: item.title,
								domain : item
							}
						}));
					}
				});
			},
			minLength: 2,
			
			select: function( event, ui ) {
				var row = $(this).closest('tr');
				title = ui.item.label.trim();
				$(".imagetable").hide();
				makeTable(ui.item.domain);
				
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		};
		
		
		
	});