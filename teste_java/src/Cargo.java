import java.util.ArrayList;

public class Cargo {
    private float salario;
    private float aumento;
    private float beneficio;
    private String Cargo;

    public void setAumento(float aumento) {
        this.aumento = aumento;
    }

    public void setBeneficio(float beneficio) {
        this.beneficio = beneficio;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getAumento() {
        return aumento;
    }

    public float getBeneficio() {
        return beneficio;
    }

    public String getCargo() {
        return Cargo;
    }

    public float getSalario() {
        return salario;
    }

}

class Secretario extends Cargo{

    public Secretario(){
        setCargo("Secretário");
        setSalario(7000);
        setAumento(1000);
        setBeneficio(0.20f);
    }

}

class Vendedor extends Cargo{

    private ArrayList<ArrayList<Integer>> regVendas = new ArrayList<ArrayList<Integer>>(); // Colunas: Mês da venda, Ano da venda, Valor total da venda

    public Vendedor(){
        setCargo("Vendedor");
        setSalario(12000);
        setAumento(1800);
        setBeneficio(0.30f);
    }

    public void setNewRegVendas(int mes, int ano, int total) {
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(mes);
        aux.add(ano);
        aux.add(total);
        this.regVendas.add(aux);
        //System.out.println("Venda registrada"); // Verificar se registrou direitinho
    }

    public int getRegVendas(int mes, int ano) {
        int i = 0;
        while (this.regVendas.get(i).get(1) != ano){
            i += 1;
        }
        while (this.regVendas.get(i).get(0) != mes){
            i += 1;
        }
        if (i < this.regVendas.size()){
            return regVendas.get(i).get(2);
        }
        return 0;
    }
}

class Gerente extends Cargo{

    public Gerente(){
        setCargo("Gerente");
        setSalario(20000);
        setAumento(3000);
        setBeneficio(0.0f);
    }
}