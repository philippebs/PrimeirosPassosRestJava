package com.ph.project.dados;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ph.project.entity.Cliente;

@Repository
public class ClienteDados {
	
	public static final Map<Long, Cliente> MAP_CLIENTES = new HashMap<>();
	public static Long idAtual = new Long(0L);
	
	static{
		idAtual += 1L;
		MAP_CLIENTES.put(1L, new Cliente(idAtual, "Teste", "Funcionando", 15));
		
	}
	
	public Cliente buscarCliente(Long id){
		return MAP_CLIENTES.get(id);
	}
	
	public void inserirCliente(Cliente cliente) throws Exception{
		if(cliente != null){
			MAP_CLIENTES.put(proximoId(cliente), cliente);
		}else{
			throw new Exception("Nenhum cliente foi passado");
		}
	}
	
	public List<Cliente> buscarTodos(){
		List<Cliente> clientes = new ArrayList<>();
		Set<Long> ids = MAP_CLIENTES.keySet();
		for(Long id : ids){
			clientes.add(MAP_CLIENTES.get(id));
		}
		return clientes;
	}
	
	private Long proximoId(Cliente cliente){
		idAtual += 1L;
		
		if(cliente.getId() != null){
			if(idAtual.compareTo(cliente.getId()) < 0){
				idAtual = cliente.getId();
			}
		}
		cliente.setId(idAtual);
		
		return idAtual;
	}
}