import java.io.Serializable;
import java.util.Objects;


public class Venda implements Serializable {
    // variáveis de instância 
    private String produto;
    private double preco;
    private int qtd;
    private String tipo;
    private String cliente;
    private int mes;
    private int filial;
    
    /**
     * Constructores da classe Venda
     */
    public Venda() {
        produto = "";
        preco = 0;
        qtd = 0;
        tipo = "";
        cliente = "";
        mes = 0;
        filial = 0;
    }
    
    public Venda(String produto, double preco, int qtd, String tipo, String cliente, int mes, int filial) {
        this.produto = produto;
        this.preco = preco;
        this.qtd = qtd;
        this.tipo = tipo;
        this.cliente = cliente;
        this.mes = mes;
        this.filial = filial;
    }
    
    public Venda(Venda v) {
        produto = v.produto;
        preco = v.preco;
        qtd = v.qtd;
        tipo = v.tipo;
        cliente = v.cliente;
        mes = v.mes;
        filial = v.filial;
    }

    //Getters
    
    String getProduto () {
        return produto;
    }
    
    double getPreco () {
        return preco;
    }
    
    int getQtd () {
        return qtd;
    }
    
    String getTipo () {
        return tipo;
    }
    
    String getCliente () {
        return cliente;
    }
    
    int getMes () {
        return mes;
    }
    
    int getFilial () {
        return filial;
    }
    
    //Setters
    
    void setProduto (String prod) {
        this.produto = prod;
    }
    
    void setPreco (double preco) {
        this.preco = preco;
    }
            
    void setQtd (int qtd) {
        this.qtd = qtd;
    }
            
    void setTipo (String tipo) {
        this.tipo = tipo;
    }
            
    void setCliente (String cli) {
        this.cliente = cli;
    }
            
    void setMes (int mes) {
        this.mes = mes;
    }
            
    void setFilial (int filial) {
        this.filial = filial;
    }
    
     public boolean equals(Object o){
       if (o == this) return true;
       if (o == null || o.getClass() != this.getClass()) return false;
       Venda v = (Venda) o;
       return (v.getProduto().equals(produto) && preco == v.getPreco() &&
              qtd == v.getQtd() && v.getTipo().equals(tipo) && v.getCliente().equals(cliente) &&
              mes == v.getMes() && filial == v.getFilial());
    }
    
     public Venda clone(){
        return new Venda(this);
    }
    
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(produto); res.append(" ");
        res.append(preco); res.append(" ");
        res.append(qtd); res.append(" ");
        res.append(tipo); res.append(" ");
        res.append(cliente); res.append(" ");
        res.append(mes); res.append(" ");
        res.append(filial);
        
        return res.toString();
    }
}