package Modelo;

public class Professor {
    private int idProfessor;
    private String nome;
    private String departamento;


    public int getIdProfessor() { return idProfessor; }
    public void setIdProfessor(int idProfessor) { this.idProfessor = idProfessor; }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }


    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }


    @Override
    public String toString() {
        return "Professor [id="+idProfessor+", nome="+nome+", departamento="+departamento+"]";
    }
}
