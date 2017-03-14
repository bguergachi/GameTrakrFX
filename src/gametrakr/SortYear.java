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
 * Overview: This class is used to sort a list of games base on their year of
 * release score. This class extends the GameListAbstraction with no change in
 * the rep invariance invariance and abstract function.
 *
 * @author agsof
 */
public class SortYear extends GameListAbstraction {

    
    /**
     *<p>
     * EFFECT: Returns a sorted list of Games by year of release
     * <p>
     * MODIFIES:One of GameListImplementations stored variable list. Choose what
     * GameListImplementation by using index
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
     * <p>
     * EFFECT: Sorts any GameListImplementation passed as gameList
     * <p>
     * MODIFIES: Changes the order of the GameListImplementation based on the on
     * the Game property year of release
     * @param gameList
     */
    @Override
    public void sort(GameListImplementation gameList) {
        Collections.sort(gameList.getGames, new YearComparator());
    }
}
