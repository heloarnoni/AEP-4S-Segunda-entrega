package br.com.ecotrilha.view;

import java.sql.SQLException;
import java.util.List;

public abstract class MenuCrud<T> implements Menu {

    protected abstract void cadastrar() throws SQLException;

    protected abstract List<T> listar() throws SQLException;

    protected abstract T buscar(int id) throws SQLException;

    protected abstract void editar(T item) throws SQLException;

    protected abstract void excluir(int id) throws SQLException;

    protected void mostrar(T item) {
        System.out.println(item);
    }

    @Override
    public void exibir() {
        while (true) {
            System.out.println("\n=== " + getTitulo() + " ===");
            System.out.println("1) Cadastrar\n2) Listar todos\n3) Consultar por ID\n4) Editar\n5) Excluir\n0) Voltar");
            int opcao = ConsoleUtil.lerInteiro("Opção");
            if (opcao == 0) {
                return;
            }
            try {
                executar(opcao);
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Atenção: " + e.getMessage());
            }
        }
    }

    private void executar(int opcao) throws SQLException {
        switch (opcao) {
            case 1 -> cadastrar();
            case 2 -> {
                List<T> itens = listar();
                itens.forEach(System.out::println);
                System.out.println("Total: " + itens.size());
            }
            case 3 -> mostrar(buscar(ConsoleUtil.lerInteiro("ID")));
            case 4 -> {
                T item = buscar(ConsoleUtil.lerInteiro("ID"));
                mostrar(item);
                System.out.println("Digite os novos valores (Enter mantém o atual):");
                editar(item);
                System.out.println("Registro atualizado.");
            }
            case 5 -> {
                int id = ConsoleUtil.lerInteiro("ID");
                mostrar(buscar(id));
                if (ConsoleUtil.confirmar("Confirma a exclusão?")) {
                    excluir(id);
                    System.out.println("Registro excluído.");
                }
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}
