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
			url : "http://localhost:8080/validacion",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				console.log(result);
				if(result.existe == false){
					console.log(result.msg);
					$("#feedback").html("<strong class='red-text'>" + result.msg +"</strong>");
				
				}else{
					
					
				
					
					
					
					window.location.replace("http://localhost:8080/index/"+dato.id);
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