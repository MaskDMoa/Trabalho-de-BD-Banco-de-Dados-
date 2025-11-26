import java.util.*;

import SQL.*;
import Modelo.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoDAO alunoDAO = new AlunoDAO();
        LivroDAO livroDAO = new LivroDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        ReservaDAO reservaDAO = new ReservaDAO();


        while(true){
            System.out.println("=== MENU BIBLIOTECA ===");
            System.out.println("1 - Inserir aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Inserir livro");
            System.out.println("4 - Listar livros");
            System.out.println("5 - JOINS exemplos");
            System.out.println("0 - Sair");
            int op = sc.nextInt();


            switch(op){
                case 1 -> {
                    Aluno a = new Aluno();
                    System.out.print("Nome: "); a.setNome(sc.next());
                    System.out.print("Matricula: "); a.setMatricula(sc.nextInt());
                    System.out.print("Curso: "); a.setCurso(sc.next());
                    alunoDAO.insert(a);
                }
                case 2 -> alunoDAO.getAll().forEach(System.out::println);
                case 3 -> {
                    Livro l = new Livro();
                    System.out.print("Titulo: "); l.setTitulo(sc.next());
                    System.out.print("Autor: "); l.setAutor(sc.next());
                    System.out.print("Ano: "); l.setAnoPublicado(sc.nextInt());
                    System.out.print("ISBN: "); l.setIsbn(sc.next());
                    livroDAO.insert(l);
                }
                case 4 -> livroDAO.getAll().forEach(System.out::println);
                case 5 -> {
                    emprestimoDAO.joinAlunoLivro().forEach(System.out::println);
                    reservaDAO.joinReservaLivro().forEach(System.out::println);
                }
                case 0 -> System.exit(0);
            }
        }
    }
    }
}