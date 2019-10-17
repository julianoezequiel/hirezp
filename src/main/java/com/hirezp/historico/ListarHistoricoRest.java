package com.hirezp.historico;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.core.JsonProcessingException;


@RequestMapping("rest/")
@RestController
@RequestScope
public class ListarHistoricoRest {

	@Autowired
	private HitoricoService hitoricoService;
	
	@RequestMapping(value = "/carregarHistorico", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> carregar(@Autowired HttpServletRequest request, @RequestBody Consulta consulta) throws Exception{
		return this.hitoricoService.carregar(consulta);
	}
}
