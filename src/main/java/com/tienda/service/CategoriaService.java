
package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaRepository.findAll();
        //acá falta un codigo
        return lista;
        
        
    }
    //Se crean los metodos para un CRUD CREATE READ UPDATE DELETE
    
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria) {
        categoria = categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
        return categoria;
    }
    
    @Transactional
    public void delete (Categoria categoria) {
        //Elimina el registroo que tiene el idCategoria pasado en el objeto categoria
        categoriaRepository.delete(categoria);

    }
    
     @Transactional
    public void save (Categoria categoria) {
        //Si el ID categoria tiene un valor se actualiza
        //Si el ID categoria no tiene valor, se agrega
        categoriaRepository.save(categoria);

    }

        
}
