var table;
var ADMIN = {"roleId":1,"role":"ADMIN"};
var MODERATOR = {"roleId":4,"role":"MODERATOR"};
var USER = {"roleId":3,"role":"USER"};

function allUsers(element) {
	if (element.className != 'active') {
		table.fnClearTable();
		table.fnReloadAjax("all-users");

	}
}

$(document)
		.ready(
				function() {
					table = $("table.table-bordered")
							.dataTable(
									{
										"sAjaxDataProp" : "",
										"fnInitComplete" : function(settings,
												json) {
										},
										"fnRowCallback" : function(nRow, aData,
												iDisplayIndex,
												iDisplayIndexFull) {
											var ul = $("<ul/>", {
												'class' : "dropdown-menu"
											});
											var li = $("<li/>");
											var div = $("<div/>", {
												"class" : "input-group-btn"
											});

										
									function html_element(aData, role) {
											var parent_element = $("<a/>",
													{
														"href" : "#"
													}).click(aData, function(e) {
																aData.role = role;
																console.log(JSON.stringify(aData));
																e.preventDefault();
																$.ajax({
																	    url : 'user-update',
																		dataType : 'json',
																        beforeSend : function() {
																			},
																		contentType : "application/json",
																		"type" : "POST",
																		data : JSON.stringify(aData),
																		success : function(response) {
																				$('td:eq(2)',nRow).html(response.role.role);
																			}
																		});
															});
											return parent_element;
										};
										
										var button = $(
												"<button/>",
												{
													text : "Switch Role",
													"type" : "button",
													"data-toggle" : "dropdown",
													"class" : "btn btn-default btn-block",
												});

						
										
										function form_element(aData) {
											 var admin = html_element(aData, ADMIN);
									         var moderator = html_element(aData, MODERATOR);
									         var user = html_element(aData, USER);
									         admin.html("Admin");
									         moderator.html("Moderator");
									         user.html("User");
											
										
											var final_element = div.append(button);
											final_element.append(ul.append(li
																	.append(admin)
																	.append(moderator)
																	.append(user)
																	));
											return final_element;
										}
										
										
										row1 = $('td:eq(5)', nRow).html(form_element(aData));

									},


										"bProcessing" : false,
										"bServerSide" : false,
										"sAjaxSource" : "all-users",
										"aoColumns" : [

												{
													"mData" : "login"
												},
												{
													"mData" : function(data,
															type, full) {
														return data.firstName
																+ " "
																+ data.lastName;
													}
												},												
												{
													"mData" : function(data,
															type, full) {
														return data.role.role;
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="resetPass/'
																+ data.userId
																+ '" class="text-blue"/>'
																+ 'Reset'
																+ '</a>'
													}
												},
												{
													"mData" : function(data,
															type, full) {
														return '<a href="deleteUser/'
																+ data.userId
																+ '" class="text-blue"/>'
																+ 'Delete'
																+ '</a>'
													}
												},
												{	 
													"mData" : "userId"

												}
												]
									});
					table
							.on(
									'draw',
									function() {
										if (table.fnSettings().sAjaxSource == "all-users") {
											$(
													'.dropdown-menu>li>a:contains("Reject"),a:contains("Approve")')
													.hide();
										} else {
											$(
													'.dropdown-menu>li>a:contains("Refuse"),a:contains("Send Again")')
													.hide();
										}
									});
				});
