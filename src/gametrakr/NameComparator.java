/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.util.Comparator;

/**
 * OverView: This class is only used for one method that overides the method
 * from the class Comparator.
 *
 * @author Adam
 */
public class NameComparator implements Comparator<Game> {

    @Override
    public int compare(Game a, Game b) {
        return a.getName().compareToIgnoreCase(b.getName());
    }
}
