package br.com.ecotrilha.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtil {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static String lerTexto(String rotulo, String atual) {
        while (true) {
            System.out.print(atual == null ? rotulo + ": " : rotulo + " [" + atual + "]: ");
            String valor = SCANNER.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            if (atual != null) {
                return atual;
            }
            System.out.println("Campo obrigatório.");
        }
    }

    public static String lerTexto(String rotulo) {
        return lerTexto(rotulo, null);
    }

    public static String lerOpcional(String rotulo, String atual) {
        System.out.print(rotulo + " [" + (atual == null ? "opcional" : atual) + "]: ");
        String valor = SCANNER.nextLine().trim();
        return valor.isEmpty() ? atual : valor;
    }

    public static int lerInteiro(String rotulo, Integer atual) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(rotulo, atual == null ? null : atual.toString()));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    public static int lerInteiro(String rotulo) {
        return lerInteiro(rotulo, null);
    }

    public static double lerDecimal(String rotulo, Double atual) {
        while (true) {
            try {
                return Double.parseDouble(lerTexto(rotulo, atual == null ? null : atual.toString()).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (ex.: 12,5).");
            }
        }
    }

    public static double lerDecimal(String rotulo) {
        return lerDecimal(rotulo, null);
    }

    public static LocalDate lerData(String rotulo, LocalDate atual) {
        while (true) {
            try {
                return LocalDate.parse(lerTexto(rotulo + " (dd/mm/aaaa)", atual.format(FORMATO_DATA)), FORMATO_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida.");
            }
        }
    }

    public static String escolher(String rotulo, List<String> opcoes, String atual) {
        System.out.println(rotulo + ":");
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + opcoes.get(i));
        }
        Integer padrao = atual != null && opcoes.contains(atual) ? opcoes.indexOf(atual) + 1 : null;
        while (true) {
            int escolha = lerInteiro("Opção", padrao);
            if (escolha >= 1 && escolha <= opcoes.size()) {
                return opcoes.get(escolha - 1);
            }
            System.out.println("Opção inválida.");
        }
    }

    public static String escolher(String rotulo, List<String> opcoes) {
        return escolher(rotulo, opcoes, null);
    }

    public static boolean confirmar(String pergunta) {
        return lerTexto(pergunta + " (S/N)").equalsIgnoreCase("S");
    }
}
