/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * <P>Overview: This class is an implementation of the game lists. It represents
 *              the list of games that the user wishes to own. The rep of this 
 *              class is no different than GameListImplementation's rep.
 * @author agsof
 */
public class WishList extends GameListImplementation {

    /**
     * EFFECT: Creates a list of games from previously saved serilaized data. 
     * MODIFIES: The inherited list of games.
     */
    public WishList() {
        super("wish.ser");

    }

    /**
     * EFFECT: Creates a file with the serilaized data of the object games,
     *         which is the list of games the user wishes to own
     * MODIFIES: If there is an original file, it file will be over written with
     *           the new serilaized data
     */
    @Override
    protected void serialization() {
         
        try{
         FileOutputStream fos= new FileOutputStream("wish.ser");
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(getGames);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}
