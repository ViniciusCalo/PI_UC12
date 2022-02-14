package com.ProjetoIntegrador.ProjetoIntegrador.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProjetoIntegrador.ProjetoIntegrador.models.Cliente;
import com.ProjetoIntegrador.ProjetoIntegrador.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository cr;

	// CADASTRAR CLIENTE
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.GET)
	public String form() {

		return "cliente/formCliente";
	}

	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public String form(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastrarCliente";
		}
		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Dados registrados com sucesso");
		return "redirect:/cadastrarCliente";
		
	}

	// LISTA CLIENTE
	@RequestMapping("/cliente")
	public ModelAndView listacliente() {
		ModelAndView mv = new ModelAndView("cliente/listaCliente");
		Iterable<Cliente> cliente = cr.findAll();
		mv.addObject("clientes", cliente);
		return mv;
	}

	// DETALHES DO CADASTRO DO CLIENTE
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesCliente(@PathVariable("id") long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("cliente/detalhes-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	public String detalhesClientePost(@PathVariable("id") long id, @Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{id}";
		}
			
			return "redirect:/{id}";
	}

	// DELETA CLIENTE
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long id) {
		Cliente cliente = cr.findById(id);
		cr.delete(cliente);
		return "redirect:/cliente";
	}

	//METODOS QUE ATUALIZAM O CLIENTE
	// formulario de edição do cliente
	@RequestMapping(value="editar-cliente", method = RequestMethod.GET)
	public ModelAndView editarCliente(long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("cliente/update-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	//UPDATE DO CLIENTE
	@RequestMapping(value="/editar-cliente", method = RequestMethod.POST)
	public String updateCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		cr.save(cliente);
		attributes.addFlashAttribute("success", "Dados cliente alterado com sucesso!!!");
		long idLong = cliente.getId();
		String id = "" + idLong;
		return "redirect:/" + id;
	}
	
	
}
