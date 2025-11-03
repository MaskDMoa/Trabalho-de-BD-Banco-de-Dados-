# Trabalho de BD (Banco de dados)
---

## Entidades
1. **Aluno**  
   - Representa os estudantes que podem pegar livros emprestados.  
   - Atributos: `id_aluno`, `nome`, `matricula`, `curso`.

2. **Professor**  
   - Representa os professores que também podem realizar empréstimos.  
   - Atributos: `id_professor`, `nome`, `departamento`.

3. **Livro**  
   - Representa cada livro disponível na biblioteca.  
   - Atributos: `id_livro`, `titulo`, `autor`, `ano_publicacao`, `isbn`.

4. **Empréstimo**  
   - Registra os livros emprestados, a quem foram emprestados e por quanto tempo.  
   - Atributos: `id_emprestimo`, `data_emprestimo`, `data_devolucao`, `status`.

5. **Reserva**  
   - Registra quando um aluno ou professor reserva um livro que está emprestado.  
   - Atributos: `id_reserva`, `data_reserva`.

---

## Relacionamentos
- **Aluno 1:N Empréstimo** → Um aluno pode realizar vários empréstimos, mas cada empréstimo pertence a apenas um aluno.  
- **Professor 1:N Empréstimo** → Um professor pode realizar vários empréstimos, mas cada empréstimo pertence a apenas um professor.  
- **Livro 1:N Empréstimo** → Um livro pode ser emprestado várias vezes, mas cada empréstimo está vinculado a um único livro.  
- **Livro N:M Reserva** → Um livro pode ser reservado por vários usuários (alunos/professores) e cada usuário pode reservar vários livros.  
- **Reserva 1:1 Empréstimo** → Cada reserva pode resultar em um único empréstimo.

---
