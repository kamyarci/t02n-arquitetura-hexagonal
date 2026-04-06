package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.time.LocalDate;
import java.util.UUID;

public class PessoaBO {

    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void validarDados() {
        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new DomainException("Nome obrigatório");
        }

        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new DomainException("CPF deve ter 11 dígitos");
        }

        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now().minusYears(18))) {
            throw new DomainException("Deve ser maior de 18 anos");
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new DomainException("Email inválido");
        }

        if (telefone == null || !telefone.matches("\\d{11}")) {
            throw new DomainException("Telefone deve ter 11 dígitos");
        }
    }

    public void validarDadosUpdate() {
        if (nomeCompleto != null && nomeCompleto.isBlank()) {
            throw new DomainException("Nome não pode ser vazio");
        }
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new DomainException("Email inválido");
        }
        if (telefone != null && !telefone.matches("\\d{11}")) {
            throw new DomainException("Telefone deve ter 11 dígitos");
        }
    }

}
