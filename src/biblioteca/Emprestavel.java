package biblioteca;

/**
 * Contrato de tudo que a biblioteca empresta: itens do acervo e tambem
 * espacos, como as salas de estudo. A interface descreve o que a classe
 * SABE FAZER — nao o que ela E.
 * =====================================================================
 * Quem implementa Emprestavel no projeto?
 * Grupo 1 — escrevem implements no arquivo:
 *   SalaEstudo — concreta, não estende nada
 *   Notebook — concreta, não estende nada
 * Grupo 2 — são Emprestavel sem nunca escrever a palavra:
 *   Livro, Revista, Midia, Jornal — concretas, herdam o contrato via extends ItemAcervo
 */
public interface Emprestavel {

    // Em interface, todo atributo e implicitamente public static final.
    int PRAZO_MAXIMO_DIAS = 30;

    // Metodos de interface sao implicitamente public abstract: so a assinatura.
    int calcularPrazoDevolucao();

    void emprestar(Usuario usuario);

    void devolver();

    boolean estaDisponivel();

    // Metodo default: ja vem com corpo e e herdado por quem implementa.
    default boolean dentroDaPolitica() {
        return calcularPrazoDevolucao() <= PRAZO_MAXIMO_DIAS;
    }
}
