$(document).ready( function () {
	listar_sectores();
	eliminar_sector();
	limpiarformulario();
	$("#btn_submit_sectores").click(function(event){
		event.preventDefault();
		fire_ajax_submit_sectores();
		
	})
		$("#cancelar_editar_sectores").click(function(event){
		event.preventDefault();
		$("#opcion").text("Nuevo");
		limpiarformulario();
	})
});


var eliminar_sector = function(){
	$("#btn_eliminar_sector").on("click", function(){
		var id=$("#eliminar_sector #id").val();
		$.ajax({
			type:"DELETE",
			url: "/sectores/"+id,
			success : function(result) {
				listar_sectores();
				Materialize.toast(result, 4000);
				limpiarformulario_proveedores();
				},
			error : function(e) {
					alert("Error!")
					console.log("ERROR: ", e);
				}
		
	});
})
}
function fire_ajax_submit_sectores(){
	var formData = {
    		id : $("#formulario_sectores #id").val(),
    		nombre : $("#formulario_sectores #nombre").val(),
    		detalle : $("#formulario_sectores #detalle").val(),
    		logo : $("#formulario_sectores #logo").val()
    	}
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/sectores",
		data : formData,
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			mostrar_mensaje(data);
			Materialize.toast(data, 4000);
			$("#btn_submit_sectores").prop("disabled",false);
			listar_sectores();
			limpiarformulario();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}
var listar_sectores=function(){
	 var table = $('#sectores_tablas').DataTable({
		 "responsive": true,
		 "destroy": true,
			"sAjaxSource": "listasectores",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "nombre"},
		          { "mData": "detalle"},
				  { "mData": "logo" },
		          { "defaultContent": "<a  href='#' class='editar_sector grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modal1' id='eliminar_sector' class='eliminar_sector grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"} 
			],
				
			"language": idioma_español,
			
	 });
	 obtener_data_editar_sectores("#sectores_tablas tbody",table);
	 obtener_data_eliminar_sectores("#sectores_tablas tbody",table);
	};
	var obtener_data_editar_sectores=function(tbody,table){
		$(tbody).on("click","a.editar_sector",function(){
			event.preventDefault();
			console.log("entro");
			$("#opcion").text("Modificar");
			var data=table.row($(this).parents("tr")).data();
			console.log(data);
			$("#logo_editar").text(data.logo);
			var nombre=$("#formulario_sectores #nombre").val(data.nombre),
					id=$("#formulario_sectores #id").val(data.id),	
			   detalle=$("#formulario_sectores #detalle").val(data.detalle),
			      logo=$("#formulario_sectores #logo").val(data.logo)
			      $("#formulario_sectores #nombre").focus();
					$("#formulario_sectores #detalle").focus();
					$("#formulario_sectores #logo").focus();
					$("#logo_editar").text(data.logo);
			 
		})
	}
	var obtener_data_eliminar_sectores=function(tbody,table){
		$(tbody).on("click","a.eliminar_sector",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_sector #id").val(data.id);
			listar_sectores();
			
		})
		
	}

	var limpiarformulario = function(){
		$("#formulario_sectores #id").val("");
		$("#formulario_sectores #nombre").val("");
		$("#formulario_sectores #detalle").val("");
		$("#formulario_sectores #logo").val("");
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
