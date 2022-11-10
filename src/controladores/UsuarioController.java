package controladores;

import ferramentas.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Usuario;

/**
 *
 * @author jonas
 */
public class UsuarioController {
    
    public boolean login(String user, String pass)
    {
        try {
            Conexao.abreConexao();
            ResultSet rs = null;
            PreparedStatement stmt;

            String wSql = "";
            wSql = " SELECT nome ";
            wSql += " FROM usuarios ";
            wSql += " WHERE usuario = ? ";
            wSql += " AND senha = md5(?) ";

            try{
                System.out.println("Vai Executar Conexão em buscar Usuario");
                stmt = Conexao.con.prepareStatement(wSql);
                stmt.setString(1, user);
                stmt.setString(2, pass);

                rs = stmt.executeQuery();
                
                return rs.next();
                
            }catch (SQLException ex )
            {
                System.out.println("ERRO de SQL: " + ex.getMessage().toString());
                return false;
            }

        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage().toString());
            return false;
        }
		
    }
    
    public boolean incluir(Usuario objUsuario){
        
        try {
            Conexao.abreConexao();
            PreparedStatement stmt = null;

            stmt = Conexao.con.prepareStatement("INSERT INTO usuarios (nome, usuario, senha, telefone) VALUES(?,?,?,?)");
            stmt.setString(1, objUsuario.getNome());
            stmt.setString(2, objUsuario.getUsuario());            
            stmt.setString(3, objUsuario.getSenha());
            stmt.setString(4, objUsuario.getTelefone());

            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }finally{
            Conexao.closeConnection(Conexao.con);
        }
        
    }
}
