$(document).ready( function () {
	cargarTablaEmpresas();
	eliminarEmpresas();
	limpiarformularioEmpresas();
	$("#btnSubmitEmpresa").click(function(event){
		event.preventDefault();
		actualizarEmpresa();
		
	})
		$("#cancelarEmpresa").click(function(event){
		event.preventDefault();
		$("#btnSubmitEmpresa").val("Nuevo");
		limpiarformularioEmpresas();
		
	})
		$("#empresasFrag #erroresEmpresas").hide();

});


var eliminarEmpresas = function(){
	$("#modalEliminarEmpresa #eliminarEmpresa").on("click", function(){
		 var id=$("#modalEliminarEmpresa #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/empresas/"+id,
				success : function(result) {
					cargarTablaEmpresas();
					limpiarformularioEmpresas
					Materialize.toast(data, 4000);
						
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function actualizarEmpresa(){
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
			var errores = data.lista_errores;
			var lista = "";
			if (errores.length != 0) {
				for (let i = 0; i < errores.length; i++) {
						lista = lista + "<p>ERROR : campo :"
								+ errores[i].field.bold().toUpperCase()+ " : --> "
								+ errores[i].defaultMessage + "</p>"
					
				}
				$("#empresasFrag #error").html(lista);
				$("#empresasFrag #erroresEmpresas").fadeIn(300);
				limpiarformularioEmpresas();
			} else {
				if(data.mensaje=="existe"){
					var lista = "";
					$("#empresasFrag #error").html(lista);
					lista ="<p>ERROR --> Ya existe Empresa con el mismo nombre</p>";
					$("#empresasFrag #error").html(lista);
					$("#empresasFrag #erroresEmpresas").fadeIn(300);
				}else{
					
				
				$("#empresasFrag #erroresEmpresas").fadeOut(300);
				cargarTablaEmpresas();
				limpiarformularioEmpresas();
				Materialize.toast(data.mensaje, 4000);
				
				}
			}

		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var cargarTablaEmpresas=function(){
	 var table = $('#tablaEmpresas').DataTable({
		 "responsive": true,
		 "destroy": true,
			"sAjaxSource": "listaempresas",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "nombre"},
		          { "mData": "especialidades"},
		          { "mData": "sector" },
		          { "defaultContent": "<a  href='#' class='editar_empresas grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modalEliminarEmpresa' class='eliminarEmpresa grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	obtener_data_editar_empresas("#tablaEmpresas tbody",table);
	 obtenerDatosEliminarEmpresa("#tablaEmpresas tbody",table);
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
	var obtenerDatosEliminarEmpresa=function(tbody,table){
		$(tbody).on("click","a.eliminarEmpresa",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#modalEliminarEmpresa #id").val(data.id);
		})
		
	}

	var limpiarformularioEmpresas = function(){
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
