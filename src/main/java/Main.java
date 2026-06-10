import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.sql.*;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        Pedido_has_ProdutoDAO phpDAO = new Pedido_has_ProdutoDAO();
        int op;
        int op2;

        do{
            System.out.println("\n=========== Menu ===========");
            System.out.println("Bem-vindo(a) à Padaria MaDaLu!");
            System.out.println("1 - Produtos");
            System.out.println("2 - Clientes");
            System.out.println("3 - Pedidos");
            System.out.println("4 - Produto(s) do Pedido");
            System.out.println("5 - Funcionários");
            System.out.println("6 - Departamentos");
            System.out.println("7 - Visualizar todo o Banco de Dados");
            System.out.println("0 - Sair");
            System.out.println("Opção: ");
            op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Listar Por Departamento | 6 - Buscar por Preço | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch (op2){
                        case 1 -> {
                            Produto p = new Produto();
                            System.out.println("Insira as informações sobre o novo produto: ");

                            System.out.print("Nome: ");
                            p.setNome(sc.nextLine());

                            System.out.print("Preço: ");
                            p.setPrecoUni(sc.nextDouble());
                            System.out.print("Quantidade em estoque: ");
                            p.setQntEstoq(sc.nextInt());
                            sc.nextLine();
                            System.out.print("Descrição: ");
                            p.setDescr(sc.nextLine());
                            System.out.print("ID do Departamento: ");
                            p.setId_dep(sc.nextInt());
                            if(produtoDAO.insert(p)){
                                System.out.println("Produto inserido com sucesso!");
                            }
                        }

                        case 2 -> {

                            System.out.print("ID do produto: ");
                            int id = sc.nextInt();

                            Produto p = produtoDAO.select(id);

                            if(p != null){

                                sc.nextLine();

                                System.out.print("Novo nome: ");
                                p.setNome(sc.nextLine());
                                System.out.print("Novo preço: ");
                                p.setPrecoUni(sc.nextDouble());
                                System.out.print("Novo estoque: ");
                                p.setQntEstoq(sc.nextInt());
                                sc.nextLine();
                                System.out.print("Nova descrição: ");
                                p.setDescr(sc.nextLine());
                                System.out.print("Novo ID Departamento: ");
                                p.setId_dep(sc.nextInt());
                                if(produtoDAO.update(p)){
                                    System.out.println("Produto atualizado.");
                                }
                            } else {
                                System.out.println("Produto não encontrado.");
                            }

                        }

                        case 3 -> {
                            System.out.print("Insira o ID do produto: ");
                            int id = sc.nextInt();
                            Produto p = produtoDAO.select(id);
                            if(p != null){

                                System.out.println("\n===== PRODUTO =====");
                                System.out.println("ID: " + p.getId());
                                System.out.println("Nome: " + p.getNome());
                                System.out.println("Preço: " + p.getPrecoUni());
                                System.out.println("Estoque: " + p.getQntEstoq());
                                System.out.println("Descrição: " + p.getDescr());
                                System.out.println("ID Departamento: " + p.getId_dep());
                            } else {
                                System.out.println("Produto não encontrado.");
                            }
                        }

                        case 4 -> {
                            System.out.print("Insira o ID do produto: ");
                            int id = sc.nextInt();
                            if(produtoDAO.delete(id)){
                                System.out.println("Produto removido.");
                            }
                        }

                        case 5 -> {
                            System.out.print("Insira o nome do departamento: ");
                            String nomeDep = sc.next();
                            produtoDAO.listarProdutosPorDepartamento(nomeDep);
                        }

                        case 6 -> {
                            System.out.println("Insira o preço máximo: ");
                            double valor = sc.nextDouble();
                            List<Produto> baratos = produtoDAO.searchPrecoMenorOuIgual(valor);
                            for (Produto p : baratos) {
                                System.out.println(p.getNome() + " - R$ " + p.getPrecoUni());
                            }
                        }
                    }

                }

                case 2 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Buscar por CEP | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch (op2){
                        case 1 -> {
                            Cliente c = new Cliente();
                            System.out.println("Insira as informações sobre o novo cliente: ");

                            System.out.println("Nome: ");
                            c.setNome(sc.nextLine());

                            System.out.println("Endereço ");
                            System.out.println("Rua: ");
                            c.setRua(sc.nextLine());
                            System.out.println("Número: ");
                            c.setNumero(sc.nextInt());
                            sc.nextLine();
                            System.out.println("CEP: ");
                            c.setCep(sc.nextLine());
                            if(clienteDAO.insert(c)){
                                System.out.println("Cliente cadastrado com sucesso!");
                            }
                        }
                        case 2 -> {
                            System.out.println("Insira o ID do cliente: ");
                            int id = sc.nextInt();

                            Cliente c = clienteDAO.select(id);

                            if(c != null){
                                sc.nextLine();

                                System.out.println("Novo nome: ");
                                c.setNome(sc.nextLine());

                                System.out.println("Novo endereço ");
                                System.out.println("Nova rua: ");
                                c.setRua(sc.nextLine());
                                System.out.println("Novo número: ");
                                c.setNumero(sc.nextInt());
                                sc.nextLine();
                                System.out.println("Novo CEP: ");
                                c.setCep(sc.nextLine());
                                if(clienteDAO.update(c)){
                                    System.out.println("Cliente atualizado com sucesso!");
                                }
                                else {
                                    System.out.println("Cliente não encontrado.");
                                }
                            }
                        }
                        case 3 -> {
                            System.out.println("Insira o ID do cliente: ");
                            int id = sc.nextInt();
                            Cliente c = clienteDAO.select(id);
                            if(c != null){
                                System.out.println("\n===== CLIENTE =====");
                                System.out.println("ID: " + c.getId());
                                System.out.println("Nome: " + c.getNome());
                                System.out.println("Endereço: " + c.getRua() + ", " + c.getNumero() + " - " + c.getCep());
                            } else {
                                System.out.println("Cliente não encontrado.");
                            }
                        }
                        case 4 -> {
                            System.out.println("Insira o ID do cliente: ");
                            int id = sc.nextInt();
                            if(clienteDAO.delete(id)){
                                System.out.println("Cliente removido com sucesso!");
                            }
                        }
                        case 5 -> {
                            System.out.println("Insira o CEP: ");
                            String cep = sc.next();

                            List<Cliente> clientes = clienteDAO.searchByCep(cep);

                            if(clientes.isEmpty()){
                                System.out.println("Nenhum cliente encontrado.");
                            } else {

                                for(Cliente c : clientes){

                                    System.out.println(
                                            "ID: " + c.getId() +
                                                    " | Nome: " + c.getNome() +
                                                    " | Endereço: " + c.getRua() +
                                                    ", " + c.getNumero() +
                                                    " - " + c.getCep()
                                    );
                                }
                            }
                        }

                    }
                }

                case 3 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Buscar por Forma de Pagamento | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch (op2){
                        case 1 -> {
                            Pedido ped = new Pedido();
                            System.out.println("Insira as informações sobre o novo pedido: ");

                            System.out.println("Forma de pagamento: ");
                            ped.setFormaPag(sc.nextLine());
                            System.out.println("ID do cliente: ");
                            ped.setId_cliente(sc.nextInt());
                            if(pedidoDAO.insert(ped)){
                                System.out.println("Pedido inserido com sucesso!");
                            }
                        }
                        case 2 -> {
                            System.out.println("ID do pedido: ");
                            int id = sc.nextInt();

                            Pedido ped = pedidoDAO.select(id);

                            if(ped != null){
                                sc.nextLine();

                                System.out.println("Nova forma de pagamento: ");
                                ped.setFormaPag(sc.nextLine());
                                System.out.println("Novo ID Cliente: ");
                                ped.setId_cliente(sc.nextInt());
                                if(pedidoDAO.update(ped)) {
                                    System.out.println("Pedido atualizado com sucesso!");
                                }
                            } else {
                                System.out.println("Pedido não encontrado.");
                            }
                        }
                        case 3 -> {
                            System.out.println("Insira o ID do pedido: ");
                            int id = sc.nextInt();
                            Pedido ped = pedidoDAO.select(id);
                            if(ped != null){
                                System.out.println("\n===== PEDIDO =====");
                                System.out.println("ID: " + ped.getId());
                                System.out.println("Forma de pagamento: " + ped.getFormaPag());
                                System.out.println("ID do Cliente: " + ped.getId_cliente());
                            }
                            else{
                                System.out.println("Pedido não encontrado.");
                            }
                        }
                        case 4 -> {
                            System.out.println("Insira o ID do pedido: ");
                            int id = sc.nextInt();
                            if(pedidoDAO.delete(id)){
                                System.out.println("Pedido removido com sucesso!");
                            }
                        }
                        case 5 -> {
                            System.out.println("Insira a forma de pagamento: ");
                            String formpag = sc.nextLine();
                            List<Pedido> pedidos =
                                    pedidoDAO.searchByFormaPag(formpag);
                            if(pedidos.isEmpty()){

                                System.out.println("Nenhum pedido encontrado.");
                            } else {

                                for(Pedido p : pedidos){

                                    System.out.println(
                                            "ID: " + p.getId() +
                                                    " | Forma de Pagamento: " + p.getFormaPag() +
                                                    " | ID Cliente: " + p.getId_cliente()
                                    );
                                }
                            }
                        }
                    }
                }

                case 4 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Exibir Informações do Pedido | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();

                    switch (op2){
                        case 1 ->{
                            Pedido_has_Produto php = new Pedido_has_Produto(0, 0, 0);
                            System.out.print("ID do Pedido: ");
                            php.setId_pedido(sc.nextInt());

                            System.out.print("ID do Produto: ");
                            php.setId_produto(sc.nextInt());

                            System.out.print("Quantidade: ");
                            php.setQuantidade(sc.nextInt());

                            if(phpDAO.insert(php)){
                                System.out.println("Produto adicionado ao pedido!");
                            }
                        }
                        case 2 ->{
                            System.out.print("ID do Pedido: ");
                            int idPedido = sc.nextInt();

                            System.out.print("ID do Produto: ");
                            int idProduto = sc.nextInt();

                            Pedido_has_Produto php =
                                    phpDAO.select(idPedido, idProduto);

                            if(php != null){

                                System.out.print("Nova quantidade: ");
                                php.setQuantidade(sc.nextInt());

                                if(phpDAO.update(php)){
                                    System.out.println("Quantidade atualizada!");
                                }

                            } else {
                                System.out.println("Registro não encontrado.");
                            }
                        }
                        case 3 ->{
                            System.out.print("ID do Pedido: ");
                            int idPedido = sc.nextInt();

                            System.out.print("ID do Produto: ");
                            int idProduto = sc.nextInt();

                            Pedido_has_Produto php =
                                    phpDAO.select(idPedido, idProduto);

                            if(php != null){
                                System.out.println("\n===== ITEM DO PEDIDO =====");
                                System.out.println("ID Pedido: " + php.getId_pedido());
                                System.out.println("ID Produto: " + php.getId_produto());
                                System.out.println("Quantidade: " + php.getQuantidade());

                            } else {
                                System.out.println("Registro não encontrado.");
                            }
                        }
                        case 4 ->{
                            System.out.print("ID do Pedido: ");
                            int idPedido = sc.nextInt();
                            System.out.print("ID do Produto: ");
                            int idProduto = sc.nextInt();
                            if(phpDAO.delete(idPedido, idProduto)){
                                System.out.println("Item removido do pedido!");
                            }
                        }
                        case 5 ->{
                            System.out.println("Insira o ID do pedido: ");
                            int idDoPedido = sc.nextInt();
                            phpDAO.listarProdutosDoPedido(idDoPedido);
                        }
                    }

                }

                case 5 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Busca por Salário | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch (op2){
                        case 1 -> {
                            Funcionario f = new Funcionario();
                            System.out.println("Insira as informações sobre o novo funcionário: ");

                            System.out.println("Nome: ");
                            f.setNome(sc.nextLine());
                            System.out.println("Data de nascimento (dd/MM/yyyy): ");

                            DateTimeFormatter formatter =
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy");

                            LocalDate data =
                                    LocalDate.parse(sc.nextLine(), formatter);

                            f.setDataNasc(Date.valueOf(data));
                            System.out.println("Idade: ");
                            f.setIdade(sc.nextInt());

                            System.out.println("CPF: ");
                            f.setCpf(sc.nextLine());
                            System.out.println("RG: ");
                            f.setRg(sc.nextLine());
                            System.out.println("Telefone: ");
                            f.setTelefone(sc.nextLine());
                            System.out.println("Salario: ");
                            f.setSalario(sc.nextDouble());
                            System.out.println("ID do Departamento: ");
                            f.setIdDep(sc.nextInt());
                            System.out.println("ID do Gerente: ");
                            f.setIdGerente(sc.nextInt());
                            if(funcionarioDAO.insert(f)){
                                System.out.println("Funcionário inserido com sucesso!");
                            }
                        }
                        case 2 -> {
                            System.out.println("Insira o ID do funcionário: ");
                            int id = sc.nextInt();

                            Funcionario f = funcionarioDAO.select(id);

                            if(f != null){
                                sc.nextLine();

                                System.out.println("Novo nome: ");
                                f.setNome(sc.nextLine());
                                System.out.println("Nova data de nascimento (dd/MM/yyyy): ");

                                DateTimeFormatter formatter =
                                        DateTimeFormatter.ofPattern("dd/MM/yyyy");

                                LocalDate data =
                                        LocalDate.parse(sc.nextLine(), formatter);

                                f.setDataNasc(Date.valueOf(data));
                                System.out.println("Nova idade: ");
                                f.setIdade(sc.nextInt());
                                sc.nextLine();

                                System.out.println("Novo CPF: ");
                                f.setCpf(sc.nextLine());
                                System.out.println("Novo RG: ");
                                f.setRg(sc.nextLine());
                                System.out.println("Novo telefone: ");
                                f.setTelefone(sc.nextLine());
                                System.out.println("Novo salario: ");
                                f.setSalario(sc.nextDouble());

                                System.out.println("Novo ID do Departamento: ");
                                f.setIdDep(sc.nextInt());
                                System.out.println("Novo ID do Gerente (0 para NULL): ");

                                int gerente = sc.nextInt();

                                if(gerente == 0){
                                    f.setIdGerente(null);
                                }
                                else{
                                    f.setIdGerente(gerente);
                                }
                                if(funcionarioDAO.update(f)){
                                    System.out.println("Funcionário atualizado com sucesso!");
                                }
                            }
                            else {
                                System.out.println("Funcionário não encontrado.");
                            }
                        }
                        case 3 -> {
                            System.out.println("Insira o ID do funcionário: ");
                            int id = sc.nextInt();
                            Funcionario f = funcionarioDAO.select(id);
                            if(f != null){
                                System.out.println("\n===== FUNCIONÁRIO =====");
                                System.out.println("Nome: " + f.getNome());
                                System.out.println("Data de nascimento: " + f.getDataNasc());
                                System.out.println("Idade: " + f.getIdade());
                                System.out.println("CPF: " + f.getCpf());
                                System.out.println("RG: " + f.getRg());
                                System.out.println("Telefone: " + f.getTelefone());
                                System.out.println("Salário: " + f.getSalario());
                                System.out.println("ID Departamento: " + f.getIdDep());
                                System.out.println("ID Gerente: " + f.getIdGerente());
                            }
                            else{
                                System.out.println("Funcionário não encontrado.");
                            }
                        }
                        case 4 -> {
                            System.out.println("Insira o ID do funcionário:");
                            int id = sc.nextInt();
                            if(funcionarioDAO.delete(id)){
                                System.out.println("Funcionário removido.");
                            }
                        }
                        case 5 -> {

                            System.out.println("Insira o salário máximo do funcionário: ");
                            double salario = sc.nextDouble();

                            List<Funcionario> funcionarios =
                                    funcionarioDAO.searchSalarioMenorOuIgual(salario);
                            if(funcionarios.isEmpty()){
                                System.out.println("Nenhum funcionário encontrado.");
                            } else {

                                for(Funcionario f : funcionarios){

                                    System.out.println(
                                            "ID: " + f.getId() +
                                                    " | Nome: " + f.getNome() +
                                                    " | Salário: R$ " + f.getSalario() +
                                                    " | CPF: " + f.getCpf() +
                                                    " | RG: " + f.getRg()
                                    );
                                }
                            }
                        }
                    }

                }

                case 6 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Listar Funcionários do Departamento | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();

                    switch(op2){
                        case 1 ->{
                            Departamento d = new Departamento();

                            System.out.print("Nome do departamento: ");
                            d.setNome(sc.nextLine());
                            if(departamentoDAO.insert(d)){
                                System.out.println("Departamento inserido com sucesso!");
                            }
                        }

                        case 2 ->{
                            System.out.print("Insira o ID do departamento: ");
                            int id = sc.nextInt();

                            Departamento d = departamentoDAO.select(id);
                            if(d != null){
                                sc.nextLine();
                                System.out.print("Novo nome: ");
                                d.setNome(sc.nextLine());
                                if(departamentoDAO.update(d)){
                                    System.out.println("Departamento atualizado.");
                                }
                            } else {
                                System.out.println("Departamento não encontrado.");
                            }
                        }

                        case 3 ->{
                            System.out.print("ID do departamento: ");
                            int id = sc.nextInt();
                            Departamento d = departamentoDAO.select(id);
                            if(d != null){
                                System.out.println("\n===== DEPARTAMENTO =====");
                                System.out.println("ID: " + d.getId());
                                System.out.println("Nome: " + d.getNome());
                            } else {
                                System.out.println("Departamento não encontrado.");
                            }
                        }
                        case 4 ->{
                            System.out.print("ID do departamento: ");
                            int id = sc.nextInt();
                            if(departamentoDAO.delete(id)){
                                System.out.println("Departamento removido.");
                            }
                        }
                        case 5 ->{
                            System.out.println("Insira o nome do departamento: ");
                            String nomeDep = sc.next();
                            departamentoDAO.listarFuncionariosPorDepartamentoNome(nomeDep);
                        }
                    }

                }

                case 7 -> {
                    System.out.println("\n===== CLIENTES =====");
                    clienteDAO.selectAll();
                    System.out.println("\n===== PRODUTOS =====");
                    produtoDAO.selectAll();
                    System.out.println("\n===== FUNCIONÁRIOS =====");
                    funcionarioDAO.selectAll();
                    System.out.println("\n===== DEPARTAMENTOS =====");
                    departamentoDAO.selectAll();
                    System.out.println("\n===== PEDIDOS =====");
                    pedidoDAO.selectAll();
                    System.out.println("\n===== PEDIDO_HAS_PRODUTO =====");
                    phpDAO.selectAll();
                }
            }
        }while(op != 0);
        System.out.println("Saindo...");

    }
}
