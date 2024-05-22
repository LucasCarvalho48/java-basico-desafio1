package banco;

public class Conta {
    private int numero;
    private String nome;
    private String agencia;
    private double saldo;

    public Conta(int numero, String nome, String agencia, double saldo) {
        this.numero = numero;
        this.nome = nome;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNome() {
        return nome;
    }
}
