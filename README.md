# UBM Biblioteca

Projeto do Laboratório de Programação Orientada a Objetos (UBM, 2026/2) —
Prof. Dr. Rubens dos Santos Guimarães.

Sistema de acervo de uma biblioteca (livros, revistas, mídias e jornais),
usado ao longo das aulas para praticar os pilares de POO em Java: abstração,
herança, polimorfismo, interfaces, coleções e ordenação.

## Estado atual: Aula 5 + desafio-05

- **`ItemAcervo`** — superclasse abstrata de todo item do acervo.
  Implementa `Emprestavel` e `Comparable<ItemAcervo>`. Tem `equals`/`hashCode`
  (identidade por tipo + título + ano) e ordem natural por título (desempate
  pelo ano).
- **`Livro`, `Revista`, `Midia`, `Jornal`** — subclasses concretas de
  `ItemAcervo`, cada uma com seu prazo de devolução e atributos próprios.
- **`Emprestavel`** — contrato (interface) de tudo que a biblioteca empresta:
  itens do acervo, salas de estudo e notebooks.
- **`SalaEstudo`, `Notebook`** — emprestáveis que não são itens do acervo.
- **`StatusItem`** — enum com as situações possíveis de um emprestável.
- **`Usuario`** — quem toma emprestado; identidade por matrícula
  (`equals`/`hashCode`, desafio-05).
- **`Acervo`** — encapsula a coleção de itens (`List<ItemAcervo>`): adicionar,
  remover, buscar por título, ordenar (ordem natural e por `Comparator`),
  contar por tipo (`HashMap`) e por status (desafio-05), listar disponíveis
  (desafio-05). Sempre devolve cópias defensivas da lista interna.
- **`Principal`** — programa de demonstração: monta o acervo, os usuários e
  os empréstimos, e exercita cada um dos recursos acima.

## Como rodar

Requer JDK 21.

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out biblioteca.Principal
```

## Estrutura

```
src/biblioteca/
├── Acervo.java
├── Emprestavel.java
├── ItemAcervo.java
├── Jornal.java
├── Livro.java
├── Midia.java
├── Notebook.java
├── Principal.java
├── Revista.java
├── SalaEstudo.java
├── StatusItem.java
└── Usuario.java
```

## Tags

- `aula-05` — estado da entrega da Aula 5.
- `desafio-05` — inclui identidade de `Usuario`, `Acervo.listarDisponiveis()`
  e `Acervo.contarPorStatus()`.
