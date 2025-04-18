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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String listado(Model model) {
        var lista = itemService.getItems();
        var totalCompra = itemService.getTotal();
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCompra", totalCompra);
        return "/carrito/listado";
    }

//    @GetMapping("/eliminar/{idProducto}")
//    public String eliminar(Model model, Item item) {
//        itemService.delete(item);
//        return "redirect:/carrito/listado";
//    }
    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(@PathVariable("idProducto") Long idProducto) {
        Producto producto = productoService.getProductoPorId(idProducto);

        if (producto == null) {
            return "redirect:/error";
        }

        Item item = new Item(producto);
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

//    @GetMapping("/modificar/{idProducto}")
//    public String modificar(Model model, Item item) {
//        Item item = itemService.getItem(item);
//        model.addAttribute("item",item);
//        return "/carrito/modifica";
//        }
    @GetMapping("/modificar/{idProducto}")
    public String modificar(@PathVariable("idProducto") Long idProducto, Model model) {
        Producto producto = productoService.getProductoPorId(idProducto);

        if (producto == null || !producto.isActivo()) {
            return "redirect:/error";
        }

        Item item = new Item(producto);
        item = itemService.getItem(item);

        if (item == null) {
            return "redirect:/carrito/listado";
        }

        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

//    @PostMapping("/guardar")
//    public String guardar(Item item) {
//        itemService.update(item);
//        return "redirect:/carrito/listado";
//    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam("idProducto") Long idProducto,
            @RequestParam("cantidad") int cantidad) {

        Producto producto = productoService.getProductoPorId(idProducto);

        if (producto == null || !producto.isActivo()) {
            return "redirect:/error";
        }

        Item item = new Item(producto);
        item.setCantidad(cantidad);

        itemService.update(item);
        return "redirect:/carrito/listado";
    }
}
