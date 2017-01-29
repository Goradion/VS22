/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteilteAnzeigetafel;

/**
 *
 * @author am
 */
public class TafelException extends Exception{
	private static final long serialVersionUID = 6227242422580153267L;

	public TafelException(String message){
        super(message);
    }
}
