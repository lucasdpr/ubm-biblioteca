package biblioteca;

/**
 * Situacao possivel de qualquer coisa que a biblioteca empresta.
 * Conjunto FECHADO de valores: o compilador nao aceita nada alem
 * do que estiver declarado aqui.
 */
public enum StatusItem {

    // As constantes vem primeiro e chamam o construtor do proprio enum.
    DISPONIVEL("Disponivel"),
    EMPRESTADO("Emprestado"),
    RESERVADO("Reservado"),
    EM_MANUTENCAO("Em manutencao"),
    EXTRAVIADO("Extraviado");

    // final: a descricao e definida quando a constante nasce e nunca muda.
    private final String descricao;

    // Construtor de enum e sempre privado: ninguem cria um StatusItem novo.
    StatusItem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Um enum tambem tem comportamento. Aqui a regra do emprestimo
    // fica junto do proprio estado: so quem esta DISPONIVEL sai.
    // "this" e a constante sobre a qual o metodo foi chamado.
    public boolean permiteEmprestimo() {
        return this == DISPONIVEL;
    }
}
