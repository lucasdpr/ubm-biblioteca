package biblioteca;

/**
 * Uma sala de estudo NAO E um item do acervo: nao tem titulo nem ano,
 * e nao estende classe nenhuma. Mas SABE FAZER o que todo emprestavel
 * faz — por isso implementa a interface. Reserva de 1 dia.
 * -------------------------------------------------------------------
 * A classe SalaEstudo precisa implementar todos os métodos da interface Emprestável?
 * Como a classe SalaEstudo é concreta, sim — mas "todos os métodos" precisa de uma qualificação.
 * Emprestavel declara cinco métodos. SalaEstudo é obrigada a implementar quatro.
 *
 * Membro de Emprestavel        Tipo                        SalaEstudo precisa?
 * ======================================================
 * PRAZO_MAXIMO_DIAS = 30       constante (public static final)  não — é herdada pronta
 * calcularPrazoDevolucao()     abstrato                          sim
 * emprestar(Usuario)           abstrato                          sim
 * devolver()                   abstrato                          sim
 * estaDisponivel()             abstrato                          sim
 * dentroDaPolitica()           default                           não — já vem com corpo
 *
 * Observação: Uma classe abstrata (por exemplo ItemAcervo) não é obrigada a implementar todos
 * os métodos de uma interface; apenas as classes concretas têm essa obrigação.
 * A regra:
 * Uma classe abstrata que implementa uma interface pode implementar todos, alguns ou
 * nenhum dos métodos. O que ela não implementar simplesmente continua abstrato, e a dívida
 * passa adiante para a primeira subclasse concreta da hierarquia.
 */
public class SalaEstudo implements Emprestavel {

    public static final int PRAZO_RESERVA = 1;

    private final String identificacao; // "Sala 3 - Bloco B"
    private int capacidade;
    private StatusItem status; // o mesmo enum do acervo

    public SalaEstudo(String identificacao, int capacidade) {
        this.identificacao = identificacao;
        this.capacidade = capacidade;
        this.status = StatusItem.DISPONIVEL;
    }

    @Override
    public int calcularPrazoDevolucao() {
        return PRAZO_RESERVA;
    }

    @Override
    public void emprestar(Usuario usuario) {
        if (!status.permiteEmprestimo()) {
            System.out.println(identificacao + ": indisponivel (" + status.getDescricao() + ")");
            return;
        }
        status = StatusItem.EMPRESTADO;
        System.out.println(identificacao + " reservada para " + usuario.getNome()
                + " por " + calcularPrazoDevolucao() + " dia(s)");
    }

    @Override
    public void devolver() {
        status = StatusItem.DISPONIVEL;
        System.out.println(identificacao + " liberada");
    }

    @Override
    public boolean estaDisponivel() {
        return status == StatusItem.DISPONIVEL;
    }

    @Override
    public String toString() {
        return identificacao + " (" + capacidade + " lugares)";
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public StatusItem getStatus() {
        return status;
    }
}
