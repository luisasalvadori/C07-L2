import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClienteDAO extends ConnectionDAO {

    public boolean insert(Cliente cliente) {
        connectToDb();

        String sql = "INSERT INTO cliente(nome, numero, cep, rua) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, cliente.getNome());
            pst.setInt(2, cliente.getNumero());
            pst.setString(3, cliente.getCep());
            pst.setString(4, cliente.getRua());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente select(int id) {
        connectToDb();

        String sql = "SELECT * FROM cliente WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setRua(rs.getString("rua"));
                cliente.setCep(rs.getString("cep"));

                return cliente;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }

        return null;
    }

    public boolean update(Cliente cliente) {
        connectToDb();

        String sql = "UPDATE cliente SET nome = ?, numero = ?, cep = ?, rua = ? WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, cliente.getNome());
            pst.setInt(2, cliente.getNumero());
            pst.setString(3, cliente.getCep());
            pst.setString(4, cliente.getRua());
            pst.setInt(5, cliente.getId());

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        connectToDb();

        String sql = "DELETE FROM cliente WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> searchByCep(String cep) { // BUSCA POR ATRIBUTO (CEP)
        connectToDb();

        String sql = "SELECT * FROM cliente WHERE cep = ?";

        List<Cliente> clientes = new ArrayList<>();

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cep);

            rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setRua(rs.getString("rua"));
                cliente.setCep(rs.getString("cep"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca por CEP: " + e.getMessage());
        }
        return clientes;
    }

    public void selectAll() {
        connectToDb();
        String sql = "SELECT * FROM cliente";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Nome: " + rs.getString("nome") +
                                " | Número: " + rs.getInt("numero") +
                                " | Rua: " + rs.getString("rua") +
                                " | CEP: " + rs.getString("cep")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
    }
}
