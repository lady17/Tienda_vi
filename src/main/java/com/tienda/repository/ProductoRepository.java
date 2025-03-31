package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductoRepository extends JpaRepository<Producto, Long>{
    


public List<Producto> findByPrecioBetweenOrderByPrecio (
    double precioInf,
    double precioSup);


@Query("SELECT a FROM Producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.precio")
public List<Producto> consultaJPQL(
        @Param("precioInf") double precioInf,
        @Param("precioSup") double precioSup);



@Query(nativeQuery = true,
       value = "SELECT * " +
               "FROM producto a " +
               "WHERE a.precio " +
               "BETWEEN ?1 AND ?2 " +
               "ORDER BY a.precio")
List<Producto> consultaSQL(double precioInf, double precioSup);



}