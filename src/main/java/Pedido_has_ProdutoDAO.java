import java.sql.*;
public class Pedido_has_ProdutoDAO extends ConnectionDAO {

    public boolean insert(Pedido_has_Produto php) {
        connectToDb();

        String sql = "INSERT INTO pedido_has_produto(id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, php.getId_pedido());
            pst.setInt(2, php.getId_produto());
            pst.setInt(3, php.getQuantidade());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto no pedido: " + e.getMessage());
            return false;
        }
    }

    public Pedido_has_Produto select(int id_pedido, int id_produto) {
        connectToDb();

        String sql = "SELECT * FROM pedido_has_produto WHERE id_pedido = ? AND id_produto = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, id_pedido);
            pst.setInt(2, id_produto);

            rs = pst.executeQuery();

            if (rs.next()) {
                return new Pedido_has_Produto(
                        rs.getInt("id_pedido"),
                        rs.getInt("id_produto"),
                        rs.getInt("quantidade")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto do pedido: " + e.getMessage());
        }

        return null;
    }

    public boolean update(Pedido_has_Produto php) {
        connectToDb();

        String sql = "UPDATE pedido_has_produto SET quantidade = ? WHERE id_pedido = ? AND id_produto = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, php.getQuantidade());
            pst.setInt(2, php.getId_pedido());
            pst.setInt(3, php.getId_produto());

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto do pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id_pedido, int id_produto) {
        connectToDb();

        String sql = "DELETE FROM pedido_has_produto WHERE id_pedido = ? AND id_produto = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, id_pedido);
            pst.setInt(2, id_produto);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto do pedido: " + e.getMessage());
            return false;
        }
    }

    public void listarProdutosDoPedido(int idPedido) { // SELECT COM JOIN

        connectToDb();

        String sql = """
        SELECT\s
                                   php.id_pedido,
                                   p.id AS id_produto,
                                   p.nome AS nome_produto,
                                   c.nome AS nome_cliente,
                                   php.quantidade,
                                   p.precoUni,
                                   (php.quantidade * p.precoUni) AS subtotal,
                                   SUM(php.quantidade * p.precoUni) OVER (PARTITION BY php.id_pedido) AS total_pedido
                               FROM pedido_has_produto php
                               JOIN produto p ON php.id_produto = p.id
                               JOIN pedido pe ON php.id_pedido = pe.id
                               JOIN cliente c ON pe.id_cliente = c.id
                               WHERE php.id_pedido = ?;
    """;

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPedido);

            rs = pst.executeQuery();

            while (rs.next()) {

                int idPed = rs.getInt("id_pedido");
                int idProd = rs.getInt("id_produto");
                String nomeProd = rs.getString("nome_produto");
                String nomeCliente = rs.getString("nome_cliente");

                int quantidade = rs.getInt("quantidade");
                double preco = rs.getDouble("precoUni");
                double subtotal = rs.getDouble("subtotal");
                double totalPedido = rs.getDouble("total_pedido");

                System.out.println(
                        "Pedido: " + idPed +
                                " | Cliente: " + nomeCliente +
                                " | Produto: " + idProd + " - " + nomeProd +
                                " | Qtd: " + quantidade +
                                " | Preço: R$ " + preco +
                                " | Subtotal: R$ " + subtotal +
                                " | TOTAL PEDIDO: R$ " + totalPedido
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro no JOIN: " + e.getMessage());
        }
    }

    public void selectAll() {

        connectToDb();

        String sql = "SELECT * FROM pedido_has_produto";

        try {

            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {

                System.out.println(
                        "ID Pedido: " + rs.getInt("id_pedido") +
                                " | ID Produto: " + rs.getInt("id_produto") +
                                " | Quantidade: " + rs.getInt("quantidade")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens dos pedidos: " + e.getMessage());
        }
    }

}