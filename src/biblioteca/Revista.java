package biblioteca;

/**
 * Uma revista E UM item do acervo. Acrescenta edicao e periodicidade.
 * Na Aula 3 herdava os 7 dias em silencio; agora a classe abstrata
 * OBRIGA a declarar o prazo — e ele passa a ser uma decisao escrita.
 */
public class Revista extends ItemAcervo {

    private int edicao;
    private String periodicidade; // "Mensal", "Trimestral", "Semestral"...

    public Revista(String titulo, int ano, int edicao, String periodicidade) {
        super(titulo, ano);
        this.edicao = edicao;
        this.periodicidade = periodicidade;
    }

    // O prazo da revista E o prazo padrao — mas agora dito, nao omitido.
    // PRAZO_PADRAO e a constante herdada de ItemAcervo.
    @Override
    public int calcularPrazoDevolucao() {
        return PRAZO_PADRAO;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public void exibirFicha() {
        super.exibirFicha();
        System.out.println("Edicao  : " + edicao);
        System.out.println("Period. : " + periodicidade);
    }

    @Override
    public String toString() {
        return super.toString() + " - ed. " + edicao + ", " + periodicidade;
    }

    public int getEdicao() {
        return edicao;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }
}
