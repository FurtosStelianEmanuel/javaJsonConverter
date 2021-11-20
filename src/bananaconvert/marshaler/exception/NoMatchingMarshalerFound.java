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
public class NoMatchingMarshalerFound extends BananaException {

    public NoMatchingMarshalerFound(Class c) {
        super(String.format("No matching marshaler found for type %s", c.getName()));
    }
}
