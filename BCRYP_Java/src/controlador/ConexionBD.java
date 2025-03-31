

package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;


public class ConexionBD {
    
    Connection con = null;
    private Usuario usuarioLogueado;
    private Usuario usuarioConsultado;
    private MD5 md5;
    
    public ConexionBD()
    {
        md5=new MD5();
    }
    public void realizarConexion()
    {
       try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/bcrypt?characterEncoding=latin1&useConfigs=maxPerformance";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexion Exitosa a la BD");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } 
    } 
    public boolean consultarUsuario(int idUsuario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean encontro=false;
        try {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM bcrypt.usuario where idUsuario="+idUsuario+"");
                while (rs.next()) 
                {
                    encontro=true;
                    //System.out.println("el usuario está en la BD");
                    usuarioConsultado=new Usuario(idUsuario,rs.getString("nombreUsuario"),"Seguridad2025.");
                }
                rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return encontro;
    }
     public boolean eliminarUsuario(int idUsuario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("delete from bcrypt.usuario where idUsuario="+idUsuario+";");
                return true;
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }        
    }
    public boolean registrarUsuario(Usuario usuario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("INSERT INTO usuario (idUsuario,nombreUsuario,contraseniaUsuario) VALUES ("+usuario.getIdUsuario()+",'"+usuario.getNombreUsuario()+"','"+bcrypt(usuario,10)+"');"); 
                return true; //rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }        
    } 
    public boolean modificarUsuario(Usuario usuario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                if(usuario.getContraseniaUsuario().equals(""))
                {
                    ejecuto = cmd.execute("UPDATE `bcrypt`.`usuario` SET `nombreUsuario` = '"+usuario.getNombreUsuario()+"' WHERE (`idUsuario`="+usuario.getIdUsuario()+");");
                    
                }
                else
                {
                    ejecuto = cmd.execute("UPDATE `bcrypt`.`usuario` SET `nombreUsuario` = '"+usuario.getNombreUsuario()+"', `contraseniaUsuario` = '"+bcrypt(usuario,10)+"' WHERE (`idUsuario`="+usuario.getIdUsuario()+");");
                }                
                return true;
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }
    public String bcrypt(Usuario usuario, int cost)
    {
        if(cost>=1)
        {
            //System.out.println("cost:"+cost);
            usuario.setContraseniaUsuario(salt(usuario));
            bcrypt(usuario, cost-1);
        }
        return usuario.getContraseniaUsuario();
    }
    public String salt(Usuario usuario)
    {
        return md5.getMd5(""+usuario.getIdUsuario()+usuario.getContraseniaUsuario());
    }
    public boolean login(Usuario usuario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean login=false;
        try {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM bcrypt.usuario where idUsuario="+usuario.getIdUsuario()+"");
                while (rs.next()) 
                {
                    //System.out.println("El usuario esta en la BD");
                    usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                    
                    if( rs.getInt("idUsuario")==usuario.getIdUsuario() && rs.getString("contraseniaUsuario").equals(bcrypt(usuario,10)))
                    {
                        usuarioLogueado=new Usuario(0,rs.getString("nombreUsuario"),"");                    
                        login=true;
                    }
                }
                rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return login;        
    }  

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Usuario getUsuarioConsultado() {
        return usuarioConsultado;
    }

    public void setUsuarioConsultado(Usuario usuarioConsultado) {
        this.usuarioConsultado = usuarioConsultado;
    }
    
}
