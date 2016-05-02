/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifpb.jrTransportadora.model.validators;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.Flash;

/**
 *
 * @author rafael
 */
public class Notifications implements Serializable{
    private static Flash flash;

    public static void messageOk(String msg) {
        //messageFaces(MessagesStatus.SUCESS.getMessage(), msg);
    }

    public static void messageError(String msg) {
        //messageFaces(MessagesStatus.ERROR.getMessage(), msg);

    }

    private static void messageFaces(String type, String message) {

        //Severity severity = FacesMessage.SEVERITY_INFO;

     //   if (type.equals(MessagesStatus.ERROR.getMessage())) {
           // severity = FacesMessage.SEVERITY_ERROR;
       //     FacesContext.getCurrentInstance().validationFailed();
     //   }

      //  flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();

      //  FacesMessage fm = new FacesMessage(severity, type, message);
     //   FacesContext.getCurrentInstance().addMessage(null, fm);

    }
}
