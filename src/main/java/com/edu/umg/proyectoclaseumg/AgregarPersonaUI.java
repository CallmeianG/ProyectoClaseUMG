/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.proyectoclaseumg;

//hola

import com.edu.umg.DTO.PersonaDTO;
import com.edu.umg.bdd.DMLBdd;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "bkn_indexUI")
@ViewScoped
public class AgregarPersonaUI implements Serializable {

    private String nombre;
    private String apellido;
    private Number telefono;
    private String correo;
    private Number estado;

    private PersonaDTO datosPersonaAgregar; // Para agregar
    private PersonaDTO datosPersonaModificar; // Para modificar

    private List<PersonaDTO> list;
    private DMLBdd dml = new DMLBdd();

    public AgregarPersonaUI() {
    }

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        System.out.println("Iniciando la página...");

        // Inicializar instancias separadas
        datosPersonaAgregar = new PersonaDTO();
        datosPersonaModificar = new PersonaDTO();

        try {
            listarPersonas();
        } catch (SQLException ex) {
            Logger.getLogger(AgregarPersonaUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarPersonas() throws SQLException {
        System.out.println("Entrando a ver los registros");

        try {
            list = dml.listaPersona();
        } catch (SQLException ex) {
            System.out.println("Error al realizar la consulta: " + ex);
        }
    }

    public void eliminarPersona(int idPersona) {
        if (idPersona > 0) {
            try {
                dml.eliminarPersona(idPersona);
                listarPersonas(); // Para actualizar la lista después de la eliminación
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Persona eliminada con éxito"));
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar la persona"));
                Logger.getLogger(AgregarPersonaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void agregarPersonaV() {
        dml.agrgarPersona(datosPersonaAgregar);
        try {
            listarPersonas();
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error: " + ex);
        }

        // Limpiar los campos después de agregar
        datosPersonaAgregar = new PersonaDTO();
    }

    public void prepararModificacion(PersonaDTO persona) {
        datosPersonaModificar = persona; // Cargar los datos de la persona seleccionada
    }

    public void modificarPersona() throws SQLException {
        dml.modificarPersona(datosPersonaModificar); // Llama al método para actualizar los datos en la base de datos
        try {
            listarPersonas(); // Refresca la lista después de la modificación
        } catch (SQLException ex) {
            System.out.println("Ocurrió un error al listar las personas: " + ex);
        }
    }

    // Getters y setters para cada campo e instancia

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Number getTelefono() {
        return telefono;
    }

    public void setTelefono(Number telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Number getEstado() {
        return estado;
    }

    public void setEstado(Number estado) {
        this.estado = estado;
    }

    public PersonaDTO getDatosPersonaAgregar() {
        return datosPersonaAgregar;
    }

    public void setDatosPersonaAgregar(PersonaDTO datosPersonaAgregar) {
        this.datosPersonaAgregar = datosPersonaAgregar;
    }

    public PersonaDTO getDatosPersonaModificar() {
        return datosPersonaModificar;
    }

    public void setDatosPersonaModificar(PersonaDTO datosPersonaModificar) {
        this.datosPersonaModificar = datosPersonaModificar;
    }

    public List<PersonaDTO> getList() {
        return list;
    }

    public void setList(List<PersonaDTO> list) {
        this.list = list;
    }
}
