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

            stmt = Conexao.con.prepareStatement("INSERT INTO usuarios (nome, usuario, senha, telefone) VALUES(?,?,md5(?),?)");
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
    
    /*public boolean alterar(){
        
        ConnectionFactory.abreConexao();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE evento SET nome=?, inicio=?, termino=?, id_palestrante=?, id_area=?, id_cidade=?, valor=? WHERE id=?");
            stmt.setString(1, objEvento.getNome());
            stmt.setString(2, objEvento.getInicio());
            stmt.setString(3, objEvento.getTermino());
            stmt.setInt(4, objEvento.getId_palestrante());
            stmt.setInt(5, objEvento.getId_area());
            stmt.setInt(6, objEvento.getId_cidade());
            stmt.setDouble(7, objEvento.getValor());
            stmt.setInt(8, objEvento.getId());
            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public boolean excluir(){
        
        ConnectionFactory.abreConexao();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM evento WHERE id=?");
            stmt.setInt(1, objEvento.getId());//1º?
                        
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }*/
}
