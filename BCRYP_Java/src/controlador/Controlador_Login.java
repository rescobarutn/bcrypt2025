package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.FRM_Login;

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
    
    public Controlador_Login(FRM_Login frm_Login,ConexionBD conexion)
    {
        this.frm_Login=frm_Login;
        this.conexion=conexion;
    }    
    public void actionPerformed(ActionEvent evento)
    {
        if(evento.getActionCommand().equals("Login"))
        {
            if(conexion.login(frm_Login.getDatosLogin()))
            {
               frm_Login.mostrarMensaje("Login Correcto");
               frm_Login.setVisible(false);
            }
            else
            {
                frm_Login.mostrarMensaje("Login Incorrecto");
            }
            frm_Login.estadoInicial();
        }
    }
}
