import java.io.*;
import static java.lang.System.nanoTime;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.TreeMap;

public class HipermercadoApp implements Serializable
{
     // Construtor privado (não queremos instâncias!...)
    private HipermercadoApp() {}

    private static Hipermercado hiper;
    // Menus da aplicação
    private static Menu menumain, menusecundario;

    // Método principal
    public static void main(String[] args) {
        carregarMenus();
        String f_clientes = "Clientes.txt", f_produtos = "Produtos.txt";
        Leitura leitura;
        
        do {
            menumain.executa();
            switch (menumain.getOpcao()) {
                case 1: leitura = new Leitura(f_clientes, f_produtos);
                        hiper = new Hipermercado(leitura.getCClientes(), leitura.getCProdutos());
                        leitura();
                        break;
                case 2: carregarDados();
                        break;
                case 3: guardarDados();
                        break;
            }
        } while (menumain.getOpcao()!=0);
        System.out.println("Até breve!...");     
    }
    
    // Métodos auxiliares
    
    private static void carregarMenus() {
        String[] ops = {"Sair",
                        "Carregar Ficheiros",
                        "Retomar Sessão Anterior",
                        "Guardar Dados"
                        };

        String [] opssec = {"Retroceder",
                            "Query 1", "Query 2", "Query 3", "Query 4",
                            "Query 5", "Query 6", "Query 7", "Query 8", "Query 9", 
                            "Consultas Estatísticas",
                            };
       

        menumain = new Menu(ops);
        menusecundario = new Menu(opssec);
    }
    
    public static final boolean DEVELOPMENT = true;
    
    private static void carregarDados() {
        System.out.print('\u000C');
        System.out.println ("--- Retomar Sessão Anterior ---\n");
        Scanner scin = new Scanner(System.in);
        try {
            hiper = Hipermercado.initApp();
        }
        catch (IOException e) {
            System.out.println("Não consegui ler os dados!\nErro de leitura.");
            return;
        } 
        catch (ClassNotFoundException e) {
            System.out.println("Não consegui ler os dados!\nFicheiro com formato desconhecido.");
            return;
        }
        catch (ClassCastException e) {
            System.out.println("Não consegui ler os dados!\nErro de formato.");
            return;
        }

        System.out.println ("Dados carregados com sucesso");
        return;
    }
    
    private static void guardarDados(){
        System.out.print('\u000C');
        System.out.println ("--- Guardar Dados ---\n");
        Scanner scin = new Scanner(System.in);
        System.out.println("Introduza o nome a dar ao ficheiro de gravação ou marque 0 para usar o nome predefinido:");
        String name = scin.nextLine();
        //scin.close();
        if (name.substring(0,1).equals("0")) name = "hipermercado.dat";
        try {
            hiper.saveApp(name);
            hiper.log("log.txt", true);
        }
        catch (IOException e) {
            System.out.println("Não consegui gravar os dados!");
            /*scin.nextLine();
            */
            return;
        }
        
        System.out.println ("Dados guardados com sucesso");
        /*scin.nextLine();
        */
        return;
    }
    
    private static void leitura() {
        System.out.print('\u000C');
        System.out.println("--- Carregar Ficheiros ---\n");
        Scanner scin = new Scanner(System.in);
        System.out.println("Selecione o ficheiro de vendas a carregar:");
        System.out.println("    1) Vendas_1M.txt");
        System.out.println("    2) Vendas_3M.txt");
        System.out.println("    3) Vendas_5M.txt");
        System.out.print("\nOpção: ");
        int opt = Integer.parseInt(scin.nextLine());
        
        while (opt < 1 && opt > 3) {
            System.out.println("Opção Inválida!");
            System.out.println("    1) Vendas_1M.txt");
            System.out.println("    2) Vendas_3M.txt");
            System.out.println("    3) Vendas_5M.txt");
            System.out.print("\nOpção: ");
            opt = Integer.parseInt(scin.nextLine());
        }
        
        String vendas = "";
        switch (opt) {
            case 1: vendas = "Vendas_1M.txt";
                    break;
            case 2: vendas = "Vendas_3M.txt";
                    break;
            case 3: vendas = "Vendas_5M.txt";
                    break;
        }
        
        long inicio_buff = nanoTime();
        hiper.leitura(vendas);
        long fim_buff = nanoTime();
        out.println("\nFicheiros carregados com sucesso!");
        out.println("Tempo de leitura: " + (fim_buff - inicio_buff)/1000000);
        double time = 0;
        do {
                menusecundario.executa();
                switch (menusecundario.getOpcao()) {
                    case 1: Crono.start();
                            query1();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 2: Crono.start();
                            query2();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 3: Crono.start();
                            query3();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 4: Crono.start();
                            query4();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 5: Crono.start();
                            query5();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 6: Crono.start();
                            query6();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 7: Crono.start();
                            query7();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 8: Crono.start();
                            query8();
                            time = Crono.stop();
                            out.println(time);
                            break;
                    case 9: Crono.start();
                            query9();
                            time = Crono.stop();
                            out.println(time);
                            break;     
                    case 10: Crono.start(); 
                            consultaEstatistica(vendas);
                            time = Crono.stop();
                            out.println(time);
                            break;
                    default: System.out.println("Opção Inválida!!!");
                             break;
                }
            } while (menusecundario.getOpcao()!=0);
    }
    
    private static void consultaEstatistica(String fich){
        Estatistica est = hiper.getEstatistica();
        int validas = est.getNVendasValidas();
        int lidas = est.getNVendasLidas();
        int totalProd = 0;
        for (String s : hiper.getCatProdutos().getProdutos().keySet())
            totalProd += hiper.getCatProdutos().getProdutos().get(s).size();
        int prodDif = est.getProdDif();
        int totalCli = 0;
        for (String s : hiper.getCatClientes().getClientes().keySet())
            totalCli += hiper.getCatClientes().getClientes().get(s).size();
        int cliDif = est.getCliDif();
        out.println("Ficheiro de vendas lido: " + fich);
        out.println("Vendas Lidas: " + lidas + "\n\rVendas Válidas: " + validas);
        out.println((lidas - validas) + " registos de venda errados");
        out.println("Total de produtos: " + totalProd);
        out.println("Total de diferentes produtos comprados: " + prodDif);
        out.println("Total de produtos não comprados: " + (totalProd - prodDif));
        out.println("Total de clientes: " + totalCli);
        out.println("Total de clientes que compraram: " + cliDif);
        out.println("Total de clientes que não compraram: " + (totalCli - cliDif));
        out.println("Total de vendas de preço 0: " + est.getValorZero());
        out.println("Total faturado: " + est.getFaturacao());
        
        for (int mes = 1; mes < 13; mes++){
            int total = 0;
            for(String s : hiper.getFiliais().getQuery2().get(mes).keySet()) {
                total += hiper.getFiliais().getQuery2().get(mes).get(s);
            }
            out.println("Número total de compras no mês " + mes + ": " + total);
        }
        out.println("Facturação total por mês (valor total das compras/vendas) para cada filial e o valor total global:");
        int compras = 0;
        int diferentes = 0;
        double gasto = 0;
        double total = 0;
        TreeMap<Integer, Filial> filiais = hiper.getFiliais().getFiliais();


        for (int mes = 1; mes < 13; mes++) {
            for(Integer i : filiais.keySet()) {
              	if (filiais.get(i).getQuery3().get(mes) != null) {
                  	for(String cli : filiais.get(i).getQuery3().get(mes).keySet()) {
                        compras += filiais.get(i).getQuery3().get(mes).get(cli).getCompras();
                        gasto += filiais.get(i).getQuery3().get(mes).get(cli).getGasto();
                        diferentes += filiais.get(i).getQuery3().get(mes).get(cli).getInfo().size();
                    }
                }
            }
          total += gasto;
            System.out.println("No mês " + mes +" foram efetuadas " + compras + " com um gasto de " + gasto + "sendo comprados " + diferentes + " produtos diferntes.");
              
            compras = 0;
            diferentes = 0;
            gasto = 0;
        }
        out.println("Gasto Total: " + total);
    
        out.println("\nNúmero de distintos clientes que compraram em cada mês:");
        for (int i = 1; i < 13; i++)
             out.println(i + "-> " + hiper.getFiliais().getQuery2().get(i).size());  
    }
    
    private static void query1(){
        System.out.print('\u000C');
        Scanner scin = new Scanner(System.in);
        System.out.println("--- QUERY 1 ---\n");
        System.out.println("(Lista ordenada alfabeticamente com os códigos\ndos produtos nunca comprados e o seu respectivo total)\n\n");
        TreeSet<String> nuncaComprados = new TreeSet<String>();
        Faturacao fat = hiper.getFaturacao();
        HashMap<String, Integer> nVezesComprado = fat.getNVezesComprado();
        TreeMap<String, TreeSet<String>> prods = hiper.getCatProdutos().getProdutos();
        prods.keySet().forEach(letra -> {
            prods.get(letra).forEach(p -> {if (!nVezesComprado.containsKey(p)) nuncaComprados.add(p);});
            System.out.println("Produtos nunca comprados começados por " + letra +":");
            System.out.print(nuncaComprados);
            scin.nextLine();
            nuncaComprados.clear();
        });
    }
    
    private static void query2(){
        System.out.print('\u000C');
        Scanner scin = new Scanner(System.in);
        System.out.println("--- QUERY 2 ---\n");
        System.out.println("(Dado um mês válido, determinar o número total global de vendas realizadas e o\nnúmero total de clientes distintos que as fizeram)\n\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduza um mês válido: ");
        int mes = sc.nextInt();
        int total = 0;
        while(mes < 1 || mes > 12) {
            System.out.println("Mês " + mes + " inválido, introduza um valor de 1 a 12.");
            System.out.print("Introduza um mês válido: ");
            mes = sc.nextInt();
        }
        System.out.println("--- QUERY 2 ---\n");
        System.out.println("Número total de vendas realizadas e total de clientes distindos no mês " + mes + ".");
        
        
        
        for(String s : hiper.getFiliais().getQuery2().get(mes).keySet()) {
            total += hiper.getFiliais().getQuery2().get(mes).get(s);
        }
        
        System.out.println("Total de vendas realizadas: " + total);
        System.out.println("Total de clientes distintos: " + hiper.getFiliais().getQuery2().get(mes).size());
    };
    
    private static void query3(){
        System.out.print('\u000C');
        Scanner scin = new Scanner(System.in);
        System.out.println("--- QUERY 3 ---\n");
        System.out.println("(Dado um código de cliente, determinar, para cada mês, quantas compras fez,\n" + "quantos produtos distintos comprou e quanto gastou no total.)\n");
        System.out.print("\nIntroduza um código de cliente: ");
        String cli = scin.nextLine();
        while (hiper.getCatClientes().verificaCliente(cli) == false) {
            System.out.println("Código incorreto!");
            System.out.print("\nIntroduza um código de cliente: ");
            cli = scin.nextLine();
        }
        //scin.close();
        int compras = 0;
        int diferentes = 0;
        double gasto = 0;
        TreeMap<Integer, Filial> filiais = hiper.getFiliais().getFiliais();
        for (int mes = 1; mes < 13; mes++) {
            for(Integer i : filiais.keySet()){
                               if (filiais.get(i).getQuery3().get(mes) != null)
                                 	if (filiais.get(i).getQuery3().get(mes).get(cli) != null) {
                                                compras += filiais.get(i).getQuery3().get(mes).get(cli).getCompras();
                                                gasto += filiais.get(i).getQuery3().get(mes).get(cli).getGasto();
                                                diferentes += filiais.get(i).getQuery3().get(mes).get(cli).getInfo().size();
              }
            }
            System.out.println("Cliente: " + cli + " | No mês " + mes +" | Comprou: " + compras + " vezes | Gastou: " + gasto + "\nProdutos diferentes comprados: " + diferentes);
            scin.nextLine();
            compras = 0;
            diferentes = 0;
            gasto = 0;
        }   
    }
    private static void query4(){
        System.out.print('\u000C');
        Scanner scin = new Scanner(System.in);
        System.out.println("--- QUERY 4 ---\n");
        System.out.println("(Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi\ncomprado, por quantos clientes diferentes e o total facturado)\n");
        System.out.print("\nIntroduza um código de produto: ");
        String prod = scin.nextLine();
        while (hiper.getCatProdutos().verificaProduto(prod) == false) {
            System.out.println("Código incorreto!");
            System.out.print("\nIntroduza um código de produto: ");
            prod = scin.nextLine();
        }
        HashMap<Integer, TriploIntSetDouble> compras = hiper.getFaturacao().getQuery4().get(prod);
        compras.keySet().forEach(mes -> {
            System.out.println("No mês " + mes + " foi comprado " + compras.get(mes).getCompras() + " por " + compras.get(mes).getInfo().size() + 
                    " clientes diferentes, tendo sido faturado " + compras.get(mes).getGasto() + " com este produto.");
        });
    }
    
    private static void query5(){
        System.out.print('\u000C');
        Scanner scin = new Scanner(System.in);
        System.out.println("--- QUERY 5 ---\n");
        System.out.println("(Dado o código de um cliente determinar a lista de códigos de produtos que mais\n" +
                           "comprou (e quantos), ordenada por ordem decrescente de quantidade e, para\n" +
                           "quantidades iguais, por ordem alfabética dos códigos;))\n");
        System.out.print("\nIntroduza um código de cliente: ");
        String cli = scin.nextLine();
        while (hiper.getCatClientes().verificaCliente(cli) == false) {
            System.out.println("Código incorreto!");
            System.out.print("\nIntroduza um código de cliente: ");
            cli = scin.nextLine();
        }
        TreeMap<String, Integer> maisComprados = new TreeMap<String, Integer>(new ComparaString());
        maisComprados.putAll(hiper.getFaturacao().getQuery5().get(cli));
        
        String maiorProd = "";
        int i, qtdDiff = 0;
        for(i = 0; i < maisComprados.keySet().size(); i++) {
            for(String s: maisComprados.keySet()) {
                if(maisComprados.get(s) > qtdDiff) {
                    qtdDiff = maisComprados.get(s);
                    maiorProd = s;
                }
                else if (maisComprados.get(s) == qtdDiff){
                        if (s.compareTo(maiorProd) == 1) maiorProd = s;
                    }
            }
            System.out.println( (i+1) + ") Produto: " + maiorProd + "   |   Nº Vezes comprado: " + maisComprados.get(maiorProd));
            qtdDiff = 0;
            maisComprados.remove(maiorProd);
        }
        System.out.println("No total, o cliente " + cli + " comprou " + maisComprados.keySet().size() + " produtos.");
    }
    
    private static void query6() {
        System.out.print('\u000C');
        Scanner scin = new Scanner(System.in);
        System.out.println("--- QUERY 6 ---\n");
        System.out.println("(Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de\n" +
                           "unidades vendidas) indicando o número total de distintos clientes que o\n" +
                           "compraram (X é um inteiro dado pelo utilizador))\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos produtos pretende pesquisar? ");
        int x = sc.nextInt(), qtd = 0;
        while(x < 0 && x > hiper.getFaturacao().getQuery6().size()) {
            System.out.println("Quantidade inválida, introduza um valor superior a 0 e inferior a " + hiper.getFaturacao().getQuery6().size() + ".");
            System.out.print("Introduza um número válido: ");
            x = sc.nextInt();
        }
        
        System.out.println("--- QUERY 6 ---\n");
        System.out.println(x + " Produtos mais vendidos e número de clientes distintos que o compraram.");
        
        TreeMap<String, Integer> prodTot = new TreeMap<String, Integer>(new ComparaString());
        TreeMap<String, DuploSetInt> aux = hiper.getFaturacao().getQuery6();
        
        for(String s : aux.keySet()) {
            if(!prodTot.containsKey(s)) prodTot.put(s, aux.get(s).getQtd());
            else {
                qtd = prodTot.get(s);
                prodTot.remove(s);
                prodTot.put(s, qtd + aux.get(s).getQtd());
            }
        }
        
        int maiorQTD = 0;
        String maiorProd = "";
        for(int i = 0; i < x; i++) {
            for(String s: prodTot.keySet()) {
                if(prodTot.get(s) > maiorQTD) {
                    maiorQTD = prodTot.get(s);
                    maiorProd = s;
                }
            }
            System.out.println( (i+1) + ") Produto: " + maiorProd + "   |   Nº Clientes Distintos: " + hiper.getFaturacao().getQuery6().get(maiorProd).getClientes().size()
                                      + "   |   Nº Unidades Vendidas: " + hiper.getFaturacao().getQuery6().get(maiorProd).getQtd());
            maiorQTD = 0;
            prodTot.remove(maiorProd);
        }
    }
    
    private static void query7(){
        System.out.println("--- QUERY 7 ---\n");
        System.out.println("(Determinar, para cada filial, a lista dos três maiores compradores em termos de dinheiro facturado)\n\n");
        TreeMap<Integer, Filial> filiais = hiper.getFiliais().getFiliais();
        HashMap<String, Double> aux;
        for (Integer i : filiais.keySet()) {
            aux = new HashMap<>(filiais.get(i).getQuery7());
            System.out.printf("                  FILIAL %d:\n", i);
            String maiorCli = "";
            int j;
            double qtdDiff = 0;
            for(j = 0; j < 3; j++) {
                for(String s: aux.keySet()) {
                    if(aux.get(s) > qtdDiff) {
                        qtdDiff = aux.get(s);
                        maiorCli = s;
                    }
                }
            System.out.println( (j+1) + ") Cliente: " + maiorCli + "   |   Gastou: " + aux.get(maiorCli));
            qtdDiff = 0;
            aux.remove(maiorCli);
            }
        }
    }
    
    private static void query8(){
        System.out.println("--- QUERY 8 ---\n");
        System.out.println("(X clientes que compraram mais produtos diferentes e respetiva quantidade)\n\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos clientes pretende pesquisar? ");
        int x = sc.nextInt(), qtd = 0;
        while(x < 0 && x > hiper.getFaturacao().getQuery8().size()) {
            System.out.println("Quantidade inválida, introduza um valor superior a 0 e inferior a " + hiper.getFaturacao().getQuery8().size() + ".");
            System.out.print("Introduza um número válido: ");
            x = sc.nextInt();
        }
                
        TreeMap<String, Integer> cliTot = new TreeMap<String, Integer>(new ComparaString());
        for(String s : hiper.getFaturacao().getQuery8().keySet()) {
            cliTot.put(s, hiper.getFaturacao().getQuery8().get(s).size());
        }
        
        String maiorCli = "";
        int i, qtdDiff = 0;
        for(i = 0; i < x; i++) {
            for(String s: cliTot.keySet()) {
                if(cliTot.get(s) > qtdDiff) {
                    qtdDiff = cliTot.get(s);
                    maiorCli = s;
                }
            }
            System.out.println( (i+1) + ") Cliente: " + maiorCli + "   |   Nº Produtos Distintos: " + cliTot.get(maiorCli));
            qtdDiff = 0;
            cliTot.remove(maiorCli);
        }
    }
    
    private static void query9(){
        System.out.println("--- QUERY 9 ---\n");
        System.out.println("(Dado o código de um produto que deve existir, determinar o conjunto dos X clientes\n" +
                           "que mais o compraram e, para cada um, qual o valor gasto)\n\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduza código de Produto: ");
        String produto = sc.nextLine().trim();
        while (hiper.getCatProdutos().verificaProduto(produto) == false) {
            System.out.println("Código incorreto!");
            System.out.print("\nIntroduza um código de Produto: ");
            produto = sc.nextLine();
        }
        
        System.out.print("Introduza o nº de clientes: ");
        int x = sc.nextInt();
        while(x < 0 || x > hiper.getFaturacao().getQuery9().get(produto).size()) {
            System.out.println("Número de Clientes excedido.");
            System.out.print("Introduza o nº de clientes de 1 a " + hiper.getFaturacao().getQuery9().get(produto).size() + ".");
            x = sc.nextInt();
        }
        
        TreeMap<String, DuploIntDouble> aux = hiper.getFaturacao().getQuery9().get(produto);
        TreeMap<String, Integer> cliUVendidas = new TreeMap<String, Integer>(new ComparaString());
        
        for(String s : aux.keySet()) cliUVendidas.put(s, aux.get(s).getTotalQtd());
        String cliMais = "";
        int maior = 0;
        for(int i = 0; i < x; i++) {
            for(String s : cliUVendidas.keySet()) {
                if(cliUVendidas.get(s) > maior) {
                    maior = cliUVendidas.get(s);
                    cliMais = s;
                }
            }
            System.out.println((i+1) + ") Cliente: " + cliMais + "   |   Unidades Compradas: " + maior + "   |   Total gasto: " + aux.get(cliMais).getFaturacaoTotal());
            cliUVendidas.remove(cliMais);
            maior = 0;
        }
    }

}
