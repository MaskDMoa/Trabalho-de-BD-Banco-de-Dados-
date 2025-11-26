package SQL;

import Modelo.Professor;

import java.sql.*;
import java.util.*;


public class ProfessorDAO {
    public void insert(Professor p){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO professor(nome, departamento) VALUES (?, ?)");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDepartamento());
            ps.execute();
        } catch(Exception e){ e.printStackTrace(); }
    }

    public List<Professor> getAll(){
        List<Professor> lista = new ArrayList<>();
        try(Connection c = DB.getConnection()){
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM professor");
            while(rs.next()){
                Professor p = new Professor();
                p.setIdProfessor(rs.getInt("id_professor"));
                p.setNome(rs.getString("nome"));
                p.setDepartamento(rs.getString("departamento"));
                lista.add(p);
            }
        }catch(Exception e){ e.printStackTrace(); }
        return lista;
    }

    public void update(Professor p){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE professor SET nome=?, departamento=? WHERE id_professor=?");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDepartamento());
            ps.setInt(3, p.getIdProfessor());
            ps.execute();
        }catch(Exception e){ e.printStackTrace(); }
    }

    public void delete(int id){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement("DELETE FROM professor WHERE id_professor=?");
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){ e.printStackTrace(); }
    }
}
