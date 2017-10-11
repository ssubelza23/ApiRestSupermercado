$(document).ready( function () {
	listar_contactos();
	eliminar_contactos();
	limpiarformulario_contactos();
	$("#btn_submit_contactos").click(function(event){
		event.preventDefault();
		fire_ajax_submit_contactos();
		
	})
		$("#cancelar_editar_contactos").click(function(event){
		event.preventDefault();
		$("#btn_submit_contactos").val("Nuevo");
		limpiarformulario_contactos();
		
	})
});


var eliminar_contactos = function(){
	
	$("#btn_eliminar_contactos").on("click", function(){
		 var id=$("#eliminar_contactos #id").val();
		$.ajax({
			type:"DELETE",
			url: "/contactos/"+id,
				success : function(result) {
					console.log(result);
						listar_contactos();
						mostrar_mensaje_contactos(result);
						limpiarformulario_contactos();
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function fire_ajax_submit_contactos(){
	var formData = {
    			id : $("#formulario_contactos #id").val(),
    		   dni : $("#formulario_contactos #dni").val(),
    		nombre : $("#formulario_contactos #nombre").val(),
    			ap : $("#formulario_contactos #ap").val(),
    			am : $("#formulario_contactos #am").val(),
     puestoTrabajo : $("#formulario_contactos #puestoTrabajo").val(),
    	  	 movil : $("#formulario_contactos #movil").val(),
    	 	correo : $("#formulario_contactos #correo").val(),
    	 	  sexo : $("#formulario_contactos #sexo").val()
    	}
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/contactos",
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			mostrar_mensaje_contactos(data);
			console.log(data);
			$("#btn_submit_contactos").prop("disabled",false);
			listar_contactos();
			limpiarformulario_contactos();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_contactos=function(){
	 var table = $('#contactos_tabla').DataTable({
		 "responsive": true,
		 "destroy": true,
			"sAjaxSource": "contactos",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
				  { "mData": "dni"},
			      { "mData": "nombre"},
				  { "mData": "ap" },
		          { "mData": "movil"},
		          { "mData": "puestoTrabajo"},
		          { "defaultContent": "<a  href='#' class='editar_contactos grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modaleliminarcontactos' id='eliminar_contactos' class='eliminar_contactos grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	obtener_data_editar_contactos("#contactos_tabla tbody",table);
	 obtener_data_eliminar_contactos("#contactos_tabla tbody",table);
	};
	var obtener_data_editar_contactos=function(tbody,table){
		$(tbody).on("click","a.editar_contactos",function(){
			 event.preventDefault();
				$("#opcion_contactos").text("Modificar");
			  var data=table.row($(this).parents("tr")).data();
			  console.log(data);
			var id = $("#formulario_contactos #id").val(data.id),
			   dni = $("#formulario_contactos #dni").val(data.dni),
			nombre = $("#formulario_contactos #nombre").val(data.nombre),
				ap = $("#formulario_contactos #ap").val(data.ap),
				am = $("#formulario_contactos #am").val(data.am),
	puestoTrabajo = $("#formulario_contactos #puestoTrabajo").val(puestoTrabajo),
    		 movil = $("#formulario_contactos #movil").val(data.movil),
    	    correo = $("#formulario_contactos #correo").val(data.correo),
    	    sexo = $("#formulario_contactos #sexo").val(data.correo)
    		
    		  $("#formulario_contactos #dni").focus();
    		  $("#formulario_contactos #nombre").focus();
			  $("#formulario_contactos #ap").focus();
			  $("#formulario_contactos #am").focus();
			  $("#formulario_contactos #puestoTrabajo").focus();
			  $("#formulario_contactos #movil").focus();
			  $("#formulario_contactos #correo").focus();
			  $("#btn_submit_contactos").val("modificar");
			 
		})
	}
	var obtener_data_eliminar_contactos=function(tbody,table){
		$(tbody).on("click","a.eliminar_contactos",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_contactos #id").val(data.id);
			var nombre=$("#eliminar_contactos #nombre").val(data.nombre);
			var nombre=$("#eliminar_contactos #ap").val(data.sector);
		})
		
	}

	var limpiarformulario_contactos = function(){
		$("#formulario_contactos #id").val("");
		$("#formulario_contactos #nombre").val("");
		$("#formulario_contactos #ap").val("");
		$("#formulario_contactos #am").val("");
		$("#formulario_contactos #dni").val("");
		$("#formulario_contactos #puestoTrabajo").val("");
		$("#formulario_contactos #movil").val("");
		$("#formulario_contactos #correo").val("");
		
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
	var mostrar_mensaje_contactos = function(mensaje){
		
		
		texto = "<strong>"+mensaje+"</strong>";
		color = "#379911";


$(".mensaje").html( texto ).css({"color": color });
$(".mensaje").fadeOut(5000, function(){
		$(this).html("");
		$(this).fadeIn(3000);
});			
}
