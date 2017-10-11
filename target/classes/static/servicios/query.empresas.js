$(document).ready( function () {
	listar_empresas();
	eliminar_empresas();
	limpiarformulario_empresas();
	$("#btn_submit_empresas").click(function(event){
		event.preventDefault();
		fire_ajax_submit_empresas();
		
	})
		$("#cancelar_editar_empresas").click(function(event){
		event.preventDefault();
		$("#btn_submit_empresas").val("Nuevo");
		limpiarformulario_empresas();
		
	})
});


var eliminar_empresas = function(){
	
	$("#btn_eliminar_empresas").on("click", function(){
	
		 var id=$("#eliminar_empresas #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/empresas/"+id,
				success : function(result) {
					console.log(result);
						listar_empresas();
						mostrar_mensaje_empresas(result);
						limpiarformulario_empresas();
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function fire_ajax_submit_empresas(){
	var formData = {
    			id : $("#formulario_empresas #id").val(),
    		nombre : $("#formulario_empresas #nombre").val(),
direccioncomercial : $("#formulario_empresas #direccioncomercial").val(),
    		correo : $("#formulario_empresas #correo").val(),
    	  sitioweb : $("#formulario_empresas #sitioweb").val(),
    	  telefono : $("#formulario_empresas #telefono").val(),
    	 	   fax : $("#formulario_empresas #fax").val(),
    	   detalle : $("#formulario_empresas #detalle").val(),
    especialidades : $("#formulario_empresas #especialidades").val(),
    		sector : $("#formulario_empresas #sector").val(),
    		  sede : $("#formulario_empresas #sede").val()
    		
    		
    	}
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/empresas",
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			mostrar_mensaje_empresas(data);
			console.log(data);
			$("#btn_submit_empresas").prop("disabled",false);
			listar_empresas();
			limpiarformulario_empresas();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_empresas=function(){
	 var table = $('#empresas_tabla').DataTable({
		 "responsive": true,
		 "destroy": true,
			"sAjaxSource": "empresas",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "nombre"},
				  { "mData": "sector" },
		          { "mData": "especialidades"},
		          { "defaultContent": "<a  href='#' class='editar_empresas grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modaleliminarempresas' id='eliminar_empresas' class='eliminar_empresas grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	/* $("select").val('10');
	  $('select').addClass("browser-default");
	  $('select').material_select();*/
	obtener_data_editar_empresas("#empresas_tabla tbody",table);
	 obtener_data_eliminar_empresas("#empresas_tabla tbody",table);
	};
	var obtener_data_editar_empresas=function(tbody,table){
		$(tbody).on("click","a.editar_empresas",function(){
			 event.preventDefault();
				$("#opcion_empresas").text("Modificar");
			  var data=table.row($(this).parents("tr")).data();
			  console.log(data);
			  var id=$("#formulario_empresas #id").val(data.id),
			  nombre=$("#formulario_empresas #nombre").val(data.nombre),
direccioncomercial = $("#formulario_empresas #direccioncomercial").val(data.direccioncomercial),
    	  sitioweb = $("#formulario_empresas #sitioweb").val(data.sitioweb),
    	  telefono = $("#formulario_empresas #telefono").val(data.telefono),
    		   fax = $("#formulario_empresas #fax").val(data.fax),
    	   detalle = $("#formulario_empresas #detalle").val(data.detalle),
    especialidades = $("#formulario_empresas #especialidades").val(data.especialidades),
    		sector = $("#formulario_empresas #sector").val(data.sector),
    		  sede = $("#formulario_empresas #sede").val(data.sede)
    		
    		
    		  $("#formulario_empresas #nombre").focus();
			  $("#formulario_empresas #direccioncomercial").focus();
			  $("#formulario_empresas #sitioweb").focus();
			  $("#formulario_empresas #telefono").focus();
			  $("#formulario_empresas #fax").focus();
			  $("#formulario_empresas #detalle").focus();
			  $("#formulario_empresas #especialidades").focus();
			  $("#formulario_empresas #sector").focus();
			  $("#formulario_empresas #sede").focus();
			  $("#btn_submit_empresas").val("modificar");
			 
		})
	}
	var obtener_data_eliminar_empresas=function(tbody,table){
		$(tbody).on("click","a.eliminar_empresas",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_empresas #id").val(data.id);
			var nombre=$("#eliminar_empresas #nombre").val(data.nombre);
			var nombre=$("#eliminar_empresas #sector").val(data.sector);
			var nombre=$("#eliminar_empresas #especialidades").val(data.especialidades);
			
		})
		
	}

	var limpiarformulario_empresas = function(){
		$("#formulario_empresas #id").val("");
		$("#formulario_empresas #nombre").val("");
		$("#formulario_empresas #direccion").val("");
		$("#formulario_empresas #sitioweb").val("");
		$("#formulario_empresas #telefono").val("");
		$("#formulario_empresas #fax").val("");
		$("#formulario_empresas #detalle").val("");
		$("#formulario_empresas #especialidades").val("");
		$("#formulario_empresas #sector").val("");
		$("#formulario_empresas #sede").val("");
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
	var mostrar_mensaje_empresas = function(mensaje){
		
		
		texto = "<strong>"+mensaje+"</strong>";
		color = "#379911";


$(".mensaje").html( texto ).css({"color": color });
$(".mensaje").fadeOut(5000, function(){
		$(this).html("");
		$(this).fadeIn(3000);
});			
}
