package biblioteca;

/**
 * Uma midia (DVD, Blu-ray, CD) E UM item do acervo.
 * Acrescenta formato e duracao. Prazo proprio: 3 dias.
 */
public class Midia extends ItemAcervo {

    private String formato; // "DVD", "Blu-ray", "CD"
    private int duracaoMinutos;

    public Midia(String titulo, int ano, String formato, int duracaoMinutos) {
        super(titulo, ano);
        this.formato = formato;
        this.duracaoMinutos = duracaoMinutos;
    }

    // Midia circula rapido: prazo proprio, bem abaixo do padrao de 7 dias.
    @Override
    public int calcularPrazoDevolucao() {
        return 3;
    }

    @Override
    public String getTipo() {
        return "Midia";
    }

    @Override
    public void exibirFicha() {
        super.exibirFicha();
        System.out.println("Formato : " + formato);
        System.out.println("Duracao : " + duracaoMinutos + " min");
    }

    @Override
    public String toString() {
        return super.toString() + " - " + formato + ", " + duracaoMinutos + " min";
    }

    public String getFormato() {
        return formato;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
}
