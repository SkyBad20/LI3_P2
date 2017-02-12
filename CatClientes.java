import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.*;

class CatClientes implements Serializable {
    //Variáveis de instância
    private TreeMap<String, TreeSet<String>> clientes;
    
    //Getters, Setters e Construtores
    public CatClientes(){
        clientes = new TreeMap<>(new ComparaString());
    }
    
    public CatClientes(TreeSet<String> clientes){
        TreeMap<String, TreeSet<String>> lclientes = new TreeMap<String, TreeSet<String>>(new ComparaString());
        TreeSet<String> lista = new TreeSet<String>(new ComparaString());
        String atual = "", subStr = "";
        
        Iterator it = clientes.iterator();
        Iterator aux;
        while(it.hasNext()) {
            atual = (String) it.next();
            subStr = atual.substring(0,1);
            
            lista = lclientes.get(subStr);
            if (lista == null) lista = new TreeSet<String>(new ComparaString());
            
            lista.add(atual);
            lclientes.put(subStr, lista);
        }
        
        this.clientes = lclientes;
    }
    
    public CatClientes(CatClientes c){
        //Transforma Um catalogo de clientes num TreeSet<String>
        Iterator iterator = c.getClientes().values().iterator();
        TreeSet<String> lista_clientes = new TreeSet<String>(new ComparaString());
        
        while(iterator.hasNext()) lista_clientes.addAll((TreeSet<String>) iterator.next());
        
        //Aplica o construtos com o TreeSet<String> criado
        TreeMap<String, TreeSet<String>> lclientes = new TreeMap<String, TreeSet<String>>(new ComparaString());
        TreeSet<String> lista = new TreeSet<String>(new ComparaString());
        String atual = "", subStr = "";
        
        Iterator it = lista_clientes.iterator();
        Iterator aux;
        while(it.hasNext()) {
            atual = (String) it.next();
            subStr = atual.substring(0,1);
            
            lista = lclientes.get(subStr);
            if (lista == null) lista = new TreeSet<String>(new ComparaString());
            
            lista.add(atual);
            lclientes.put(subStr, lista);
        }
        
        this.clientes = lclientes;
    }
    
    public TreeMap<String, TreeSet<String>> getClientes(){
        return clientes;
    }
    
    public void setClientes(TreeMap<String, TreeSet<String>> clientes){
        this.clientes = (TreeMap<String, TreeSet<String>>) clientes.clone();
    }
    
    //Métodos de instância
    public CatClientes clone(){
        return new CatClientes(this);
    }
    
    public boolean verificaCliente(String s){
        return clientes.get(s.substring(0,1)).contains(s);
    }
}