package SQL;

import Modelo.Livro;

import java.sql.*;
import java.util.*;

public class LivroDAO {
    public void insert(Livro l){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO livro(titulo, autor, ano_publicado, isbn) VALUES (?, ?, ?, ?)");
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setInt(3, l.getAnoPublicado());
            ps.setString(4, l.getIsbn());
            ps.execute();
        } catch(Exception e){ e.printStackTrace(); }
    }

    public List<Livro> getAll(){
        List<Livro> lista = new ArrayList<>();
        try(Connection c = DB.getConnection()){
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM livro");
            while(rs.next()){
                Livro l = new Livro();
                l.setIdLivro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setAnoPublicado(rs.getInt("ano_publicado"));
                l.setIsbn(rs.getString("isbn"));
                lista.add(l);
            }
        }catch(Exception e){ e.printStackTrace(); }
        return lista;
    }

    public void update(Livro l){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE livro SET titulo=?, autor=?, ano_publicado=?, isbn=? WHERE id_livro=?"
            );
            ps.setString(1, l.getTitulo());
            ps.setString(2, l.getAutor());
            ps.setInt(3, l.getAnoPublicado());
            ps.setString(4, l.getIsbn());
            ps.setInt(5, l.getIdLivro());
            ps.execute();
        }catch(Exception e){ e.printStackTrace(); }
    }

    public void delete(int id){
        try(Connection c = DB.getConnection()){
            PreparedStatement ps = c.prepareStatement("DELETE FROM livro WHERE id_livro=?");
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){ e.printStackTrace(); }
    }
}
