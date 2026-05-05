package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventItemDTO;

import java.util.ArrayList;
import java.util.List;

public class SqsPedidoEventDTOMapper {

    private SqsPedidoEventDTOMapper() {
    }

    public static PedidoBO toBo(PedidoEventDTO evento) {
        final PedidoBO bo = new PedidoBO();

        final PessoaBO pessoaBO = new PessoaBO();
        pessoaBO.setId(evento.getCustomerId());
        bo.setPessoa(pessoaBO);

        bo.setCep(evento.getZipCode());

        final List<PedidoProdutoBO> itens = new ArrayList<>();
        for (PedidoEventItemDTO item : evento.getOrderItems()) {
            final ProdutoBO produtoBO = new ProdutoBO();
            produtoBO.setId(item.getSku());

            final PedidoProdutoBO itemBO = new PedidoProdutoBO();
            itemBO.setProduto(produtoBO);
            itemBO.setQuantidade(item.getAmount());

            itens.add(itemBO);
        }
        bo.setItens(itens);

        return bo;
    }
}
