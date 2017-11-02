$(document).ready( function () {
	listar_categorias();
	eliminar_categoria();
	limpiarformulario();
	$("#btn_submit_categoria").click(function(event){
		event.preventDefault();
		console.log("submit");
		fire_ajax_submit_categoria();
		
	})
		$("#cancelar_editar_categoria").click(function(event){
		event.preventDefault();
		$("#opcion").text("Nuevo");
		limpiarformulario();
		
	})
});


var eliminar_categoria = function(){
	
	$("#btn_eliminar_categoria").on("click", function(){
	
		var idu = $("#eliminar_categoria #id_eliminar_categoria").val();
		
		console.log(idu)
		$.ajax({
			type:"POST",
			url: "/eliminarCategoria",
			data: {'id':idu},
				success : function(result) {
					console.log(result);
						listar_categorias();
						mostrar_mensaje(result);
						limpiarformulario();
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function fire_ajax_submit_categoria(){
	var formData = {
    		id : $("#formulario_categorias #id").val(),
    		nombre : $("#formulario_categorias #nombre").val(),
    		detalle : $("#formulario_categorias #detalle").val(),
    		logo : $("#formulario_categorias #logo").val()
    	}
	console.log("datos obtenidos");
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/editarcategoria",
		data : formData,
		success:function(data){
			mostrar_mensaje(data);
			console.log(data);
			$("#btn_submit_categoria").prop("disabled",false);
			listar_categorias();
			
			limpiarformulario();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_categorias=function(){
	 var table = $('#categoria_tablas').DataTable({
		 "responsive": true,
		 "destroy": true,
			"sAjaxSource": "listaCategorias",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "nombre"},
		          { "mData": "detalle"},
				  { "mData": "logo" },
		          { "defaultContent": "<a  href='#' class='editar_categoria grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modal1' id='eliminar_categoria' class='eliminar_categoria grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}
				 
				  
			],
				
			"language": idioma_español,
			
	 });
	/* $("select").val('10');
	  $('select').addClass("browser-default");
	  $('select').material_select();*/
	obtener_data_editar_categoria("#categoria_tablas tbody",table);
	 obtener_data_eliminar_categoria("#categoria_tablas tbody",table);
	};
	var obtener_data_editar_categoria=function(tbody,table){
		$(tbody).on("click","a.editar_categoria",function(){
			 event.preventDefault();
				$("#opcion").text("Modificar");
			var data=table.row($(this).parents("tr")).data();
			console.log(data);
			$("#logo_editar").text(data.logo);
			var nombre=$("#formulario_categorias #nombre").val(data.nombre),
					id=$("#formulario_categorias #id").val(data.id),	
			   detalle=$("#formulario_categorias #detalle").val(data.detalle),
			      logo=$("#formulario_categorias #logo").val(data.logo)
			      $("#formulario_categorias #nombre").focus();
			$("#formulario_categorias #detalle").focus();
			 $("#formulario_categorias #logo").focus();	
			 
		})
	}
	var obtener_data_eliminar_categoria=function(tbody,table){
		$(tbody).on("click","a.eliminar_categoria",function(){
		
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_categoria #id_eliminar_categoria").val(data.id);
			var id=$("#eliminar_categoria #nombre_eliminar").val(data.nombre);
			var id=$("#eliminar_categoria #detalle_eliminar").val(data.detalle);
			var id=$("#eliminar_categoria #logo_eliminar").val(data.logo);
			listar_categorias();
			console.log(data);
			
		})
		
	}

	var limpiarformulario = function(){
		$("#formulario_categorias #id").val("");
		$("#formulario_categorias #nombre").val("");
		$("#formulario_categorias #detalle").val("");
		$("#formulario_categorias #logo").val("");
		$("#logo_editar").text("");
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
	var mostrar_mensaje = function(mensaje){
		
		
		texto = "<strong>"+mensaje+"</strong>";
		color = "#379911";


$(".mensaje").html( texto ).css({"color": color });
$(".mensaje").fadeOut(5000, function(){
		$(this).html("");
		$(this).fadeIn(3000);
});			
}
