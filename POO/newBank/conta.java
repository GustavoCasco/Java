package poo.newBank;


public abstract class conta {
    
    private int numeroConta;
    private int agencia;
    private double saldoConta;
    private cliente titular;
    private static int total;

    public conta(int conta, int agencia, cliente titular){
            total++;
            this.numeroConta = conta;
            this.agencia = agencia;
            this.saldoConta = 0.0;
            this.titular = titular;
    }

    public void deposita(double valor){
        this.saldoConta += valor;
    }

    public void sacar(double valor){
        if (this.saldoConta >= valor) {            
            this.saldoConta -= valor;
        }else {
            System.out.println("Valor insuficiente");
        }
    }

    public void transferir(double valor, conta destino){
        if (this.saldoConta >= valor) {
            this.sacar(valor);
            destino.deposita(valor);
            System.out.println("Transferencia executada com sucesso!!");
        }else{
            System.out.println("Saldo insuficiente");
        }
    }

    public double getSaldo(){
        return this.saldoConta;
    }

    public int getNumero(){
        return this.numeroConta;
    }

    public void setNumeroConta(int novoNumero){
        if (novoNumero > 0) {
            this.numeroConta = novoNumero;
        }else{
            System.out.println("Digite um numero positivo");
        }
    } 

    public int getAgencia(){
        return this.agencia;
    }

    public void setAgencia(int agencia){
        if (agencia > 0) {
            this.agencia = agencia;
        }else{
            System.out.println("Digite um numero positivo");
        }
    } 

    public cliente getTitular(){
        return titular;
    }

    public void setTitular(cliente titular){
        this.titular = titular;
    }

    public static int getTotal(){
        return total;
    }
}

