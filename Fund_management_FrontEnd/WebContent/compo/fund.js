$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});



$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 

 $.ajax( 
 { 
 url : "FundApi", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : decodeURIComponent("text"), 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

$(document).on("click", ".btnUpdate", function(event)
{ 
$("#hidItemIDSave").val($(this).data("itemid")); 
 $("#ProjectID").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#Announcement").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#Duration").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#Instructions").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#Amount").val($(this).closest("tr").find('td:eq(6)').text());
});

$(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "FundApi", 
 type : "DELETE", 
 data : "itemId=" + $(this).data("itemid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});




function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}







function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


function validateItemForm() {

	// CODE
if ($("#ProjectID").val().trim() == "") 
 { 
 return "Insert ProjectID."; 
 } 
// NAME
if ($("#Announcement").val().trim() == "") 
 { 
 return "Insert Announcement..If you are Not admin.Please type NO"; 
 } 9
// PRICE-------------------------------
if ($("#Duration").val().trim() == "") 
 { 
 return "Insert Duration"; 
 } 

// DESCRIPTION------------------------
if ($("#Instructions").val().trim() == "") 
 { 
 return "Insert Instructions.If you are Not admin.Please type NO"; 
 } 
return true; 
}
