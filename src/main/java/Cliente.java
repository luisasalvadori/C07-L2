public class Cliente {
    private int id;
    private String nome;
    private int numero;
    private String rua;
    private String cep;

    public Cliente(String nome, int numero, String cep, String rua) {
        this.nome = nome;
        this.numero = numero;
        this.cep = cep;
        this.rua = rua;
    }
    public Cliente(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
