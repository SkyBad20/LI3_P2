import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.TreeSet;
import java.io.Serializable;

public class Leitura implements Serializable {
    private CatClientes clientes;
    private CatProdutos produtos;
    
    
    public Leitura(String pC, String pP) {
        clientes = leitura_cli(pC);
        produtos = leitura_pro(pP);
    }
    
    public CatClientes getCClientes() {
        return clientes;
    }
    
    public CatProdutos getCProdutos() {
        return produtos;
    }
    
    public CatClientes leitura_cli(String path) {
        TreeSet<String> linhas = new TreeSet<>(new ComparaString());
        BufferedReader inStream = null;
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(path));
            while( (linha = inStream.readLine()) != null ) linhas.add(linha);
        }
        catch(IOException e) {
            out.println(e.getMessage());
            return null;
        }
        return new CatClientes(linhas);
    }
    
    public CatProdutos leitura_pro(String path) {
        TreeSet<String> linhas = new TreeSet<>(new ComparaString());
        BufferedReader inStream = null;
        String linha = null;
        try {
            inStream = new BufferedReader(new FileReader(path));
            while( (linha = inStream.readLine()) != null ) linhas.add(linha);
        }
        catch(IOException e) {
            out.println(e.getMessage());
            return null;
        }
        return new CatProdutos(linhas);
    }
}