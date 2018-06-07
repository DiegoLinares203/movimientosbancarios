package com.diego.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diego.app.models.entity.Cliente;
import com.diego.app.models.entity.CuentaBancaria;
import com.diego.app.service.inte.IClienteService;
import com.diego.app.service.inte.ICuentaBancariaService;

@Controller
@SessionAttributes("cuentabancaria")
@RequestMapping("/cuentabancaria")
public class CuentaBancariaController {
	
	@Autowired
	private ICuentaBancariaService cuentabancariaService;
	
	private IClienteService clienteService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("cuentabancarias", cuentabancariaService.findAll());
		model.addAttribute("titulo", "Listado de Cuentas");
		
		return "cuentabancaria/listar";
	}
	
	@RequestMapping(value="/form/{clienteid}", method=RequestMethod.GET)
	public String crear(@PathVariable(value="clienteid") Long id, Model model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(id);
		
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/listar";
		}
		
		CuentaBancaria cuentabancaria= new CuentaBancaria();
		cuentabancaria.setCliente(cliente);
		
		model.addAttribute("cuentabancaria", cuentabancaria);
		model.addAttribute("titulo", "Crear Cuenta Bancaria");
		
		return "cuentabancaria/form";
	}
	
	@RequestMapping(value="/ver/{id}", method=RequestMethod.GET)
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		CuentaBancaria cuentabancaria = cuentabancariaService.findOne(id);
		
		if(cuentabancaria==null) {
			flash.addFlashAttribute("error", "La cuenta no existe");
			return "redirect:/listar";
		}
		
		model.addAttribute("cuentabancaria", cuentabancaria);
		model.addAttribute("titulo", "Detalle de Cuenta Bancaria del Banco: " + cuentabancaria.getBanco());
		
		return "cuentabancaria/ver";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid CuentaBancaria cuentabancaria, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cuenta Bancaria");
			return "cuentabancaria/form";
		}
		
		cuentabancariaService.save(cuentabancaria);
		status.setComplete();
		flash.addFlashAttribute("success", "Cuenta creada correctamente");
		
		return "redirect:/ver/" + cuentabancaria.getCliente().getId();
	}

}
