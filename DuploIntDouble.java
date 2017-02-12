import java.io.Serializable;

public class DuploIntDouble implements Serializable {
    //Variáveis de Instância
    private int totalQtd;
    private double faturacaoTotal;

    //Getters, Setters e Construtores
    public DuploIntDouble () {
        totalQtd = 0;
        faturacaoTotal = 0;
    }

    public DuploIntDouble (int totalQtd, double faturacaoTotal) {
        this.totalQtd = totalQtd;
        this.faturacaoTotal = faturacaoTotal;
    }

    public DuploIntDouble (DuploIntDouble did) {
        this (did.getTotalQtd(), did.getFaturacaoTotal());
    }

    public int getTotalQtd () {
        return this.totalQtd;
    }

    public double getFaturacaoTotal () {
        return this.faturacaoTotal;
    }
    
    public void setTotalQtd (int totalQtd) {
        this.totalQtd = totalQtd;
    }
    
    public void setFaturacaoTotal (double faturacaoTotal) {
        this.faturacaoTotal = faturacaoTotal;
    }
}