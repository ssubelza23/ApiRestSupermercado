$( document ).ready(function() {

	var url = window.location;
	
	// SUBMIT FORM
    $("#login_usuario").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		login : $("#login").val(),
    		clave : $("#clave").val()
    	}
    	console.log(formData);
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/validacion",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				console.log(result);
				if(result.existe == false){
					console.log(result.msg);
					$("#error").html("<div id='card-alert' class='card card red lighten-5 red-text'>" +
							"<div class='alert card-content red-text'>" +
							"<p><i class='material-icons'>report_problem</i>"+result.msg+"</p>" +
							" </div></div>");
					$("#feedback").html("<strong class='red-text'>" + result.msg +"</strong>");
				
				}else{
					console.log("llego usuario autneticado"+result.usuario.id);
					console.log(result);
					window.location.replace("/index/"+result.usuario.id);
				}
					
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	resetData();
 
    }
    function resetData(){
    	$("#Login").val("");
    	$("#Clave").val("");
    }
})