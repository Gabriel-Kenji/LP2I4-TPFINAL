package Model;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncDAO {
    ConexaoDAO dao = new ConexaoDAO();
    Connection conn = dao.connectionDAO();

    public ResultSet getAlunos(){
        try {
            String sql = "Select * from alunos";
            PreparedStatement pstm = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            return null;
        }
    }

    public void setAluno(Aluno aluno){
        String sql = "INSERT INTO alunos(nome,idade,altura,peso,objetivo) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, aluno.nome);
            pstm.setInt(2, aluno.idade);
            pstm.setFloat(3, aluno.altura);
            pstm.setFloat(4, aluno.peso);
            pstm.setString(5, aluno.objetivo);
            int adicionado = pstm.executeUpdate();
            if (adicionado>0){
                JOptionPane.showMessageDialog(null,"Aluno adicionado com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ResultSet getFunc(String name) {
        try {
            String sql = "Select * from tb_funcs join aulajava.tb_cargos tc on tb_funcs.cd_cargos = tc.cd_cargos where nome_func LIKE ?";
            PreparedStatement pstm = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            pstm.setString(1, "%" + name + "%");
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            return null;
        }
    }
}
