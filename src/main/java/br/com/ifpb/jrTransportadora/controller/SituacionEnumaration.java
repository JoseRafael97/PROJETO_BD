package br.com.ifpb.jrTransportadora.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
public enum SituacionEnumaration {

    //Tipos:
    INICIAL("Em espera"),
    ANDAMENTO("Em Andamento"),
    ENVIADO("Produto em Transporte"),
    CARGAENVIADA("Carga em Transporte"),
    ENTREGUE("Produto Recebido"),
    VEHICLENORMAL("Em dias, a disposição"),
    VEHICLEOCUPADO("Ocupado");

    private String nome;

    SituacionEnumaration(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
