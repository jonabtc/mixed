public class Filtro {
    private Long id;
    private String tipoFiltro;

    public Filtro() {
    }

    public Filtro(Long id, String tipoFiltro) {
        this.id = id;
        this.tipoFiltro = tipoFiltro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoFiltro() {
        return tipoFiltro;
    }

    public void setTipoFiltro(String tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }
}