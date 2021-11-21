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
public class SerializationException extends BananaException {

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(Exception rootException) {
        super(rootException);
    }
}
