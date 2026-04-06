package com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.h2;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaRepositoryPort {

    PessoaBO salvar(PessoaBO pessoaBO);

    Optional<PessoaBO> buscarPorId(UUID id);

    List<PessoaBO> buscarTodos();

    PessoaBO atualizar(UUID id, PessoaBO pessoaBO);

    void deletar(UUID id);
}
