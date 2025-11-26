package Modelo;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private int anoPublicado;
    private String isbn;


    public int getIdLivro() { return idLivro; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }


    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }


    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }


    public int getAnoPublicado() { return anoPublicado; }
    public void setAnoPublicado(int anoPublicado) { this.anoPublicado = anoPublicado; }


    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }


    @Override
    public String toString() {
        return "Livro [id="+idLivro+", titulo="+titulo+", autor="+autor+", ano="+anoPublicado+", isbn="+isbn+"]";
    }
}
