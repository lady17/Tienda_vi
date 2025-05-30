package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoRepository.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;

    }
    //Se crean los metodos para un CRUD CREATE READ UPDATE DELETE

    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        producto = productoRepository.findById(producto.getIdProducto()).orElse(null);
        return producto;
    }

    @Transactional
    public void delete(Producto producto) {
        //Elimina el registroo que tiene el idProducto pasado en el objeto producto
        productoRepository.delete(producto);

    }

    @Transactional
    public void save(Producto producto) {
        //Si el ID producto tiene un valor se actualiza
        //Si el ID producto no tiene valor, se agrega
        productoRepository.save(producto);

    }

    @Transactional(readOnly = true)
    public List<Producto> consultaAmpliada(double precioInf, double precioSup) {
        return productoRepository.findByPrecioBetweenOrderByPrecio(precioInf, precioSup);

    }

    @Transactional(readOnly = true)
    public List<Producto> consultaJPQL(double precioInf, double precioSup) {
        return productoRepository.consultaJPQL(precioInf, precioSup);

    }

    @Transactional(readOnly = true)
    public List<Producto> consultaSQL(double precioInf, double precioSup) {
        return productoRepository.consultaSQL(precioInf, precioSup);

    }

    @Transactional(readOnly = true)
    public Producto getProductoPorId(Long idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

}
