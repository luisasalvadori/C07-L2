import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FuncionarioDAO extends ConnectionDAO{

    public boolean insert (Funcionario funcionario){
        connectToDb();
        String sql = "INSERT INTO funcionario(nome, cpf, rg, salario, dataNasc, idade, telefone, id_dep, id_gerente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getRg());
            pst.setDouble(4, funcionario.getSalario());
            pst.setDate(5, java.sql.Date.valueOf(funcionario.getDataNasc()));
            pst.setInt(6, funcionario.getIdade());
            pst.setString(7, funcionario.getTelefone());
            pst.setInt(8, funcionario.getIdDep());
            if(funcionario.getIdGerente() == null){
                pst.setNull(9, Types.INTEGER);
            } else {
                pst.setInt(9, funcionario.getIdGerente());
            }

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
    public Funcionario select(int id) {
        connectToDb();

        String sql = "SELECT * FROM funcionario WHERE id = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);

            rs = pst.executeQuery();

            if (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setDataNasc(rs.getDate("datanasc").toLocalDate());
                funcionario.setIdade(rs.getInt("idade"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setIdDep(rs.getInt("id_dep"));

                int idGerente = rs.getInt("id_gerente");

                if(rs.wasNull()){
                    funcionario.setIdGerente(null);
                } else {
                    funcionario.setIdGerente(idGerente);
                }
                return funcionario;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionario: " + e.getMessage());
        }
        return null;
    }
    public boolean update(Funcionario funcionario) {
        connectToDb();

        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, rg = ?, salario = ?, dataNasc = ?, idade = ?, telefone = ?, id_dep = ?, id_gerente = ? WHERE id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getCpf());
            pst.setString(3, funcionario.getRg());
            pst.setDouble(4, funcionario.getSalario());
            pst.setDate(5, java.sql.Date.valueOf(funcionario.getDataNasc()));
            pst.setInt(6, funcionario.getIdade());
            pst.setString(7, funcionario.getTelefone());
            pst.setInt(8, funcionario.getIdDep());
            if(funcionario.getIdGerente() == null){
                pst.setNull(9, Types.INTEGER);
            } else {
                pst.setInt(9, funcionario.getIdGerente());
            }
            pst.setInt(10, funcionario.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionario: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        connectToDb();
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionario: " + e.getMessage());
            return false;
        }
    }

    public List<Funcionario> searchSalarioMenorOuIgual(double valor) { // BUSCA POR ATRIBUTO (SALARIO)

        connectToDb();

        String sql = "SELECT * FROM funcionario WHERE salario <= ?";

        List<Funcionario> lista = new ArrayList<>();

        try {
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, valor);

            rs = pst.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setSalario(rs.getDouble("salario"));
                funcionario.setDataNasc(rs.getDate("dataNasc").toLocalDate());
                funcionario.setIdade(rs.getInt("idade"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setIdDep(rs.getInt("id_dep"));

                int idGerente = rs.getInt("id_gerente");

                if (rs.wasNull()) {
                    funcionario.setIdGerente(null);
                } else {
                    funcionario.setIdGerente(idGerente);
                }
                lista.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca por salário: " + e.getMessage());
        }
        return lista;
    }

    public void selectAll() {

        connectToDb();

        String sql = "SELECT * FROM funcionario";

        try {

            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Integer idGerente = (Integer) rs.getObject("id_gerente");
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Nome: " + rs.getString("nome") +
                                " | CPF: " + rs.getString("cpf") +
                                " | RG: " + rs.getString("rg") +
                                " | Salário: R$ " + rs.getDouble("salario") +
                                " | Data Nasc.: " + rs.getDate("dataNasc") +
                                " | Idade: " + rs.getInt("idade") +
                                " | Telefone: " + rs.getString("telefone") +
                                " | ID Departamento: " + rs.getInt("id_dep") +
                                " | ID Gerente: " + idGerente
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
    }

}
