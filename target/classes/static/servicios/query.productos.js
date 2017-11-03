$(document).ready( function () {
	cargarTablaProductos();
	eliminarProducto();
	limpiarformularioProductos();
	$("#btnSubmitProductos").click(function(event){
		event.preventDefault();
		actualizarProductos();
	})
		$("#cancelarProductos").click(function(event){
		$("#productosFrag #erroresProductos").fadeOut(300);
		event.preventDefault();
		$("#btnSubmitProductos").val("Nuevo");
		limpiarformularioProductos();
 		$("#formularioProductos #imagenEditar").fadeOut(300);
		
	})
	
			$("a.cambiarImagen").click(function(event){
				$("#imgInputProductos").fadeIn(400);
		
	})

	$("#productosFrag #erroresProductos").hide();
	$("#imgProducto").hide();
});


var eliminarProducto = function(){
	$("#btnEliminarProducto").on("click", function(){
		var id=$("#modalEliminarProductos #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/productos/"+id,
			success : function(data) {
				cargarTablaProductos();
				limpiarformularioProductos();
				Materialize.toast(data, 4000);
				
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function actualizarProductos(){
	var form=$("#formularioProductos")[0];
	var data=new FormData(form);
	$.ajax({
		type : "POST",
		enctype:"multipart/form-data",
		url : "/productos",
		data : data,
		contentType : "application/json",
		processData: false,
		contentType: false,
		cache: false,
		timeout: 600000,
		success:function(data){
			var errores = data.lista_errores;
			var lista = "";
			if (errores.length != 0) {
				for (let i = 0; i < errores.length; i++) {
						lista = lista + "<p>ERROR : campo :"
								+ errores[i].field.bold().toUpperCase()+ " : --> "
								+ errores[i].defaultMessage + "</p>"
					
				}
				$("#productosFrag #error").html(lista);
				$("#productosFrag #erroresProductos").fadeIn(300);
				
			} else {
				if(data.mensaje=="existe"){
					var lista = "";
					$("#productosFrag #error").html(lista);
					lista ="<p>ERROR --> Ya existe Empresa con el mismo nombre</p>";
					$("#productosFrag #error").html(lista);
					$("#productosFrag #erroresProductos").fadeIn(300);
				}else{
				$("#productosFrag #erroresProductos").fadeOut(300);
				cargarTablaProductos();
				Materialize.toast(data.mensaje, 4000);
				}
			}
			
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
	
}
var cargarTablaProductos=function(){
	 var table = $('#tablaProductos').DataTable({
		 "destroy": true,
		 "cache": false,
			"sAjaxSource": "listaproductos",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"lengthMenu": [[5, 10, 20, 25, 50, -1], [5, 10, 20, 25, 50, "Todos"]],
			"aoColumns": [
			      { "mData": "codigoBarra"},
			      { "mData": "nombre"},
			      { "mData": "contenidoneto"},
			      { "mData": "presentacion"},
			      { "mData": "detalles"},
			      { "mData": "sector.nombre"},
		          { "defaultContent": "<a  href='#' class='editarProductos grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modalEliminarProductos'  class='eliminarProductos grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	 $(".tp select").val('5');
	  $(".tp select").addClass("browser-default");
	  $(".tp select").material_select();
	
	obtenerDatosEditarProductos("#tablaProductos tbody",table);
	 obtenerDatosEliminarProductos("#tablaProductos tbody",table);
	};
	var obtenerDatosEditarProductos=function(tbody,table){
		$(tbody).on("click","a.editarProductos",function(){
			 event.preventDefault();
				$("#opcionProductos").text("Modificar");
			  var data=table.row($(this).parents("tr")).data();
			  var id=$("#formularioProductos #id").val(data.id);
			  var nombre=$("#formularioProductos #nombre").val(data.nombre);
			  var codigoBarra=$("#formularioProductos #codigoBarras").val(data.codigoBarra);
			  var pesoneto=$("#formularioProductos #contenidoNeto").val(data.contenidoneto);
			  var detalles=$("#formularioProductos #detalles").val(data.detalles);
			  var presentacion=$("#formularioProductos #presentacion").val(data.presentacion);
			 
			  $("#idCategoria").val(data.categoria.id);
			  $("#idSector").val(data.sector.id);
			  $("#idMarca").val(data.marca.id);
			  $("#idProveedor").val(data.proveedor.id);
			  
			  $("#formularioProductos #codigoBarras").focus();
			  $("#formularioProductos #nombre").focus();
			  $("#formularioProductos #id").focus();
			  $("#formularioProductos #contenidoNeto").focus();
			  $("#formularioProductos #detalles").focus();
			  $("#formularioProductos #presentacion").focus();
			  
			 $("#imgInputProductos").fadeOut(400);
			  $("#imagenEditarProducto").attr("src","assets/images/productos/"+data.foto);
			  $("#imgProducto").fadeIn(3000);
			  
			  
			
			  
		})
	}
	var obtenerDatosEliminarProductos=function(tbody,table){
		$(tbody).on("click","a.eliminarProductos",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#modalEliminarProductos #id").val(data.id);
			
			
		})
		
	}

	var limpiarformularioProductos = function(){
			$("#formularioProductos #codigoBarras").val("");
		  $("#formularioProductos #nombre").val("");
		  $("#formularioProductos #id").val("");
		  $("#formularioProductos #contenidoNeto").val("");
		  $("#formularioProductos #detalles").val("");
		  $("#formularioProductos #presentacion").val("");
		
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