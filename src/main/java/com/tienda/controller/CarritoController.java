package com.tienda.controller;

import com.tienda.domain.Item;
import com.tienda.domain.Producto;
import com.tienda.service.ItemService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author leidy
 */
@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/agregar/{idProducto}")
    public ModelAndView agregar(@PathVariable("idProducto") Long idProducto, Model model) {

        Producto producto = productoService.getProductoPorId(idProducto);
        if (producto == null || !producto.isActivo()) {
            return new ModelAndView("redirect:/error");
        }
        Item item = new Item(producto);
        itemService.save(item);

        var lista = itemService.getItems();
        var totalCompra = itemService.getTotal();

        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCompra", totalCompra);
        model.addAttribute("totalProductos", lista.size());

        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
    @GetMapping("/listado")
    public String listado (Model model){
        var lista = itemService.getItems();
        model.addAttribute("listaItems", lista);
        return "/carrito/listado";
    }
    
    
    
}
