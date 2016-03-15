package com.ph.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ph.project.dados.ClienteDados;
import com.ph.project.entity.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteService {
	
	@Autowired
	private ClienteDados clienteDados;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		System.out.print("Buscando cliente");
		HttpStatus  httpStatus;
		Cliente cliente = clienteDados.buscarCliente(id);
		if(cliente != null){
			httpStatus = HttpStatus.OK;
		}else{
			httpStatus = HttpStatus.NOT_FOUND;
		}
		System.out.println(" " + cliente);
		return new ResponseEntity<>(cliente, httpStatus);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> buscarTodos() throws Exception{
		try {
			System.out.print("Buscando cliente");
			HttpStatus  httpStatus;
			List<Cliente> clientes = clienteDados.buscarTodos();
			if(clientes != null && clientes.size() > 0){
				httpStatus = HttpStatus.OK;
			}else{
				httpStatus = HttpStatus.NOT_FOUND;
			}
			System.out.println(" QTD: " + clientes.size());
			return new ResponseEntity<> (clientes, httpStatus);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente)throws Exception{
		try {
			clienteDados.inserirCliente(cliente);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
