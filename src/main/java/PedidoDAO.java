import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PedidoDAO extends ConnectionDAO {

    public boolean insert(Pedido pedido) {
        connectToDb();

        String sql = "INSERT INTO pedido(formaPag, id_cliente) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, pedido.getFormaPag());
            pst.setInt(2, pedido.getId_cliente());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir pedido: " + e.getMessage());
            return false;
        }
    }

    public Pedido select(int id) {
        connectToDb();

        String sql = "SELECT * FROM pedido WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setFormaPag(rs.getString("formaPag"));
                pedido.setId_cliente(rs.getInt("id_cliente"));

                return pedido;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pedido: " + e.getMessage());
        }

        return null;
    }

    public boolean update(Pedido pedido) {
        connectToDb();

        String sql = "UPDATE pedido SET formaPag = ?, id_cliente = ? WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, pedido.getFormaPag());
            pst.setInt(2, pedido.getId_cliente());
            pst.setInt(3, pedido.getId());

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        connectToDb();

        String sql = "DELETE FROM pedido WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar pedido: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> searchByFormaPag(String formaPag) { // BUSCA POR ATRIBUTO (FORMA DE PAGAMENTO)

        connectToDb();

        String sql = "SELECT * FROM pedido WHERE formaPag = ?";

        List<Pedido> lista = new ArrayList<>();
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, formaPag);
            rs = pst.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setFormaPag(rs.getString("formaPag"));
                pedido.setId_cliente(rs.getInt("id_cliente"));

                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca por forma de pagamento: " + e.getMessage());
        }
        return lista;
    }
    public void selectAll() {

        connectToDb();
        String sql = "SELECT * FROM pedido";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Forma de Pagamento: " + rs.getString("formaPag") +
                                " | ID Cliente: " + rs.getInt("id_cliente")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
    }

}
