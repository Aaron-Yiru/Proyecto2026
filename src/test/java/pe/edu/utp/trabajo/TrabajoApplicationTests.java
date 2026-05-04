package pe.edu.utp.trabajo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pe.edu.utp.trabajo.controller.ComentarioController;
import pe.edu.utp.trabajo.controller.ConfesionController;
import pe.edu.utp.trabajo.controller.UsuarioController;

import pe.edu.utp.trabajo.service.ComentarioService;
import pe.edu.utp.trabajo.service.ConfesionService;
import pe.edu.utp.trabajo.service.UsuarioService;


import pe.edu.utp.trabajo.model.Comentario;
import pe.edu.utp.trabajo.model.Confesion;
import pe.edu.utp.trabajo.model.Usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class TrabajoApplicationTests {

	// @Mock crea servicios simulados (vacíos)
	@Mock private ConfesionService confesionService;
	@Mock private UsuarioService usuarioService;
	@Mock private ComentarioService comentarioService;

	// @InjectMocks inyecta los servicios simulados en los controladores reales
	@InjectMocks private ConfesionController confesionController;
	@InjectMocks private UsuarioController usuarioController;
	@InjectMocks private ComentarioController comentarioController;


	// OPERACIÓN 1: Crear Usuario


	/*@Test
	void test1_crearUsuario_Exito() {
		Usuario mockUser = new Usuario();
		mockUser.setId(1L);
		mockUser.setInstitucion("UTP Arequipa");

		Mockito.when(usuarioService.guardar(any(Usuario.class))).thenReturn(mockUser);

		ResponseEntity<Usuario> response = usuarioController.crearUsuario(new Usuario());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("UTP Arequipa", response.getBody().getInstitucion());
	}*/
	@Test
	void test1_crearUsuario_Exito() {
		Usuario mockUser = new Usuario();
		mockUser.setId(1L);
		// El servicio simulado "guarda" un alumno de Arequipa
		mockUser.setInstitucion("UTP Arequipa");

		Mockito.when(usuarioService.guardar(any(Usuario.class))).thenReturn(mockUser);

		ResponseEntity<Usuario> response = usuarioController.crearUsuario(new Usuario());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// ¡AQUÍ ROMPEMOS LA PRUEBA INTENCIONALMENTE!
		// Le decimos a JUnit que espere "UTP Lima", pero recibirá "UTP Arequipa"
		assertEquals("UTP Arequipa", response.getBody().getInstitucion(), "La institución no coincide");
	}

	@Test
	void test2_crearUsuario_EmailDuplicado() {
		Mockito.when(usuarioService.guardar(any(Usuario.class)))
				.thenThrow(new RuntimeException("El email ya existe"));

		assertThrows(RuntimeException.class, () -> {
			usuarioController.crearUsuario(new Usuario());
		});
	}
	/*@Test
	void test2_crearUsuario_EmailDuplicado() {
		// 1. La simulación (Mock) sigue lanzando un RuntimeException
		Mockito.when(usuarioService.guardar(any(Usuario.class)))
				.thenThrow(new RuntimeException("El email ya existe"));

		// 2. ¡AQUÍ ROMPEMOS LA PRUEBA!
		// Cambiamos lo que JUnit "espera" atrapar.
		// Le pedimos que espere un NullPointerException.
		assertThrows(NullPointerException.class, () -> {
			usuarioController.crearUsuario(new Usuario());
		});
	}*/

	@Test
	void test3_crearUsuario_DatosIncompletos() {
		Mockito.when(usuarioService.guardar(any(Usuario.class)))
				.thenThrow(new IllegalArgumentException("Faltan datos obligatorios"));

		assertThrows(IllegalArgumentException.class, () -> {
			usuarioController.crearUsuario(new Usuario());
		});
	}

	@Test
	void test4_crearUsuario_FalloBaseDatos() {
		Mockito.when(usuarioService.guardar(any(Usuario.class)))
				.thenThrow(new RuntimeException("Error de conexión"));

		assertThrows(RuntimeException.class, () -> {
			usuarioController.crearUsuario(new Usuario());
		});
	}


	// OPERACIÓN 2: Obtener Usuario (4 Casos)


	@Test
	void test5_obtenerUsuario_Exito() {
		Usuario mockUser = new Usuario();
		mockUser.setId(1L);

		Mockito.when(usuarioService.buscarPorId(1L)).thenReturn(mockUser);

		ResponseEntity<Usuario> response = usuarioController.obtenerUsuario(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1L, response.getBody().getId());
	}

	@Test
	void test6_obtenerUsuario_NoEncontrado() {
		Mockito.when(usuarioService.buscarPorId(99L))
				.thenThrow(new RuntimeException("Usuario no encontrado"));

		assertThrows(RuntimeException.class, () -> {
			usuarioController.obtenerUsuario(99L);
		});
	}

	@Test
	void test7_obtenerUsuario_IdNegativo() {
		Mockito.when(usuarioService.buscarPorId(-1L))
				.thenThrow(new IllegalArgumentException("ID no válido"));

		assertThrows(IllegalArgumentException.class, () -> {
			usuarioController.obtenerUsuario(-1L);
		});
	}

	@Test
	void test8_obtenerUsuario_ErrorInterno() {
		Mockito.when(usuarioService.buscarPorId(1L))
				.thenThrow(new RuntimeException("Timeout del servidor"));

		assertThrows(RuntimeException.class, () -> {
			usuarioController.obtenerUsuario(1L);
		});
	}


	// OPERACIÓN 3: Crear Confesión (4 Casos)


	@Test
	void test9_publicarConfesion_Exito() {
		Confesion confesion = new Confesion();
		confesion.setId(10L);
		confesion.setContenido("No estudié para el examen");

		Mockito.when(confesionService.guardar(any(Confesion.class))).thenReturn(confesion);

		ResponseEntity<Confesion> response = confesionController.publicarConfesion(new Confesion());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(10L, response.getBody().getId());
	}

	@Test
	void test10_publicarConfesion_SinContenido() {
		Mockito.when(confesionService.guardar(any(Confesion.class)))
				.thenThrow(new IllegalArgumentException("El contenido no puede estar vacío"));

		assertThrows(IllegalArgumentException.class, () -> {
			confesionController.publicarConfesion(new Confesion());
		});
	}

	@Test
	void test11_publicarConfesion_AutorInvalido() {
		Mockito.when(confesionService.guardar(any(Confesion.class)))
				.thenThrow(new RuntimeException("El autor especificado no existe"));

		assertThrows(RuntimeException.class, () -> {
			confesionController.publicarConfesion(new Confesion());
		});
	}

	@Test
	void test12_publicarConfesion_ErrorServicio() {
		Mockito.when(confesionService.guardar(any(Confesion.class)))
				.thenThrow(new RuntimeException("Memoria llena"));

		assertThrows(RuntimeException.class, () -> {
			confesionController.publicarConfesion(new Confesion());
		});
	}


	// OPERACIÓN 4: Obtener Confesión (4 Casos)


	@Test
	void test13_obtenerConfesion_Exito() {
		Confesion confesion = new Confesion();
		confesion.setId(5L);

		Mockito.when(confesionService.buscarPorId(5L)).thenReturn(confesion);

		ResponseEntity<Confesion> response = confesionController.obtenerConfesion(5L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(5L, response.getBody().getId());
	}

	@Test
	void test14_obtenerConfesion_NoExiste() {
		Mockito.when(confesionService.buscarPorId(999L))
				.thenThrow(new RuntimeException("Confesión borrada o inexistente"));

		assertThrows(RuntimeException.class, () -> {
			confesionController.obtenerConfesion(999L);
		});
	}

	@Test
	void test15_obtenerConfesion_EsAnonima() {
		Confesion confesion = new Confesion();
		confesion.setId(5L);
		confesion.setEsAnonimo(true);

		Mockito.when(confesionService.buscarPorId(5L)).thenReturn(confesion);

		ResponseEntity<Confesion> response = confesionController.obtenerConfesion(5L);

		assertEquals(true, response.getBody().isEsAnonimo());
	}

	@Test
	void test16_obtenerConfesion_IdNulo() {
		Mockito.when(confesionService.buscarPorId(null))
				.thenThrow(new IllegalArgumentException("ID no puede ser nulo"));

		assertThrows(IllegalArgumentException.class, () -> {
			confesionController.obtenerConfesion(null);
		});
	}


	// OPERACIÓN 5: Eliminar Confesión (4 Casos)


	@Test
	void test17_eliminarConfesion_Exito() {
		Mockito.doNothing().when(confesionService).eliminar(1L);

		ResponseEntity<Void> response = confesionController.eliminarConfesion(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		Mockito.verify(confesionService, Mockito.times(1)).eliminar(1L);
	}

	@Test
	void test18_eliminarConfesion_NoEncontrada() {
		Mockito.doThrow(new RuntimeException("No existe para eliminar"))
				.when(confesionService).eliminar(99L);

		assertThrows(RuntimeException.class, () -> {
			confesionController.eliminarConfesion(99L);
		});
	}

	@Test
	void test19_eliminarConfesion_ViolacionIntegridad() {
		Mockito.doThrow(new RuntimeException("No se puede eliminar porque tiene comentarios"))
				.when(confesionService).eliminar(1L);

		assertThrows(RuntimeException.class, () -> {
			confesionController.eliminarConfesion(1L);
		});
	}

	@Test
	void test20_eliminarConfesion_SinPermisos() {
		Mockito.doThrow(new SecurityException("Solo el autor puede eliminarla"))
				.when(confesionService).eliminar(1L);

		assertThrows(SecurityException.class, () -> {
			confesionController.eliminarConfesion(1L);
		});
	}


	// OPERACIÓN 6: Agregar Comentario (4 Casos)


	@Test
	void test21_agregarComentario_Exito() {
		Comentario comentario = new Comentario();
		comentario.setId(100L);
		comentario.setConfesionId(2L);

		Mockito.when(comentarioService.guardar(eq(2L), any(Comentario.class))).thenReturn(comentario);

		ResponseEntity<Comentario> response = comentarioController.agregarComentario(2L, new Comentario());

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(100L, response.getBody().getId());
	}

	@Test
	void test22_agregarComentario_ConfesionInexistente() {
		Mockito.when(comentarioService.guardar(eq(99L), any(Comentario.class)))
				.thenThrow(new RuntimeException("La confesión a comentar no existe"));

		assertThrows(RuntimeException.class, () -> {
			comentarioController.agregarComentario(99L, new Comentario());
		});
	}

	@Test
	void test23_agregarComentario_TextoVacio() {
		Mockito.when(comentarioService.guardar(eq(2L), any(Comentario.class)))
				.thenThrow(new IllegalArgumentException("El comentario no puede estar vacío"));

		assertThrows(IllegalArgumentException.class, () -> {
			comentarioController.agregarComentario(2L, new Comentario());
		});
	}

	@Test
	void test24_agregarComentario_SpamDetectado() {
		Mockito.when(comentarioService.guardar(eq(2L), any(Comentario.class)))
				.thenThrow(new RuntimeException("El comentario parece ser spam"));

		assertThrows(RuntimeException.class, () -> {
			comentarioController.agregarComentario(2L, new Comentario());
		});
	}
}
