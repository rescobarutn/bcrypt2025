/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.FRM_Login;
import vista.FRM_MantenimientoUsuarios;
import vista.FRM_MenuPrincipal;

/**
 *
 * @author Usuario
 */
public class Controlador_MenuPrincipal implements ActionListener{
    
    ConexionBD conexion;
    FRM_Login frm_Login;    
    FRM_MenuPrincipal frm_MenuPrincipal;
    FRM_MantenimientoUsuarios frm_MantenimientoUsuarios;
    
    public Controlador_MenuPrincipal(FRM_MenuPrincipal frm_MenuPrincipal)
    {
        this.frm_MenuPrincipal=frm_MenuPrincipal;
        conexion=new ConexionBD();
        conexion.realizarConexion();        
        //conexion.registrarUsuario(new Usuario(101110111,"Roberto Escobar","Seguridad2025."));
        //conexion.registrarUsuario(new Usuario(202220222,"Jhon Cano","Seguridad2025."));
        //conexion.registrarUsuario(new Usuario(303330333,"Miguel Chavarria","Seguridad2025."));
        frm_Login=new FRM_Login(conexion,frm_MenuPrincipal);
        frm_MantenimientoUsuarios=new FRM_MantenimientoUsuarios(conexion);
    }    
    public void actionPerformed(ActionEvent evento)
    {
        if(evento.getActionCommand().equals("Login / Cambiar Sesión"))
        {
            frm_MenuPrincipal.estadoInicial();
            frm_Login.setVisible(true);            
        }
        if(evento.getActionCommand().equals("RegistroUsuarios"))
        {
            frm_MantenimientoUsuarios.setVisible(true);
            frm_MantenimientoUsuarios.estadoInicial();
        }
        if(evento.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }
    }
    
}
