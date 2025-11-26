package SQL;

import java.sql.*;
import Modelo.*;
import java.util.*;

public class EmprestimoDAO {
    public void insert(Emprestimo e){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO emprestimo(id_livro, id_aluno, id_professor, data_emprestimo, data_devolucao, status_livro) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, e.getIdLivro());
            if(e.getIdAluno() != null) ps.setInt(2, e.getIdAluno()); else ps.setNull(2, Types.INTEGER);
            if(e.getIdProfessor() != null) ps.setInt(3, e.getIdProfessor()); else ps.setNull(3, Types.INTEGER);
            ps.setString(4, e.getDataEmprestimo());
            ps.setString(5, e.getDataDevolucao());
            ps.setBoolean(6, e.isStatusLivro());
            ps.execute();
        }catch(Exception ex){ ex.printStackTrace(); }
    }

    public List<String> joinAlunoLivro(){
        List<String> list = new ArrayList<>();
        try(Connection c = DB.getConnection()){
            String sql = """
                    SELECT e.id_emprestimo, l.titulo, a.nome AS aluno
                    FROM emprestimo e
                    JOIN livro l ON e.id_livro = l.id_livro
                    LEFT JOIN aluno a ON e.id_aluno = a.id_aluno
                """;

            ResultSet rs = c.createStatement().executeQuery(sql);
            while(rs.next()){
                list.add("Emprestimo: "+rs.getInt("id_emprestimo")+
                        " | Livro: "+rs.getString("titulo")+
                        " | Aluno: "+rs.getString("aluno"));
            }
        }catch(Exception ex){ ex.printStackTrace(); }
        return list;
    }
}
