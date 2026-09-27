package biblioteca;

/**
 * Um jornal E UM item do acervo. Acrescenta a data da edicao e a editoria.
 * Circula por um unico dia: prazo proprio de 1 dia.
 */
public class Jornal extends ItemAcervo {

    private String dataEdicao; // "28/08/2026" — texto por enquanto (datas: Aula 7)
    private String editoria;   // "Cidades", "Economia", "Esportes"...

    public Jornal(String titulo, int ano, String dataEdicao, String editoria) {
        super(titulo, ano);
        this.dataEdicao = dataEdicao;
        this.editoria = editoria;
    }

    @Override
    public int calcularPrazoDevolucao() {
        return 1;
    }

    @Override
    public String getTipo() {
        return "Jornal";
    }

    @Override
    public void exibirFicha() {
        super.exibirFicha();
        System.out.println("Data    : " + dataEdicao);
        System.out.println("Editoria: " + editoria);
    }

    @Override
    public String toString() {
        return super.toString() + " - " + dataEdicao + ", " + editoria;
    }

    public String getDataEdicao() {
        return dataEdicao;
    }

    public String getEditoria() {
        return editoria;
    }
}
