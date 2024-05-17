package com.daw.swapp.controller.vistaAlex;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.daw.swapp.model.AlexModel.Alumno;

@Controller
public class AlexController {
	@GetMapping("/alex")
	public String showAlex(Model model) {
		Alumno pepe = new Alumno("Pepe",18);
		Alumno jose = new Alumno("Jose",21);
		List<Alumno> alumnos = new ArrayList<Alumno>();
		alumnos.add(jose);
		alumnos.add(pepe);
		model.addAttribute("alumnos", alumnos);
		model.addAttribute("alumno1", pepe);
		model.addAttribute("alumno2", jose);
		return "alex/home";
	}
}
