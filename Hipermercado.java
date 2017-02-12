import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.nanoTime;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.TreeSet;
import java.io.*;


public class Hipermercado implements Serializable {
    private CatProdutos produtos;
    private CatClientes clientes;
    private Filiais filiais;
    private Faturacao faturacao;
    private Estatistica est;
    
    public Hipermercado(CatClientes cC, CatProdutos cP) {
        produtos = new CatProdutos(cP);
        clientes = new CatClientes(cC);
        filiais = new Filiais();
        faturacao = new Faturacao();
        est = new Estatistica();
    }
    
    public Filiais getFiliais(){
        return filiais;
    }
    
    public Faturacao getFaturacao(){
        return faturacao;
    }
   
    public CatClientes getCatClientes(){
        return clientes;
    }
   
    public CatProdutos getCatProdutos(){
        return produtos;
    }
    
    public Estatistica getEstatistica(){
        return est;
    }
    
    public ArrayList<String> readLinesWithBuff(String fich) {
        ArrayList<String> linhas = new ArrayList<>();
        int nVendasLidas = 0;
        
        BufferedReader inStream = null;
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(fich));
            while( (linha = inStream.readLine()) != null ) {
                linhas.add(linha);
                nVendasLidas++;
            }
            est.setNVendasLidas(nVendasLidas);
        }
        catch(IOException e) {
            out.println(e.getMessage());
            return null;
        }
        return linhas;
    }
    
    public Venda parseLinhaVenda(String linha) throws NumberFormatException, NullPointerException{
        String[] campos = linha.split(" ");
        String produto = campos[0].trim();
        String preco = campos[1].trim();
        String qtd = campos[2].trim();
        String tipo = campos[3].trim();
        String cliente = campos[4].trim();
        String mes = campos[5].trim();
        String filial = campos[6].trim();
        
        double precoV;
        int qtdV, filialV, mesV;
                
        try {precoV = Double.parseDouble(preco);}
        catch (NumberFormatException | NullPointerException e) {return null;}
        if (precoV < 0 || precoV > 999.99) return null;
        
        try {qtdV = Integer.parseInt(qtd);}
        catch (NumberFormatException | NullPointerException e) {return null;}
        if (qtdV < 0 || qtdV > 200) return null;
        
        try {mesV = Integer.parseInt(mes);}
        catch (NumberFormatException | NullPointerException e) {return null;}
        if (mesV <= 0 || mesV > 12) return null;
        
        try {filialV = Integer.parseInt(filial);}
        catch (NumberFormatException | NullPointerException e) {return null;}
        if (filialV < 1 || filialV > 3) return null;
        
        if (!tipo.equals("N") && !tipo.equals("P")) return null;
        
        if(!clientes.getClientes().get(cliente.substring(0,1)).contains(cliente)) return null; //verifica se cliente existe
        if(!produtos.getProdutos().get(produto.substring(0,2)).contains(produto)) return null; //verifica se produto existe
        
        return new Venda(produto, precoV, qtdV, tipo, cliente, mesV, filialV);
    }
    
    public void carregaVendas(ArrayList<String> linhas) {
        int valorZero = 0;
        double fat = 0;
        int nVendasValidas = 0;
        TreeSet<String> prodDifComprados = new TreeSet<>();
        TreeSet<String> cliDifCompraram = new TreeSet<>();
        for (String s : linhas){
            Venda v = parseLinhaVenda(s);
            if (v != null) {
                nVendasValidas++;
                faturacao.sendVenda(v);
                filiais.sendVenda(v);
                String produto = v.getProduto();
                String cliente = v.getCliente();
                int qtd = v.getQtd();
                double preco = v.getPreco();
                if (preco == 0) valorZero++;
                else fat += (preco*qtd);
                prodDifComprados.add(produto);
                cliDifCompraram.add(cliente);
            }
        }
        est.setNVendasValidas(nVendasValidas);
        est.setValorZero(valorZero);
        est.setFaturacao(fat);
        est.setProdDifComprados(prodDifComprados.size());
        est.setCliDifCompraram(cliDifCompraram.size());   
    }
    
    public void leitura(String ficheiro) {
        ArrayList<String> linhas_buff = new ArrayList<String>();
        
        linhas_buff = readLinesWithBuff(ficheiro);
        carregaVendas(linhas_buff);
    }
    
    public static Hipermercado initApp() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream("hipermercado.dat"));
        Hipermercado h = (Hipermercado) ois.readObject();
        ois.close();
        return h;
    } 
    
    public void saveApp (String nomeFicheiro) throws FileNotFoundException, IOException{
        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(nomeFicheiro));
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    public void log(String f, boolean ap) throws IOException{
        FileWriter fw = new FileWriter(f, ap);
        fw.write("\n---- LOG - LOG ---- \n");
        fw.write(this.toString());
        fw.write("\n---- LOG - LOG ---- \n");
        fw.flush();
        fw.close();
    }
}