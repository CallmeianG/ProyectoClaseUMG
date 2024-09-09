/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.umg.proyectoclaseumg.pruebas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;  // Importar la clase Base64
import org.json.simple.JSONObject;

/**
 *
 * @author Mayela
 */
public class ComsunoApi {
    public static void main(String[] args) {
        try {
            // URL del WebService
            String url = "http://localhost:8080/WSUMG/resources/WSUMGH/pruebaUMG";
            
            // Crear la conexión
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
                      
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Obtener la respuesta en Base64
            String base64Response = response.toString();
            
            // Decodificar la respuesta Base64 a un String JSON
            byte[] decodedBytes = Base64.getDecoder().decode(base64Response);
            String decodedJson = new String(decodedBytes);
            
            // Convertir el String JSON decodificado a un objeto JSON
            JSONObject jsonResponse = (JSONObject) new org.json.simple.parser.JSONParser().parse(decodedJson);
                
            // Extraer el nombre y el apellido
            String nombre = (String) jsonResponse.get("Nombre");
            String apellido = (String) jsonResponse.get("Apellido");
                
            // Mostrar solo el nombre y apellido
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
