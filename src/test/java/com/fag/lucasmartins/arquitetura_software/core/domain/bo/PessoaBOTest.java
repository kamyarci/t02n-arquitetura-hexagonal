package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaBOTest {

    private PessoaBO pessoaValida() {
        PessoaBO bo = new PessoaBO();
        bo.setNomeCompleto("Kamyla teste");
        bo.setCpf("12345678910");
        bo.setDataNascimento(LocalDate.of(1974, 6, 7));
        bo.setEmail("kamyla@teste.fag.edu.br");
        bo.setTelefone("45999999999");
        return bo;
    }

    @Test
    void deveSalvarPessoaValida() {
        assertDoesNotThrow(() -> pessoaValida().validarDados());
    }

    @Test
    void deveRejeitarMenorDeIdade() {
        PessoaBO bo = pessoaValida();
        bo.setDataNascimento(LocalDate.now().minusYears(17));
        assertThrows(DomainException.class, bo::validarDados);
    }

    @Test
    void deveRejeitarCpfInvalido() {
        PessoaBO bo = pessoaValida();
        bo.setCpf("123");
        assertThrows(DomainException.class, bo::validarDados);
    }

    @Test
    void deveRejeitarEmailInvalido() {
        PessoaBO bo = pessoaValida();
        bo.setEmail("emailteste");
        assertThrows(DomainException.class, bo::validarDados);
    }

    @Test
    void deveRejeitarTelefoneInvalido() {
        PessoaBO bo = pessoaValida();
        bo.setTelefone("4593");
        assertThrows(DomainException.class, bo::validarDados);
    }

    @Test
    void deveAtualizarComEmailValido() {
        PessoaBO bo = new PessoaBO();
        bo.setEmail("novoemail@fag.edu.br");
        assertDoesNotThrow(bo::validarDadosUpdate);
    }

    @Test
    void deveRejeitarEmailInvalidoNoUpdate() {
        PessoaBO bo = new PessoaBO();
        bo.setEmail("emailtestes");
        assertThrows(DomainException.class, bo::validarDadosUpdate);
    }

    @Test
    void deveRejeitarTelefoneInvalidoNoUpdate() {
        PessoaBO bo = new PessoaBO();
        bo.setTelefone("123");
        assertThrows(DomainException.class, bo::validarDadosUpdate);
    }

    @Test
    void deveAtualizarComTelefoneValido() {
        PessoaBO bo = new PessoaBO();
        bo.setTelefone("45999999999");
        assertDoesNotThrow(bo::validarDadosUpdate);
    }
}

