import java.sql.*;
public class DepartamentoDAO extends ConnectionDAO {

    public boolean insert(Departamento departamento) {
        connectToDb();

        String sql = "INSERT INTO departamento(nome) VALUES (?)";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, departamento.getNome());

            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir departamento: " + e.getMessage());
            return false;
        }
    }

    public Departamento select(int id) {
        connectToDb();

        String sql = "SELECT * FROM departamento WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                Departamento departamento = new Departamento();

                departamento.setId(rs.getInt("id"));
                departamento.setNome(rs.getString("nome"));

                return departamento;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar departamento: " + e.getMessage());
        }

        return null;
    }

    public boolean update(Departamento departamento) {
        connectToDb();

        String sql = "UPDATE departamento SET nome = ? WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, departamento.getNome());
            pst.setInt(2, departamento.getId());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar departamento: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        connectToDb();

        String sql = "DELETE FROM departamento WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar departamento: " + e.getMessage());
            return false;
        }
    }

    public void listarFuncionariosPorDepartamentoNome(String nomeDep) {

        connectToDb();

        String sql = """
        SELECT 
            d.id AS id_departamento,
            d.nome AS nome_departamento,
            f.id AS id_funcionario,
            f.nome AS nome_funcionario
        FROM departamento d
        JOIN funcionario f ON f.id_dep = d.id
        WHERE d.nome = ?
    """;

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nomeDep);
            rs = pst.executeQuery();
            while (rs.next()) {

                int idDep = rs.getInt("id_departamento");
                String nomeDepartamento = rs.getString("nome_departamento");

                int idFunc = rs.getInt("id_funcionario");
                String nomeFunc = rs.getString("nome_funcionario");

                System.out.println(
                        "Depto: " + idDep + " - " + nomeDepartamento +
                                " | Funcionário ID: " + idFunc + " - " + nomeFunc
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro no JOIN: " + e.getMessage());
        }
    }

    public void selectAll() {
        connectToDb();
        String sql = "SELECT * FROM departamento";

        try {

            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Nome: " + rs.getString("nome")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar departamentos: " + e.getMessage());
        }
    }
}