$(document)
		.ready(
				function() {
					$('#addMoreWorkExpbtn')
							.click(
									function() {
										var num = $('.cloneWorkExpTexts').length; // how
										var id=num-1;
		
										
										var newNum = new Number(num ); // the
										

										var newrow=$("<tr class='cloneWorkExpTexts' id='workExpTexts"+num+"'>" +
												"<td style='width:25%'><input style='width:90%'  type='text' value='' class='title' name='workExperiences["+num+"].title' id='workExperiences["+num+"].title'></td>" +
												"<td style='width:25%'><input style='width:90%' type='text' value='' class='areaOfExperience' name='workExperiences["+num+"].areaOfExpertise' ></td>" +
												"<td style='width:25%'><input style='width:90%' type='text' value='' class='roleDescription' name='workExperiences["+num+"].roleDescription'></td>" +
											    "<td style='width:13%'><input style='width: 90%' type='text' value='' class='fromDatepicker'  name='workExperiences["+num+"].fromDate' ></td> " +
											    "<td style='width:12%'><input style='width: 90%' type='text' value='' class='toDatepicker'  name='workExperiences["+num+"].toDate' ></td>" +
											    "</tr>") ;
										
									

										$('#workExpBtnTr').before(newrow);
										
										$(
												'#workExpTexts'
														+ num
														+ " td input.fromDatepicker")
												.datepicker({ dateFormat: 'dd-mm-yy' });

										$(
												'#workExpTexts'
														+ newNum
														+ " td input.toDatepicker")
												.datepicker({ dateFormat: 'dd-mm-yy' });

									});

					$('#addMoreSocialbtn')
							.click(
									function() {
										var newTr = $("<tr><td><input type='text' name='socialSiteName'></td><td><input type='text' name='socialSiteId'></td></tr>");
										$('#socialButtonRow').before(newTr);

									});

					$('#btnDel').click(function() {
						var num = $('.clonedInput').length; // how many
						// "duplicatable"
						// input fields we currently
						// have
						$('#input' + num).remove(); // remove the last element

						// enable the "add" button
						$('#btnAdd').attr('disabled', '');

						// if only one element remains, disable the "remove"
						// button
						if (num - 1 == 1)
							$('#btnDel').attr('disabled', 'disabled');
					});

					$('#btnDel').attr('disabled', 'disabled');
				});

$(function() {
	$(".fromDatepicker").datepicker({ dateFormat: 'dd-mm-yy' });
	$(".toDatepicker").datepicker({ dateFormat: 'dd-mm-yy' });
	$("#firstName").watermark("First Name");
	$("#middleName").watermark("Middle Name");
	$("#lastName").watermark("Last Name");
});
