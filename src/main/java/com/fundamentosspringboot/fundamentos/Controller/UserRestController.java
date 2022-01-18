package com.fundamentosspringboot.fundamentos.Controller;


import com.fundamentosspringboot.fundamentos.Caseuse.*;
import com.fundamentosspringboot.fundamentos.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestControler: Hereda de la anotación controller y a us vez permite que todos los métodos creados en la clase
*                 se formatien con formato JSON.
* @RequestMapping: Permite agregar la ruta por donde el servicio será consumido
* @GetMapping: A nivel de método permite acceder a un servicio http
* @PostMapping: permite crear
* @DeleteMapping: permite borrar a partir de un id
* @PutMapping: permite actualizar , el método implementa usuario e id(@PathVariable)
* */
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    //Servicios: create, get, delete y update
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;
    private PageUser pageUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser,
                              UpdateUser updateUser, PageUser pageUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.pageUser = pageUser;
    }

    @GetMapping("/")
    List<User>get(){
        return getUser.gelAll();
    }

    //método crear
    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody  User newUser){
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }

    //método delete
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //método update
    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser,id),HttpStatus.OK);
    }

    @GetMapping("/pageable")
    List<User> getPageUser(@RequestParam int page, @RequestParam int size){
        return pageUser.getPages(page,size);
    }
}
