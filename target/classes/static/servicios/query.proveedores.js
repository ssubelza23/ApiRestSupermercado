$(document).ready( function () {		        
	listar_proveedores();
	eliminar_proveedores();
	
	$("#btn_submit_proveedores").click(function(event){
		event.preventDefault();
		fire_ajax_submit_proveedores();
		
	})
		$("#cancelar_editar_proveedores").click(function(event){
		event.preventDefault();
		$("#btn_submit_proveedores").val("Nuevo");
	})
	

	
	$("#proveedoresFrag #erroresProveedores").hide();
});


var eliminar_proveedores = function(){
	$("#btnEliminarProveedor").on("click", function(){
		 var id=$("#modalEliminarProveedores #id").val();
		console.log(id);
		$.ajax({
			type:"DELETE",
			url: "/proveedores/"+id,
				success : function(result) {
					console.log(result);
					listar_proveedores();
					Materialize.toast(result, 4000);
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
	idcontacto : $("#formulario_proveedores #idcontacto").val()
    	}
	console.log(formData);
	$.ajax({
		type : "POST",
		url : "/proveedores",
		data : JSON.stringify(formData),
		contentType : "application/json",
		success:function(data){
			var errores = data.lista_errores;
			var lista = "";
			if (errores.length != 0) {
				for (let i = 0; i < errores.length; i++) {
						lista = lista + "<p>ERROR : campo :"
								+ errores[i].field.bold().toUpperCase()+ " : --> "
								+ errores[i].defaultMessage + "</p>"
					
				}
				$("#proveedoresFrag #error").html(lista);
				$("#proveedoresFrag #erroresProveedores").fadeIn(300);
			} else {
				if(data.mensaje=="existe"){
					var lista = "";
					$("#marcasFrag #error").html(lista);
					lista ="<p>ERROR --> Ya existe Empresa con el mismo nombre</p>";
					$("#proveedoresFrag #error").html(lista);
					$("#proveedoresFrag #erroresProveedores").fadeIn(300);
				}else{
					
				
				$("#proveedoresFrag #erroresProveedores").fadeOut(300);
				listar_proveedores();
				Materialize.toast(data.mensaje, 4000);
				
				}
			}
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
				  { "mData": "contacto.ap" },
		          { "mData": "contacto.movil"},
				  { "defaultContent": "<a  href='#modalEliminarProveedores' class='eliminarProveedores grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}	 
				  
			],
				
			"language": idioma_español,
			
	 });
	
	 obtener_data_eliminar_proveedores("#proveedores_tabla tbody",table);
	};
	var obtener_data_eliminar_proveedores=function(tbody,table){
		$(tbody).on("click","a.eliminarProveedores",function(){
			var data=table.row($(this).parents("tr")).data();
			var id=$("#modalEliminarProveedores #id").val(data.id);
			
		})
		
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
