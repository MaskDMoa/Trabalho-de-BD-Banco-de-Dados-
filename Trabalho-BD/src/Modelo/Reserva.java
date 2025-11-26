package Modelo;

import java.util.*;

public class Reserva {
    private int idReserva;
    private String dataReserva;
    private List<Integer> alunos = new ArrayList<>();
    private List<Integer> professores = new ArrayList<>();
    private List<Integer> livros = new ArrayList<>();

    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public String getDataReserva() { return dataReserva; }
    public void setDataReserva(String dataReserva) { this.dataReserva = dataReserva; }

    public List<Integer> getAlunos() { return alunos; }
    public List<Integer> getProfessores() { return professores; }
    public List<Integer> getLivros() { return livros; }

    @Override
    public String toString(){
        return "Reserva [id="+idReserva+", data="+dataReserva+
                ", alunos="+alunos+", professores="+professores+
                ", livros="+livros+"]";
    }
}
