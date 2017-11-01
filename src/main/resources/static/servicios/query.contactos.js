$(document).ready(function() {
	cargarTablaContactos();
	eliminarContactos();
	$("#contactosFrag #erroresContactos").hide();
	$("#btnSubmitContactos").click(function(event) {
		event.preventDefault();
		actualizarContactos();
	})
	$("#formularioContactos #cancelar").click(function(event) {
		limpiarformularioContactos();
		$("#contactosFrag #errores").fadeOut(300);
	})
	
	

});
function actualizarContactos() {
	var formData = {
		id : $("#formularioContactos #id").val(),
		dni : $("#formularioContactos #dni").val(),
		nombre : $("#formularioContactos #nombre").val(),
		ap : $("#formularioContactos #ap").val(),
		am : $("#formularioContactos #am").val(),
		movil : $("#formularioContactos #movil").val(),
		sexo : $("#formularioContactos #sexo").val()

	};
console.log(formData);
	$
			.ajax({
				type : "POST",
				url : "/contactos",
				data : JSON.stringify(formData),
				contentType : "application/json",
				success : function(data) {
					var errores = data.lista_errores;
					var lista = "";
					if (errores.length != 0) {
						for (let i = 0; i < errores.length; i++) {
							lista = lista + "<p>ERROR : campo :"
									+ errores[i].field.bold().toUpperCase()
									+ " : --> " + errores[i].defaultMessage
									+ "</p>"

						}
						$("#contactosFrag #error").html(lista);
						$("#contactosFrag #erroresContactos").fadeIn(300);
						limpiarformulario();
					} else {
						if (data.mensaje == "existe") {
							var lista = "";
							$("#contactosFrag #error").html(lista);
							lista = "<p>ERROR --> Ya existe un Registro con el mismo DNI</p>";
							$("#contactosFrag #error").html(lista);
							$("#contactosFrag #erroresContactos").fadeIn(300);
						} else {
							$("#contactosFrag #erroresContactos").fadeOut(300);
							cargarTablaContactos();
							Materialize.toast(data.mensaje, 4000);
							limpiarFormularioContactos();
						}
					}

				},
				error : function(e) {
					console.log("ERROR:", e);
				}
			})
}

var cargarTablaContactos = function() {
	var table = $('#tablaContactos')
			.DataTable(
					{
						responsive : true,
						"destroy" : true,
						"sAjaxSource" : "listacontactos",
						"sAjaxDataProp" : "",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [
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
									"mData" : "movil"
								},
								{
									"defaultContent" : "<a  href='#' class='editarContactos grey-text blue-text'><i class='material-icons'>edit</i></a>"
								},
								{
									"defaultContent" : "<a  href='#modalEliminarContactos' id='colored-footer-button-dialog-activation' class='eliminarContactos grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"
								}

						],
						"language" : idioma_español,

					});
	obtenerDatosModificarContactos("#tablaContactos tbody", table);
	obtenerDatosEliminarContactos("#tablaContactos tbody", table);
};

var eliminarContactos = function() {
	$("#btnEliminarContacto").on("click", function() {
		var id = $("#modalEliminarContactos #id").val();
		console.log(id);
		$.ajax({
			type : "DELETE",
			url : "/contactos/" + id,
			success : function(mensaje) {
				cargarTablaContactos();
				limpiarFormularioContactos();
				Materialize.toast(mensaje, 4000);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}

		});
	})
}
var limpiarFormularioContactos = function() {
	$("#formularioContactos")[0].reset();
}

var obtenerDatosModificarContactos = function(tbody, table) {
	$(tbody)
			.on(
					"click",
					"a.editarContactos",
					function() {
						event.preventDefault();
						var data = table.row($(this).parents("tr")).data();
						var id = $("#formularioContactos #id").val(data.id), nombre = $(
								"#formularioContactos #nombre").val(data.nombre), 
								dni = $("#formularioContactos #dni").val(data.dni),
						ap = $("#formularioContactos #ap").val(data.ap),
						am = $("#formularioContactos #am").val(data.am),
						sexo = $("#formularioContactos #sexo").val(data.sexo),
						movil = $("#formularioContactos #movil").val(data.movil)
						
						$("#formularioContactos #nombre").focus();
						$("#formularioContactos #ap").focus();
						$("#formularioContactos #am").focus();
						$("#formularioContactos #dni").focus();
						$("#formularioContactos #movil").focus();
						
					})
}
var obtenerDatosEliminarContactos = function(tbody, table) {
	$(tbody).on(
			"click",
			"a.eliminarContactos",
			function() {
				var data = table.row($(this).parents("tr")).data();
				console.log(data);
				var id = $("#modalEliminarContactos #id").val(data.id);
		
			});

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
