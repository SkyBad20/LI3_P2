import java.util.HashSet;
import java.io.Serializable;

public class Estatistica implements Serializable {
    
    private int valorZero;
    private double faturacao;
    private int nVendasValidas;
    private int nVendasLidas;
    private int prodDifComprados;
    private int cliDifCompraram;
    
    public Estatistica(){
        valorZero = 0;
        faturacao = 0;
        nVendasValidas = 0;
        nVendasLidas = 0;
        prodDifComprados = 0;
        cliDifCompraram = 0;
    }
    
    public Estatistica(int valorZero, double faturacao, int nVendasValidas, int nVendasLidas, int prodDifComprados, int cliDifCompraram){
        this.valorZero = valorZero;
        this.faturacao = faturacao;
        this.nVendasValidas = nVendasValidas;
        this.nVendasLidas = nVendasLidas;
        this.prodDifComprados = prodDifComprados;
        this.cliDifCompraram = cliDifCompraram;
    }
    
    public Estatistica(Estatistica e){
        this(e.getValorZero(), e.getFaturacao(), e.getNVendasLidas(), e.getNVendasValidas(), e.getProdDif(), e.getCliDif());
    }
    
    public int getNVendasLidas(){
        return nVendasLidas;
    }
    
    public int getNVendasValidas(){
        return nVendasValidas;
    }
    
    public int getValorZero(){
        return valorZero;
    }
    
    public double getFaturacao(){
        return faturacao;
    }
    
    public int getCliDif(){
        return cliDifCompraram;
    }
    
    public int getProdDif(){
        return prodDifComprados;
    }
    
    public void setValorZero(int valorZero){
        this.valorZero = valorZero;
    }
    
    public void setFaturacao(double faturacao){
        this.faturacao = faturacao;
    }
    
    public void setCliDifCompraram(int cliDifCompraram){
        this.cliDifCompraram = cliDifCompraram;
    }
    
    public void setProdDifComprados(int prodDifComprados){
        this.prodDifComprados = prodDifComprados;
    }
    
    public void setNVendasLidas(int nVendasLidas){
        this.nVendasLidas = nVendasLidas;
    }
     
    public void setNVendasValidas(int nVendasValidas){
        this.nVendasValidas = nVendasValidas;
    }
}
