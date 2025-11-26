package Modelo;

public class Aluno {
    private int idAluno;
    private String nome;
    private int matricula;
    private String curso;


    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }


    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }


    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }


    @Override
    public String toString() {
        return "Aluno [id="+idAluno+", nome="+nome+", matricula="+matricula+", curso="+curso+"]";
    }
}
