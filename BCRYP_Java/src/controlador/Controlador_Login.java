package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.FRM_Login;
import vista.FRM_MenuPrincipal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Controlador_Login implements ActionListener{
    
    private ConexionBD conexion;
    private FRM_Login frm_Login;
    private FRM_MenuPrincipal frm_MenuPrincipal;
    
    public Controlador_Login(FRM_Login frm_Login,ConexionBD conexion,FRM_MenuPrincipal frm_MenuPrincipal)
    {
        this.frm_Login=frm_Login;
        this.conexion=conexion;
        this.frm_MenuPrincipal=frm_MenuPrincipal;
    }    
    public void actionPerformed(ActionEvent evento)
    {
        if(evento.getActionCommand().equals("Login"))
        {
            if(conexion.login(frm_Login.getDatosLogin()))
            {
               frm_Login.mostrarMensaje("Login Correcto");
               frm_Login.setVisible(false);
               frm_MenuPrincipal.habilitarOpciones();
               frm_MenuPrincipal.colocarNombreUsuario(conexion.getUsuarioLogueado().getNombreUsuario());
            }
            else
            {
                frm_Login.mostrarMensaje("Login Incorrecto");
            }
            frm_Login.estadoInicial();
        }
    }
}
