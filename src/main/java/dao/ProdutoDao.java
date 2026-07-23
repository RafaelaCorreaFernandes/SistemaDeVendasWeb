package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUD;
import modelos.Produto;
import utils.ConectaDB;


public class ProdutoDao implements ICRUD {

	@Override
	public Produto salvar(Produto prod) {
		String sql = "insert into tb_produtos(descricao, preco, estoque) values(?,?,?)";

		Connection con = ConectaDB.conectar();
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, prod.getDescricao());
			stm.setDouble(2, prod.getPreco());
			stm.setInt(3, prod.getEstoque());
			stm.execute();

			stm.close();
			con.close();

			return prod;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deletar(int id) {
		String sql = "delete from tb_produtos where id=?";

		try {
			Connection con = ConectaDB.conectar();

			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			stm.execute();

			stm.close();
			con.close();

			System.out.println("Produto removido");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void alterar(Produto prod) {
		System.out.println("alterando produto: " + prod.getId());

	}

	@Override
	public Produto consultar(int id) {
		Produto p = null;
		Connection con = ConectaDB.conectar();
		try {
			PreparedStatement stm = con.prepareStatement("select * from tb_produtos where id = ?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				p = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
			}
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Produto> consultar() {

		List<Produto> produtos = new ArrayList<Produto>();
		Connection con = ConectaDB.conectar();
		try {
			PreparedStatement stm = con.prepareStatement("select * from tb_produtos");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Produto p = new Produto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
				produtos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
}