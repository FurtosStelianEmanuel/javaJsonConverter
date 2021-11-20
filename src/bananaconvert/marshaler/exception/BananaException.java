/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.exception;

/**
 *
 * @author Manel
 */
public class BananaException extends Exception {

    public Exception rootException;

    public BananaException(String message) {
        super(message);
    }

    public BananaException(Exception rootException) {
        super(rootException.getMessage());
        super.setStackTrace(rootException.getStackTrace());
        this.rootException = rootException;
    }
}
