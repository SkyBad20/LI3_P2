import java.util.HashMap;
import java.util.HashSet;
import java.io.Serializable;

public class Filial implements Serializable {
    private HashMap<Integer, HashMap<String, TriploIntSetDouble>> query3;
    private HashMap<String, Double> query7;
  
  public Filial() {
        query3 = new HashMap<Integer, HashMap<String, TriploIntSetDouble>>();
        query7 = new HashMap<String, Double>();
    }

  
  public HashMap <String, Double> getQuery7 () {
        HashMap<String, Double> res = new HashMap<>();
        for (String s : this.query7.keySet()) {
          double aux;
          aux = this.query7.get(s);
          res.put(s, aux);
        }
        return res;
    }
  
  public HashMap<Integer, HashMap<String, TriploIntSetDouble>> getQuery3 () {
        HashMap<Integer, HashMap<String, TriploIntSetDouble>> res = new HashMap<>();
        for (Integer i : this.query3.keySet()) {
          HashMap<String, TriploIntSetDouble> aux = new HashMap<>();
          aux = this.query3.get(i);
          res.put(i, aux);
        }
        return res;
    }
  
    public void sendVenda(Venda v) {
        String cliente = v.getCliente();
        int mes = v.getMes();
        
        //QUERY 3
        HashMap<String, TriploIntSetDouble> aux_q3 = new HashMap<>();
        String produto = v.getProduto();
        int quantidade = v.getQtd();
        double preco = v.getPreco();
        double gasto = quantidade * preco;
        if (query3.get(mes) == null) {
            HashSet<String> prodsComprados = new HashSet<>();
            prodsComprados.add(produto);
            aux_q3.put(cliente, new TriploIntSetDouble(1, prodsComprados, gasto));
        }
        else {
            aux_q3 = query3.get(mes);
            if (aux_q3.get(cliente) == null) {
                HashSet<String> prodsComprados = new HashSet<>();
                prodsComprados.add(produto);
                aux_q3.put(cliente, new TriploIntSetDouble(1, prodsComprados, gasto));
            }
            else {
                HashSet<String> prodsComprados = new HashSet<>();
                prodsComprados = aux_q3.get(cliente).getInfo();
                prodsComprados.add(produto);
                int compras = aux_q3.get(cliente).getCompras();
                double gastoAtual = aux_q3.get(cliente).getGasto();
                aux_q3.replace(cliente, new TriploIntSetDouble (compras + 1, prodsComprados, gastoAtual + gasto));
            }
        }
        query3.put(mes, aux_q3);
        //FIM QUERY3
         
        //QUERY 7
        if (query7.get(cliente) == null) {
            query7.put(cliente, gasto);
        }
        else {
            double gastoAtual = query7.get(cliente);
            query7.replace(cliente, gastoAtual + gasto);
        }
        //FIM QUERY 7
    }
}