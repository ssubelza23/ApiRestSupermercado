$(document).ready( function () {
	listar_marcas();
	eliminar_marcas();
	$("#formulario_marcas #logomarcas").hide();
	limpiarformulario_marcas();
	$("#btn_submit_marcas").click(function(event){
		event.preventDefault();
		fire_ajax_submit_marcas();
		
	})
		$("#cancelar_editar_marcas").click(function(event){
		event.preventDefault();
		$("#btn_submit_marcas").val("Nuevo");
		limpiarformulario_marcas();
		 var src="";
 		$("#formulario_marcas #logomarcas").hide();
		
	})
});


var eliminar_marcas = function(){
	
	$("#btn_eliminar_marcas").on("click", function(){
	
		 var id=$("#eliminar_marcas #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/marcas/"+id,
				success : function(result) {
					console.log(result);
						listar_marcas();
						mostrar_mensaje_marcas(result);
						limpiarformulario_marcas();
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function fire_ajax_submit_marcas(){
	var form=$("#formulario_marcas")[0];
	var data=new FormData(form);
	$("#btn_submit_marcas").prop("disabled",true);
	console.log(data);
	$.ajax({
		type : "POST",
		enctype:"multipart/form-data",
		url : "/marcas",
		data : data,
		contentType : "application/json",
		processData: false,
		contentType: false,
		cache: false,
		timeout: 600000,
		success:function(data){
			mostrar_mensaje_marcas(data);
			console.log(data);
			$("#btn_submit_marcas").prop("disabled",false);
			listar_marcas();
			limpiarformulario_marcas();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_marcas=function(){
	 var table = $('#marcas_tabla').DataTable({
		 "destroy": true,
			"sAjaxSource": "listamarcas",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"lengthMenu": [[5, 10, 20, 25, 50, -1], [5, 10, 20, 25, 50, "Todos"]],
			"aoColumns": [
				  { "mData": "logo","render":function(mData,type,row){
		        	  return "<img class='z-depth-2 responsive-img activator' width='70' height='60' src='/assets/images/marcas/"+mData+"'/>"
		        	  }
				  },
			      { "mData": "nombre"},
		          { "defaultContent": "<a  href='#' class='editar_marcas grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modaleliminarmarcas' id='eliminar_marcas' class='eliminar_marcas grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	 $(".marcas").hover(function() {
	        $(this).find("select").val('5');
	    });	
	 $(".marcas select").val('5');
	  $(".marcas select").addClass("browser-default");
	  $(".marcas select").material_select();
	obtener_data_editar_marcas("#marcas_tabla tbody",table);
	 obtener_data_eliminar_marcas("#marcas_tabla tbody",table);
	};
	var obtener_data_editar_marcas=function(tbody,table){
		$(tbody).on("click","a.editar_marcas",function(){
			 event.preventDefault();
				$("#opcion_marcas").text("Modificar");
			  var data=table.row($(this).parents("tr")).data();
			  console.log(data);
			  var id=$("#formulario_marcas #id").val(data.id),
			  nombre=$("#formulario_marcas #nombre").val(data.nombre),
			  
    	   detalle = $("#formulario_marcas #detalle").val(data.detalle)
    		
    	   var src="/assets/images/usuarios/"+data.logo;
			  $("#formulario_marcas #logomarcas").prop("src",src);
			  $("#formulario_marcas #logomarcas").show();
    		  $("#formulario_marcas #nombre").focus();
			  $("#formulario_marcas #logo").focus();
			  $("#formulario_marcas #detalle").focus();
			 
		})
	}
	var obtener_data_eliminar_marcas=function(tbody,table){
		$(tbody).on("click","a.eliminar_marcas",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_marcas #id").val(data.id);
			var nombre=$("#eliminar_marcas #nombre").val(data.nombre);
			
		})
		
	}

	var limpiarformulario_marcas = function(){
		$("#formulario_marcas #id").val("");
		$("#formulario_marcas #nombre").val("");
		$("#formulario_marcas #logo").val("");
		$("#formulario_marcas #detalles").val("");
	}
	
	
	var idioma_español={
		    "sProcessing":     "Procesando...",
		    "sLengthMenu":     "Mostrar _MENU_ registros",
		    "sZeroRecords":    "No se encontraron resultados",
		    "sEmptyTable":     "Ningún dato disponible en esta tabla",
		    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
		    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
		    "sInfoPostFix":    "",
		    "sSearch":         "Buscar:",
		    "sUrl":            "",
		    "sInfoThousands":  ",",
		    "sLoadingRecords": "Cargando...",
		    "oPaginate": {
		        "sFirst":    "Primero",
		        "sLast":     "Último",
		        "sNext":     "Siguiente",
		        "sPrevious": "Anterior"
		    },
		    "oAria": {
		        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
		        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
		    }
		};
	var mostrar_mensaje_marcas = function(mensaje){
		
		
		texto = "<strong>"+mensaje+"</strong>";
		color = "#379911";


$(".mensaje").html( texto ).css({"color": color });
$(".mensaje").fadeOut(5000, function(){
		$(this).html("");
		$(this).fadeIn(3000);
});			
}
