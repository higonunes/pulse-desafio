package com.pulse.desafiotecnico.service;

import com.pulse.desafiotecnico.domain.*;
import com.pulse.desafiotecnico.dto.pedido.PedidoNovoDTO;
import com.pulse.desafiotecnico.enums.EstadoPedido;
import com.pulse.desafiotecnico.enums.FormaPagamento;
import com.pulse.desafiotecnico.repositories.*;
import com.pulse.desafiotecnico.security.UserLogin;
import com.pulse.desafiotecnico.service.exceptions.AuthorizationException;
import com.pulse.desafiotecnico.service.exceptions.ForaDoEstoqueException;
import com.pulse.desafiotecnico.service.exceptions.NaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PedidoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TransportadoraRepository transportadoraRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Page<Pedido> listaPedidos(Pageable pageable) {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        return pedidoRepository.findAllByCliente_Id(userLogin.getId(), pageable);
    }

    public Pedido getPedido(Long id) {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        return pedidoRepository.findAllByIdAndCliente_Id(id, userLogin.getId()).orElseThrow(
                () -> new NaoEncontradoException("Pedido não encontrado na base de dados")
        );
    }

    @Transactional
    public Pedido inserirPedido(PedidoNovoDTO pedidoDTO) {
        UserLogin userLogin = LoginService.autenticado();

        if (userLogin == null) {
            throw new AuthorizationException();
        }

        Carrinho carrinho = carrinhoRepository.findById(pedidoDTO.getIdCarrinho()).orElseThrow(
                () -> new NaoEncontradoException("Carrinho não encontrado")
        );

        Pedido pedido = new Pedido();

        pedido.setValorBruto(carrinho.getValorBruto());
        pedido.setValorFrete(carrinho.getValorFrete());
        pedido.setNumeroPedido(Math.abs(new Random().nextLong()));

        pedido.setCliente(clienteRepository.findById(userLogin.getId()).orElseThrow(
                () -> new NaoEncontradoException("Cliente não encontrado")
        ));

        if (carrinho.getEnderecoEntrega() == null) {
            throw new NaoEncontradoException("Endereço de entrega não informado no carrinho de compras passado");
        } else {
            pedido.setEnderecoEntrega(carrinho.getEnderecoEntrega());
        }

        if (carrinho.getTransportadora() == null) {
            throw new NaoEncontradoException("Transportadora não informada no carrinho de compras passado");
        } else {
            pedido.setTransportadora(carrinho.getTransportadora());
        }

        if (carrinho.getCupom() == null && carrinho.getValorDesconto() > 0.0) {
            throw new IllegalArgumentException("Erro ao aplicar desconto, não foi encontrado um cupom para justificar o desconto");
        } else {
            pedido.setCupom(carrinho.getCupom());
            pedido.setValorDesconto(carrinho.getValorDesconto());
        }

        TipoPagamento tipoPagamento = new TipoPagamento();
        if (pedidoDTO.getFormaPagamento().getId() == FormaPagamento.CARTAO.getId()) {
            Cartao cartao = cartaoRepository.findByIdAndCliente(pedidoDTO.getIdCartao(), pedido.getCliente()).orElseThrow(
                    () -> new NaoEncontradoException("Cartão não encontrado")
            );

            tipoPagamento.setFormaPagamento(pedidoDTO.getFormaPagamento());
            tipoPagamento.setCartao(cartao);
            tipoPagamento.setValorTotal(carrinho.getValorTotal());

            tipoPagamento = tipoPagamentoRepository.save(tipoPagamento);
        } else if (pedidoDTO.getFormaPagamento().getId() == FormaPagamento.BOLETO.getId()) {
            Boleto boleto = new Boleto();
            boleto.setNumero(boletoService.geraNumeroBoleto());
            boleto.setVencimento(boletoService.calculaDataVencimento());
            boleto = boletoRepository.save(boleto);

            tipoPagamento.setFormaPagamento(pedidoDTO.getFormaPagamento());
            tipoPagamento.setBoleto(boleto);
            tipoPagamento.setValorTotal(carrinho.getValorTotal());
            tipoPagamento = tipoPagamentoRepository.save(tipoPagamento);
        } else {
            throw new NaoEncontradoException("Tipo de pagamento não cadastrado");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(tipoPagamento);

        pagamento = pagamentoRepository.save(pagamento);
        pedido.setPagamento(pagamento);

        List<PedidoProduto> listaProdutos = new ArrayList<>();
        for (CarrinhoProduto p : carrinho.getCarrinhoProdutos()) {
            Produto pTemp = produtoRepository.findById(p.getProduto().getId()).orElseThrow(
                    () -> new NaoEncontradoException("Produto não encontrado")
            );
            if (pTemp.getQuantidadeEstoque() - p.getQuantidade() < 0) {
                throw new ForaDoEstoqueException(pTemp.getNome().concat(" não está mais disponível na quantidade solicitada"));
            }

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setProduto(pTemp);
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setQuantidade(p.getQuantidade());
            pedidoProduto.setPrecoPago(pTemp.getPreco() * p.getQuantidade());
            listaProdutos.add(pedidoProduto);

            pTemp.setQuantidadeEstoque(pTemp.getQuantidadeEstoque() - p.getQuantidade());
            produtoRepository.save(pTemp);
        }
        pedidoProdutoRepository.saveAll(listaProdutos);

        pedido.setProdutos(listaProdutos);
        pedido.setEstadoPedido(EstadoPedido.ABERTO);
        carrinhoRepository.delete(carrinho);
        return pedidoRepository.save(pedido);
    }


}
