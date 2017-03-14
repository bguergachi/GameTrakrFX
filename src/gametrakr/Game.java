/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.io.Serializable;
import java.util.Comparator;

/**
 * <p>
 * Overview: This class is used to describe the properties of a game. This is an
 * immutable class where all properties are set when instantiated.
 *
 * <p>
 * Abstract function: AF(c) = game, g, such that g.name = c.gameTitel g.year =
 * c.ReleaseYear g.metacritic = c.MetaCriticScore g.completionTime =
 * c.AverageCompletionTime g.imagePath = c.GameCover
 *
 *
 * <p>
 * Rep invariant: c.name != null and c.year greater than or equal to 1960 and
 * c.metacritic greater than or equal to 0 and c.completionTime greater than 0
 * and c.imagePath != null
 *
 *
 * @author agsof
 */
public class Game implements Serializable {

    private String name;
    private int year;
    private double metacritic;
    private double completionTime;
    private String imagePath;

    /**
     * <p>
     * EFFECT:Instantiate a Game object with its properties
     *
     * @param name
     * @param year
     * @param metacritic
     * @param completionTime
     * @param imagePath
     */
    public Game(String name, int year, double metacritic, double completionTime, String imagePath) {
        this.name = name;
        this.year = year;
        this.completionTime = completionTime;
        this.imagePath = imagePath;
        this.metacritic = metacritic;
        if (!repOk()) {
            throw new IllegalArgumentException("The data file is not propperly read");
        }
    }

    /**
     * ****************************************Getters*******************************
     */
    /**
     * Return name of Game
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return year of release of Game
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Return completion time of Game
     *
     * @return
     */
    public double getCompletionTime() {
        return completionTime;
    }

    /**
     * Return the image path of the Game cover
     *
     * @return
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Return the metaCritic score of the Game
     *
     * @return
     */
    public double getMetacritic() {
        return metacritic;
    }

    /**
     * *******************************************************************
     */
    /**
     * <p>
     * EFFECT:Returns a string as a list of all attributes of the Game
     *
     * @return
     */
    @Override
    public String toString() {
        return name + "\nYear: " + year + "\nMetaCritic: " + metacritic + "\nEstimated Time: " + completionTime + " hours";
    }

    /**
     * <p>
     * EFFECT: checks if the rep invariant is true
     *
     * @return
     */
    public boolean repOk() {
        if (name != null && year >= 1960 && completionTime > 0 && metacritic >= 0 && imagePath != null) {
            return true;
        }

        return false;
    }

}
