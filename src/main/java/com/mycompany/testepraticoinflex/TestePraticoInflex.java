package com.mycompany.testepraticoinflex;

/**
 *
 * @author thayr
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TestePraticoInflex {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, Month.MARCH, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 Remover o funcionário “João” da lista.
        funcionarios.removeIf(func -> func.getNome().equals("João"));

        // 3.3 Imprimir todos os funcionários com todas suas informações.
        System.out.println("Funcionários:");
        funcionarios.forEach(func -> System.out.println(func));

        // 3.4 Aumentar salário em 10%
        funcionarios.forEach(func -> {
            BigDecimal aumento = func.getSalario().multiply(new BigDecimal("0.10"));
            func.setSalario(func.getSalario().add(aumento));
        });

        // 3.5 Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 Imprimir os funcionários, agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(func -> System.out.println(func));
        });

        // 3.8 Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários que fazem aniversário em Outubro e Dezembro:");
        funcionarios.stream()
            .filter(func -> func.getDataNascimento().getMonth() == Month.OCTOBER || func.getDataNascimento().getMonth() == Month.DECEMBER)
            .forEach(func -> System.out.println(func));

        // 3.9 Imprimir o funcionário com a maior idade
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(func -> Period.between(func.getDataNascimento(), LocalDate.now()).getYears()));
        int idadeMaisVelho = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\nFuncionário com a maior idade: Nome: " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho);

        // 3.10 Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .forEach(func -> System.out.println(func));

        // 3.11 Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + totalSalarios.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(func -> {
            BigDecimal qtdSalariosMinimos = func.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(func.getNome() + ": " + qtdSalariosMinimos + " salários mínimos");
        });
    }
}
