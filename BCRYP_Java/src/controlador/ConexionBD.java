

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
    private Usuario usuarioRegistrado;
    private MD5 md5;
    
    public ConexionBD()
    {
        md5=new MD5();
    }
    public void realizarConexion()
    {
       /*Acá deben programar la conexión con la base de datos*/
    }   
    public boolean registrarUsuario(Usuario usuario)
    {
        /*Acá deben registrar el usuario en la base de datos, por ahora se hace local*/
        String resultadoBcrypt=bcrypt(usuario,10);
        System.out.println(resultadoBcrypt);
        usuarioRegistrado=new Usuario(usuario.getId(),usuario.getNombreUsuario(),resultadoBcrypt);
        return true;
    } 
    public String bcrypt(Usuario usuario, int cost)
    {
        if(cost>=1)
        {
            System.out.println("cost:"+cost);
            usuario.setContrasenia(md5.getMd5(usuario.getContrasenia()+salt(usuario)));
            bcrypt(usuario, cost-1);
        }
        return usuario.getContrasenia();
    }
    public String salt(Usuario usuario)
    {
        return md5.getMd5(usuario.getNombreUsuario());
    }
    public boolean login(Usuario usuario)
    {
        /*Acá el usuario registrado debe venir de una consulta a la base de datos*/
        if(bcrypt(usuario,10).equals(usuarioRegistrado.getContrasenia()))
        {
            return true;
        }
        else
        {
            return false;
        }        
    }  
    
}
