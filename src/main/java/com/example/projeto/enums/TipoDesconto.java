package com.example.projeto.enums;

import lombok.Getter;

@Getter
public enum TipoDesconto {
    VALOR(1, "Valor"),
    PERCENTUAL(2, "Percentual");

    private int codigo;
    private String descricao;

    
    private TipoDesconto(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }


    public static TipoDesconto toEnum(Integer codigo){
        if(codigo == null){
            return null;
         }

        for (TipoDesconto x: TipoDesconto.values()){
            if(codigo.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Tipo de oferta inválida");
    }


}
