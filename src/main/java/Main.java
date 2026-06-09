import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
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
            System.out.println("Bem-vindo(a) à Padaria Madalu!");
            System.out.println("1 - Produtos (FEITO)");
            System.out.println("2 - Clientes");
            System.out.println("3 - Pedidos");
            System.out.println("4 - Produto(s) do Pedido (FEITO)");
            System.out.println("5 - Funcionários");
            System.out.println("6 - Departamentos (FEITO)");
            System.out.println("7 - Visualizar todo o Banco de Dados (FEITO)");
            System.out.println("0 - Sair (FEITO)");
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
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Buscar por CEP | 6 - Buscar por Preço | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
                }

                case 3 -> {
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Buscar por Forma de Pagamento | 6 - Buscar por Preço | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
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
                    System.out.println("1 - INSERT | 2 - UPDATE | 3 - SELECT | 4 - DELETE | 5 - Busca por Salário | 6 - Buscar por Preço | 0 - Voltar");
                    System.out.println("Que operação será realizada?");
                    op2 = sc.nextInt();
                    sc.nextLine();
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