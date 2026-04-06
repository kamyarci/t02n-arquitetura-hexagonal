package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.request.PessoaRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.request.PessoaUpdateRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.response.PessoaResponseDTO;

public class PessoaDTOMapper {
    private PessoaDTOMapper() {
    }

    public static PessoaBO toBo(PessoaRequestDTO dto) {
        final PessoaBO bo = new PessoaBO();
        bo.setNomeCompleto(dto.getNomeCompleto());
        bo.setCpf(dto.getCpf());
        bo.setTelefone(dto.getTelefone());
        bo.setEmail(dto.getEmail());
        bo.setDataNascimento(dto.getDataNascimento());

        return bo;
    }

    public static PessoaResponseDTO toDto(PessoaBO bo) {
        final PessoaResponseDTO dto = new PessoaResponseDTO();
        dto.setId(bo.getId());
        dto.setNomeCompleto(bo.getNomeCompleto());
        dto.setCpf(bo.getCpf());
        dto.setTelefone(bo.getTelefone());
        dto.setEmail(bo.getEmail());
        dto.setDataNascimento(bo.getDataNascimento());

        return dto;
    }

    public static PessoaBO toBoUpdate(PessoaUpdateRequestDTO dto) {
        final PessoaBO bo = new PessoaBO();
        bo.setNomeCompleto(dto.getNomeCompleto());
        bo.setEmail(dto.getEmail());
        bo.setTelefone(dto.getTelefone());

        return bo;
    }
}
