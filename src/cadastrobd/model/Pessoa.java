package cadastrobd.model;

public class Pessoa {
    
    //Atributos
    int id;
    String nome;
    String logradouro;
    String cidade;
    String telefone;
    String email;
    
    //Construtor padrão
    public Pessoa(){}
    
    //Construtor completo
    public Pessoa(int id,String nome,String logradouro,String cidade,String telefone,String email){
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
    
    
    //Método exibir
    public void exibir(){
        System.out.println("Id: " + this.id + ", " + "Nome: " + this.nome + ", " + "Logradouro: " 
                + this.logradouro + ", " + "Cidade: " + this.cidade + ", " + "Telefone: " + this.telefone + ", " + "Email: " + this.email );
    }
        
}
