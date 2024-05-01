package com.example.projeto.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.EAN;

import com.example.projeto.enums.TipoDesconto;
import com.example.projeto.models.DescontoModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DescontoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private TipoDesconto tipoDesconto;

    private Integer oferta_id;

    private double valor;

    private String dataLimite;

    public DescontoDTO() {
    }

    public DescontoDTO(DescontoModel model) {
        this.id = model.getId();
        this.tipoDesconto = model.getTipoDesconto();
        this.oferta_id = model.getOferta().getId();
        this.valor = model.getValor();
        this.dataLimite = model.getDataLimite();
    }

    public static DescontoDTO transformaEmDTO(DescontoModel desconto) {
        return new DescontoDTO(desconto);
    }

}
