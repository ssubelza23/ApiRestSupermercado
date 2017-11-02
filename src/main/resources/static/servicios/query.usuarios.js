$(document).ready(function() {
	cargarTablaUsuarios();
	eliminarUsuario();
	$("#usuariosFrag #errores").hide();
	$("#btn_submit_usuario").click(function(event) {
		event.preventDefault();
		actualizarUsuarios();
	})

	$("#formularioUsuarios #cancelar").click(function(event) {
		limpiarFormularioUsuarios();
		$("#usuariosFrag #errores").fadeOut(300);
	})

});
function actualizarUsuarios() {
	var form = $("#formularioUsuarios")[0];
	var data = new FormData(form);
	$
			.ajax({
				type : "POST",
				enctype : "multipart/form-data",
				url : "/usuarios",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					var errores = data.lista_errores;
					var lista = "";
					if (errores.length != 0) {
						for (let i = 0; i < errores.length; i++) {
							if (errores[i].field == "dni") {
								lista = lista
										+ "<p>ERROR : campo : "+errores[i].field.bold().toUpperCase()+" : --> Introduzca número de DNI válido</p>"
							} else {
								lista = lista + "<p>ERROR : campo :"
										+ errores[i].field.bold().toUpperCase()+ " : --> "
										+ errores[i].defaultMessage + "</p>"
							}
						}
						$("#usuariosFrag #error").html(lista);
						$("#usuariosFrag #errores").fadeIn(300);
						limpiarformulario();
					} else {
						$("#usuariosFrag #errores").fadeOut(300);
						cargarTablaUsuarios();
						Materialize.toast(data.mensaje, 4000);
						limpiarFormularioUsuarios();
						
					}

				},
				error : function(e) {
					console.log("ERROR:", e);
				}
			})
}

var cargarTablaUsuarios = function() {
	var table = $('#tablaUsuarios')
			.DataTable(
					{
						responsive : true,
						"destroy" : true,
						"sAjaxSource" : "listaUsuarios",
						"sAjaxDataProp" : "",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [
								{
									"mData" : "dni",
									"render" : function(mData, type, row) {
										return "<img class='circle' width='30%' height='30%' src='/assets/images/usuarios/"
												+ mData + ".jpg'/>"
									}
								},
								{
									"mData" : "dni"
								},
								{
									"mData" : "nombre"
								},
								{
									"mData" : "ap"
								},
								{
									"mData" : "am"
								},
								{
									"mData" : "roles[-].nombre"
								},
								{
									"defaultContent" : "<a  href='#' class='editarUsuarios grey-text blue-text'><i class='material-icons'>edit</i></a>"
								},
								{
									"defaultContent" : "<a  href='#modalEliminarUsuarios' class='eliminarUsuarios grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"
								}

						],
						"language" : idioma_español,

					});
	obtenerDatosModificarUsuarios("#tablaUsuarios tbody", table);
	obtenerDatosEliminarUsuarios("#tablaUsuarios tbody", table);
};

var eliminarUsuario = function() {
	$("#btnEliminarUsuarios").on("click", function() {
		var id = $("#modalEliminarUsuarios #id").val();
		$.ajax({
			type : "DELETE",
			url : "/usuarios/" + id,
			success : function(mensaje) {
				cargarTablaUsuarios();
				limpiarFormularioUsuarios();
				Materialize.toast(mensaje, 4000);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}

		});
	})
}
var limpiarFormularioUsuarios = function() {
		   $("#formularioUsuarios")[0].reset();
}

var obtenerDatosModificarUsuarios = function(tbody, table) {
	$(tbody).on("click","a.editarUsuarios",
			function() {
		console.log("click");
				event.preventDefault();
					var data = table.row($(this).parents("tr")).data();
					var id = $("#formularioUsuarios #id").val(data.id), 
					nombre = $("#formularioUsuarios #Nombre").val(data.nombre),
						ap = $("#formularioUsuarios #Ap").val(data.ap), 
						am = $("#formularioUsuarios #Am").val(data.am), 
						dni = $("#formularioUsuarios #dni").val(data.dni), 
						correo = $("#formularioUsuarios #Correo").val(data.correo), 
						telefono = $(
								"#formularioUsuarios #Telefono").val(
								data.telefono), direccion = $(
								"#formularioUsuarios #Direccion").val(
								data.direccion), fechaNacimiento = $(
								"#formularioUsuarios #fechaNacimiento").val(
								data.fechaNacimiento), id_dato = $(
								"#formularioUsuarios #id_dato").val(
								data.datos.id), sexo = $(
								"#formularioUsuarios #Sexo").val(data.sexo), login = $(
								"#formularioUsuarios #Login").val(
								data.datos.login), clave = $(
								"#formularioUsuarios #Clave").val(
								data.datos.clave), clave = $(
								"#formularioUsuarios #id_Rol").val(
								data.roles[0].id), id_rol = $(
								"#formularioUsuarios #id_Rol").val(
								data.roles[0].id)

					})
}

	var obtenerDatosEliminarUsuarios=function(tbody,table){
		$(tbody).on("click","a.eliminarUsuarios",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#modalEliminarUsuarios #id").val(data.id);
			
			
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
