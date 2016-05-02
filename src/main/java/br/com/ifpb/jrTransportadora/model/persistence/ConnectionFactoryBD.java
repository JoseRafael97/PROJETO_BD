/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ConnectionFactoryBD implements ConnectionFactory {

    private final String url = "jdbc:mysql://localhost/Transportadora";
    private final String user = "root";
    private final String password = "27041995r";
    private Connection connection;

    @Override
    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

}
