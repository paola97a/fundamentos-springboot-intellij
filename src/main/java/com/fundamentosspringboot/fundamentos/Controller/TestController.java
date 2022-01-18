package com.fundamentosspringboot.fundamentos.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
     /*Para hacer el test usamos
     * @RequestMapping: Anotación que nos sirve para aceptar todas las solicitudes dentro del método a nivel http
     * @ResponseBody: Para responder un cuerpo a nivel del servicio*/

    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hola desde controlador", HttpStatus.OK);
    }
}
