$(document).ready(function() {
	cargarTablaProcesos();
	eliminarProcesos();
	$("#procesosFrag #erroresProcesos").hide();
	$("#btnSubmitProcesos").click(function(event) {
		event.preventDefault();
		actualizarProcesos();
	})
	$("#formularioRoles #cancelar").click(function(event) {
		limpiarFormularioRoles();
		$("#rolesFrag #errores").fadeOut(300);
	})
	
	

});
function actualizarProcesos() {
	var formData = {
		id : $("#formularioProcesos #id").val(),
		nombre : $("#formularioProcesos #nombre").val(),
		descripcion : $("#formularioProcesos #descripcion").val(),
		logo : $("#formularioProcesos #logo").val(),
		enlace : $("#formularioProcesos #enlace").val()

	};

	$
			.ajax({
				type : "POST",
				url : "/procesos",
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
						$("#procesosFrag #error").html(lista);
						$("#procesosFrag #erroresProcesos").fadeIn(300);
						limpiarformulario();
					} else {
						if (data.mensaje == "existe") {
							var lista = "";
							$("#procesosFrag #error").html(lista);
							lista = "<p>ERROR --> Ya existe Rol con el mismo nombre</p>";
							$("#procesosFrag #error").html(lista);
							$("#procesosFrag #erroresProcesos").fadeIn(300);
						} else {
							$("#procesosFrag #erroresProcesos").fadeOut(300);
							cargarTablaProcesos();
							Materialize.toast(data.mensaje, 4000);
							limpiarFormularioProcesos();
						}
					}

				},
				error : function(e) {
					console.log("ERROR:", e);
				}
			})
}

var cargarTablaProcesos = function() {
	var table = $('#tablaProcesos')
			.DataTable(
					{
						responsive : true,
						"destroy" : true,
						"sAjaxSource" : "listaProcesos",
						"sAjaxDataProp" : "",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [
							{
								"mData" : "logo",
								"render" : function(mData, type, row) {
									return "<i  class='material-icons circle center'>"+ mData + "<i/>"
								}
							},
								{
									"mData" : "nombre"
								},
								{
									"mData" : "enlace"
								},
								{
									"defaultContent" : "<a  href='#' class='editarProcesos grey-text blue-text'><i class='material-icons'>edit</i></a>"
								},
								{
									"defaultContent" : "<a  href='#eliminar' id='colored-footer-button-dialog-activation' class='eliminar grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"
								}

						],
						"language" : idioma_español,

					});
	obtenerDatosModificarProcesos("#tablaProcesos tbody", table);
	obtenerDatosEliminar("#tablaProcesos tbody", table);
};

var eliminarProcesos = function() {
	$("#eliminarProceso").on("click", function() {
		var id = $("#idEliminar").val();
		$.ajax({
			type : "DELETE",
			url : "/procesos/" + id,
			success : function(mensaje) {
				cargarTablaProcesos();
				limpiarFormularioProcesos();
				Materialize.toast(mensaje, 4000);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}

		});
	})
}
var limpiarFormularioProcesos = function() {
	$("#formularioProcesos")[0].reset();
}

var obtenerDatosModificarProcesos = function(tbody, table) {
	$(tbody)
			.on(
					"click",
					"a.editarProcesos",
					function() {
						event.preventDefault();
						var data = table.row($(this).parents("tr")).data();
						var id = $("#formularioProcesos #id").val(data.id), nombre = $(
								"#formularioProcesos #nombre").val(data.nombre), 
								descripcion = $("#formularioProcesos #descripcion").val(data.descripcion),
						logo = $("#formularioProcesos #logo").val(data.logo),
						enlace = $("#formularioProcesos #enlace").val(data.enlace)
						
						$("#formularioProcesos #descripcion").focus();
						$("#formularioProcesos #nombre").focus();
						$("#formularioProcesos #logo").focus();
						$("#formularioProcesos #enlace").focus();
						var procesosAsignados = data.procesos;
						var procesosAll = $("#formularioProcesos #listaProcesos")
								.attr('value');

					})
}
var obtenerDatosEliminar = function(tbody, table) {
	$(tbody).on(
			"click",
			"a.eliminar",
			function() {
				var data = table.row($(this).parents("tr")).data();
				var id = $("#idEliminar").val(data.id);
		
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
