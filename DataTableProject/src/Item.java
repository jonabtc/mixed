
public class Item implements Cloneable{
    private Long id;
    private String nombre;
    private String serial;
    private Double precio;
    private int stock;
    private int pedido;

    public Item(Long id, String serial, String nombre, Double precio, int stock){
        this.id = id;
        this.serial = serial;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.pedido = 0;
    }
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPedido() {

        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }
}
