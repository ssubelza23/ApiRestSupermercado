$(document).ready(function() {
	listar_productos();
	eliminar_productos();
	
	limpiarformulario_productos();
	$("#btn_submit_productos").click(function(event) {
		event.preventDefault();
		fire_ajax_submit_productos();

	})
	$("#cancelar_editar_productos").click(function(event) {
		event.preventDefault();

		$("#btn_submit_productos").val("Nuevo");
		limpiarformulario_productos();

	})
});

var eliminar_productos = function() {

	$("#btn_eliminar_productos").on("click", function() {

		var id = $("#eliminar_productos #id").val();
		console.log(id);
		$.ajax({
			type : "DELETE",
			url : "/productos/" + id,
			success : function(result) {
				console.log(result);
				listar_productos();
				mostrar_mensaje_productos(result);
				limpiarformulario_productos();
				Materialize.toast('Bien, Producto eliminado', 4000);
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}

		});
	})
}
function fire_ajax_submit_productos() {
	var form=$("#formulario_usuario")[0];
	var data=new FormData(form);
	$("#btn_submit_usuario").prop("disabled",true);
	$.ajax({
		type:"POST",
		enctype:"multipart/form-data",
		url:"/productos",
		data:data,
		processData: false,
		contentType: false,
		cache: false,
		timeout: 600000,
		success:function(data){
			console.log(data);
			$("#btn_submit_usuario").prop("disabled",false);
			listar();
			Materialize.toast('Bien, nuevo producto añadido', 4000);
			limpiarformulario();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_productos = function() {
	var table = $('#productos_tabla')
			.DataTable(
					{
						"responsive" : true,
						"destroy" : true,
						"sAjaxSource" : "listaproductos",
						"sAjaxDataProp" : "",
						"order" : [ [ 0, "asc" ] ],
						"aoColumns" : [
								{
									"mData" : "empresa.nombre"
								},
								{
									"mData" : "contactos[, ].nombre"
								},
								{
									"mData" : "marcas[, ].nombre"
								},
								{
									"mData" : "detalles"
								},
								{
									"defaultContent" : "<a  href='#' class='editar_productos grey-text'><i class='material-icons'>edit</i></button>"
								},
								{
									"defaultContent" : "<a  href='#modaleliminarproductos' id='eliminar_productos' class='eliminar_productos grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"
								}

						],

						"language" : idioma_español,

					});
	/*
	 * $("select").val('10'); $('select').addClass("browser-default");
	 * $('select').material_select();
	 */
	obtener_data_editar_productos("#productos_tabla tbody", table);
	obtener_data_eliminar_productos("#productos_tabla tbody", table);
};
var obtener_data_editar_productos = function(tbody, table) {
	$(tbody).on("click", "a.editar_productos", function() {
		event.preventDefault();
		$("#opcion_productos").text("Modificar");
		var data = table.row($(this).parents("tr")).data();
		console.log(data);
		var id = $("#formulario_productos #id").val(data.id)

	})
}
var obtener_data_eliminar_productos = function(tbody, table) {
	$(tbody).on("click", "a.eliminar_productos", function() {
		var data = table.row($(this).parents("tr")).data();
		var id = $("#eliminar_productos #id").val(data.id);

	})

}

var limpiarformulario_productos = function() {
	$("#formulario_productos #id").val("");
	$("#formulario_productos #idempresas").val("");
	$("#formulario_productos #idmarcas").val("");
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
var mostrar_mensaje_productos = function(mensaje) {

	texto = "<strong>" + mensaje + "</strong>";
	color = "#379911";

	$(".mensaje").html(texto).css({
		"color" : color
	});
	$(".mensaje").fadeOut(5000, function() {
		$(this).html("");
		$(this).fadeIn(3000);
	});
}
