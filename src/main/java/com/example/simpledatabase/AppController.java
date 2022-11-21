/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.simpledatabase;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EKSEMPEL på tilgang til database på localhost
 * @author  EK 20.11.2022
 */
public class AppController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Nu kalder vi LibDB og sender en sql-kommando afsted");

        // Sæt forbindelsen op.
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("LibDB");
        ds.setUser("sa");
        ds.setPassword("U6u7U8u5yY");
        ds.setPortNumber(1433);
        ds.setServerName("localhost");
        //ds.setEncrypt();
        // Bemærk: der skal være secure connection
        ds.setTrustServerCertificate(true);  // tilføjet EK 4.11.2022

        int antalRækker = 0;                // Tæl antal rækker vi henter

        // Selve kaldet er "checked", dvs der skal være exception handling.
        try(Connection con = ds.getConnection())
        {
            // Kaldet sendes afsted i forbindelse med et "ResultSet", der kan håndtere resultatet.
            // ResultSet bliver kun fyldt op ved at gå det igennem skridtvis med "next()"
            String sql = "SELECT * FROM Book";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //Her går vi ResultSet igennem og tager række efter række ud:
            while(rs.next()){
                // Vi gennem det bare i foreløbige felter, men vi kunne jo have gemt dem i objekter eller et array.
                String isbn     = rs.getString("Isbn");
                String title    = rs.getString("Title");
                String type     = rs.getString("Type");

                // Skriv resultatet ud og tæl antal rækker
                System.out.println(isbn + ", " + title + ", " + type);
                ++antalRækker;
            }
        }
        // Exception handling: skriv fejlen ud og log den
        catch (SQLServerException sqlse)
        {
            System.out.println(sqlse);
        }
        catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Sæt besked i GUI
        label.setText("Der blev hentet " + antalRækker + " rækker");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}