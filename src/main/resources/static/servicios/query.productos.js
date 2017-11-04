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
		
	});
	
	$("#btnActualizarDatosProducto").click(function(event){
		event.preventDefault();
		actualizarProductosVentas();
	})
	
	
	

	$("#productosFrag #erroresProductos").hide();
	$("#imgProducto").hide();
});

function actualizarProductosVentas(){
	var formData = {
    			id : $("#modalProductosVentas #id").val(),
    		addStock : $("#modalProductosVentas #addStock").val(),
    		fechaVencimientoMod : $("#modalProductosVentas #fechaVencimientoMod").val(),
    		costoMod : $("#modalProductosVentas #costoMod").val(),
    		gananciaMod : $("#modalProductosVentas #gananciaMod").val()
    	}
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/productoVentas",
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			cargarTablaProductos();
			Materialize.toast(data, 4000);
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
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
			      { "mData": "categoria.nombre"},
			      { "mData": "marca.nombre"},
			      { "mData": "sector.nombre"},
			      { "mData": "costos[]"},
			      { "mData": "porcentajeGanancia[]"},
		/*	      { "mData": "costos","render":function(mData,type,row){
		        	  return "<span>"+mData.pop();+"'</span>"
		        	  }
				  },
				  
				  { "mData": "porcentajeGanancia","render":function(mData,type,row){
		        	  return "<span>"+mData.pop();+"'</span>"
		        	  }
				  },*/
				  { "mData": "stock"},
		          { "defaultContent": "<a  href='#' class='editarProductos grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modalEliminarProductos'  class='eliminarProductos grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"},
				  { "defaultContent": "<a  href='#modalProductosVentas'  class='ventasProducto grey-text modal-trigger'><i class='material-icons dp48'>settings</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	 $(".tp select").val('5');
	  $(".tp select").addClass("browser-default");
	  $(".tp select").material_select();
	
	obtenerDatosEditarProductos("#tablaProductos tbody",table);
	 obtenerDatosEliminarProductos("#tablaProductos tbody",table);
	 ventasProducto("#tablaProductos tbody",table);
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
			  
			  var presentacion=$("#formularioProductos #costo").val(data.costo);
			  var presentacion=$("#formularioProductos #porcentajeGanancia").val(data.pocentajeGanancia);
			  
			 /* var presentacion=$("#formularioProductos #costo").val(data.costo);
			  var presentacion=$("#formularioProductos #porcentajeGanancia").val(data.pocentajeGanancia);
			  var presentacion=$("#formularioProductos #precioVenta").val(data.precioVenta);
			  var presentacion=$("#formularioProductos #stock").val(data.stock);
			  var presentacion=$("#formularioProductos #fechaVencimiento").val(data.stock);*/
			 
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
			  $("#formularioProductos #costo").focus();
			  $("#formularioProductos #porcentajeGanancia").focus();
	/*		  $("#formularioProductos #costo").focus();
			  $("#formularioProductos #porcentajeGanancia").focus();
			  $("#formularioProductos #precioVenta").focus();
			  $("#formularioProductos #stock").focus();
			  $("#formularioProductos #fechaVencimineto").focus();*/
			  
			  $("#imgInputProductos").fadeOut(400);
			  $("#imagenEditarProducto").attr("src","assets/images/productos/"+data.foto);
			  $("#imgProducto").fadeIn(3000);
			  
			  
			
			  
		})
	}
	
	var ventasProducto=function(tbody,table){
		$(tbody).on("click","a.ventasProducto",function(){
			 event.preventDefault();
			  var data=table.row($(this).parents("tr")).data();
			  var id=$("#modalProductosVentas #id").val(data.id);
			  $("#imgVentasProducto").attr("src","assets/images/productos/"+data.foto);
			  var nombre=$("#modalProductosVentas #nombre").text(data.nombre);
			  var detalles=$("#modalProductosVentas #detalle").text(data.detalles);
			  var contenidoNeto=$("#modalProductosVentas #contenidoNeto").text(data.contenidoneto);
			  var stock=$("#modalProductosVentas #stockActual").val(data.stock);
			  var fechaVencimiento=$("#modalProductosVentas #fechaVencimiento").val(data.fechaVencimiento.pop());
			  var costos=$("#modalProductosVentas #costoActual").val(data.costos.pop());
			  var porcentajeGanancia=$("#modalProductosVentas #gananciaActual").val(data.porcentajeGanancia.pop());
			  var costos=$("#modalProductosVentas #p").val(data.proveedor.id);
			  
			  
			  $("#modalProductosVentas #stockActual").focus();
			  $("#modalProductosVentas #fechaVencimiento").focus();
			  $("#modalProductosVentas #costoActual").focus();
			  $("#modalProductosVentas #gananciaActual").focus();
			 
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
		  $("#formularioProductos #costo").val("");
		  $("#formularioProductos #porcentajeGanancia").val("");
/*		  $("#formularioProductos #costo").val("");
		  $("#formularioProductos #porcentajeGanancia").val("");
		  $("#formularioProductos #precioVenta").val("");
		  $("#formularioProductos #stock").val("");
		  $("#formularioProductos #fechaVencimiento").val("");*/
		
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