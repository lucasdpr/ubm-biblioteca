package biblioteca;

/**
 * Um livro E UM item do acervo: herda titulo, ano, ficha basica e prazo,
 * e acrescenta autor e numero de paginas. Prazo proprio: 14 dias.
 */
public class Livro extends ItemAcervo {

    // Apenas o que e exclusivo do livro. Titulo e ano vivem em ItemAcervo.
    private String autor;
    private int numeroPaginas;

    // Construtor 1: delega o titulo a superclasse e cuida do autor.
    public Livro(String titulo, String autor) {
        super(titulo); // sempre na primeira linha
        this.autor = autor;
    }

    // Construtor 2: delega titulo E ano a superclasse.
    public Livro(String titulo, String autor, int ano) {
        super(titulo, ano);
        this.autor = autor;
    }

    // Construtor 3: encadeia o construtor 2 (que ja chama super) e
    // acrescenta as paginas. this(...) e super(...) convivem na classe,
    // mas nunca no mesmo construtor.
    public Livro(String titulo, String autor, int ano, int paginas) {
        this(titulo, autor, ano);
        this.numeroPaginas = paginas;
    }

    // Obrigatorio desde a Aula 4: ItemAcervo declarou este metodo abstrato.
    @Override
    public int calcularPrazoDevolucao() {
        return 14;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    // Sobrescrita que ESTENDE: reaproveita a ficha basica via super
    // e acrescenta o que so o livro tem.
    @Override
    public void exibirFicha() {
        super.exibirFicha(); // imprime tipo, titulo, ano e status
        System.out.println("Autor   : " + autor);
        System.out.println("Paginas : " + numeroPaginas);
    }

    // Estende a representacao herdada com o autor.
    @Override
    public String toString() {
        return super.toString() + " - " + autor;
    }

    // Acessores dos atributos proprios. Autor e identidade: sem setter.
    public String getAutor() {
        return autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}
