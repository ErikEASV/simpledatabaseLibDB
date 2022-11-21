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
 *
 * @author jeppjleemoritzled / modif. EK 4.11.2022
 */
public class AppController1 implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Nu kalder vi db og sender en sql-kommando afsted");

        // Configuring the connection
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("MyShop");
        ds.setUser("CSe19B_40");
        ds.setPassword("CSe19B_40");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
        //ds.setEncrypt();
        ds.setTrustServerCertificate(true);  // tilføjet EK 4.11.2022
        /*

        try(Connection con = ds.getConnection()){
            String sql = "INSERT INTO PERSON(name, email) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "Stig");
            pstmt.setString(2, "ssi@easv.dk");
            pstmt.execute();

        } catch (SQLServerException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */

        try(Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Person ORDER BY id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int id          = rs.getInt("id");
                String name     = rs.getString("name");
                String email    = rs.getString("email");
                label.setText(name);
                System.out.println(id + ", "+ name + ", " + email);
            }
        }
        catch (SQLServerException sqlse)
        {
            System.out.println(sqlse);
        }
        catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        label.setText("Sådan!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}