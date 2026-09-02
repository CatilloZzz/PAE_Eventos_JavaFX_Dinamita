package ni.edu.uam.reto_1.modelos;

import javafx.scene.image.ImageView;

public class Artesania {

    private String codigo;
    private String nombre;
    private double precio;
    private ImageView imagen;

    public Artesania(String codigo, String nombre, double precio, ImageView imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;

        // Ajusta el tamaño de la imagen automáticamente para que encaje en la tabla
        if (this.imagen != null) {
            this.imagen.setFitHeight(50);
            this.imagen.setFitWidth(50);
        }
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public ImageView getImagen() { return imagen; }
}
