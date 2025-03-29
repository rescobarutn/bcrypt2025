/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.FRM_Login;

/**
 *
 * @author Usuario
 */
public class Controlador_MenuPrincipal implements ActionListener{
    
    FRM_Login frm_Login;
    ConexionBD conexion;
    public Controlador_MenuPrincipal()
    {
        conexion=new ConexionBD();
        conexion.realizarConexion();        
        conexion.registrarUsuario(new Usuario(0,"rescobar","Seguridad2024."));
        frm_Login=new FRM_Login(conexion);
    }    
    public void actionPerformed(ActionEvent evento)
    {
        if(evento.getActionCommand().equals("Login"))
        {
            frm_Login.setVisible(true);
        }
        if(evento.getActionCommand().equals("RegistroUsuarios"))
        {
            System.out.println("RegistroUsuarios");
        }
        if(evento.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }
    }
    
}
