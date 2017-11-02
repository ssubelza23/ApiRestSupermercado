$(document).ready( function () {
	cargarTablaMarcas();
	eliminarMarca();
	limpiarformularioMarcas();
	$("#btnSubmitMarcas").click(function(event){
		event.preventDefault();
		actualizarMarcas();
		
	})
		$("#cancelarMarcas").click(function(event){
			$("#marcasFrag #erroresMarcas").fadeOut(300);
		event.preventDefault();
		$("#btnSubmitMarcas").val("Nuevo");
		limpiarformularioMarcas();
 		$("#formularioMarcas #imagenEditar").fadeOut(300);
		
	})

	$("#marcasFrag #erroresMarcas").hide();
});


var eliminarMarca = function(){
	$("#btnEliminarMarca").on("click", function(){
		 var id=$("#modalEliminarMarcas #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/marcas/"+id,
			success : function(data) {
				cargarTablaMarcas();
				Materialize.toast(data, 4000);
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function actualizarMarcas(){
	var form=$("#formularioMarcas")[0];
	var data=new FormData(form);
	$.ajax({
		type : "POST",
		enctype:"multipart/form-data",
		url : "/marcas",
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
				$("#marcasFrag #error").html(lista);
				$("#marcasFrag #erroresMarcas").fadeIn(300);
				limpiarformularioE();
			} else {
				if(data.mensaje=="existe"){
					var lista = "";
					$("#marcasFrag #error").html(lista);
					lista ="<p>ERROR --> Ya existe Empresa con el mismo nombre</p>";
					$("#marcasFrag #error").html(lista);
					$("#marcasFrag #erroresMarcas").fadeIn(300);
				}else{
					
				
				$("#marcasFrag #erroresMarcas").fadeOut(300);
				cargarTablaMarcas();
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
var cargarTablaMarcas=function(){
	 var table = $('#tablaMarcas').DataTable({
		 	"destroy": true,
		 	"cache": false,
			"sAjaxSource": "listamarcas",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"lengthMenu": [[5, 10, 20, 25, 50, -1], [5, 10, 20, 25, 50, "Todos"]],
			"aoColumns": [
				  { "mData": "logo","render":function(mData,type,row){
		        	  return "<img class='z-depth-2 responsive-img activator' width='70' height='60' src='/assets/images/marcas/"+mData+"'/>"
		        	  }
				  },
			      { "mData": "nombre"},
		          { "defaultContent": "<a  href='#' class='editar_marcas grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modalEliminarMarcas'  class='eliminarMarcas grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	 $(".marcas").hover(function() {
	        $(this).find("select").val('5');
	    });	
	obtener_data_editar_marcas("#tablaMarcas tbody",table);
	 obtenerDatosEliminarMarcas("#tablaMarcas tbody",table);
	};
	var obtener_data_editar_marcas=function(tbody,table){
		$(tbody).on("click","a.editar_marcas",function(){
			 event.preventDefault();
				$("#opcion_marcas").text("Modificar");
			  var data=table.row($(this).parents("tr")).data();
			  var id=$("#formularioMarcas #id").val(data.id);
			  nombre=$("#formularioMarcas #nombre").val(data.nombre);
			
			   $("#imagenEditar").attr("src","assets/images/marcas/"+data.logo);
			   $("#formularioMarcas #imagenEditar").fadeIn(300);
    		  $("#formularioMarcas #nombre").focus();
			  $("#formularioMarcas #logo").focus();
			  
			  
			 
		})
	}
	var obtenerDatosEliminarMarcas=function(tbody,table){
		$(tbody).on("click","a.eliminarMarcas",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#modalEliminarMarcas #id").val(data.id);
			
			
		})
		
	}

	var limpiarformularioMarcas = function(){
		$("#formularioMarcas #id").val("");
		$("#formularioMarcas #nombre").val("");
		$("#formularioMarcas #logo").val("");
		
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