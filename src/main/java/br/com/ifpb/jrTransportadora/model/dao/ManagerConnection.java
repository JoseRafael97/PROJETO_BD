/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import br.com.ifpb.jrTransportadora.model.persistence.ConnectionFactoryBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class ManagerConnection {
    protected static Connection connection;
	
	
    public static Connection connect(){
	return connection = new ConnectionFactoryBD().getConnection();
    }
    
    public static void closeConnection(){
	try {
            connection.close();
	} catch (SQLException e) {
	
        }
    }
}
