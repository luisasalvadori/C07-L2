public class Produto {

    private int id;
    private String nome;
    private double precoUni;
    private int qntEstoq;
    private String descr;
    private int id_dep;

    public Produto() {
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

    public double getPrecoUni() {
        return precoUni;
    }

    public void setPrecoUni(double precoUni) {
        this.precoUni = precoUni;
    }

    public int getQntEstoq() {
        return qntEstoq;
    }

    public void setQntEstoq(int qntEstoq) {
        this.qntEstoq = qntEstoq;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId_dep() {
        return id_dep;
    }

    public void setId_dep(int id_dep) {
        this.id_dep = id_dep;
    }
}
