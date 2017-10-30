$(document).ready(function() {
	cargarTablaRoles();
	eliminarRol();
	
	  $("#btnAddProRol").click(function(event) {
		  addProrol(delProcesos,aProcesos);
	  });
	  $("#btnF").hide();
	 var delProcesos=[];
	 var cont=0;
	$('.procesosAsignados').click(function(event) {
	    if(delProcesos.includes($(this).attr('value'))){
	    	var i=delProcesos.indexOf($(this).attr('value'));
	    	console.log(i);
	    	delProcesos.splice(i,1);
	    	  console.log("Si se encuentra");
	    	  $('a',this).show(100);
	    	  
	 		 
	 }else{
		 		$('a',this).hide(100);
			 $(this).removeClass("green-text");
 		delProcesos.push($(this).attr('value'));
	 }
	  })
var aProcesos=[];
	$('.procesosNoAsignados  a',this).hide(100);
	$('.procesosNoAsignados').click(function(event) {
	    console.log($(this).attr('value'))
	    console.log(aProcesos.length);
	    if(aProcesos.includes($(this).attr('value'))){
	    	var i=aProcesos.indexOf($(this).attr('value'));
	    	
	    	aProcesos.splice(i,1);
	    	 
	    		$('a',this).hide(100);
	    	 
	 }else{
		
		  $('a',this).show(100);
		
 		aProcesos.push($(this).attr('value'));
	 }
	    

	    	  
	console.log(aProcesos);
	  });

	
	$("#rolesFrag #errores").hide();
	$("#btn_submit_roles").click(function(event) {
		event.preventDefault();
		actualizarRoles(aProcesos);
	})
	$("#formularioRoles #cancelar").click(function(event) {
		limpiarFormularioRoles();
		$("#rolesFrag #errores").fadeOut(300);
	})
});
function addProrol(delProcesos, aProcesos){
	
	var idMod=$("#idRolModificar").val();
	var formData={
			eliminar:delProcesos,
			añadir:aProcesos,
			idRolModificar:idMod
	}
	$.ajax({
		type : "POST",
		url : "/addProRol",
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			Materialize.toast(data, 4000);

		},
		error : function(e) {
			console.log("ERROR:", e);
		}
	})
}
function actualizarRoles(aProcesos) {
	console.log(aProcesos)
	var formData = {
			id:$("#formularioRoles #id").val(),
			nombre:$("#formularioRoles #nombre").val(),
			descripcion:$("#formularioRoles #descripcion").val(),
			
	}
	
			console.log(formData+"*************");
			$.ajax({
				type : "POST",
				url : "/roles",
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
						$("#rolesFrag #error").html(lista);
						$("#rolesFrag #errores").fadeIn(300);
						limpiarformulario();
					} else {
						if(data.mensaje=="existe"){
							var lista = "";
							$("#rolesFrag #error").html(lista);
							lista ="<p>ERROR --> Ya existe Rol con el mismo nombre</p>";
							$("#rolesFrag #error").html(lista);
							$("#rolesFrag #errores").fadeIn(300);
						}else{
							
						
						$("#rolesFrag #errores").fadeOut(300);
						cargarTablaRoles();
						Materialize.toast(data.mensaje, 4000);
						limpiarFormularioRoles();
						}
					}

				},
				error : function(e) {
					console.log("ERROR:", e);
				}
			})
}

function asignarProcesos(idRol){
	$.ajax({
		type : "GET",
		url : "/asignarProcesos/" + idRol,
		success : function(mensaje) {
			
		},
		error : function(e) {
			alert("Error!")
			console.log("ERROR: ", e);
		}

	});
}

var cargarTablaRoles = function() {
	var table = $('#tablaRoles').DataTable(
					{
						responsive : true,
						"destroy" : true,
						"sAjaxSource" : "listaRoles",
						"sAjaxDataProp" : "",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [
								{
									"mData" : "nombre"
								},
								{
									"mData" : "descripcion"
								},
								{
									"defaultContent" : "<a  href='#' class='editarRoles grey-text blue-text'><i class='material-icons'>edit</i></a>"
								},
								{
									"defaultContent" : "<a  href='#modal1' id='eliminar' class='eliminar grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"
								}

						],
						"language" : idioma_español,

					});
	obtenerDatosModificarRoles("#tablaRoles tbody", table);
	obtener_datos_eliminar("#tablaRoles tbody", table);
};

var eliminarRol = function() {
	$("#btn_eliminar_rol").on("click", function() {
		var id = $("#eliminarRol #id").val();
		$.ajax({
			type : "DELETE",
			url : "/roles/" + id,
			success : function(mensaje) {
				cargarTablaRoles();
				limpiarFormularioRoles();
				Materialize.toast(mensaje, 4000);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}

		});
	})
}
var limpiarFormularioRoles = function() {
		   $("#formularioRoles")[0].reset();
}

var obtenerDatosModificarRoles = function(tbody, table) {
	$(tbody).on("click","a.editarRoles",
			function() {
				event.preventDefault();
					var data = table.row($(this).parents("tr")).data();
					var id = $("#formularioRoles #id").val(data.id), 
					nombre = $("#formularioRoles #nombre").val(data.nombre),
					descripcion = $("#formularioRoles #descripcion").val(data.descripcion)
					$("#formularioRoles #descripcion").focus();
					$("#formularioRoles #nombre").focus();
					var procesosAsignados=data.procesos;
					var procesosAll=$("#formularioRoles #listaProcesos").attr('value');
				
				
})
}
var obtener_datos_eliminar = function(tbody, table) {
	$(tbody).on(
			"click",
			"a.eliminar",
			function() {

				var data = table.row($(this).parents("tr")).data();
				var id = $("#eliminarRol #id").val(data.id);
				var nombre = $("#eliminarRol #nombre").val(data.nombre);
				var descripcion = $("#eliminarUsuario #dni_eliminar").val(data.descripcion);
			})

}
var idioma_español = {
	"sProcessing" : "Procesando...",
	"sLengthMenu" : "Mostrar _MENU_ registros",
	"sZeroRecords" : "No se encontraron resultados",
	"sEmptyTable" : "Ningún dato disponible en esta tabla",
	"sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	"sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
	"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
	"sInfoPostFix" : "",
	"sSearch" : "Buscar:",
	"sUrl" : "",
	"sInfoThousands" : ",",
	"sLoadingRecords" : "Cargando...",
	"oPaginate" : {
		"sFirst" : "Primero",
		"sLast" : "Último",
		"sNext" : "Siguiente",
		"sPrevious" : "Anterior"
	},
	"oAria" : {
		"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
		"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
	}
};
