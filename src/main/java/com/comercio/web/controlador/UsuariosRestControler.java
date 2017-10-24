package com.comercio.web.controlador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.web.config.FileUploadUtility;
import com.comercio.web.dao.DatoDao;
import com.comercio.web.dao.RolesDao;
import com.comercio.web.dao.UsuarioDao;
import com.comercio.web.model.Dato;
import com.comercio.web.model.FeedBack;
import com.comercio.web.model.Usuario;
import com.comercio.web.model.response;
import com.comercio.web.model.bean.DatoBean;

import com.comercio.web.model.bean.UsuarioBean;

@RestController
public class UsuariosRestControler {
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
		if (datoDao.getByDatos(datos.getLogin(), datos.getClave()).isEmpty()) {
			result.setMsg("el usuario no existe");
		} else {
			Dato d = new Dato();
			d = datoDao.getByLoginClave(datos.getLogin(), datos.getClave());

			result.setMsg("succes");
			Usuario usuario = new Usuario();
			usuario = userDao.getByDatos(d);
			httpSession.setAttribute("userLog", usuario);
			result.setUsuario(usuario);
			result.setExiste(true);
			result.setPlantilla("notificaciones");
			result.setFragmento("notificaciones");
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "listaUsuarios")
	public List<Usuario> tablas(Model model) {
		return userDao.getAll();
	}

	@DeleteMapping(value = "/usuarios/{id}")
	public String eliminarUsuario(@PathVariable long id) {
		userDao.delete(id);
		return "Bien!. usuario eliminado correctamente.";
	}

	@PostMapping(value = "/usuarios")
	public FeedBack editarusuario(@Valid @ModelAttribute UsuarioBean u, BindingResult result,
			HttpServletRequest request) {

		FeedBack feedBack = new FeedBack();
		if (result.hasErrors()) {
			feedBack.setLista_errores(result.getAllErrors());

			return feedBack;
		}

		try {

			Usuario usuario = new Usuario();

			usuario.setNombre(u.getNombre());
			usuario.setAp(u.getAp());
			usuario.setAm(u.getAm());
			usuario.setDNI(u.getDni());
			usuario.setSexo(u.getSexo());

			usuario.setDireccion(u.getDireccion());
			if (!u.getTelefono().equals("") && !u.getTelefono().equals(null)) {
				usuario.setTelefono(Integer.parseInt(u.getTelefono()));
			}
			usuario.setCorreo(u.getCorreo());
			usuario.setEstado(1);
			System.out.println(usuario + "" + u.getNit());
			if (!(u.getNit() == null)) {
				usuario.setNIT(Integer.parseInt(u.getNit()));
			}
			
			usuario.setPuestoTrabajo(u.getPuestoTrabajo());
			usuario.setMovil(u.getMovil());
			
			if (u.getId_rol() != 0) {
				usuario.addRol(rolDao.getById(u.getId_rol()));
			}
			
			Dato d = new Dato();
			if (u.getIdDato() == 0 && !u.getClave().equals("") && !u.getClave().equals("")) {
				d.setId(u.getIdDato());
				d.setLogin(u.getLogin());
				d.setClave(u.getClave());
				usuario.setDatos(d);

			} else {
				d.setLogin(u.getLogin());
				d.setClave(u.getClave());
				long iddato = datoDao.create(d);
				usuario.setDatos(datoDao.getById(iddato));
			}
			System.out.println(usuario + "" + u.getIdDato()+""+u.getClave()+"*******"+u.getFoto());
			String dni1 = "";
			if (!u.getFoto().getOriginalFilename().equals("")) {
				FileUploadUtility.uploadFile(request, u.getFoto(), dni1 + u.getDni());
			}
			if (u.getFechaNacimiento() != " " && u.getFechaNacimiento() != null && !u.getFechaNacimiento().equals("")) {

				String startDateString = u.getFechaNacimiento();
				DateFormat df = new SimpleDateFormat("yyyy-dd-mm");
				Date startDate = df.parse(startDateString);
				java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
				usuario.setFechaNacimiento(sqlDate);

			} else {
				if (u.getFechaNacimiento() != " " && u.getFechaNacimiento() != null
						&& !u.getFechaNacimiento().equals("")) {
					String startDateString = u.getFechaNacimiento();
					DateFormat df = new SimpleDateFormat("yyyy/dd/mm");
					Date startDate = df.parse(startDateString);
					java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
					usuario.setFechaNacimiento(sqlDate);

				}
			}
			System.out.println(usuario);
			if (!u.getId().equals("")) {
				usuario.setId(Long.parseLong(u.getId()));
				userDao.update(usuario);
				feedBack.setMensaje("BIEN, datos modificados correctamenta");
			} else {
				userDao.create(usuario);
				feedBack.setMensaje("BIEN, usuario creado correctamenta");
			}

		} catch (Exception e) {
			e.printStackTrace();
			feedBack.setMensaje("Ya existe el DNI registrado");
		}

		return feedBack;
	}

}