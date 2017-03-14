/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gametrakr.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author agsof
 */
public class TestingCases {

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testaddGame() {
        boolean flag = false;

        //Game to compare
        Game Skyrim = new Game("Skyrim", 2011, 94, 31.5, "The_Elder_Scrolls_V_Skyrim_-_Custom.jpg");

        //Game Wish list 
        GameListImplementation test = new WishList();
        test.addGame("Skyrim");

        for (int i = 0; i < test.getGames().size(); i++) {
            if (Skyrim.toString().equals(test.getGames().get(i).toString())) {
                flag = true;
                break;
            }
        }

        assertTrue(flag);

    }

    @Test
    public void testremoveGame() {
        boolean flag = false;

        //Game to compare
        Game Skyrim = new Game("Skyrim", 2011, 94, 31.5, "The_Elder_Scrolls_V_Skyrim_-_Custom.jpg");

        //Game Wish list 
        GameListImplementation test = new WishList();
        test.removeGame("Skyrim");
        for (int i = 0; i < test.getGames().size(); i++) {
            if (Skyrim.toString().equals(test.getGames().get(i).toString())) {
                flag = true;
                break;
            }
        }

        assertFalse(flag);
    }

    @Test
    public void testSortMetacritic() {
        boolean flag = true;
        //Unsorted
        GameListImplementation unsorted = new PlayingGameList();
        unsorted.addGame("Call of Duty 4");
        unsorted.addGame("Crysis 3");
        unsorted.addGame("Starcraft 2");

        GameListAbstraction metacritic = new SortMetacritic();
        metacritic.addGameListImplementation(unsorted);

        unsorted = metacritic.getData(0);

        //Sorted       
        GameListImplementation sorted = new CompletedGameList();
        sorted.addGame("Call of Duty 4");
        sorted.addGame("Starcraft 2");
        sorted.addGame("Crysis 3");

        for (int i = 0; i < unsorted.getGames().size(); i++) {
            if (!unsorted.getGames().get(i).toString().equals(sorted.getGames().get(i).toString())) {
                flag = false;
            }

        }
        assertTrue(flag);
        sorted.removeGame("Call of Duty 4");
        sorted.removeGame("Starcraft 2");
        sorted.removeGame("Crysis 3");
        unsorted.removeGame("Call of Duty 4");
        unsorted.removeGame("Starcraft 2");
        unsorted.removeGame("Crysis 3");
    }

    @Test
    public void testSortName() {
        boolean flag = true;
        //Unsorted
        GameListImplementation unsorted = new PlayingGameList();
        unsorted.addGame("Starcraft 2");
        unsorted.addGame("Crysis 3");
        unsorted.addGame("Call of Duty 4");

        GameListAbstraction name = new SortName();
        name.addGameListImplementation(unsorted);

        unsorted = name.getData(0);

        //Sorted       
        GameListImplementation sorted = new CompletedGameList();
        sorted.addGame("Call of Duty 4");
        sorted.addGame("Crysis 3");
        sorted.addGame("Starcraft 2");

        for (int i = 0; i < unsorted.getGames().size(); i++) {
            if (!unsorted.getGames().get(i).toString().equals(sorted.getGames().get(i).toString())) {
                flag = false;
            }

        }
        assertTrue(flag);
        sorted.removeGame("Call of Duty 4");
        sorted.removeGame("Starcraft 2");
        sorted.removeGame("Crysis 3");
        unsorted.removeGame("Call of Duty 4");
        unsorted.removeGame("Starcraft 2");
        unsorted.removeGame("Crysis 3");
    }

    @Test
    public void testSortYear() {
        boolean flag = true;
        //Unsorted
        GameListImplementation unsorted = new PlayingGameList();
        unsorted.addGame("Call of Duty 4");
        unsorted.addGame("Crysis 3");
        unsorted.addGame("Starcraft 2");

        GameListAbstraction year = new SortYear();
        year.addGameListImplementation(unsorted);

        unsorted = year.getData(0);

        //Sorted       
        GameListImplementation sorted = new CompletedGameList();
        sorted.addGame("Crysis 3");
        sorted.addGame("Starcraft 2");
        sorted.addGame("Call of Duty 4");

        for (int i = 0; i < unsorted.getGames().size(); i++) {
            if (!unsorted.getGames().get(i).toString().equals(sorted.getGames().get(i).toString())) {
                flag = false;
            }

        }
        assertTrue(flag);
        sorted.removeGame("Call of Duty 4");
        sorted.removeGame("Starcraft 2");
        sorted.removeGame("Crysis 3");
        unsorted.removeGame("Call of Duty 4");
        unsorted.removeGame("Starcraft 2");
        unsorted.removeGame("Crysis 3");
    }

    @Test
    public void testWishListSerialization() {
        boolean flag = true;
        GameListImplementation wishList = new WishList();
        wishList = null;
        GameListImplementation test1 = new WishList();
        GameListImplementation test2 = new WishList();

        for (int i = 0; i < test2.getGames().size(); i++) {
            if (!test1.getGames().get(i).toString().equals(test2.getGames().get(i).toString())) {
                flag = false;
            }

        }
        
        assertTrue(flag);

    }

    @Test
    public void testPlayingListSerialization() {
        boolean flag = true;
        GameListImplementation playingList = new PlayingGameList();
        playingList = null;
        GameListImplementation test1 = new PlayingGameList();
        GameListImplementation test2 = new PlayingGameList();

        for (int i = 0; i < test2.getGames().size(); i++) {
            if (!test1.getGames().get(i).toString().equals(test2.getGames().get(i).toString())) {
                flag = false;
            }

        }
        
        assertTrue(flag);
    }

    @Test
    public void testCompletedListSerialization() {
        boolean flag = true;
        GameListImplementation completedList = new CompletedGameList();
        completedList = null;
        GameListImplementation test1 = new CompletedGameList();
        GameListImplementation test2 = new CompletedGameList();

        for (int i = 0; i < test2.getGames().size(); i++) {
            if (!test1.getGames().get(i).toString().equals(test2.getGames().get(i).toString())) {
                flag = false;
            }

        }
        
        assertTrue(flag);
    }

    @Test
    public void testImagePath() {
        boolean flag = false;

        GameListImplementation test = new WishList();

        test.addGame("Call of Duty 4");

        String x = test.getGames().get(0).getImagePath();
        System.out.println("ImagePath" + x);
        String y = ".jpg";
        if (x.contains(y)) {
            flag = true;
        }

        assertTrue(flag);
        test.removeGame("Call of Duty 4");
    }
    
    @Test
    public void testInDataBase() {
        GameListImplementation test = new WishList();
        test.addGame("Skyrim");
        assertTrue(GameListImplementation.found);
        
        
        
    }
}
