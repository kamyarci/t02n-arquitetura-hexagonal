package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.controller;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.request.PessoaRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.request.PessoaUpdateRequestDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.response.PessoaResponseDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper.PessoaDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaControllerAdapter {
    private final PessoaServicePort pessoaServicePort;

    public PessoaControllerAdapter(PessoaServicePort pessoaServicePort) {
        this.pessoaServicePort = pessoaServicePort;
    }

    @PostMapping
    public ResponseEntity<PessoaResponseDTO> cadastrarPessoa(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        PessoaBO pessoaBO = PessoaDTOMapper.toBo(pessoaRequestDTO);

        PessoaBO pessoaCriadaBo = pessoaServicePort.salvar(pessoaBO);

        return ResponseEntity.status(201).body(PessoaDTOMapper.toDto(pessoaCriadaBo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> getById(@PathVariable UUID id) {
        PessoaBO bo = pessoaServicePort.buscarPorId(id);

        return ResponseEntity.ok(PessoaDTOMapper.toDto(bo));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> getAll() {
        List<PessoaResponseDTO> lista = pessoaServicePort.buscarTodos()
                .stream()
                .map(PessoaDTOMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> atualizar(@PathVariable UUID id, @RequestBody PessoaUpdateRequestDTO dto) {
        PessoaBO bo = PessoaDTOMapper.toBoUpdate(dto);
        PessoaBO atualizado = pessoaServicePort.atualizar(id, bo);
        return ResponseEntity.ok(PessoaDTOMapper.toDto(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        pessoaServicePort.deletar(id);
        return ResponseEntity.ok("Pessoa deletada com sucesso");
    }

}
