package com.cdh.apitaller.controller;

import com.cdh.apitaller.dtos.UserDTO;
import com.cdh.apitaller.entitys.User;
import com.cdh.apitaller.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
@RestController
@Tag(name = "UserRestController", description = "Controlador para la entidad User")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
@Slf4j
public class UserRestController implements GenericController<User, UserDTO>{
    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @Operation(summary = "Obtener todos los usuarios",
            operationId = "getAllUsers",
            description = "Este endpoint devuelve una lista de todos los usuarios", tags = {"UserRestController"})
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @GetMapping(value = "/user/GetAllUsers")
    @Override
    public ResponseEntity<List<User>> getAll() throws ParseException {
    log.info("Obteniendo todos los usuarios");
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);

    }
    @Operation(summary = "Obtener usuario por ID",
            operationId = "getUserById",
            description = "Este endpoint devuelve un usuario por su ID", tags = {"UserRestController"})
    @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente")
    @GetMapping(value = "/user/GetUserById/{id}")
    @Override
    public ResponseEntity<User> getById(@PathVariable Long id) throws ParseException {
        log.info("Obteniendo usuario con ID: {}", id);
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @Operation(summary = "Crear un nuevo usuario",
            operationId = "createUser",
            description = "Este endpoint crea un nuevo usuario", tags = {"UserRestController"})
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente")
    @PostMapping(value = "/user/CreateUser")
    @Override
    public ResponseEntity<String> post(@RequestBody UserDTO userDTO) throws ParseException {
        log.info("Creando nuevo usuario");
        userService.addUser(userDTO);
        return ResponseEntity.ok("Usuario creado correctamente");
    }
    @Operation(summary = "Actualizar un usuario",
            operationId = "updateUser",
            description = "Este endpoint actualiza un usuario existente", tags = {"UserRestController"})
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @PutMapping(value = "/user/UpdateUser")
    @Override
    public ResponseEntity<String> put(@RequestBody UserDTO S) throws ParseException {
        log.info("Actualizando usuario");
        userService.updateClient(S);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }
    @Operation(summary = "Eliminar un usuario",
            operationId = "deleteUser",
            description = "Este endpoint elimina un usuario por su ID", tags = {"UserRestController"})
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente")
    @PostMapping(value = "/user/DeleteUser/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) throws ParseException {
        log.info("Eliminando usuario con ID: {}", id);
        User user = userService.findById(id);
        userService.deleteClient(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}
