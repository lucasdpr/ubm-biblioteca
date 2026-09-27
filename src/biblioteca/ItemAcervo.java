package biblioteca;

import java.util.Objects;

/**
 * Superclasse ABSTRATA de todo item do acervo (livro, revista, midia, jornal).
 * Nao pode ser instanciada: "item generico" nao existe na biblioteca real.
 * Implementa Emprestavel: todo item do acervo sabe ser emprestado.
 * Implementa Comparable: todo item sabe dizer se vem antes ou depois de outro
 * (a ordem natural do acervo e por titulo, com desempate pelo ano).
 */
public abstract class ItemAcervo implements Emprestavel, Comparable<ItemAcervo> {

    // static final: constante da CLASSE. Nome em MAIUSCULAS, por convencao.
    public static final int PRAZO_PADRAO = 7;

    // static: pertence a classe, nao ao objeto. Um contador para todos.
    private static int totalItens = 0;

    // Atributos comuns a todo item. Permanecem private mesmo para as
    // subclasses: elas acessam pelos getters (norma do projeto).
    // final: recebe valor no construtor e nunca mais muda. Titulo e identidade.
    private final String titulo;
    // A partir da Aula 5 o ano tambem compoe a identidade (ver equals):
    // por isso o setter foi removido. O valor entra pelo construtor.
    private int anoPublicacao;
    private StatusItem status;

    // Construtor minimo: todo item nasce com titulo e disponivel.
    public ItemAcervo(String titulo) {
        this.titulo = titulo;
        this.status = StatusItem.DISPONIVEL;
        totalItens++; // conta na classe, nao no objeto
    }

    // Construtor completo: encadeia o minimo e acrescenta o ano.
    public ItemAcervo(String titulo, int anoPublicacao) {
        this(titulo);
        this.anoPublicacao = anoPublicacao;
    }

    // ----- METODOS ABSTRATOS: assinatura sem corpo, obrigatorios na subclasse -----

    // Redeclarado aqui, sem corpo, para deixar explicito: quem estender
    // ItemAcervo E OBRIGADO a dizer qual e o seu prazo.
    public abstract int calcularPrazoDevolucao();

    // O nome do tipo, usado pela ficha logo abaixo.
    public abstract String getTipo();

    // ----- METODO CONCRETO QUE USA UM METODO ABSTRATO -----

    // A superclasse organiza a ficha; a subclasse fornece so a parte que varia.
    public void exibirFicha() {
        System.out.println("--- " + getTipo() + " ---");
        System.out.println("Titulo  : " + titulo);
        System.out.println("Ano     : " + anoPublicacao);
        System.out.println("Status  : " + status.getDescricao());
    }

    // ----- CONTRATO Emprestavel, implementado uma unica vez para toda a familia -----

    @Override
    public void emprestar(Usuario usuario) {
        // A regra saiu daqui e foi para o enum: quem sabe se um estado
        // permite emprestimo e o proprio estado.
        if (!status.permiteEmprestimo()) {
            System.out.println(titulo + ": indisponivel (" + status.getDescricao() + ")");
            return;
        }
        status = StatusItem.EMPRESTADO;
        // calcularPrazoDevolucao() e abstrato aqui: quem responde e o objeto real.
        System.out.println(titulo + " emprestado a " + usuario.getNome()
                + " por " + calcularPrazoDevolucao() + " dias");
    }

    @Override
    public void devolver() {
        status = StatusItem.DISPONIVEL;
        System.out.println(titulo + " devolvido");
    }

    @Override
    public boolean estaDisponivel() {
        return status == StatusItem.DISPONIVEL; // enum se compara com ==
    }

    // Sobrescreve o toString() herdado de Object. A partir daqui,
    // System.out.println(item) e "..." + item usam esta representacao.
    @Override
    public String toString() {
        return titulo + " (" + anoPublicacao + ")";
    }

    // ----- IDENTIDADE: quando dois itens sao "o mesmo"? -----
    // Object.equals() compara ENDERECO: dois objetos so sao iguais se forem
    // o mesmo objeto. Para o acervo, dois itens sao o mesmo quando tem o
    // mesmo tipo, o mesmo titulo e o mesmo ano. A receita tem quatro passos.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {                              // 1) mesmo objeto: igual
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;                                // 2) nulo ou tipo diferente
        }
        ItemAcervo outro = (ItemAcervo) obj;             // 3) agora o cast e seguro
        return titulo.equals(outro.titulo)               // 4) compara os atributos
                && anoPublicacao == outro.anoPublicacao;
    }

    // REGRA: quem sobrescreve equals() sobrescreve hashCode() com os MESMOS
    // atributos. Objetos iguais devem ter o mesmo hash — HashMap depende disso.
    @Override
    public int hashCode() {
        return Objects.hash(titulo, anoPublicacao);
    }

    // ----- ORDEM NATURAL: Comparable -----
    // Devolve negativo se este item vem ANTES do outro, zero se empatam
    // e positivo se vem DEPOIS. E o que Collections.sort() consulta.
    @Override
    public int compareTo(ItemAcervo outro) {
        int porTitulo = titulo.compareTo(outro.titulo);
        if (porTitulo != 0) {
            return porTitulo; // titulos diferentes decidem
        }
        return Integer.compare(anoPublicacao, outro.anoPublicacao); // desempate
    }

    // Metodo static: chamado pela CLASSE -> ItemAcervo.getTotalItens()
    public static int getTotalItens() {
        return totalItens;
    }

    // Titulo e identidade do item: getter, sem setter.
    public String getTitulo() {
        return titulo;
    }

    // Ano compoe a identidade do item: getter, sem setter (decisao da Aula 5).
    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public StatusItem getStatus() {
        return status;
    }
}
