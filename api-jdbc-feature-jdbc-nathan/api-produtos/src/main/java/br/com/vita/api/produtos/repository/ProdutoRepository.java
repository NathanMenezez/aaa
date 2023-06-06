package br.com.vita.api.produtos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vita.api.produtos.ConnectionFactory;
import br.com.vita.api.produtos.model.ProdutoModel;

@Repository
public class ProdutoRepository {

    @Autowired
    ConnectionFactory conn;

    public Boolean existsByNome(String nome) {
        Connection connection = conn.recoveryConnection();
        String sql = "SELECT EXISTS(SELECT 1 FROM produtos WHERE nome = ?);";
        Boolean exists = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet result = ps.executeQuery();
            result.next();
            exists = result.getBoolean(1);
            result.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    public void save(ProdutoModel produtoModel) {
        Connection connection = conn.recoveryConnection();
        String sql = "INSERT INTO produtos (nome, descricao, preco, quantidade) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, produtoModel.getNome());
            ps.setString(2, produtoModel.getDescricao());
            ps.setDouble(3, produtoModel.getPreco());
            ps.setInt(4, produtoModel.getQuantidadeEstoque());
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdutoModel findByNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        Connection connection = conn.recoveryConnection();
        ProdutoModel produto = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet result = ps.executeQuery();
            result.next();

            Integer idProduto = result.getInt(1);
            String nomeProduto = result.getString(2);
            String descProduto = result.getString(3);
            Double precoProduto = result.getDouble(4);
            Integer quantidadeProduto = result.getInt(5);
            produto = new ProdutoModel(idProduto, nomeProduto, descProduto, precoProduto, quantidadeProduto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produto;
    }

    public void deleteByNome(String nome) {
        Connection connection = conn.recoveryConnection();
        String sql = "DELETE FROM produtos WHERE nome = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nome);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(ProdutoModel produto) {
        String sql = "UPDATE produtos SET nome=?, descricao=?, preco=?, quantidade=? WHERE id=?";
        Connection connection = conn.recoveryConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getQuantidadeEstoque());
            ps.setInt(5, produto.getId());
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(Integer id) {
        Connection connection = conn.recoveryConnection();
        String sql = "SELECT EXISTS(SELECT 1 FROM produtos WHERE id = ?);";
        Boolean exists = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();
            exists = result.getBoolean(1);
            result.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exists;
    }

    public ProdutoModel findById(Integer id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Connection connection = conn.recoveryConnection();
        ProdutoModel produto = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            result.next();

            Integer idProduto = result.getInt(1);
            String nomeProduto = result.getString(2);
            String descProduto = result.getString(3);
            Double precoProduto = result.getDouble(4);
            Integer quantidadeProduto = result.getInt(5);
            produto = new ProdutoModel(idProduto, nomeProduto, descProduto, precoProduto, quantidadeProduto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produto;
    }
}
