package modelos;

import java.util.HashMap;
import java.util.Map;

public class GestorInventarios {
    private Map<String, Producto> inventario = new HashMap<>();

    public boolean guardarProducto(String codigo, String nombre, double precio, int cantidad) {
        if (inventario.containsKey(codigo)) {
            return false; // Evita duplicados
        }
        inventario.put(codigo, new Producto(codigo, nombre, precio, cantidad));
        return true;
    }

    public Producto buscarProducto(String codigo) {
        return inventario.get(codigo);
    }
}

