package com.ProjetoIntegrador.ProjetoIntegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ProjetoIntegrador.ProjetoIntegrador.models.Cliente; 

public interface ClienteRepository extends CrudRepository<Cliente, String> {
	Cliente findById(long id);
	List<Cliente> findByNome(String nome);
}
