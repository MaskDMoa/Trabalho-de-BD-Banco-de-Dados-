package SQL;

import java.sql.*;
import java.util.*;
import Modelo.Aluno;

public class AlunoDAO {

    public void insert(Aluno a) {
        try (Connection c = DB.getConnection();) {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO aluno(nome, matricula, curso) VALUES (?, ?, ?)" );
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getMatricula());
            ps.setString(3, a.getCurso());
            ps.execute();
        } catch(Exception e){ e.printStackTrace(); }
    }


    public List<Aluno> getAll(){
        List<Aluno> list = new ArrayList<>();
        try (Connection c = DB.getConnection()) {
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM aluno");
            while(rs.next()){
                Aluno a = new Aluno();
                a.setIdAluno(rs.getInt("id_aluno"));
                a.setNome(rs.getString("nome"));
                a.setMatricula(rs.getInt("matricula"));
                a.setCurso(rs.getString("curso"));
                list.add(a);
            }
        } catch(Exception e){ e.printStackTrace(); }
        return list;
    }


    public void update(Aluno a){
        try (Connection c = DB.getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE aluno SET nome=?, matricula=?, curso=? WHERE id_aluno=?");
            ps.setString(1, a.getNome());
            ps.setInt(2, a.getMatricula());
            ps.setString(3, a.getCurso());
            ps.setInt(4, a.getIdAluno());
            ps.execute();
        } catch(Exception e){ e.printStackTrace(); }
    }


    public void delete(int id){
        try (Connection c = DB.getConnection()) {
            PreparedStatement ps = c.prepareStatement("DELETE FROM aluno WHERE id_aluno=?");
            ps.setInt(1, id);
            ps.execute();
        } catch(Exception e){ e.printStackTrace(); }
    }
}
}
