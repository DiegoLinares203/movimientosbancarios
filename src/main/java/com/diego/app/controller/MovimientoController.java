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

import com.diego.app.models.entity.CuentaBancaria;
import com.diego.app.models.entity.Movimiento;
import com.diego.app.service.inte.ICuentaBancariaService;
import com.diego.app.service.inte.IMovimientoService;

@Controller
@SessionAttributes("movimiento")
@RequestMapping("/movimiento")
public class MovimientoController {
	
	@Autowired
	private IMovimientoService movimientoService;
	
	@Autowired
	private ICuentaBancariaService cuentabancariaService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("movimientos", movimientoService.findAll());
		model.addAttribute("titulo", "Lista de Movimientos");
		
		return "movimiento/listar";
	}
	
	@RequestMapping(value="/form/{cuentabancariaid}", method=RequestMethod.GET)
	public String crear(@PathVariable(value="cuentabancariaid") Long id, Model model, RedirectAttributes flash) {
		
		CuentaBancaria cuentabancaria = cuentabancariaService.findOne(id);
		
		if(cuentabancaria==null) {
			flash.addFlashAttribute("error", "La Cuenta Bancaria no existe");
			return "redirect:/cuentabancaria/listar";
		}
		
		Movimiento movimiento = new Movimiento();
		movimiento.setCuentabancaria(cuentabancaria);
		
		model.addAttribute("movimiento", movimiento);
		model.addAttribute("titulo", "Crear Movimiento");
		
		return "movimiento/form";
	}
	
	@RequestMapping(value="/ver/{id}", method=RequestMethod.GET)
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Movimiento movimiento = movimientoService.findOne(id);
		
		if(movimiento == null) {
			flash.addFlashAttribute("error", "El movimiento no existe");
			return "redirect:/cuentabancaria/listar";
		}
		
		model.addAttribute("movimiento", movimiento);
		model.addAttribute("titulo", "Detalle del Movimiento: " + movimiento.getId());
		
		return "movimiento/ver";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Movimiento movimiento, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Movimiento");
			return "movimiento/form";
		}
		
		movimientoService.save(movimiento);
		status.setComplete();
		flash.addFlashAttribute("success", "Movimiento Creado Correctamente");
		
		return "redirect:/ver" + movimiento.getCuentabancaria().getId();
	}

}
