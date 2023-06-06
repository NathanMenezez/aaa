package br.com.vita.api.usuarios.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vita.api.usuarios.ConnectionFactory;
import br.com.vita.api.usuarios.model.UsuarioModel;

@Repository
public class UsuarioRepository {

    @Autowired
    ConnectionFactory conn;

    public boolean existsByCPF(String cpf) {
        Connection connection = conn.recoveryConnection();
        String sql = "SELECT EXISTS(SELECT 1 FROM usuarios WHERE cpf = ?);";
        Boolean exists = false;
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, cpf);
            ResultSet set = pr.executeQuery();
            set.next();
            exists = set.getBoolean(1);
            pr.close();
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return exists;
    }

    public void save(UsuarioModel usuarioModel) {
        Connection connection = conn.recoveryConnection();
        String sql = "INSERT INTO usuarios (CPF, nome, email, telefone, endereco) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, usuarioModel.getCpf());
            pr.setString(2, usuarioModel.getNome());
            pr.setString(3, usuarioModel.getEmail());
            pr.setString(4, usuarioModel.getTelefone());
            pr.setString(5, usuarioModel.getEndereco());
            pr.execute();
            pr.close();
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public UsuarioModel findByCpf(String cpf) {
        Connection connection = conn.recoveryConnection();
        UsuarioModel usuario = null;
        String sql = "SELECT * FROM usuarios WHERE cpf = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet result = ps.executeQuery();
            result.next();
            Integer id = result.getInt(1);
            String resultCpf = result.getString(3);
            String nome = result.getString(2);
            String email = result.getString(4);
            String telefone = result.getString(5);
            String endereco = result.getString(6);

            usuario = new UsuarioModel(id, nome, resultCpf, email, telefone, endereco);
            ps.close();
            result.close();
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return usuario;
    }

    public void deleteByCpf(String cpf) {
        Connection connection = conn.recoveryConnection();
        String sql = "DELETE FROM USUARIOS WHERE CPF = ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void update(UsuarioModel usuarioModel) {
        Connection connection = conn.recoveryConnection();
        String sql = "UPDATE usuarios SET nome=?, email=?, telefone=?, endereco=? WHERE cpf=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuarioModel.getNome());
            ps.setString(2, usuarioModel.getEmail());
            ps.setString(3, usuarioModel.getTelefone());
            ps.setString(4, usuarioModel.getEndereco());
            ps.setString(5, usuarioModel.getCpf());

            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
}
