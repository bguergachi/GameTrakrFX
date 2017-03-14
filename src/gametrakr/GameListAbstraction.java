/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * OverView: This class is used to list games based on implemented sorting
 * method
 *
 * <p>
 * Abstract function: AF(c) = a list of games lists, lg, such that
 * lg.list[].getGames = c.listsOfGameLists
 * <p>
 * Rep invariant: for all i, list[i] instanceof GameListImplementation
 *
 * @author agsof
 */
public abstract class GameListAbstraction {

    /**
     * The List of game lists
     */
    protected List<GameListImplementation> list = new ArrayList<>();

    /**
     * EFFECT: Adds a GameListImplementation to the list in this class MODIFIES:
     * The list of GameListImplementation in this class
     *
     * @param gameImp
     */
    public void addGameListImplementation(GameListImplementation gameImp) {
        list.add(gameImp);

        if (!repOk()) {
            throw new IllegalArgumentException("The list is not propperly instant instantiated");
        }
    }

    /**
     * <p>
     * EFFECT: Return a GameListImplementation of choice by parameter index in a
     * sort method of choice
     * <p>
     * MODIFIES: The GameListImplementation specified by the user using the
     * parameter index
     *
     * @param index
     * @return
     */
    public abstract GameListImplementation getData(int index);

    /**
     * <p>
     * EFFECT: Sorts the GameListImplementation passed
     * <p>
     * MODIFIES: The GameListImplementation passed
     *
     * @param games
     */
    protected abstract void sort(GameListImplementation games);

    /**
     * EFFECT: Returns String of the whole lists of of game lists
     *
     * @return
     */
    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < list.size(); i++) {
            text += "List " + i + "\n" + list.get(i) + "\n";
        }
        return text;
    }

    /**
     * EEFECT: Checks if the rep invariant is maintained
     *
     * @return
     */
    public boolean repOk() {
        if(list != null){
            for (int i = 0; i < list.size(); i++) {
            if (!(list.get(i) instanceof GameListImplementation)) {
                return false;
            }
        }
        } else{
            return true;
        }
        

        return true;
    }

}
