package modelos;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDate data;
    private String status;
    private List<Produto> produtos;

    public Pedido() {
    	produtos = new ArrayList<>();
    }

    public Pedido(Cliente cliente, LocalDate data,
                  String status, List<Produto> produtos) {
        this.cliente = cliente;
        this.data = data;
        this.status = status; //IF PRODUTOS NOT NULL - USE LISTA PRODUTOS - ELSE - NEW LIST
        this.produtos = produtos != null ? produtos : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void adicionarCarrinho(Produto produto) {
    	if(produtos == null) {
    		produtos = new ArrayList<>();
    	}
    	
    	produtos.add(produto);
    }
    
    public void removerCarrinho(int  idProduto) {
    	if (produtos == null) {
    		produtos.remove(idProduto);
    }
    }
    
    public boolean finalizarPedido() {

        if (cliente == null) {
            System.out.println("Nenhum cliente informado.");
            return false;
        }

        if (produtos == null || produtos.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return false;
        }

        
        status = "FINALIZADO";
        return true;
    }


    
}