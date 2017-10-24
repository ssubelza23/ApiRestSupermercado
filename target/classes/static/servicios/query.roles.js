$(document).ready(function() {
	cargarTablaRoles();
	eliminarUsuario();
	$("#rolesFrag #errores").hide();
	$("#btn_submit_usuario").click(function(event) {
		event.preventDefault();
		actualizarRoles();
	})

	$("#formularioRoles #cancelar").click(function(event) {
		limpiarFormularioRoles();
		$("#rolesFrag #errores").fadeOut(300);
	})

});
function actualizarRoles() {
	var form = $("#formularioRoles")[0];
	var data = new FormData(form);
	$
			.ajax({
				type : "POST",
				enctype : "multipart/form-data",
				url : "/roles",
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
						$("#rolesFrag #error").html(lista);
						$("#rolesFrag #errores").fadeIn(300);
						limpiarformulario();
					} else {
						$("#rolesFrag #errores").fadeOut(300);
						cargarTablaRoles();
						Materialize.toast(data.mensaje, 4000);
						limpiarFormularioRoles();
						
					}

				},
				error : function(e) {
					console.log("ERROR:", e);
				}
			})
}

var cargarTablaRoles = function() {
	var table = $('#tablaRoles')
			.DataTable(
					{
						responsive : true,
						"destroy" : true,
						"sAjaxSource" : "listaRoles",
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
									"mData" : "roles[0].nombre"
								},
								{
									"defaultContent" : "<a  href='#' class='editar grey-text'><i class='material-icons'>edit</i></button>"
								},
								{
									"defaultContent" : "<a  href='#modal1' id='eliminar' class='eliminar grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"
								}

						],
						"language" : idioma_español,

					});
	obtenerDatosModificar("#tablaRoles tbody", table);
	obtener_datos_eliminar("#tablaRoles tbody", table);
};

var eliminarUsuario = function() {
	$("#btn_eliminar_usuario").on("click", function() {
		var id = $("#eliminarUsuario #id").val();
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

var obtenerDatosModificar = function(tbody, table) {
	$(tbody).on("click","a.editar",
			function() {
				event.preventDefault();
					var data = table.row($(this).parents("tr")).data();
					var id = $("#formularioRoles #id").val(data.id), 
					nombre = $("#formularioRoles #Nombre").val(data.nombre),
						ap = $("#formularioRoles #Ap").val(data.ap), 
						am = $("#formularioRoles #Am").val(data.am), 
						dni = $("#formularioRoles #dni").val(data.dni), 
						correo = $("#formularioRoles #Correo").val(data.correo), 
						telefono = $(
								"#formularioRoles #Telefono").val(
								data.telefono), direccion = $(
								"#formularioRoles #Direccion").val(
								data.direccion), fechaNacimiento = $(
								"#formularioRoles #fechaNacimiento").val(
								data.fechaNacimiento), id_dato = $(
								"#formularioRoles #id_dato").val(
								data.datos.id), sexo = $(
								"#formularioRoles #Sexo").val(data.sexo), login = $(
								"#formularioRoles #Login").val(
								data.datos.login), clave = $(
								"#formularioRoles #Clave").val(
								data.datos.clave), clave = $(
								"#formularioRoles #id_Rol").val(
								data.roles[0].id), id_rol = $(
								"#formularioRoles #id_Rol").val(
								data.roles[0].id)

					})
}
var obtener_datos_eliminar = function(tbody, table) {
	$(tbody).on(
			"click",
			"a.eliminar",
			function() {

				var data = table.row($(this).parents("tr")).data();
				var id = $("#eliminarUsuario #id").val(data.id);
				var usuario = $("#eliminarUsuario #usuario_eliminar").val(
						data.nombre + " " + data.ap + " " + data.am);
				var dni = $("#eliminarUsuario #dni_eliminar").val(data.dni);
				var rol = $("#eliminarUsuario #rol_eliminar").val(
						data.roles[0].nombre);
				var dni = data.dni;
				var src = "/assets/images/usuarios/" + dni + ".jpg";
				$("#eliminar_usuario #img").attr("src", src);
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
