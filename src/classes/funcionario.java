package classes;


import java.math.BigDecimal;
import java.time.LocalDate;

public class funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    public funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;

    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String formatSalario() {
        return String.format("%.2f", salario);
    }

    public void AumentoSalario(BigDecimal valorPercentual) {
        BigDecimal multiplicador = BigDecimal.ONE.add(valorPercentual.divide(BigDecimal.valueOf(100)));
        salario = salario.multiply(multiplicador);
    }
}
