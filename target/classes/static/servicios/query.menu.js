$( document ).ready(function() {

	$(".links").click(function(event) {
		  var href= $(this).attr('href');
		  event.preventDefault();
		  $.get(href, function(htmlexterno){
			  window.location.replace("http://localhost:8080/index/"+dato.id);
			    	});
		  
		});
})