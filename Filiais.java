import java.util.TreeMap;
import java.io.Serializable;

class Filiais implements Serializable {
    
    private TreeMap<Integer, Filial> filiais;
    private TreeMap<Integer, TreeMap<String, Integer>> query2;
    
    public Filiais() {
        filiais = new TreeMap<Integer, Filial>(new ComparaInt());
        query2 = new TreeMap<Integer, TreeMap<String, Integer>>(new ComparaInt());
    }
    
    public TreeMap<Integer, Filial> getFiliais() {
        return new TreeMap<>(filiais);
    }
    
    public TreeMap<Integer, TreeMap<String, Integer>> getQuery2() {
        return new TreeMap<>(query2);
    }
    
    public void sendVenda(Venda v) {
        int filial = v.getFilial();
        
        //QUERY 2
        String cliente = v.getCliente();
        int mes = v.getMes();
        int qtd = 0;
        TreeMap<String, Integer> aux_q2 = new TreeMap<String, Integer>(new ComparaString());
        if(query2.get(mes) == null) {
            aux_q2.put(cliente, 1);
        }
        else {
            aux_q2 = query2.get(mes);
            if(aux_q2.get(cliente) == null) {
                aux_q2.put(cliente, 1);
            }
            else {
                qtd = aux_q2.get(cliente) + 1;
                aux_q2.remove(cliente);
                aux_q2.put(cliente, qtd);
            }
        }
        query2.put(mes, aux_q2);
        //FIM QUERY 2
        
        if(filiais.get(filial) == null) { //Caso a filial ainda nao tenha sido inicializada, entra aqui
            filiais.put(filial, new Filial());
        }
        filiais.get(filial).sendVenda(v);
    }
}