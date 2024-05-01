package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projeto.models.OfertaModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class OfertaDTOSuer implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String tipo;

    private double valor;

    private String nomeUsuario;

    private List<DescontoDTO> descontoDTO;

    public OfertaDTOSuer() {
    }

    public OfertaDTOSuer(OfertaModel model) {
        this.id = model.getId();
        this.valor = model.getValor();
        this.nomeUsuario = model.getUserModel().getNome();
        this.descontoDTO = model.getDescontos().stream().map(desconto -> DescontoDTO.transformaEmDTO(desconto)).collect(Collectors.toList());
    }

}
