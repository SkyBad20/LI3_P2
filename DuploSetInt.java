import java.util.HashSet;
import java.io.Serializable;

public class DuploSetInt implements Serializable {
    //Variáveis de Instância
    private HashSet<String> clientes;
    private int qtd;
    
    //Getters, Setters e Construtores
    public DuploSetInt () {
        clientes = new HashSet<>();
        qtd = 0;
    }
    
    public DuploSetInt (HashSet<String> clientes, int qtd) {
        this.clientes = new HashSet<>();
        clientes.stream().forEach(c -> this.clientes.add(c));
        this.qtd = qtd;
    }
    
    public DuploSetInt (DuploSetInt dpq) {
        this (dpq.getClientes(), dpq.getQtd());
    }
    
    public HashSet<String> getClientes () {
        HashSet<String> res = new HashSet<>();
        clientes.stream().forEach(c -> res.add(c));
        return res;
    }
    
    public int getQtd () {
        return this.qtd;
    }
    
    public void setClientes (HashSet<String> clientes) {
        this.clientes.clear();
        clientes.stream().forEach(c -> this.clientes.add(c));
    }
    
    public void setQtd (int qtd) {
        this.qtd = qtd;
    }
}