/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.FRM_MantenimientoUsuarios;

/**
 *
 * @author Usuario
 */
public class Controlador_MantenimientoUsuarios implements ActionListener{

    ConexionBD conexion;
    FRM_MantenimientoUsuarios frm_MantenimientoUsuarios;
    
    public Controlador_MantenimientoUsuarios(FRM_MantenimientoUsuarios frm_MantenimientoUsuarios,ConexionBD conexion) {
        this.frm_MantenimientoUsuarios=frm_MantenimientoUsuarios;
        this.conexion=conexion;
    }
    
    public void actionPerformed(ActionEvent evento)
    {
        if(evento.getActionCommand().equals("Consultar"))
        {
            if(conexion.consultarUsuario(frm_MantenimientoUsuarios.devolverIDUsuario()))
            {
                frm_MantenimientoUsuarios.mostrarInformacion(conexion.getUsuarioConsultado());
                frm_MantenimientoUsuarios.habilitarOpcionesModificarEliminar();
            }
            else
            {
                if(frm_MantenimientoUsuarios.consultarAlUsuario("La cédula consultada no corresponde a ningún usuario\n¿Desea registrarlo?"))
                {
                    frm_MantenimientoUsuarios.habilitarOpcionAgregar();
                }
                else
                {
                    frm_MantenimientoUsuarios.estadoInicial();
                }
                
            }
        }
        if(evento.getActionCommand().equals("Agregar"))
        {
            if(conexion.registrarUsuario(frm_MantenimientoUsuarios.devolverInformacion()))
            {
                frm_MantenimientoUsuarios.mostrarMensaje("Usuario registrado de forma correcta.");
                frm_MantenimientoUsuarios.estadoInicial();
            }
            else
            {
                frm_MantenimientoUsuarios.mostrarMensaje("Error al registrar usuario");
            }
        }
        if(evento.getActionCommand().equals("Modificar"))
        {
            if(frm_MantenimientoUsuarios.consultarAlUsuario("¿Está seguro(a) que desea modificar?"))
            {
                if(conexion.modificarUsuario(frm_MantenimientoUsuarios.devolverInformacion()))
                {
                    frm_MantenimientoUsuarios.mostrarMensaje("Usuario modificado de forma correcta.");
                    frm_MantenimientoUsuarios.estadoInicial();
                }
                else
                {
                    frm_MantenimientoUsuarios.mostrarMensaje("Error al modificar usuario");
                }
            }
            else
            {
                frm_MantenimientoUsuarios.estadoInicial();
            }
            
        }
        if(evento.getActionCommand().equals("Eliminar"))
        {
            if(frm_MantenimientoUsuarios.consultarAlUsuario("¿Está seguro(a) que desea eliminar?"))
            {
                if(conexion.eliminarUsuario(frm_MantenimientoUsuarios.devolverIDUsuario()))
                {
                    frm_MantenimientoUsuarios.mostrarMensaje("Usuario eliminado de forma correcta.");
                    frm_MantenimientoUsuarios.estadoInicial();
                }
                else
                {
                    frm_MantenimientoUsuarios.mostrarMensaje("Error al eliminar usuario");
                }
            }
            else
            {
                frm_MantenimientoUsuarios.estadoInicial();
            }
        }
    }
    
}
