/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.proyectoclaseumg;

//hola

import com.edu.umg.DTO.Persona;
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

    private Persona datosPersonaAgregar; // Para agregar
    private Persona datosPersonaModificar; // Para modificar

    private List<Persona> list;
    private DMLBdd dml = new DMLBdd();

    public AgregarPersonaUI() {
    }

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        System.out.println("Iniciando la página...");

        // Inicializar instancias separadas
        datosPersonaAgregar = new Persona();
        datosPersonaModificar = new Persona();

        listarPersonas();
    }

    public void listarPersonas(){
        System.out.println("Entrando a ver los registros");

        list = dml.getAllPersonas();
    }

    public void eliminarPersona(long idPersona) {
        if (idPersona > 0) {
            dml.deletePersona(idPersona);
            listarPersonas(); // Para actualizar la lista después de la eliminación
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Persona eliminada con éxito"));
        }
    }

    public void agregarPersonaV() {
        dml.addPersona(datosPersonaAgregar);
        listarPersonas();

        // Limpiar los campos después de agregar
        datosPersonaAgregar = new Persona();
    }

    public void prepararModificacion(Persona persona) {
        datosPersonaModificar = persona; // Cargar los datos de la persona seleccionada
    }

    public void modificarPersona() throws SQLException {
        dml.updatePersona(datosPersonaModificar); // Llama al método para actualizar los datos en la base de datos
        listarPersonas(); // Refresca la lista después de la modificación
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

    public Persona getDatosPersonaAgregar() {
        return datosPersonaAgregar;
    }

    public void setDatosPersonaAgregar(Persona datosPersonaAgregar) {
        this.datosPersonaAgregar = datosPersonaAgregar;
    }

    public Persona getDatosPersonaModificar() {
        return datosPersonaModificar;
    }

    public void setDatosPersonaModificar(Persona datosPersonaModificar) {
        this.datosPersonaModificar = datosPersonaModificar;
    }

    public List<Persona> getList() {
        return list;
    }

    public void setList(List<Persona> list) {
        this.list = list;
    }
}
