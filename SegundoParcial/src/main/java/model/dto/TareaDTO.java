package model.dto;

public class TareaDTO {
    private int id;
    private String texto;
    private String fecha;
    private int tipo;

    public TareaDTO(int id, String texto, String fecha, int tipo) {
        this.id = id;
        this.texto = texto;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public TareaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
