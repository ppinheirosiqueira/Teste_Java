public class Funcionario {
    private String nome;
    private Cargo cargo;
    private int mesContratado;
    private int anoContratado;

    public Funcionario(String Nome, int cargoID, int mes, int ano){
        nome = Nome;
        mesContratado = mes;
        anoContratado = ano;
        if (cargoID == 1){
            cargo = new Secretario();
        }
        else if (cargoID == 2){
            cargo = new Vendedor();
        }
        else{
            cargo = new Gerente();
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMesContratado(int mesContratado) {
        this.mesContratado = mesContratado;
    }

    public void setAnoContratado(int anoContratado) {
        this.anoContratado = anoContratado;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public int getMesContratado() {
        return mesContratado;
    }

    public int getAnoContratado() {
        return anoContratado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void printFuncionario(){
        System.out.println("O funcionario de nome: " + nome + ", tem o cargo: " + cargo.getCargo() + ", ganha " + String.valueOf(cargo.getSalario()) + " por mês e está no cargo desde: " + String.valueOf(mesContratado) + "/" + String.valueOf(anoContratado));
    }

    public void setVendas(int mes, int ano, int venda){
        if (this.cargo instanceof Vendedor) { 
            Vendedor objB = (Vendedor) this.cargo;
            objB.setNewRegVendas(mes, ano, venda); 
        }
    }

    public int getVendas(int mes, int ano){
        if (this.cargo instanceof Vendedor) { 
            Vendedor objB = (Vendedor) this.cargo;
            return objB.getRegVendas(mes, ano); 
        }
        return 0;
    }

    public boolean contratado(int mes, int ano){
        if (ano > anoContratado || (anoContratado == ano && mes >= mesContratado)){
            return true;
        }
        return false;
    }

    public float salarioAReceber(int mes, int ano){
        int difAno = ano - anoContratado;
        if (difAno > 0){
          int difMes = mes - mesContratado;
          if (difMes < 0){
            difAno--;
          }
        }
        float SalarioAtual = cargo.getSalario() + cargo.getAumento()*difAno;
        return SalarioAtual;
    }

    public float beneficioAReceber(int mes, int ano, float salario){
        float Beneficio = 0;
        if (cargo instanceof Vendedor){ // Beneficio para vendedores
            Beneficio = getVendas(mes,ano)*cargo.getBeneficio();
        }
        else{
            Beneficio = salario*cargo.getBeneficio();
        }
        return Beneficio;
    }

    public boolean checkBeneficio(){
        if(cargo.getBeneficio() > 0){
            return true;
        }
        return false;
    }

    public boolean checkVendedor(){
        if (cargo instanceof Vendedor){
            return true;
        }
        return false;
    }
}
