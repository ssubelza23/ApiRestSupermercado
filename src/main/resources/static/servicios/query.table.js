$(document).ready( function () {
	listar();
	eliminar();
	limpiarformulario();
	
	$("#btn_submit_usuario").click(function(event){
		event.preventDefault();
		fire_ajax_submit();
	})

});
function fire_ajax_submit(){
	var form=$("#formulario_usuario")[0];
	var data=new FormData(form);
	$("#btn_submit_usuario").prop("disabled",true);
	data.append("CustomField","this is some extra data for testing");
	console.log(data);
	$.ajax({
		type:"POST",
		enctype:"multipart/form-data",
		url:"/editarusuario",
		data:data,
		processData: false,
		contentType: false,
		cache: false,
		timeout: 600000,
		success:function(data){
			console.log(data);
			$("#btn_submit_usuario").prop("disabled",false);
			listar();
			mostrar_mensaje(data);
			limpiarformulario();
		},
		error: function(e){
			console.log("ERROR:", e);
		}
	})
}



	var listar=function(){
	 var table = $('#employeesTable').DataTable({
		 responsive: true,
		 "destroy": true,
		 
			"sAjaxSource": "Lista_Usuarios",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "dni"},
		          { "mData": "nombre"},
				  { "mData": "ap" },
				  { "mData": "am" },
				  { "mData": "roles[0].nombre" },
				  { "defaultContent": "<a  href='#' class='editar grey-text'><i class='material-icons'>edit</i></button>"},
				  { "defaultContent": "<a  href='#modal1' id='eliminar' class='eliminar grey-text modal-trigger'><i class='material-icons dp48'>delete</i></a>"}
				 
				  
			],
			"language": idioma_español,
			
	 });
	 /*$("select").val('10');
	  $('select').addClass("browser-default");
	  $('select').material_select();*/
	 obtener_data_editar("#employeesTable tbody",table);
	 obtener_data_eliminar("#employeesTable tbody",table);
	};
	
	var eliminar = function(){
		
		$("#btn_eliminar_usuario").on("click", function(){
			var idu = $("#eliminar_usuario #id_eliminar").val();
			
			console.log(idu)
			$.ajax({
				type:"POST",
				url: "/eliminarUsuario",
				data: {'id':idu},
					success : function(result) {
						console.log(result);
							listar();
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
	var limpiarformulario = function(){
		$("#Login").val("");
		$("#clave").val("");
	}
	
	var obtener_data_editar=function(tbody,table){
		$(tbody).on("click","a.editar",function(){
			 event.preventDefault();
			var data=table.row($(this).parents("tr")).data();
			console.log(data);
			var nombre=$("#Nombre").val(data.nombre),
				id=$("#formulario_usuario #id").val(data.id),
				ap=$("#Ap").val(data.ap),
				am=$("#Am").val(data.am),
				dni=$("#formulario_usuario #DNI").val(data.dni),
				correo=$("#Correo").val(data.correo),
				telefono=$("#Telefono").val(data.telefono),
				direccion=$("#Direccion").val(data.direccion),
				fechaNacimiento=$("#fechaNacimiento").val(data.fechaNacimiento),
				id_dato=$("#id_dato").val(data.datos.id),
				sexo=$("#Sexo").val(data.sexo),
				login=$("#Login").val(data.datos.login),
				clave=$("#Clave").val(data.datos.clave),
				clave=$("#id_Rol").val(data.roles[0].id),
				id_rol=$("#id_Rol").val(data.roles[0].id)
				
		})
	}
	var obtener_data_eliminar=function(tbody,table){
		$(tbody).on("click","a.eliminar",function(){
		
			var data=table.row($(this).parents("tr")).data();
			var id=$("#eliminar_usuario #id_eliminar").val(data.id);
			var id=$("#eliminar_usuario #usuario_eliminar").val(data.nombre+" "+data.ap+" "+data.am);
			var id=$("#eliminar_usuario #dni_eliminar").val(data.dni);
			var id=$("#eliminar_usuario #rol_eliminar").val(data.roles[0].nombre);
			var dni=data.dni;
			var src="/assets/images/usuarios/"+dni+".jpg";
			console.log(src);
			$("#eliminar_usuario #img").attr("src",src);
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
	

	var mostrar_mensaje = function(mensaje){
		
		
				texto = "<strong>"+mensaje+"</strong>";
				color = "#379911";
	

		$(".mensaje").html( texto ).css({"color": color });
		$(".mensaje").fadeOut(5000, function(){
				$(this).html("");
				$(this).fadeIn(3000);
		});			
	}
	