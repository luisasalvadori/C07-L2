public class Pedido {
    private int id;
    private String formaPag;
    private int id_cliente;

    public Pedido(String formaPag, int id_cliente) {
        this.formaPag = formaPag;
        this.id_cliente = id_cliente;
    }

    public Pedido(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}