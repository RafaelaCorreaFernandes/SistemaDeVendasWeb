package dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUDPedido;
import modelos.Pedido;
import modelos.Produto;
import utils.ConectaDB;

public class PedidoDao implements ICRUDPedido {

    @Override
    public Pedido salvar(Pedido pedido) {
        String sqlPedido = "INSERT INTO tb_pedidos(cliente_id, data, status) VALUES(?,?,?)";
        String sqlItem = "INSERT INTO tb_pedidos_produtos(pedido_id, produto_id) VALUES(?,?)";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stmPedido = con.prepareStatement(sqlPedido,
                    Statement.RETURN_GENERATED_KEYS);

            stmPedido.setInt(1, pedido.getCliente().getId());
            stmPedido.setDate(2, Date.valueOf(pedido.getData()));
            stmPedido.setString(3, pedido.getStatus());
            stmPedido.executeUpdate();

            ResultSet rs = stmPedido.getGeneratedKeys();
            int pedidoId = 0;

            if (rs.next()) {
                pedidoId = rs.getInt(1);
            }

            PreparedStatement stmItem = con.prepareStatement(sqlItem);

            for (Produto p : pedido.getProdutos()) {
                stmItem.setInt(1, pedidoId);
                stmItem.setInt(2, p.getId());
                stmItem.executeUpdate();
            }

            stmItem.close();
            stmPedido.close();
            con.close();

            pedido.setId(pedidoId);
            return pedido;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Pedido> consultar() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM tb_pedidos";

        try {

            Connection con = ConectaDB.conectar();

            PreparedStatement stm = con.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            ClienteDao clienteDao = new ClienteDao();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setCliente(clienteDao.consultar(rs.getInt("cliente_id")));
                pedido.setData(rs.getDate("data").toLocalDate());
                pedido.setStatus(rs.getString("status"));

                pedidos.add(pedido);
            }

            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
    
    @Override
    public void alterar(Pedido pedido) {

        String sql = "UPDATE tb_pedidos SET cliente_id=?, data=?, status=? WHERE id=?";

        try {

            Connection con = ConectaDB.conectar();

            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, pedido.getCliente().getId());
            stm.setDate(2, Date.valueOf(pedido.getData()));
            stm.setString(3, pedido.getStatus());
            stm.setInt(4, pedido.getId());

            stm.executeUpdate();

            stm.close();
            con.close();

            System.out.println("Pedido alterado!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deletar(int id) {

        try {

            Connection con = ConectaDB.conectar();

            PreparedStatement stm1 = con.prepareStatement(
                    "DELETE FROM tb_pedidos_produtos WHERE pedido_id=?");
            stm1.setInt(1, id);
            stm1.executeUpdate();

            PreparedStatement stm2 = con.prepareStatement(
                    "DELETE FROM tb_pedidos WHERE id=?");
            stm2.setInt(1, id);
            stm2.executeUpdate();

            stm1.close();
            stm2.close();
            con.close();

            System.out.println("Pedido removido.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Pedido consultar(int id) {

        String sql = "SELECT * FROM tb_pedidos WHERE id=?";

        try {

            Connection con = ConectaDB.conectar();

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));

                ClienteDao clienteDao = new ClienteDao();
                pedido.setCliente(clienteDao.consultar(rs.getInt("cliente_id")));

                pedido.setData(rs.getDate("data").toLocalDate());
                pedido.setStatus(rs.getString("status"));

                stm.close();
                con.close();

                return pedido;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}