import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.ArrayList;

public class App {
  public static void main(String[] args) throws Exception {
    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>(); // Array de Funcionarios

    addFuncionarios(funcionarios);
    addVendas(funcionarios);

    System.out.println("\nOk, se nenhuma mensagem apareceu antes, os dados foram importados com sucesso!\n");

    System.out.println("Os testes a seguir usam um mesmo tipo de documento. Tenha certeza de que o seu documento está neste formato, ou não funcionará corretamente.");
    System.out.println("O documento começa com Nomes dos funcionários, sendo 1 por linha");
    System.out.println("Depois é uma linha para o Mês e outra linha para o Ano\n");

    boolean aux = true; // auxiliar criada para manter o processo em loop

    Scanner lerEscolha = new Scanner(System.in);

    do{
      printMenu();
      String escolha;
      escolha = lerEscolha.nextLine();  

      if (!escolha.equals("1") && !escolha.equals("2") && !escolha.equals("3") && !escolha.equals("4") && !escolha.equals("5") && !escolha.equals("6") ){
        aux = false;
      }
      else if(escolha.equals("1")){
        File auxFile = FileChooser.main();
        FileReader listaFuncionarios = new FileReader(auxFile);
        float totalPago = PrimeiroPedido(funcionarios, listaFuncionarios);
        System.out.println("O total pago foi: " + totalPago + "\n");
      }
      else if(escolha.equals("2")){
        File auxFile = FileChooser.main();
        FileReader listaFuncionarios = new FileReader(auxFile);
        float totalPago = SegundoPedido(funcionarios, listaFuncionarios);
        System.out.println("O total pago em salários foi: " + totalPago + "\n");
      }      
      else if(escolha.equals("3")){
        File auxFile = FileChooser.main();
        FileReader listaFuncionarios = new FileReader(auxFile);
        float totalPago = TerceiroPedido(funcionarios, listaFuncionarios);
        if (totalPago >= 0){
          System.out.println("O total pago em beneficios foi: " + totalPago + "\n");
        }
        else{
          System.out.println("A lista não possui só nomes de funcionários com Benefícios\n");
        }
      }
      else if(escolha.equals("4")){
        File auxFile = FileChooser.main();
        FileReader listaFuncionarios = new FileReader(auxFile);
        String maisRecebeu = QuartoPedido(funcionarios, listaFuncionarios);
        System.out.println("Quem mais recebeu foi " + maisRecebeu + "\n");
      }
      else if(escolha.equals("5")){
        File auxFile = FileChooser.main();
        FileReader listaFuncionarios = new FileReader(auxFile);
        String maisRecebeu = QuintoPedido(funcionarios, listaFuncionarios);
        if (!maisRecebeu.equals("")){
          System.out.println("Quem mais recebeu foi " + maisRecebeu + "\n");
        }
        else{
          System.out.println("A lista não possui só nomes de funcionários com Benefícios\n");
        }
      }
      else if(escolha.equals("6")){
        File auxFile = FileChooser.main();
        FileReader listaFuncionarios = new FileReader(auxFile);
        String maisVendeu = SextoPedido(funcionarios, listaFuncionarios);
        if (!maisVendeu.equals("")){
          System.out.println("Quem mais vendeu foi " + maisVendeu + "\n");
        }
        else{
          System.out.println("A lista não possui só nomes de vendedores\n");
        }
      }

    }while(aux == true);

    lerEscolha.close();

  }

  public static void addFuncionarios(ArrayList<Funcionario> funcionarios){
    try {
      File myObj = new File("database/funcionarios.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
          String line = myReader.nextLine();
          String split[] = line.split(",");
          String nome = split[0];                
          int auxCargo = Integer.parseInt(split[1]);
          int mes = Integer.parseInt(split[2]);
          int ano = Integer.parseInt(split[3]);
          funcionarios.add(new Funcionario(nome,auxCargo,mes,ano));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void addVendas(ArrayList<Funcionario> funcionarios){
    try {
      File myObj = new File("database/vendas.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
          String line = myReader.nextLine();
          String split[] = line.split(",");
          String nome = split[0];                
          int mes = Integer.parseInt(split[1]);
          int ano = Integer.parseInt(split[2]);
          int venda = Integer.parseInt(split[3]);
          int i = 0;
          while (!funcionarios.get(i).getNome().equals(nome)){
            i += 1;
          }
          funcionarios.get(i).setVendas(mes, ano, venda);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void printMenu(){
    System.out.println("O que deseja fazer? (digite o número)");
    System.out.println("1 - Um método que receba uma lista de funcionários, mês e ano e retorne o valor total pago (salário e benefício) a esses funcionários no mês");
    System.out.println("2 - Um método que receba uma lista de funcionários, mês e ano e retorne o total pago somente em salários no mês");
    System.out.println("3 - Um método que receba uma lista somente com os funcionários que recebem benefícios, mês e ano e retorne o total pago em benefícios no mês.");
    System.out.println("4 - Um método que receba uma lista de funcionários, mês e ano e retorne o que recebeu o valor mais alto no mês");
    System.out.println("5 - Um método que receba uma lista somente com os funcionários que recebem benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais alto em benefícios no mês.");
    System.out.println("6 - Um método que receba uma lista de vendedores, mês e ano e retorne o que mais vendeu no mês.");
    System.out.println("Sair - Qualquer outra tecla");
  }

  public static int checkLista(ArrayList<Funcionario> funcionarios, String nome){
    int index = 0;
    Boolean igual = false;
    while(index < funcionarios.size() && igual == false){
      if (nome.equals(funcionarios.get(index).getNome())){
        return index;
      }
      else{
        index++;
      }
    }
    return -1;
  }

  public static float PrimeiroPedido(ArrayList<Funcionario> funcionarios, FileReader arquivoLido){
    float total = 0;

    for (int i = 0; i < arquivoLido.getSize(); i++) {
      float pago = 0;
      int j = checkLista(funcionarios, arquivoLido.nomes.get(i));
      if (j != -1){
        if (funcionarios.get(j).contratado(arquivoLido.mes, arquivoLido.ano)){
            float SalarioAtual = funcionarios.get(j).salarioAReceber(arquivoLido.mes, arquivoLido.ano);
            float Beneficio = funcionarios.get(j).beneficioAReceber(arquivoLido.mes, arquivoLido.ano, SalarioAtual);
            pago = SalarioAtual + Beneficio;
            //System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " recebeu " + pago + ", sendo " + SalarioAtual + " de salário e " + Beneficio + " de benefício este mês.");
        }else{
          System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " até existe, mas não havia sido contratado ainda nesse mês testado.");
        }
      }
      else{
        System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " não existe.");
      }
      // somar ao total
      total = total + pago;
    }
    return total;
  }

  public static float SegundoPedido(ArrayList<Funcionario> funcionarios, FileReader arquivoLido){
    float total = 0;

    for (int i = 0; i < arquivoLido.getSize(); i++) {
      float SalarioAtual = 0;
      int j = checkLista(funcionarios, arquivoLido.nomes.get(i));
      if (j != -1){
        if (funcionarios.get(j).contratado(arquivoLido.mes, arquivoLido.ano)){
            SalarioAtual = funcionarios.get(j).salarioAReceber(arquivoLido.mes, arquivoLido.ano);
            //System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " recebeu " + SalarioAtual + " de salário este mês.");
        }else{
          System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " até existe, mas não havia sido contratado ainda nesse mês testado.");
        }
      }
      else{
        System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " não existe.");
      }
      total = total + SalarioAtual;
    }
    return total;
  }

  public static float TerceiroPedido(ArrayList<Funcionario> funcionarios, FileReader arquivoLido){
    float total = 0;
    for (int i = 0; i < arquivoLido.getSize(); i++) {
      float BeneficioAtual = 0;
      int j = checkLista(funcionarios, arquivoLido.nomes.get(i));
      if (j!=-1){
        if(!funcionarios.get(j).checkBeneficio()){
          return -1;
        }
        if (funcionarios.get(j).contratado(arquivoLido.mes, arquivoLido.ano)){
          float SalarioAtual = 0;
          if (!funcionarios.get(j).checkVendedor()){
            SalarioAtual = funcionarios.get(j).salarioAReceber(arquivoLido.mes, arquivoLido.ano);
          }
          BeneficioAtual = funcionarios.get(j).beneficioAReceber(arquivoLido.mes, arquivoLido.ano, SalarioAtual);
        }else{
          System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " até existe, mas não havia sido contratado ainda nesse mês testado.");
        }
      }
      else{
        return -1;
      }
      total = total + BeneficioAtual;
    }
    return total;
  }

  public static String QuartoPedido(ArrayList<Funcionario> funcionarios, FileReader arquivoLido){
    String maisRecebeu = "";
    float maiorValor = -1;
    
    for (int i = 0; i < arquivoLido.getSize(); i++) {
      float pago = 0;
      int j = checkLista(funcionarios, arquivoLido.nomes.get(i));
      if (j != -1){
        if (funcionarios.get(j).contratado(arquivoLido.mes, arquivoLido.ano)){
            float SalarioAtual = funcionarios.get(j).salarioAReceber(arquivoLido.mes, arquivoLido.ano);
            float Beneficio = funcionarios.get(j).beneficioAReceber(arquivoLido.mes, arquivoLido.ano, SalarioAtual);
            pago = SalarioAtual + Beneficio;
            if (pago > maiorValor){
              maiorValor = pago;
              maisRecebeu = arquivoLido.nomes.get(i);
            }
            //System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " recebeu " + pago + ", sendo " + SalarioAtual + " de salário e " + Beneficio + " de benefício este mês.");
        }else{
          System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " até existe, mas não havia sido contratado ainda nesse mês testado.");
        }
      }
      else{
        System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " não existe.");
      }
    }

    return maisRecebeu;
  }

  public static String QuintoPedido(ArrayList<Funcionario> funcionarios, FileReader arquivoLido){
    String maisRecebeu = "";
    float quantoRecebeu = -1;
    for (int i = 0; i < arquivoLido.getSize(); i++) {
      float BeneficioAtual = 0;
      int j = checkLista(funcionarios, arquivoLido.nomes.get(i));
      if (j!=-1){
        if(!funcionarios.get(j).checkBeneficio()){
          return "";
        }
        if (funcionarios.get(j).contratado(arquivoLido.mes, arquivoLido.ano)){
          float SalarioAtual = 0;
          if (!funcionarios.get(j).checkVendedor()){
            SalarioAtual = funcionarios.get(j).salarioAReceber(arquivoLido.mes, arquivoLido.ano);
          }
          BeneficioAtual = funcionarios.get(j).beneficioAReceber(arquivoLido.mes, arquivoLido.ano, SalarioAtual);
          if (BeneficioAtual > quantoRecebeu){
            quantoRecebeu = BeneficioAtual;
            maisRecebeu = arquivoLido.nomes.get(i);
          }
        }else{
          System.out.println("O funcionário " + arquivoLido.nomes.get(i) + " até existe, mas não havia sido contratado ainda nesse mês testado.");
        }
      }
      else{
        return "";
      }
    }
    return maisRecebeu;
  }

  public static String SextoPedido(ArrayList<Funcionario> funcionarios, FileReader arquivoLido){
    String maisVendeu = "";
    int quantoVendeu = -1;
    for (int i = 0; i < arquivoLido.getSize(); i++) {
      int j = checkLista(funcionarios, arquivoLido.nomes.get(i));
      if (j!=-1){
        if (!funcionarios.get(j).checkVendedor()){
          return "";
        }
        int vendeu = funcionarios.get(j).getVendas(arquivoLido.mes, arquivoLido.ano);
        if (vendeu > quantoVendeu){
          quantoVendeu = vendeu;
          maisVendeu = arquivoLido.nomes.get(i);
        }
      }
      else{
        return "";
      }
    }  
    return maisVendeu;
  }
}