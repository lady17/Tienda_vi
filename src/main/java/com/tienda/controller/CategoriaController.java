
package com.tienda.controller;

import com.tienda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
/*Todo lo que el usuario llame como Categoria va a entrar en esta clase */

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping ("/listado")
    public String listado (Model model) {
        
        
        var lista = categoriaService.getCategorias(false);
        model.addAttribute("categorias", lista);
        
        
        return "/categoria/listado";
    }
}
