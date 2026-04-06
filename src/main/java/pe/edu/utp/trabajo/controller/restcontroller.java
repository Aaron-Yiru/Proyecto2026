package pe.edu.utp.trabajo.controller;

import org.springframework.web.bind.annotation.*;
import pe.edu.utp.trabajo.model.Estudiante;

import java.util.Date;

@RestController
public class restcontroller {
    @GetMapping("/consultar")
    public Estudiante consultar(@RequestParam(name = "id") int id) {
        Estudiante e = new Estudiante(id, "Estudiante "+id, "estudiante"+id+"@gmail.com");
        return e;
    }
    @GetMapping("/estudiante/{id}")
    public Estudiante consultarEstudiante(@PathVariable(name = "id") int id) {
        Estudiante e = new Estudiante(id, "Estudiante "+id, "estudiante"+id+"@gmail.com");
        return e;
    }
    @PostMapping("/registrar")
    public Estudiante registrar(@RequestBody Estudiante nuevo) {
        Date ahora = new Date();
        nuevo.setId((int)ahora.getTime());
        return nuevo;
    }
}
