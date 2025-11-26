package Modelo;

public class Emprestimo {
    private int idEmprestimo;
    private int idLivro;
    private Integer idAluno;
    private Integer idProfessor;
    private String dataEmprestimo;
    private String dataDevolucao;
    private boolean statusLivro;


    public int getIdEmprestimo() { return idEmprestimo; }
    public void setIdEmprestimo(int idEmprestimo) { this.idEmprestimo = idEmprestimo; }


    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }


    public Integer getIdAluno() { return idAluno; }
    public void setIdAluno(Integer idAluno) { this.idAluno = idAluno; }


    public Integer getIdProfessor() { return idProfessor; }
    public void setIdProfessor(Integer idProfessor) { this.idProfessor = idProfessor; }


    public String getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(String dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }


    public String getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(String dataDevolucao) { this.dataDevolucao = dataDevolucao; }


    public boolean isStatusLivro() { return statusLivro; }
    public void setStatusLivro(boolean statusLivro) { this.statusLivro = statusLivro; }


    @Override
    public String toString() {
        return "Emprestimo [id="+idEmprestimo+", livro="+idLivro+", aluno="+idAluno+", professor="+idProfessor+
                " data_emprestimo="+dataEmprestimo+", devolução="+dataDevolucao+", status="+statusLivro+"]";
    }
}
