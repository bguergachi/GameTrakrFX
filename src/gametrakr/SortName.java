/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Overview: This class is used to sort a list of games base on their name
 * alphabetically. This class extends the GameListAbstraction with no change in
 * the rep invariance and abstract function.
 *
 * @author agsof
 */
public class SortName extends GameListAbstraction {

    /**
     * EFFECT: Returns a sorted list of Games by name, alphabetically
     * MODIFIES:One of GameListImplementations stored variable list. Choose what
     * GameListImplementation by using index
     *
     * @param index
     * @return
     */
    @Override
    public GameListImplementation getData(int index) {
        //Get list of games to be sorted and sort
        GameListImplementation sorted = list.get(index);
        sort(sorted);

        return sorted;
    }

    /**
     * EFFECT: Sorts any GameListImplementation passed as gameList
     * MODIFIES:Changes the order of the GameListImplementation based on the on
     * the Game property name, alphabetically
     *
     * @param gameList
     */
    @Override
    public void sort(GameListImplementation gameList) {
        Collections.sort(gameList.getGames, new NameComparator());
    }

}
