package com.tienda.service;

import com.tienda.domain.Item;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private HttpSession session;

    public void save(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        if (lista == null) {
            lista = new ArrayList<>();
        }

        boolean existe = false;
        for (Item i : lista) {
            if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                existe = true;
                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;

            }

        }
        if (!existe) {
            item.setCantidad(1);
            lista.add(item);
        }
        session.setAttribute("listaItems", lista);

    }

    public Item getItem(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        if (lista == null) {
            return null;
        }

        for (Item i : lista) {
            if (Objects.equals(item.getIdProducto(), i.getIdProducto())) {
                return i;

            }

        }
        return null;

    }

    public double getTotal() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        if (lista == null) {
            return 0;
        }

        double total = 0;
        for (Item i : lista) {
            total += i.getCantidad() * i.getPrecio();
        }
        return total;

    }

    public List<Item> getItems() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        return lista;
    }

}
