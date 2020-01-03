import java.util.Date;

public class Mensaje {

    private Long idMensaje;
    private Date fechaMensaje;
    private String emisorMensaje;
    private String receptorMensaje;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(Long idMensaje, Date fechaMensaje, String emisorMensaje, String receptorMensaje, String mensaje) {
        this.idMensaje = idMensaje;
        this.fechaMensaje = fechaMensaje;
        this.emisorMensaje = emisorMensaje;
        this.receptorMensaje = receptorMensaje;
        this.mensaje = mensaje;
    }

    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFechaMensaje() {
        return fechaMensaje;
    }

    public void setFechaMensaje(Date fechaMensaje) {
        this.fechaMensaje = fechaMensaje;
    }

    public String getEmisorMensaje() {
        return emisorMensaje;
    }

    public void setEmisorMensaje(String emisorMensaje) {
        this.emisorMensaje = emisorMensaje;
    }

    public String getReceptorMensaje() {
        return receptorMensaje;
    }

    public void setReceptorMensaje(String receptorMensaje) {
        this.receptorMensaje = receptorMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
