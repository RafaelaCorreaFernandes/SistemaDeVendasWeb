package interfaces;

import java.util.List;

import modelos.Cliente;

public interface ICRUDCliente {
	Cliente salvar(Cliente cliente);
	void deletar(int id);
	void alterar(Cliente cliente);
	Cliente consultar(int id);
	List<Cliente> consultar();
}
