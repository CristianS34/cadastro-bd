package cadastrobd.model;

public class PessoaFisica extends Pessoa{
    
    //Atributos
    private String cpf;
    
    //Construtor padrão
    public PessoaFisica(){}
    
    //Construtor completo
    public PessoaFisica(int id,String nome,String logradouro,String cidade,String telefone,String email,String cpf){
        super(id, nome, logradouro, cidade, telefone, email);
        this.cpf = cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCpf() {
        return cpf;
    }

    
    
    //Método exibir
    @Override
    public void exibir() {
          System.out.println("Id: " + this.id + ", " + "Nome: " + this.nome + ", " + "Logradouro: " 
                + this.logradouro + ", " + "Cidade: " + this.cidade + ", " + "Telefone: " + this.telefone + ", " + "Email: " + this.email + ", " + "Cpf: " + this.cpf);
    }
    
}
