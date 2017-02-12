import java.util.HashSet;
import java.io.*;

public class TriploIntSetDouble implements Serializable {
    //Variáveis de Instância
    private int compras;
    private HashSet<String> info;
    private double gasto;
    
    //Getters, Setters e Construtores
    public TriploIntSetDouble () {
        compras = 0;
        info = new HashSet<>();
        gasto = 0;
    }
    
    public TriploIntSetDouble(int compras, HashSet<String> info, double gasto) {
        this.compras = compras;
        this.gasto = gasto;
        this.info = new HashSet<>();
        info.stream().forEach(p -> this.info.add(p));
    }
    
    public TriploIntSetDouble (TriploIntSetDouble novo) {
        this (novo.getCompras(), novo.getInfo(), novo.getGasto());
    }
    
    public int getCompras () {
        return this.compras;
    }
    
    public HashSet<String> getInfo () {
        HashSet<String> res = new HashSet<>();
        info.stream().forEach(i -> res.add(i));
        return res;
    }
    
    public double getGasto () {
        return this.gasto;
    }
    
    public void setCompras (int compras) {
        this.compras = compras;
    }
    
    public void setInfo (HashSet<String> info) {
        this.info.clear();
        info.stream().forEach(i -> this.info.add(i));
    }
    
    public void setGasto (double gasto) {
        this.gasto = gasto;
    }
}