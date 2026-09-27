package biblioteca;

/**
 * Um notebook do laboratorio NAO E item do acervo e NAO E espaco fisico:
 * e equipamento. Nao estende classe alguma — apenas assina o contrato.
 * Segunda prova de que interface atravessa hierarquias. Prazo: 2 dias.
 */
public class Notebook implements Emprestavel {

    public static final int PRAZO_EMPRESTIMO = 2;

    private final String patrimonio; // "PAT-00214" — identidade: final
    private String marca;
    private StatusItem status;

    public Notebook(String patrimonio, String marca) {
        this.patrimonio = patrimonio;
        this.marca = marca;
        this.status = StatusItem.DISPONIVEL;
    }

    @Override
    public int calcularPrazoDevolucao() {
        return PRAZO_EMPRESTIMO;
    }

    @Override
    public void emprestar(Usuario usuario) {
        if (!status.permiteEmprestimo()) {
            System.out.println(patrimonio + ": indisponivel ("
                    + status.getDescricao() + ")");
            return;
        }
        status = StatusItem.EMPRESTADO;
        System.out.println("Notebook " + patrimonio + " emprestado a "
                + usuario.getNome() + " por " + calcularPrazoDevolucao() + " dias");
    }

    @Override
    public void devolver() {
        status = StatusItem.DISPONIVEL;
        System.out.println("Notebook " + patrimonio + " devolvido");
    }

    @Override
    public boolean estaDisponivel() {
        return status.permiteEmprestimo();
    }

    @Override
    public String toString() {
        return "Notebook " + marca + " (" + patrimonio + ")";
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public String getMarca() {
        return marca;
    }

    public StatusItem getStatus() {
        return status;
    }
}
