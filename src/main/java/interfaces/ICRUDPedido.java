package interfaces;

import java.util.List;
import modelos.Pedido;

public interface ICRUDPedido {

    Pedido salvar(Pedido pedido);

    void alterar(Pedido pedido);

    void deletar(int id);

    Pedido consultar(int id);

    List<Pedido> consultar();
}
