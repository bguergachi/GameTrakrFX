/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * OverView: This class is used to implement concrete Game lists. Games can be
 * added or removed from its list
 *
 *
 * <p>
 * Abstract function: AF(c) = a list, l, such that l.games = c.AllGamesInList
 *
 * <p>
 * Rep invariant: all objects in l.games is instance of Game
 *
 * @author agsof
 */
public abstract class GameListImplementation {

    /**
     * A static boolean flag turned true when a game is found in the game
     * database
     */
    public static boolean found = false;

    /**
     * The List containing all the games of the concrete list
     */
    protected List<Game> getGames = new ArrayList<>();

    /**
     * <p>
     * EFFECT: Deserializes data from a file and instantiates a new list of
     * games
     * <p>
     * MODIFIES: The arraylist of games, called games
     *
     * @param file
     */
    public GameListImplementation(String file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            getGames = (ArrayList<Game>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("ArrayList<Game> class not found");
            c.printStackTrace();
            return;
        }

        if (!repOk()) {
            throw new IllegalArgumentException("The list is not propperly instant instantiated");
        }
    }

    /**
     *
     */
    protected abstract void serialization();

    /**
     * <p>
     * EFFECT: Adds a game to the game list
     * <p>
     * MODIFFIES: The game list, called games, and rewrites the serialized data
     *
     * @param name
     */
    public void addGame(String name) {
        found = false;
        Scanner txtscan;
        double year;
        int metacritic;
        double completionTime;
        String imagePath;
        try {
            txtscan = new Scanner(new File("Database.txt"));

            while (txtscan.hasNextLine()) {
                String temp = "";
                temp = txtscan.nextLine();
                if (!name.equalsIgnoreCase(temp)) {
                    txtscan.nextLine();
                    txtscan.nextLine();
                    txtscan.nextLine();
                    txtscan.nextLine();
                } else {
                    found = true;
                    getGames.add(new Game(temp,
                            Integer.parseInt(txtscan.nextLine()),
                            Double.parseDouble(txtscan.nextLine()),
                            Double.parseDouble(txtscan.nextLine()),
                            txtscan.nextLine()));
                    break;
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameListImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        serialization();

    }

    /**
     * <p>
     * EFFECT: Removes game from the games list
     * <p>
     * MODIFFIES: The game list,games, and rewrites the serialized data
     *
     * @param name
     */
    public void removeGame(String name) {
        for (Game g : getGames) {
            if (g.getName().equalsIgnoreCase(name)) {
                getGames.remove(g);
                break;
            }
        }
        serialization();
    }

    /**
     * Returns list of games
     *
     * @return
     */
    public List<Game> getGames() {
        return getGames;
    }

    /**
     * <p>
     * EFFECT: returns the list of games as a string
     *
     * @return
     */
    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < getGames.size(); i++) {
            text += "Game " + i + "\n" + getGames.get(i) + "\n";
        }
        return text;
    }

    /**
     * EFFECT: Returns ture if rep invariant is not false
     *
     * @return
     */
    public boolean repOk() {
        if (getGames != null) {
            for (int i = 0; i < getGames.size(); i++) {
                if (!(getGames.get(i) instanceof Game)) {
                    return false;
                }
            }
        } else{
            return true;
        }

        return true;
    }

}
