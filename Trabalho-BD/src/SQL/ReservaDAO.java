package SQL;

import java.sql.*;
import java.util.*;
import Modelo.*;

public class ReservaDAO {
    public void insert(Reserva r){
        try(Connection c = DB.getConnection()){

            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO reserva(data_reserva) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, r.getDataReserva());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){ r.setIdReserva(rs.getInt(1)); }

            // Inserir alunos ligados à reserva
            for(Integer idA : r.getAlunos()){
                PreparedStatement psa = c.prepareStatement(
                        "INSERT INTO reserva_aluno(id_reserva, id_aluno) VALUES (?, ?)");
                psa.setInt(1, r.getIdReserva());
                psa.setInt(2, idA);
                psa.execute();
            }

            // Inserir professores ligados à reserva
            for(Integer idP : r.getProfessores()){
                PreparedStatement psp = c.prepareStatement(
                        "INSERT INTO reserva_professor(id_reserva, id_professor) VALUES (?, ?)");
                psp.setInt(1, r.getIdReserva());
                psp.setInt(2, idP);
                psp.execute();
            }

            // Inserir livros ligados à reserva
            for(Integer idL : r.getLivros()){
                PreparedStatement psl = c.prepareStatement(
                        "INSERT INTO reserva_livro(id_reserva, id_livro) VALUES (?, ?)");
                psl.setInt(1, r.getIdReserva());
                psl.setInt(2, idL);
                psl.execute();
            }

        }catch(Exception e){ e.printStackTrace(); }
    }

    // JOINS -> Buscar reservas com tudo relacionado
    public List<String> getReservasDetalhadas(){
        List<String> lista = new ArrayList<>();
        try(Connection c = DB.getConnection()){

            String sql = """
                SELECT r.id_reserva, r.data_reserva,
                       l.titulo AS livro,
                       a.nome AS aluno,
                       p.nome AS professor
                FROM reserva r
                LEFT JOIN reserva_livro rl ON r.id_reserva = rl.id_reserva
                LEFT JOIN livro l ON rl.id_livro = l.id_livro
                LEFT JOIN reserva_aluno ra ON r.id_reserva = ra.id_reserva
                LEFT JOIN aluno a ON ra.id_aluno = a.id_aluno
                LEFT JOIN reserva_professor rp ON r.id_reserva = rp.id_reserva
                LEFT JOIN professor p ON rp.id_professor = p.id_professor
                ORDER BY r.id_reserva;
            """;

            ResultSet rs = c.createStatement().executeQuery(sql);

            while(rs.next()){
                lista.add("RESERVA "+rs.getInt("id_reserva")+
                        " | Data: "+rs.getString("data_reserva")+
                        " | Livro: "+rs.getString("livro")+
                        " | Aluno: "+rs.getString("aluno")+
                        " | Professor: "+rs.getString("professor"));
            }
        }catch(Exception e){ e.printStackTrace(); }

        return lista;
    }
}
