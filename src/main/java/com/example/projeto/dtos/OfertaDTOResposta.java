package com.example.projeto.dtos;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.projeto.models.OfertaModel;
import com.example.projeto.service.UserService;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OfertaDTOResposta implements Serializable{
    private static final long serialVersionUID =1L;

     @Autowired
    private UserService userService;

    private Integer id;

    private String tipo;
 
    private double valor; 

    private String nomeUsuario;

    private ImovelDTOResposta imovelDTOResposta;
    
    private DescontoDTO descontoDTO;

    

    public OfertaDTOResposta(){}

    public OfertaDTOResposta(OfertaModel model){
      this.id = model.getId();
      this.valor = model.getValor();
      this.nomeUsuario = model.getUserModel().getNome();
      this.imovelDTOResposta = imovelDTOResposta.transformaEmDTO(model.getImovelModel());
      
    }

   
}
