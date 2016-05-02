/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.dao;

import java.util.List;

/**
 *
 * @author rafael
 * @param <T>
 */
public interface DAO<T>{
   
    public void add(T Object);
	
    public void remove(int id);
	
    public void edit(T Object, int id);
	
    public T search(int id);
    
    public List<T> list();
}
