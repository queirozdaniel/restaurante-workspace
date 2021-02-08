package com.danielqueiroz.restaurantesystem.api.controller;

import com.danielqueiroz.restaurantesystem.api.feingclient.UserFeingClient;
import com.danielqueiroz.restaurantesystem.api.model.input.UserEmailInput;
import com.danielqueiroz.restaurantesystem.domain.model.Mesa;
import com.danielqueiroz.restaurantesystem.domain.model.User;
import com.danielqueiroz.restaurantesystem.domain.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mesa")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @Autowired
    private UserFeingClient userFeingClient;

    @PostMapping("/solicitar")
    public ResponseEntity<Mesa> solicitarMesa(@RequestBody UserEmailInput userEmailInput){
        User user = userFeingClient.findByEmail(userEmailInput.getEmail()).getBody();

        Mesa mesa = new Mesa();
        mesa.setUser(user);

        mesaService.save(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
