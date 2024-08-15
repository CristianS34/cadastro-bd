package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
   private String cnpj;
    
    public PessoaJuridica(int id,String nome,String logradouro,String cidade,String telefone,String email,String cnpj){
        super(id, nome, logradouro, cidade, telefone, email);
        this.cnpj = cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
    
    //Método exibir
    @Override
    public void exibir() {
          System.out.println("Id: " + this.id + ", " + "Nome: " + this.nome + ", " + "Logradouro: " 
                + this.logradouro + ", " + "Cidade: " + this.cidade + ", " + "Telefone: " + this.telefone + ", " + "Email: " + this.email + ", " + "Cnpj: " + this.cnpj);
    }
}
