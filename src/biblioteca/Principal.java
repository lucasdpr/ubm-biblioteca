package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {

        // for-each: "para cada situacao em values()". Sem indice, sem length,
        // sem risco de errar o limite. Serve para vetores E para colecoes.
        System.out.println("=== Situacoes possiveis ===");
        for (StatusItem situacao : StatusItem.values()) {
            System.out.println("- " + situacao.getDescricao());
        }

        // Itens do acervo. l2 agora nasce com o ano: setAnoPublicacao() saiu,
        // porque o ano passou a fazer parte da identidade do item.
        Livro l1 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro l2 = new Livro("O Cortico", "Aluisio Azevedo", 1890);
        l2.setNumeroPaginas(304);
        Livro l3 = new Livro("Memorias Postumas de Bras Cubas",
                "Machado de Assis", 1881, 368);
        Revista r1 = new Revista("Revista UBM Ciencia", 2026, 12, "Semestral");
        Midia m1 = new Midia("Central do Brasil", 1998, "DVD", 113);
        Jornal j1 = new Jornal("Jornal de Barra Mansa", 2026, "28/08/2026", "Cidades");

        // Emprestaveis que NAO sao itens do acervo: um espaco e um equipamento.
        SalaEstudo s1 = new SalaEstudo("Sala 3 - Bloco B", 6);
        Notebook n1 = new Notebook("PAT-00214", "Dell");

        Usuario u1 = new Usuario("Ana Souza", "2026101",
                "Engenharia de Software", "ana.souza@aluno.ubm.br");
        Usuario u2 = new Usuario("Bruno Lima", "2026102", "Sistemas de Informacao");
        u2.setEmail("bruno.lima@aluno.ubm.br");

        // O vetor ItemAcervo[] saiu de cena: o acervo agora e um objeto,
        // e a lista mora dentro dele.
        Acervo acervo = new Acervo();
        acervo.adicionar(l1);
        acervo.adicionar(l2);
        acervo.adicionar(l3);
        acervo.adicionar(r1);
        acervo.adicionar(m1);
        acervo.adicionar(j1);

        System.out.println("=== UBM Biblioteca - acervo ===");
        for (ItemAcervo item : acervo.getItens()) {
            item.exibirFicha();
            System.out.println("Prazo   : " + item.calcularPrazoDevolucao() + " dias");
        }

        // Membro static: pedido a CLASSE, nao a um objeto.
        System.out.println("=== Itens cadastrados: " + ItemAcervo.getTotalItens() + " ===");
        // O tamanho da lista e outra pergunta: quantos itens ESTAO no acervo.
        System.out.println("=== Itens no acervo: " + acervo.tamanho() + " ===");

        // ----- IDENTIDADE: equals() em acao -----
        // Um livro NOVO, com os mesmos dados de l1. Sem o equals de ItemAcervo,
        // contem() devolveria false: Object compara endereco, nao conteudo.
        Livro copia = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        System.out.println("=== Identidade ===");
        System.out.println("copia == l1        ? " + (copia == l1));
        System.out.println("copia.equals(l1) ? " + copia.equals(l1));
        System.out.println("acervo contem a copia? " + acervo.contem(copia));
        // Mesmo titulo, outro ano: outra edicao, outro item.
        Livro outraEdicao = new Livro("Dom Casmurro", "Machado de Assis", 2019);
        System.out.println("acervo contem a edicao de 2019? " + acervo.contem(outraEdicao));

        // ----- BUSCA E REMOCAO -----
        System.out.println("=== Busca ===");
        ItemAcervo achado = acervo.buscarPorTitulo("central do brasil");
        System.out.println("Encontrado: " + achado);
        ItemAcervo naoAchado = acervo.buscarPorTitulo("Quincas Borba");
        System.out.println("Encontrado: " + naoAchado); // null: a Aula 6 cuida disso

        System.out.println("=== Remocao ===");
        System.out.println("Removeu a copia? " + acervo.remover(copia));
        System.out.println("Itens no acervo: " + acervo.tamanho());
        // O contador static conta objetos CRIADOS (ja sao 8, com copia e
        // outraEdicao); a lista conta itens que ESTAO no acervo. Sao perguntas diferentes.
        System.out.println("Itens ja criados (static): " + ItemAcervo.getTotalItens());
        acervo.adicionar(l1); // de volta, para o resto do programa

        // ----- ORDENACAO -----
        System.out.println("=== Ordenado por titulo (ordem natural) ===");
        acervo.ordenarPorTitulo();
        acervo.exibirResumo();

        System.out.println("=== Ordenado por ano (Comparator) ===");
        acervo.ordenarPorAno();
        acervo.exibirResumo();

        // ----- CONTAGEM POR TIPO: percorrendo um Map -----
        System.out.println("=== Itens por tipo ===");
        Map<String, Integer> porTipo = acervo.contarPorTipo();
        for (String tipo : porTipo.keySet()) {
            System.out.println(tipo + ": " + porTipo.get(tipo));
        }

        // ----- USUARIOS POR MATRICULA: o Map como cadastro -----
        // A matricula existe desde a Aula 2. Agora ela vira CHAVE.
        Map<String, Usuario> usuarios = new HashMap<>();
        usuarios.put(u1.getMatricula(), u1);
        usuarios.put(u2.getMatricula(), u2);

        System.out.println("=== Cadastro de usuarios ===");
        System.out.println("Matricula 2026102 -> " + usuarios.get("2026102").getNome());
        System.out.println("Existe 2026999? " + usuarios.containsKey("2026999"));
        System.out.println("get de chave inexistente: " + usuarios.get("2026999"));

        // Metodo default da interface + constante da interface.
        System.out.println("Prazo de livro dentro da politica de "
                + Emprestavel.PRAZO_MAXIMO_DIAS + " dias? " + l1.dentroDaPolitica());

        // POLIMORFISMO POR INTERFACE, agora em LISTA: cresce com add().
        List<Emprestavel> emprestaveis = new ArrayList<>();
        emprestaveis.add(l1);
        emprestaveis.add(r1);
        emprestaveis.add(m1);
        emprestaveis.add(j1);
        emprestaveis.add(s1);
        emprestaveis.add(n1);

        System.out.println("=== Emprestimos ===");
        for (Emprestavel e : emprestaveis) {
            e.emprestar(u1);
        }
        l1.emprestar(u2); // ja esta emprestado: o status impede
        l1.devolver();

        // instanceof com padrao de tipo: so o item do acervo tem status.
        // Pela variavel e (tipo Emprestavel) so se chama o que a interface
        // declara; item (tipo ItemAcervo) alcanca getStatus().
        System.out.println("=== Situacao ===");
        for (Emprestavel e : emprestaveis) {
            if (e instanceof ItemAcervo item) {
                System.out.println(item + " | " + item.getStatus().getDescricao());
            } else {
                // Nao e item do acervo: so da para perguntar o que a interface
                // declara — e estaDisponivel() esta no contrato.
                if (e.estaDisponivel()) {
                    System.out.println(e + " | disponivel");
                } else {
                    System.out.println(e + " | em uso");
                }
            }
        }

        // ----- DESAFIO-05: identidade de Usuario -----
        Usuario copiaUsuario = new Usuario("Bruno Souza Lima", "2026102");
        System.out.println("=== Identidade de Usuario (desafio-05) ===");
        System.out.println("copiaUsuario.equals(u2)? " + copiaUsuario.equals(u2));

        // ----- DESAFIO-05: itens disponiveis -----
        System.out.println("=== Itens disponiveis (desafio-05) ===");
        for (ItemAcervo item : acervo.listarDisponiveis()) {
            System.out.println(item);
        }

        // ----- DESAFIO-05: contagem por status -----
        System.out.println("=== Itens por status (desafio-05) ===");
        Map<StatusItem, Integer> porStatus = acervo.contarPorStatus();
        for (StatusItem status : porStatus.keySet()) {
            System.out.println(status.getDescricao() + ": " + porStatus.get(status));
        }

        System.out.println("=== Usuarios ===");
        for (Usuario u : usuarios.values()) {
            u.exibirFicha();
        }
    }
}
