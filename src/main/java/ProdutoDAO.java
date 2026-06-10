import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class ProdutoDAO extends ConnectionDAO {
    public boolean insert(Produto produto) {
        connectToDb();

        String sql = "INSERT INTO produto(nome, precoUni, qntEstoq, descr, id_dep) VALUES (?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getPrecoUni());
            pst.setInt(3, produto.getQntEstoq());
            pst.setString(4, produto.getDescr());
            pst.setInt(5, produto.getId_dep());

            pst.execute();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public Produto select(int id) {
        connectToDb();

        String sql = "SELECT * FROM produto WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPrecoUni(rs.getDouble("precoUni"));
                produto.setQntEstoq(rs.getInt("qntEstoq"));
                produto.setDescr(rs.getString("descr"));
                produto.setId_dep(rs.getInt("id_dep"));

                return produto;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }

        return null;
    }

    public boolean update(Produto produto) {
        connectToDb();

        String sql = "UPDATE produto SET nome = ?, precoUni = ?, qntEstoq = ?, descr = ?, id_dep = ? WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getPrecoUni());
            pst.setInt(3, produto.getQntEstoq());
            pst.setString(4, produto.getDescr());
            pst.setInt(5, produto.getId_dep());
            pst.setInt(6, produto.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        connectToDb();

        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> searchPrecoMenorOuIgual(double valor) { // BUSCA POR ATRIBUTO (PRECO)
        connectToDb();

        String sql = "SELECT * FROM produto WHERE precoUni <= ?";
        List<Produto> produtos = new ArrayList<>();

        try {
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, valor);

            rs = pst.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPrecoUni(rs.getDouble("precoUni"));
                produto.setQntEstoq(rs.getInt("qntEstoq"));
                produto.setDescr(rs.getString("descr"));
                produto.setId_dep(rs.getInt("id_dep"));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca por preço: " + e.getMessage());
        }
        return produtos;
    }

    public void listarProdutosPorDepartamento(String nomeDepartamento) {

        connectToDb();

        String sql = """
        SELECT
            d.id AS id_departamento,
            d.nome AS nome_departamento,
            p.id AS id_produto,
            p.nome AS nome_produto,
            p.precoUni,
            p.qntEstoq
        FROM departamento d
        LEFT JOIN produto p ON p.id_dep = d.id
        WHERE d.nome = ?
    """;

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nomeDepartamento);

            rs = pst.executeQuery();

            boolean encontrouDepartamento = false;
            boolean possuiProdutos = false;

            while (rs.next()) {
                encontrouDepartamento = true;
                int idDep = rs.getInt("id_departamento");
                String nomeDep = rs.getString("nome_departamento");
                Integer idProd = (Integer) rs.getObject("id_produto");

                if (idProd == null) {
                    System.out.println(
                            "Departamento: " + idDep +
                                    " - " + nomeDep +
                                    " não possui produtos cadastrados."
                    );
                } else {
                    possuiProdutos = true;

                    String nomeProd = rs.getString("nome_produto");
                    double preco = rs.getDouble("precoUni");
                    int estoque = rs.getInt("qntEstoq");

                    System.out.println(
                            "Departamento: " + idDep + " - " + nomeDep +
                                    " | Produto: " + idProd + " - " + nomeProd +
                                    " | Preço: R$ " + preco +
                                    " | Estoque: " + estoque
                    );
                }
            }
            if (!encontrouDepartamento) {
                System.out.println("Departamento não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro no JOIN produto-departamento: " + e.getMessage());
        }
    }

    public void selectAll() {

        connectToDb();
        String sql = "SELECT * FROM produto";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Nome: " + rs.getString("nome") +
                                " | Preço: R$ " + rs.getDouble("precoUni") +
                                " | Estoque: " + rs.getInt("qntEstoq") +
                                " | Descrição: " + rs.getString("descr") +
                                " | ID Departamento: " + rs.getInt("id_dep")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

}
