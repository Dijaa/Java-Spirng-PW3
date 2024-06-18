package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projeto.dtos.DTOImovelSuper;
import com.example.projeto.dtos.DescontoDTO;
import com.example.projeto.dtos.ImovelDTO;
import com.example.projeto.dtos.ImovelDTOResposta;
import com.example.projeto.dtos.OfertaDTOResposta;
import com.example.projeto.models.DescontoModel;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;
import com.example.projeto.service.DescontoService;
import com.example.projeto.service.ImovelService;
import com.example.projeto.service.OfertaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/imoveis")
public class ImovelController {

	@Autowired
	private ImovelService service;

	@Autowired
	private DescontoService descontoService;

	@Autowired
	private OfertaService ofertaService;

	// @RequestMapping(method = RequestMethod.GET)
	// public ResponseEntity<List<ImovelModel>> getAll() {
	// List<ImovelModel> list = service.getAll();
	// return ResponseEntity.status(HttpStatus.OK).body(list);
	// }

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DTOImovelSuper>> getAll() {
			List<ImovelModel> list = service.getAll();

		List<DTOImovelSuper> listaDtos = list.stream().map(imovel ->  DTOImovelSuper.transformaEmDTO(imovel))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(listaDtos);

	}

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<Void> insert(@RequestBody ImovelDTO dto) {
	// ImovelModel imovel = service.transformaParaObjeto(dto);
	// service.insert(imovel);
	// return new ResponseEntity(imovel, HttpStatus.CREATED);
	// }

	// DIA 12/03
	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<ImovelDTOResposta> insert(@RequestBody ImovelDTO dto) {
	// ImovelModel imovel = service.transformaParaObjeto(dto);
	// service.insert(imovel);

	// return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel),
	// HttpStatus.CREATED);
	// }

	@RequestMapping(value = "/descontos", method = RequestMethod.GET)
	public ResponseEntity<List<DTOImovelSuper>> getDescontos() {
		List<DescontoModel> descontos = descontoService.getAllValidDescontos();
		// List<OfertaDTOResposta> ofertas = descontos.stream()
		// .map(desconto -> new OfertaDTOResposta(desconto.getOferta()))
		// .collect(Collectors.toList());
		// List<ImovelDTOResposta> imoveis = ofertas.stream().map(oferta ->
		// oferta.getImovelDTOResposta())
		// .collect(Collectors.toList());
		List<ImovelDTOResposta> imoveis = descontos.stream()
				.map(desconto -> ImovelDTOResposta.transformaEmDTO(desconto.getOferta().getImovelModel()))
				.collect(Collectors.toList());

		imoveis.forEach(imovel -> {
			imovel.setOfertas(descontos.stream()
					.filter(desconto -> desconto.getOferta().getImovelModel().getId() == imovel.getId())
					.map(desconto -> desconto.getOferta()).collect(Collectors.toList()));
		});
		List<DTOImovelSuper> imoveisSuper = imoveis.stream().map(imovel -> DTOImovelSuper.DTOemNovoDTO(imovel))
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(imoveisSuper);
		// return ResponseEntity.status(HttpStatus.OK)
		// .body(descontos.stream().map(desconto ->
		// desconto.getOferta().getImovelModel())
		// .collect(Collectors.toList()));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ImovelDTOResposta> insert(
			@RequestParam("descricao") String descricao,
			@RequestParam("quartos") Integer quartos,
			@RequestParam("vagas") Integer vagas,
			@RequestParam("usuario_id") Integer usuarioId,
			@RequestParam("imagem") MultipartFile imagem) {

		ImovelDTO imovelDTO = new ImovelDTO();
		imovelDTO.setDescricao(descricao);
		imovelDTO.setQuartos(quartos);
		imovelDTO.setVagas(vagas);
		imovelDTO.setUsuario_id(usuarioId);

		ImovelModel imovel = service.transformaParaObjeto(imovelDTO);

		// if (!imovel.getUserModel().isAdmin()) {
		// throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		// }

		String urlImagem = service.uploadImagem(imagem);

		imovel.setImagem(urlImagem);

		service.insert(imovel);

		return new ResponseEntity(ImovelDTOResposta.transformaEmDTO(imovel), HttpStatus.CREATED);
	}

}
