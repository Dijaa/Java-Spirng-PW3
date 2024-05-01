package com.example.projeto.models;

import java.io.Serializable;
import java.util.List;

import com.example.projeto.enums.TipoDesconto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="descontos")
public class DescontoModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer tipoDesconto;

    @ManyToOne
    @JoinColumn(name="oferta_id")
    @JsonIgnore
    private OfertaModel oferta;

    private double valor;

    // datalimite expiração
    private String dataLimite;

    

    public TipoDesconto getTipoDesconto() {
        return TipoDesconto.toEnum(tipoDesconto);
    }

    public void setTipoDesconto(TipoDesconto tipoDesconto) {
        this.tipoDesconto = tipoDesconto.getCodigo();
    }

    
}
