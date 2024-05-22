package banco;

import java.sql.*;

public class GerenciadorDatabase {
    private static final String URL = "jdbc:h2:tcp://localhost/~/ContaBanco";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection abrirConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void fecharConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveConta(String agencia, String nomeCliente, double saldo) throws SQLException {
        Connection con = abrirConnection();
        String sql = "INSERT INTO Clientes (AGENCIA, NOMECLIENTE, SALDO) VALUES (?, ?, ?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, agencia);
        psmt.setString(2, nomeCliente);
        psmt.setDouble(3, saldo);
        int rowsAffected = psmt.executeUpdate();
        if (rowsAffected > 0) {
            Conta contaCliente = getConta(agencia, nomeCliente);
            System.out.println(String
                    .format("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %.2f já está disponível para saque.",
                            contaCliente.getNome(),
                            contaCliente.getAgencia(),
                            contaCliente.getNumero(),
                            contaCliente.getSaldo()));
        } else {
            System.out.println("Erro ao inserir cliente.");
        }
        fecharConnection(con);
    }

    public static Conta getConta(String agencia, String nomeCliente) throws SQLException {
        Connection con = abrirConnection();
        String sql = "SELECT * FROM Clientes WHERE AGENCIA = ? AND NOMECLIENTE = ?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1, agencia);
        psmt.setString(2, nomeCliente);
        ResultSet rs = psmt.executeQuery();
        if (rs.next()){
            double saldo = rs.getDouble("SALDO");
            int numero = rs.getInt("NUMERO");
            return new Conta(numero, nomeCliente, agencia, saldo);
        }else {
            throw new SQLException("Cliente não encontrado.");
        }
    }
}