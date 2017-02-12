import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.*;


class Faturacao implements Serializable {
    private HashMap<String, Integer> nVezesComprado;
    private HashMap<String, HashMap<Integer, TriploIntSetDouble>> query4;
    private HashMap<String, HashMap<String, Integer>> query5;
    private TreeMap<String, DuploSetInt> query6;
    private TreeMap<String, HashSet<String>> query8;
    private TreeMap<String, TreeMap<String, DuploIntDouble>> query9;
    
    public Faturacao() {
        nVezesComprado = new HashMap<>();
        query4 = new HashMap<>();
        query5 = new HashMap<>();
        query6 = new TreeMap<>();
        query8 = new TreeMap<>();
        query9 = new TreeMap<>();
    }
    
    public Faturacao(HashMap<String, Integer> nVezesComprado, HashMap<String, HashMap<Integer, TriploIntSetDouble>> query4, HashMap<String, HashMap<String, Integer>> query5, TreeMap<String, DuploSetInt> query6, TreeMap<String, HashSet<String>> query8, TreeMap<String, TreeMap<String, DuploIntDouble>> query9){
        this.nVezesComprado = new HashMap<>(nVezesComprado);
        this.query4 = new HashMap<>(query4);
        this.query5 = new HashMap<>(query5);
        this.query6 = new TreeMap<>(query6);
        this.query8 = new TreeMap<>(query8);
        this.query9 = new TreeMap<>(query9);
    }
    
    public Faturacao(Faturacao fat){
        this(fat.getNVezesComprado(), fat.getQuery4(), fat.getQuery5(), fat.getQuery6(), fat.getQuery8(), fat.getQuery9());
    }
    
    public HashMap<String, Integer> getNVezesComprado() {
        return new HashMap<>(nVezesComprado);
    }
    
    public HashMap<String, HashMap<Integer, TriploIntSetDouble>> getQuery4() {
        return new HashMap<>(query4);
    }
    
    public HashMap<String, HashMap<String, Integer>> getQuery5() {
        return new HashMap<>(query5);
    }
    
    public TreeMap<String, DuploSetInt> getQuery6() {
        return new TreeMap<>(query6);
    }
    
    public TreeMap<String, HashSet<String>> getQuery8() {
        return new TreeMap<>(query8);
    }
    
    public TreeMap<String, TreeMap<String, DuploIntDouble>> getQuery9() {
        return new TreeMap<>(query9);
    }
    
    public void sendVenda(Venda v) {
        // QUERY 1
        String produto = v.getProduto();
        if(nVezesComprado.get(produto) == null) nVezesComprado.put(produto, 1);
        else nVezesComprado.put(produto, nVezesComprado.get(produto) + 1);
        // FIM QUERY 1
        
        
        // QUERY 4
        int mes = v.getMes();
        double vFaturado = v.getQtd() * v.getPreco();
        String cliente = v.getCliente();
        HashSet<String> set_client = new HashSet<String>();
        HashMap<Integer, TriploIntSetDouble> aux_q4 = new HashMap<Integer, TriploIntSetDouble>();
        if(query4.get(produto) == null) {  //Caso o produto ainda nao tenha sido introduzido, entra aqui
            set_client.add(cliente);
            aux_q4.put(mes, new TriploIntSetDouble(1, set_client, vFaturado));
        }
        else {
            aux_q4 = query4.get(produto);
            if(aux_q4.get(mes) == null) { //Caso o mes ainda nao tenha sido introduzido, entra aqui
                set_client.add(cliente);
                aux_q4.put(mes, new TriploIntSetDouble(1, set_client, vFaturado));
            }
            else {
                TriploIntSetDouble aux_q4_2 = aux_q4.get(mes);
                set_client = aux_q4_2.getInfo();
                set_client.add(cliente);
                aux_q4.put(mes, new TriploIntSetDouble(aux_q4_2.getCompras() + 1, set_client, aux_q4_2.getGasto() + vFaturado));
            }
        }
        query4.put(produto, aux_q4);
        // FIM QUERY 4
        
        
        // QUERY 5
        HashMap<String, Integer> aux_q5 = new HashMap<String, Integer>();
        int qtd = v.getQtd();
        if(query5.get(cliente) == null) { //Caso o cliente nao tenha sido introduzido, entra aqui
            aux_q5.put(produto, qtd);
        }
        else {
            aux_q5 = query5.get(cliente);
            if(aux_q5.get(produto) == null) { //Caso o Produto ainda nao tenha sido introduzido, entra aqui
                aux_q5.put(produto, qtd);
            }
            else {
                qtd += aux_q5.get(produto);
                aux_q5.put(produto, qtd);
            }
        }
        query5.put(cliente, aux_q5);
        // FIM QUERY 5
        
        
        // QUERY 6
        DuploSetInt duplo;
        qtd = v.getQtd();
        if(query6.get(produto) == null) { //Caso o produto ainda nao tenha sido introduzido, entra aqui
            set_client = new HashSet<String>();
            set_client.add(cliente);
            duplo = new DuploSetInt(set_client, qtd);
        }
        else {
            duplo = query6.get(produto);
            set_client = duplo.getClientes();
            set_client.add(cliente);
            qtd += duplo.getQtd();
            duplo = new DuploSetInt(set_client, qtd);
        }
        query6.put(produto, duplo);
        // FIM QUERY 6
        
        
        // QUERY 8
        HashSet<String> set_produtos = new HashSet<String>();
	if (query8.get(cliente) == null) {   //Caso o cliente ainda nao tenha sido introduzido, entra aqui
            set_produtos = new HashSet<String> ();
            set_produtos.add(produto);
        }
	else {
            set_produtos = query8.get(cliente);
            set_produtos.add(produto);
        }
	query8.put(cliente, set_produtos);
	// FIM QUERY 8
        
        
        // QUERY 9
        TreeMap<String, DuploIntDouble> aux_q9 = new TreeMap<String, DuploIntDouble>(new ComparaString());
        qtd = v.getQtd();
        if(query9.get(produto) == null) { //Caso o produto ainda nao tenha sido introduzido, entra aqui
            aux_q9.put(cliente, new DuploIntDouble(qtd, vFaturado));
        }
        else {
            aux_q9 = query9.get(produto);
            if(aux_q9.get(cliente) == null) { //Caso o cliente ainda nao tenha sido introduzido, entra aqui
                aux_q9.put(cliente, new DuploIntDouble(qtd, vFaturado));
            }
            else {
                DuploIntDouble duplo_q9 = aux_q9.get(cliente);
                qtd += duplo_q9.getTotalQtd();
                aux_q9.put(cliente, new DuploIntDouble(qtd, vFaturado + duplo_q9.getFaturacaoTotal()));
            }
        }
        query9.put(produto, aux_q9);
// FIM QUERY 9
    }
}