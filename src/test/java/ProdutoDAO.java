import java.sql.*;
public class ProdutoDAO extends ConnectionDAO {
    public boolean insert (Produto produto){
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
        }catch (SQLException e){
            System.out.println("Erro ao inserir funcionario: " + e.getMessage());
            return false;
        } finally {
            try {

                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                System.out.println(
                        "Erro ao fechar recursos: "
                                + e.getMessage()
                );
            }
        }
    }
}
