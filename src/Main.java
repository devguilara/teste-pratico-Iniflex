import classes.funcionario;

import java.util.Comparator;
import java. util. stream. Collectors;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        List<funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("*******************************************************************");

        System.out.println("Funcionários:");
        funcionarios.forEach(funcionario -> {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.formatDataNascimento());
            System.out.println("Salário: R$" + funcionario.formatSalario());
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        });


        BigDecimal percentualAumento = new BigDecimal("10.00");
        funcionarios.forEach(funcionario -> funcionario.AumentoSalario(percentualAumento));


        System.out.println("*******************************************************************");

        Map<String, List<funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(funcionario::getFuncao));

        System.out.println("Funcionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, listafuncionarios) -> {
            System.out.println("Função: " + funcao);
            listafuncionarios.forEach(funcionario -> System.out.println(funcionario.getNome()));
            System.out.println();
        });


        System.out.println("*******************************************************************");

        List<funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .toList();


        System.out.println("Funcionários que fazem aniversário em outubro e dezembro:");
        aniversariantes.forEach(funcionario -> System.out.println(funcionario.getNome()));


        System.out.println("*******************************************************************");

        funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(funcionario -> funcionario.getDataNascimento()))
                .orElse(null);

        if (maisVelho != null) {
            long idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
            System.out.println("Funcionário mais velho:");
            System.out.println("Nome: " + maisVelho.getNome());
            System.out.println("Idade: " + idade + " anos");
        }


        System.out.println("*******************************************************************");

        System.out.println("Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(funcionario::getNome))
                .forEach(funcionario -> System.out.println(funcionario.getNome()));

        System.out.println("*******************************************************************");

        BigDecimal totalSalarios = funcionarios.stream()
                .map(funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários dos funcionários: R$" + totalSalarios);

        System.out.println("Salários mínimos que cada funcionário ganha:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(new BigDecimal("1212.00"), 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });
    }

}