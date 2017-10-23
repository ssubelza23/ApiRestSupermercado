$(document).ready( function () {
	listar_proveedores();
	eliminar_proveedores();
	limpiarformulario_proveedores();
	$("#btn_submit_proveedores").click(function(event){
		event.preventDefault();
		fire_ajax_submit_proveedores();
		
	})
		$("#cancelar_editar_proveedores").click(function(event){
		event.preventDefault();
		$("#btn_submit_proveedores").val("Nuevo");
		limpiarformulario_proveedores();
		
	})
});


var eliminar_proveedores = function(){
	
	$("#btn_eliminar_proveedores").on("click", function(){
	
		 var id=$("#eliminar_proveedores #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/proveedores/"+id,
				success : function(result) {
					console.log(result);
						listar_proveedores();
						mostrar_mensaje_proveedores(result);
						limpiarformulario_proveedores();
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function fire_ajax_submit_proveedores(){
	var formData = {
	id : $("#formulario_proveedores #id").val(),
	idempresa : $("#formulario_proveedores #idempresa").val(),
	idmarca : $("#formulario_proveedores #idmarca").val(),
	idcontacto : $("#formulario_proveedores #idcontacto").val(),
	detalles : $("#formulario_proveedores #detalles").val()
    	 
    		
    		
    	}
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/proveedores",
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			mostrar_mensaje_proveedores(data);
			console.log(data);
			$("#btn_submit_proveedores").prop("disabled",false);
			listar_proveedores();
			limpiarformulario_proveedores();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_proveedores=function(){
	 var table = $('#proveedores_tabla').DataTable({
		 "responsive": true,
		 "destroy": true,
			"sAjaxSource": "listaproveedores",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "empresa.nombre"},
				  { "mData": "contacto.nombre" },
				  { "mData": "marcas.nombre" },
		          { "mData": "detalles"},
		          { "defaultContent": "<a  href='#' class='editar_proveedores grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modaleliminarproveedores' id='eliminar_proveedores' class='eliminar_proveedores grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	/* $("select").val('10');
	  $('select').addClass("browser-default");
	  $('select').material_select();*/
	obtener_data_editar_proveedores("#proveedores_tabla tbody",table);
	 obtener_data_eliminar_proveedores("#proveedores_tabla tbody",table);
	};
	var obtener_data_editar_proveedores=function(tbody,table){
		$(tbody).on("click","a.editar_proveedores",function(){
			 event.preventDefault();
				$("#opcion_proveedores").text("Modificar");
			  var data=table.row($(this).parents("tr")).data();
			  console.log(data);
			  var id=$("#formulario_proveedores #id").val(data.id)
    		
    		
    		  
			 
		})
	}
	var obtener_data_eliminar_proveedores=function(tbody,table){
		$(tbody).on("click","a.eliminar_proveedores",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_proveedores #id").val(data.id);
			
		})
		
	}

	var limpiarformulario_proveedores = function(){
		$("#formulario_proveedores #id").val("");
		$("#formulario_proveedores #idempresa").val("");
		$("#formulario_proveedores #idmarca").val("");
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
	var mostrar_mensaje_proveedores = function(mensaje){
		
		
		texto = "<strong>"+mensaje+"</strong>";
		color = "#379911";


$(".mensaje").html( texto ).css({"color": color });
$(".mensaje").fadeOut(5000, function(){
		$(this).html("");
		$(this).fadeIn(3000);
});			
}
