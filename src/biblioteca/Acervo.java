package biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * O acervo da biblioteca: a colecao de todos os itens, encapsulada.
 * Substitui o vetor ItemAcervo[] do Principal — que tinha tamanho fixo
 * e nao sabia procurar, remover nem ordenar.
 * Quem usa o acervo nao toca na lista: pede pelos metodos desta classe.
 */
public class Acervo {

    // List e a INTERFACE (o contrato); ArrayList e a IMPLEMENTACAO.
    // Declarar pelo contrato e a mesma ideia de Emprestavel[] na Aula 4.
    // final: a referencia a lista nunca troca; o CONTEUDO dela muda.
    private final List<ItemAcervo> itens = new ArrayList<>();

    // ----- INSERIR E REMOVER: o que o vetor nunca soube fazer -----

    public void adicionar(ItemAcervo item) {
        itens.add(item); // a lista cresce sozinha
    }

    // remove(Object) procura com equals(): sem o equals de ItemAcervo,
    // so removeria se recebesse EXATAMENTE o mesmo objeto.
    public boolean remover(ItemAcervo item) {
        return itens.remove(item); // true se achou e removeu
    }

    // contains() tambem pergunta ao equals().
    public boolean contem(ItemAcervo item) {
        return itens.contains(item);
    }

    public int tamanho() {
        return itens.size(); // size(), nao length
    }

    // ----- BUSCA -----

    // Percorre a lista com for-each: "para cada item em itens".
    // Devolve null quando nao encontra — a Aula 6 vai trocar isso por excecao.
    public ItemAcervo buscarPorTitulo(String titulo) {
        for (ItemAcervo item : itens) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }

    // ----- ORDENACAO -----

    // Sem comparador: usa a ordem NATURAL, o compareTo() de ItemAcervo.
    public void ordenarPorTitulo() {
        Collections.sort(itens);
    }

    // Com comparador: um criterio alternativo, escrito como lambda —
    // um metodo de comparacao sem nome, passado como argumento.
    public void ordenarPorAno() {
        Comparator<ItemAcervo> porAno =
                (a, b) -> Integer.compare(a.getAnoPublicacao(), b.getAnoPublicacao());
        Collections.sort(itens, porAno);
    }

    // ----- CONTAGEM POR TIPO: o HashMap -----

    // Map associa CHAVE -> VALOR. Aqui: nome do tipo -> quantidade.
    // getOrDefault() devolve 0 na primeira vez que o tipo aparece.
    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> contagem = new HashMap<>();
        for (ItemAcervo item : itens) {
            String tipo = item.getTipo();
            contagem.put(tipo, contagem.getOrDefault(tipo, 0) + 1);
        }
        return contagem;
    }

    // ----- CONTAGEM POR STATUS (desafio-05): chave e o proprio enum -----
    public Map<StatusItem, Integer> contarPorStatus() {
        Map<StatusItem, Integer> contagem = new HashMap<>();
        for (ItemAcervo item : itens) {
            StatusItem status = item.getStatus();
            contagem.put(status, contagem.getOrDefault(status, 0) + 1);
        }
        return contagem;
    }

    // ----- ITENS DISPONIVEIS (desafio-05): copia filtrada, acervo nao muda -----
    public List<ItemAcervo> listarDisponiveis() {
        List<ItemAcervo> disponiveis = new ArrayList<>();
        for (ItemAcervo item : itens) {
            if (item.estaDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }

    // ----- ACESSO A LISTA -----

    // Copia defensiva: quem receber a lista pode mexer nela a vontade,
    // sem alterar o acervo. Devolver "itens" direto furaria o encapsulamento.
    public List<ItemAcervo> getItens() {
        return new ArrayList<>(itens);
    }

    // Uma linha por item, na ordem atual da lista.
    public void exibirResumo() {
        for (ItemAcervo item : itens) {
            System.out.println(item + " | " + item.calcularPrazoDevolucao() + " dias");
        }
    }
}
