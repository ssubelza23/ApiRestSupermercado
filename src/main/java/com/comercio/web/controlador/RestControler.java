package com.comercio.web.controlador;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.comercio.web.config.FileUploadUtility;
import com.comercio.web.dao.DatoDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.response;
import com.comercio.web.model.bean.DatoBean;

@RestController
public class RestControler {
	@Autowired
	UsuarioDao userDao;
	@Autowired
	DatoDao datoDao;

	@Autowired
	RolesDao rolDao;

	@Autowired
	private HttpSession httpSession;

	@PostMapping("/validacion")
	public ResponseEntity<?> valida(@RequestBody DatoBean datos, Errors errors) {
		response result = new response();
		if (errors.hasErrors()) {
			result.setMsg(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(result);
		}
		List<Dato> da = new ArrayList<>();
		da = datoDao.getByDatos(datos.getLogin(), datos.getClave());
		if (da.isEmpty()) {
			result.setMsg("el usuario no existe");
		} else {
			Dato d = new Dato();
			d = datoDao.getByLoginClave(datos.getLogin(), datos.getClave());
			httpSession.setAttribute("userLog", d);
			result.setMsg("succes");
			Usuario usuario = new Usuario();
			usuario = userDao.getByDatos(d);
			result.setUsuario(usuario);
			result.setExiste(true);
			result.setPlantilla("notificaciones");
			result.setFragmento("notificaciones");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/logout")
	public String logout(Model model) {
		httpSession.removeAttribute("userLoggedIn");
		return "redirect:/";
	}

	@GetMapping(value = "/Lista_Usuarios")
	public List<Usuario> tablas(Model model) {
		return userDao.getAll();
	}

	@PostMapping(value = "/eliminarUsuario")
	public String eliminarUsuario(@RequestParam("id") long id) {
		userDao.delete(id);
		return "Bien!. usuario eliminado correctamente.";
	}

	@PostMapping(value = "/editarusuario")
	public String editarusuario(@RequestParam("nombre") String nombre, @RequestParam("id") String id,
			@RequestParam("ap") String ap, @RequestParam("am") String am, @RequestParam("DNI") String dni,
			@RequestParam("id_Rol") String id_rol, @RequestParam("fechaNacimiento") String fechaNacimiento,
			@RequestParam("Sexo") char sexo, @RequestParam("Direccion") String direccion,
			@RequestParam("Telefono") String telefono, @RequestParam("Correo") String correo,
			// @RequestParam("Nit") String nit,
			@RequestParam("Login") String login, @RequestParam("Clave") String clave,
			@RequestParam("id_dato") String id_dato, @RequestParam("Foto") MultipartFile foto,
			HttpServletRequest request) throws ParseException {
		String mensaje = "";
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setAp(ap);
		usuario.setAm(am);
		usuario.setDNI(Integer.parseInt(dni));
		usuario.setEstado(1);

		if (id_rol != "")
			usuario.addRol(rolDao.getById(Long.parseLong(id_rol)));

		usuario.setSexo(sexo);
		usuario.setDireccion(direccion);

		usuario.setTelefono(Integer.parseInt(telefono));
		usuario.setCorreo(correo);
		Dato d = new Dato();
		if (!id_dato.equals("0")) {
			d.setId(Long.parseLong(id_dato));
			d.setLogin(login);
			d.setClave(clave);
			usuario.setDatos(d);

		} else {
			d.setLogin(login);
			d.setClave(clave);
			long iddato = datoDao.create(d);
			usuario.setDatos(datoDao.getById(iddato));
		}

		String dni1 = "";
		if (!foto.getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, foto, dni1 + dni);
		}

		if (!id.equals("")) {

			usuario.setId(Long.parseLong(id));
			if (fechaNacimiento != " " && fechaNacimiento != null && !fechaNacimiento.equals("")) {

				String startDateString = fechaNacimiento;
				DateFormat df = new SimpleDateFormat("yyyy-dd-mm");
				Date startDate = df.parse(startDateString);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				usuario.setFechaNacimiento(sqlDate);

			}
			mensaje = "BIEN, datos modificados correctamenta";
			userDao.update(usuario);

		} else {
			if (fechaNacimiento != " " && fechaNacimiento != null && !fechaNacimiento.equals("")) {
				String startDateString = fechaNacimiento;
				DateFormat df = new SimpleDateFormat("yyyy/dd/mm");
				Date startDate = df.parse(startDateString);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				usuario.setFechaNacimiento(sqlDate);

			}
			userDao.create(usuario);
			mensaje = "BIEN, usuario creado correctamenta";
		}
		return mensaje;
	}
}
