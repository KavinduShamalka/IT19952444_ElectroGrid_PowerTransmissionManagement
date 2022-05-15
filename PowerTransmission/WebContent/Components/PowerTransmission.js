$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePTForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidptIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PowerTransmissionAPI", 
 type : type, 
 data : $("#formPT").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPtSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onPtSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPTDGrid").html(resultSet.data); 
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
$("#hidptIDSave").val(""); 
$("#formPT")[0].reset(); 
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidptIDSave").val($(this).data("ptid")); 
		 $("#t_ID").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#t_Acommercial").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#t_Aresidential").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#t_Aagriculture").val($(this).closest("tr").find('td:eq(3)').text());
		$("#t_date").val($(this).closest("tr").find('td:eq(4)').text());  
		});

//DELETE
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "PowerTransmissionAPI", 
		 type : "DELETE", 
		 data : "ptid=" + $(this).data("ptid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onPtDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onPtDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPTDGrid").html(resultSet.data); 
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



// CLIENT-MODEL================================================================
function validatePTForm()
{
	// CODE
	if ($("#t_ID").val().trim() == "")
	{
	return "Insert Power Code.";
	}
	// NAME
	if ($("#t_Acommercial").val().trim() == "")
	{
	return "Insert ommercial Name.";
	}
	if ($("#t_Aresidential").val().trim() == "")
	{
	return "Insert agriculture Name.";
	}
    if ($("#t_Aagriculture").val().trim() == "")
	{
	return "Insert residential Name.";
	}
	if ($("#t_date").val().trim() == ""){
		return "Insert Item Date.";
	}
	return true;
}/**
 * 
 */