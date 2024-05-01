package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projeto.models.ImovelModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DTOImovelSuper implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Integer quartos;
    private Integer vagas;
    private Integer usuario_id;
    private String imagem;
    private List<OfertaDTOSuer> ofertas;

    public DTOImovelSuper() {
    }

    public DTOImovelSuper(Integer id, String descricao, Integer quartos, Integer vagas, String imagem,
            Integer usuario_id, List<OfertaDTOSuer> ofertas) {
        this.id = id;
        this.descricao = descricao;
        this.quartos = quartos;
        this.vagas = vagas;
        this.imagem = imagem;
        this.usuario_id = usuario_id;
        this.ofertas = ofertas;
    }

    public static DTOImovelSuper transformaEmDTO(ImovelModel imovel) {
        return new DTOImovelSuper(imovel.getId(), imovel.getDescricao(), imovel.getQuartos(), imovel.getVagas(),
                imovel.getImagem(), imovel.getUserModel().getId(), imovel.getOfertas().stream()
                        .map(oferta -> new OfertaDTOSuer(oferta)).collect(Collectors.toList()));
    }

    public static DTOImovelSuper DTOemNovoDTO(ImovelDTOResposta imovel) {
        return new DTOImovelSuper(imovel.getId(), imovel.getDescricao(), imovel.getQuartos(), imovel.getVagas(),
                imovel.getImagem(), imovel.getUsuario_id(), imovel.getOfertas().stream()
                        .map(oferta -> new OfertaDTOSuer(oferta)).collect(Collectors.toList()));
    }
}
