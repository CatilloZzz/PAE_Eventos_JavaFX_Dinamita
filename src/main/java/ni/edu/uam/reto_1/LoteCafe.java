package ni.edu.uam.reto_1;

import java.time.LocalDate;

public class LoteCafe {
    private String idLote;
    private String productor;
    private double quintales;
    private String tipoVariedad; // Ej: Arábica, Robusta
    private LocalDate fechaEntrega;

    public LoteCafe(String idLote, String productor, double quintales, String tipoVariedad, LocalDate fechaEntrega) {
        this.idLote = idLote;
        this.productor = productor;
        this.quintales = quintales;
        this.tipoVariedad = tipoVariedad;
        this.fechaEntrega = fechaEntrega;
    }

    public String getIdLote() { return idLote; }
    public void setIdLote(String idLote) { this.idLote = idLote; }

    public String getProductor() { return productor; }
    public void setProductor(String productor) { this.productor = productor; }

    public double getQuintales() { return quintales; }
    public void setQuintales(double quintales) { this.quintales = quintales; }

    public String getTipoVariedad() { return tipoVariedad; }
    public void setTipoVariedad(String tipoVariedad) { this.tipoVariedad = tipoVariedad; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
}