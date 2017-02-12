import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.*;

class CatProdutos implements Serializable {
    //Variáveis de instância
    private TreeMap<String, TreeSet<String>> produtos;
    
    //Getters, Setters e Construtores
    public CatProdutos(){
        produtos = new TreeMap<>(new ComparaString());
    }
    
    public CatProdutos(TreeSet<String> produtos){
        TreeMap<String, TreeSet<String>> lprodutos = new TreeMap<String, TreeSet<String>>(new ComparaString());
        TreeSet<String> lista = new TreeSet<String>(new ComparaString());
        String atual = "", subStr = "";
        
        Iterator it = produtos.iterator();
        Iterator aux;
        while(it.hasNext()) {
            atual = (String) it.next();
            subStr = atual.substring(0,2);
            
            lista = lprodutos.get(subStr);
            if (lista == null) lista = new TreeSet<String>(new ComparaString());
            
            lista.add(atual);
            lprodutos.put(subStr, lista);
        }
        
        this.produtos = lprodutos;
    }
    
    
    public CatProdutos(CatProdutos c){
        //Transforma Um catalogo de produtos num TreeSet<String>
        Iterator iterator = c.getProdutos().values().iterator();
        TreeSet<String> lista_produtos = new TreeSet<String>(new ComparaString());
        
        while(iterator.hasNext()) lista_produtos.addAll((TreeSet<String>) iterator.next());
        
        //Aplica o construtos com o TreeSet<String> criado
        TreeMap<String, TreeSet<String>> lprodutos = new TreeMap<String, TreeSet<String>>(new ComparaString());
        TreeSet<String> lista = new TreeSet<String>(new ComparaString());
        String atual = "", subStr = "";
        
        Iterator it = lista_produtos.iterator();
        Iterator aux;
        while(it.hasNext()) {
            atual = (String) it.next();
            subStr = atual.substring(0,2);
            
            lista = lprodutos.get(subStr);
            if (lista == null) lista = new TreeSet<String>(new ComparaString());
            
            lista.add(atual);
            lprodutos.put(subStr, lista);
        }
        
        this.produtos = lprodutos;
    }
    
    public TreeMap<String, TreeSet<String>> getProdutos(){
        return produtos;
    }
    
    public void setProdutos(TreeMap<String, String> produtos) {
        this.produtos = (TreeMap<String, TreeSet<String>>) produtos.clone();
    }
    
    //Métodos de instância
    public CatProdutos clone(){
        return new CatProdutos(this);
    }
    
    public boolean verificaProduto(String s){
        return produtos.get(s.substring(0, 2)).contains(s);
    }
}