package banco;

import java.sql.SQLException;
import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String menu = """ 
            Menu:
            1. Criar Conta.
            2. Verificar dados da conta.
            3. Sair.
            """;
        int menuControle = 0;
        while(menuControle != 3)
        {
            System.out.println(menu);
            menuControle = Integer.parseInt(scanner.nextLine());

            switch (menuControle){
                case 1:
                    System.out.println("Informe o número da agência:");
                    String agencia = scanner.nextLine();
                    System.out.println("Informe o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Valor inicial da conta:");
                    double saldo = Double.parseDouble(scanner.nextLine());

                    GerenciadorDatabase.saveConta(agencia, nome, saldo);

                    break;
                case 2:
                    System.out.println("Informe o número da agência:");
                    String agenciaPesquisa = scanner.nextLine();
                    System.out.println("Informe o nome do cliente:");
                    String nomePesquisa = scanner.nextLine();

                    Conta contaCliente = GerenciadorDatabase.getConta(agenciaPesquisa, nomePesquisa);
                    String mensagem = String
                            .format("Olá %s, sua agência é %s, conta %d e seu saldo %.2f",
                            contaCliente.getNome(),
                            contaCliente.getAgencia(),
                            contaCliente.getNumero(),
                            contaCliente.getSaldo());
                    System.out.println(mensagem);
                    break;
                case 3:
                    break;
            }
        }
    }

}