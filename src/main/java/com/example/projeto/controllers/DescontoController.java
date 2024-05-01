package com.example.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.models.DescontoModel;
import com.example.projeto.models.OfertaModel;
import com.example.projeto.service.DescontoService;
import com.example.projeto.service.OfertaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/descontos")
public class DescontoController {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private DescontoService descontoService;

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DescontoModel> insert(@RequestBody DescontoModel model, @RequestParam Integer ofertaId) {
        OfertaModel oferta = ofertaService.find(ofertaId);

        model.setOferta(oferta);

        model = descontoService.insert(model);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }
}
