/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametrakr;

import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * OverView: This class is use to display the user interface to be used by user.
 * It is an immutable class that is never meant to be instantiated, it extends
 * The Application Class.
 *
 *
 * @author Adam
 */
public class Display extends Application {

    public static boolean showingFlag = false;
    
    private Stage window;
    private Scene mainScene;

    private GameListAbstraction nameSort = new SortName();
    private GameListAbstraction yearSort = new SortYear();
    private GameListAbstraction metacriticSort = new SortMetacritic();

    private GameListImplementation wishList = new WishList();
    private GameListImplementation playingList = new PlayingGameList();
    private GameListImplementation completedList = new CompletedGameList();

    private ListView<Text> viewPlaying;
    private ObservableList<Text> playingItems;

    private ListView<Text> viewCompleted;
    private ObservableList<Text> completedItems;

    private ListView<Text> viewWish;
    private ObservableList<Text> wishItems;
    
    

    class ImageCell extends ListCell<Text> {

    }

    @Override
    public void start(Stage primaryStage) {

        nameSort.addGameListImplementation(wishList);
        nameSort.addGameListImplementation(playingList);
        nameSort.addGameListImplementation(completedList);

        yearSort.addGameListImplementation(wishList);
        yearSort.addGameListImplementation(playingList);
        yearSort.addGameListImplementation(completedList);

        metacriticSort.addGameListImplementation(wishList);
        metacriticSort.addGameListImplementation(playingList);
        metacriticSort.addGameListImplementation(completedList);

        window = primaryStage;
        window.setTitle("Game Trakr - Main Page");
        window.setResizable(false);
        window.getIcons().add(new Image("file:GT.png"));

        Pane root = new Pane();

        viewPlaying = new ListView<Text>();
        playingItems = FXCollections.<Text>observableArrayList();
        refreshListView('p');

        viewCompleted = new ListView<Text>();
        completedItems = FXCollections.observableArrayList();
        refreshListView('c');

        Button addGame = new Button("Add Game", new ImageView(new Image("file:AddGame.png")));
        addGame.setOnAction(e -> {
            displayToAddGame('p');

        });

        Label labelOfPlayingList = new Label("Games Playing");

        Button seeWishList = new Button("See your Wish List", new ImageView(new Image("file:wish.png")));
        Scene wishScene = setWishScene();
        seeWishList.setOnAction(e -> {
            window.setScene(wishScene);
            window.setTitle("Game Trakr - Wish List");

        });

        
        
        Label labelOfCompletedList = new Label("Completed Games");

        Button moveToCompleted = new Button("", new ImageView(new Image("file:RightArrow.png", 50, 50, true, true)));
        moveToCompleted.setOnAction(e -> {
            for (Game g : playingList.getGames()) {
                if (g.toString().equals(viewPlaying.getSelectionModel().getSelectedItem().getText())) {
                    playingList.removeGame(g.getName());
                    completedList.addGame(g.getName());
                    if (viewPlaying.getItems() != null) {
                        viewPlaying.getItems().clear();
                    }
                    if (viewCompleted.getItems() != null) {
                        viewCompleted.getItems().clear();
                    }
                    refreshListView('p');
                    refreshListView('c');
                }
            }
        });

        Button moveToPlaying = new Button("", new ImageView(new Image("file:LeftArrow.png", 50, 50, true, true)));
        moveToPlaying.setOnAction(e -> {
            for (Game g : completedList.getGames()) {
                if (g.toString().equals(viewCompleted.getSelectionModel().getSelectedItem().getText())) {
                    completedList.removeGame(g.getName());
                    playingList.addGame(g.getName());
                    if (viewPlaying.getItems() != null) {
                        viewPlaying.getItems().clear();
                    }
                    if (viewCompleted.getItems() != null) {
                        viewCompleted.getItems().clear();
                    }
                    refreshListView('p');
                    refreshListView('c');
                }
            }
        });

        Button removePlaying = new Button("", new ImageView(new Image("file:Garbage.png", 25, 25, true, true)));
        removePlaying.setOnAction(e -> {
            for (Game g : playingList.getGames()) {
                if (g.toString().equals(viewPlaying.getSelectionModel().getSelectedItem().getText())) {
                    playingList.removeGame(g.getName());
                    if (viewPlaying.getItems() != null) {
                        viewPlaying.getItems().clear();
                    }

                    refreshListView('p');
                }
            }
        });

        Button removeCompleted = new Button("", new ImageView(new Image("file:Garbage.png", 25, 25, true, true)));
        removeCompleted.setOnAction(e -> {
            for (Game g : completedList.getGames()) {
                if (g.toString().equals(viewCompleted.getSelectionModel().getSelectedItem().getText())) {
                    completedList.removeGame(g.getName());
                    if (viewCompleted.getItems() != null) {
                        viewCompleted.getItems().clear();
                    }

                    refreshListView('c');
                }
            }
        });

        //Sorting buttons
        Button sortByNamePlaying = new Button("Sort By Name");
        sortByNamePlaying.setOnAction(e -> {
            playingList = nameSort.getData(1);

            if (viewPlaying.getItems() != null) {
                viewPlaying.getItems().clear();
            }

            refreshListView('p');
        });

        Button sortByYearPlaying = new Button("Sort By Year");
        sortByYearPlaying.setOnAction(e -> {
            playingList = yearSort.getData(1);

            if (viewPlaying.getItems() != null) {
                viewPlaying.getItems().clear();
            }

            refreshListView('p');
        });

        Button sortByMetacriticPlaying = new Button("Sort By MetaCritic");
        sortByMetacriticPlaying.setOnAction(e -> {
            playingList = metacriticSort.getData(1);

            if (viewPlaying.getItems() != null) {
                viewPlaying.getItems().clear();
            }

            refreshListView('p');
        });

        Button sortByNameCompleted = new Button("Sort By Name");
        sortByNameCompleted.setOnAction(e -> {
            completedList = nameSort.getData(2);

            if (viewCompleted.getItems() != null) {
                viewCompleted.getItems().clear();
            }

            refreshListView('c');
        });

        Button sortByYearCompleted = new Button("Sort By Year");
        sortByYearCompleted.setOnAction(e -> {
            completedList = yearSort.getData(2);

            if (viewCompleted.getItems() != null) {
                viewCompleted.getItems().clear();
            }

            refreshListView('c');
        });

        Button sortByMetacriticCompleted = new Button("Sort By MetaCritic");
        sortByMetacriticCompleted.setOnAction(e -> {
            completedList = metacriticSort.getData(2);

            if (viewCompleted.getItems() != null) {
                viewCompleted.getItems().clear();
            }

            refreshListView('c');
        });

        //Add layout objects to the scene
        root.getChildren().addAll(addGame,
                labelOfPlayingList,
                moveToCompleted,
                moveToPlaying,
                removePlaying,
                removeCompleted,
                seeWishList,
                labelOfCompletedList,
                viewPlaying,
                viewCompleted,
                sortByNamePlaying,
                sortByYearPlaying,
                sortByMetacriticPlaying,
                sortByNameCompleted,
                sortByYearCompleted,
                sortByMetacriticCompleted);

        mainScene = new Scene(root, 900, 600);

        window.setScene(mainScene);

        window.show();
        showingFlag = window.isShowing();

        /**
         * **************************************Game Layout **********************************************************
         */
        
        //Set viewList sizes
        viewPlaying.setPrefHeight(mainScene.getHeight() - 10 - sortByNamePlaying.getHeight() - 10 - labelOfPlayingList.getHeight() - 10 - addGame.getHeight() - 20);
        viewPlaying.setPrefWidth(mainScene.getWidth() / 2 - moveToCompleted.getWidth() - 20);
        viewCompleted.setPrefHeight(mainScene.getHeight() - 10 - sortByNameCompleted.getHeight() - 10 - labelOfCompletedList.getHeight() - 10 - seeWishList.getHeight() - 20);
        viewCompleted.setPrefWidth(mainScene.getWidth() / 2 - moveToCompleted.getWidth() - 20);
        
        //Add button
        addGame.setLayoutX(10);
        addGame.setLayoutY(10);
        
        //Lable of playingList
        labelOfPlayingList.setLayoutX(10);
        labelOfPlayingList.setLayoutY(10 + addGame.getHeight() + 10);

        //ListView of playingList
        viewPlaying.setLayoutX(10);
        viewPlaying.setLayoutY(10 + addGame.getHeight() + 10 + labelOfPlayingList.getHeight() + 10);

        //See wishList button
        seeWishList.setLayoutX(mainScene.getWidth() / 2 - 10 + moveToCompleted.getWidth() + 20);
        seeWishList.setLayoutY(10);

        //Lable of the compltedList
        labelOfCompletedList.setLayoutX(mainScene.getWidth() / 2 - 10 + moveToCompleted.getWidth() + 20);
        labelOfCompletedList.setLayoutY(10 + addGame.getHeight() + 10);

        //ListView of completedList
        viewCompleted.setLayoutX(mainScene.getWidth() / 2 - 10 + moveToCompleted.getWidth() + 20);
        viewCompleted.setLayoutY(10 + seeWishList.getHeight() + 10 + labelOfCompletedList.getHeight() + 10);

        //Move Game Button
        moveToCompleted.setLayoutX(mainScene.getWidth() / 2 - moveToCompleted.getWidth() / 2);
        moveToCompleted.setLayoutY(mainScene.getHeight() / 2 - 20 - moveToCompleted.getHeight());

        moveToPlaying.setLayoutX(mainScene.getWidth() / 2 - moveToPlaying.getWidth() / 2);
        moveToPlaying.setLayoutY(mainScene.getHeight() / 2 + 20 + moveToPlaying.getHeight());

        //Sort Buttons playing list
        sortByNamePlaying.setLayoutX(viewPlaying.getLayoutX() + 10);
        sortByNamePlaying.setLayoutY(viewPlaying.getLayoutY() + viewPlaying.getHeight() + 90);

        sortByYearPlaying.setLayoutX(viewPlaying.getLayoutX() + 10 + sortByNamePlaying.getWidth() + 10);
        sortByYearPlaying.setLayoutY(viewPlaying.getLayoutY() + viewPlaying.getHeight() + 90);

        sortByMetacriticPlaying.setLayoutX(viewPlaying.getLayoutX() + 10 + sortByNamePlaying.getWidth() + 10 + sortByYearPlaying.getWidth() + 10);
        sortByMetacriticPlaying.setLayoutY(viewPlaying.getLayoutY() + viewPlaying.getHeight() + 90);

        //Sort Buttons completed list
        sortByNameCompleted.setLayoutX(viewCompleted.getLayoutX() + 10);
        sortByNameCompleted.setLayoutY(viewCompleted.getLayoutY() + viewCompleted.getHeight() + 90);

        sortByYearCompleted.setLayoutX(viewCompleted.getLayoutX() + 10 + sortByNamePlaying.getWidth() + 10);
        sortByYearCompleted.setLayoutY(viewCompleted.getLayoutY() + viewCompleted.getHeight() + 90);

        sortByMetacriticCompleted.setLayoutX(viewCompleted.getLayoutX() + 10 + sortByNamePlaying.getWidth() + 10 + sortByYearPlaying.getWidth() + 10);
        sortByMetacriticCompleted.setLayoutY(viewCompleted.getLayoutY() + viewCompleted.getHeight() + 90);

        System.out.println(sortByMetacriticCompleted.getHeight());

        //Remove button
        removePlaying.setLayoutX(viewPlaying.getLayoutX() + 10 + sortByNamePlaying.getWidth() + 10 + sortByYearPlaying.getWidth() + 10 + sortByMetacriticPlaying.getWidth() + 10);
        removePlaying.setLayoutY(viewPlaying.getLayoutY() + viewPlaying.getHeight() + 85);

        removeCompleted.setLayoutX(viewCompleted.getLayoutX() + 10 + sortByNameCompleted.getWidth() + 10 + sortByYearCompleted.getWidth() + 10 + sortByMetacriticCompleted.getWidth() + 10);
        removeCompleted.setLayoutY(viewCompleted.getLayoutY() + viewCompleted.getHeight() + 85);

    }

    /**
     * <p>
     * EFFECT:Refreshes the listView
     * <p>
     * MODIFIES: ListView to new list
     *
     * @param choose
     */
    public void refreshListView(char choose) {
        if (choose == 'p') {
            for (Game g : playingList.getGames()) {
                Text text = new Text(g.getName() + "\nYear: " + g.getYear()
                        + "\nMetaCritic: " + g.getMetacritic()
                        + "\nEstimated Time: " + g.getCompletionTime() + " hours");
                text.setFont(Font.font("Segoe UI", FontWeight.BOLD, 12));
                playingItems.add(text);
            }

            viewPlaying.setItems(playingItems);
            viewPlaying.setCellFactory(new Callback<ListView<Text>, ListCell<Text>>() {
                @Override
                public ListCell<Text> call(ListView<Text> param) {
                    return new ImageCell() {
                        @Override
                        public void updateItem(Text item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                for (Game g : playingList.getGames()) {
                                    if (item.getText().equals(g.toString())) {
                                        ImageView image = new ImageView(new Image("file:" + g.getImagePath(), 150, 150, true, true));
                                        setGraphic(image);

                                        break;
                                    }
                                }
                                setFont(item.getFont());
                                setText(item.getText());

                            }
                        }
                    };
                }
            }
            );
        } else if (choose == 'c') {
            for (Game g : completedList.getGames()) {
                completedItems.add(new Text(g.getName()
                        + "\nYear: " + g.getYear()
                        + "\nMetaCritic: " + g.getMetacritic()
                        + "\nEstimated Time: " + g.getCompletionTime() + " hours"));
            }

            viewCompleted.setItems(completedItems);
            viewCompleted.setCellFactory(new Callback<ListView<Text>, ListCell<Text>>() {
                @Override
                public ListCell<Text> call(ListView<Text> param) {
                    return new ImageCell() {
                        @Override
                        public void updateItem(Text item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                for (Game g : completedList.getGames()) {
                                    if (item.getText().equals(g.toString())) {
                                        ImageView image = new ImageView(new Image("file:" + g.getImagePath(), 150, 150, true, true));
                                        setGraphic(image);

                                        break;
                                    }
                                }
                                setFont(item.getFont());
                                setText(item.getText());

                            }
                        }
                    };
                }
            }
            );
        } else if (choose == 'w') {
            for (Game g : wishList.getGames()) {
                wishItems.add(new Text(g.getName()
                        + "\nYear: " + g.getYear()
                        + "\nMetaCritic: " + g.getMetacritic()
                        + "\nEstimated Time: " + g.getCompletionTime() + " hours"));
            }

            viewWish.setItems(wishItems);
            viewWish.setCellFactory(new Callback<ListView<Text>, ListCell<Text>>() {
                @Override
                public ListCell<Text> call(ListView<Text> param) {
                    return new ImageCell() {
                        @Override
                        public void updateItem(Text item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                for (Game g : wishList.getGames()) {
                                    if (item.getText().equals(g.toString())) {
                                        ImageView image = new ImageView(new Image("file:" + g.getImagePath(), 100, 100, true, true));
                                        setGraphic(image);

                                        break;
                                    }
                                }
                                setFont(item.getFont());
                                setText(item.getText());

                            }
                        }
                    };
                }
            }
            );
        }

    }

    /**
     * <p>
     * EFFECT: Create a pop up window to allow the user to enter
     * <p>
     * MODIFIES: The wishList and playingList base on the user choice of games
     *
     * @param choose
     */
    public void displayToAddGame(char choose) {

        Stage alertBox = new Stage();
        alertBox.setResizable(false);
        alertBox.initStyle(StageStyle.UTILITY);

        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle("Add Your Game");
        alertBox.setMinWidth(250);

        Label nameOfGame = new Label("Please search for your game.");

        TextField gameInput = new TextField();
        gameInput.setPromptText("Add Game");
//        gameInput.setOnKeyPressed(e ->{
//            if (e.getCode().equals(KeyCode.ENTER))
//            {
//                doSomething();
//            }
//        });

        Image addGameIcon = new Image("file:AddGame.png");
        Button addGame = new Button("Add Game", new ImageView(addGameIcon));

        if (choose == 'p') {
            addGame.setOnAction(e -> {
                String name = gameInput.getText();
                if (name == null || name == "") {
                    nameOfGame.setText("Please enter a game name");
                } else {
                    boolean contains = true;
                    for (Game g : playingList.getGames()) {
                        if (name.equalsIgnoreCase(g.getName())) {
                            nameOfGame.setText("You already have this game");
                            contains = false;
                        }
                    }
                    for (Game g : completedList.getGames()) {
                        if (name.equalsIgnoreCase(g.getName())) {
                            nameOfGame.setText("You have already completed this game");
                            contains = false;
                        }
                    }

                    if (contains) {
                        playingList.addGame(name);
                        if (!GameListImplementation.found) {
                            nameOfGame.setText("Sorry, we did not find your Game");
                        } else {
                            if (viewPlaying.getItems() != null) {
                                viewPlaying.getItems().clear();
                            }
                            refreshListView('p');
                            alertBox.close();

                        }
                    }

                }
            });
        } else if (choose == 'w') {
            addGame.setOnAction(e -> {
                String name = gameInput.getText();
                if (name == null || name == "") {
                    nameOfGame.setText("Please enter a game name");
                } else {
                    boolean contains = true;

                    for (Game g : wishList.getGames()) {
                        if (name.equalsIgnoreCase(g.getName())) {
                            nameOfGame.setText("This game is already in your wish list");
                            contains = false;
                        }
                    }
                    for (Game g : playingList.getGames()) {
                        if (name.equalsIgnoreCase(g.getName())) {
                            nameOfGame.setText("You already own this game");
                            contains = false;
                        }
                    }
                    for (Game g : completedList.getGames()) {
                        if (name.equalsIgnoreCase(g.getName())) {
                            nameOfGame.setText("You have already completed this game");
                            contains = false;
                        }
                    }

                    if (contains) {
                        wishList.addGame(name);
                        if (!GameListImplementation.found) {
                            nameOfGame.setText("Sorry, we did not find your Game");
                        } else {
                            if (viewWish.getItems() != null) {
                                viewWish.getItems().clear();
                            }
                            refreshListView('w');
                            alertBox.close();

                        }
                    }

                }
            });
        }

        VBox layout = new VBox(20);
        layout.getChildren().addAll(nameOfGame, gameInput, addGame);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }

    /**
     * <p>
     * EFFECT: Creates a new scene when switching to see the wish list
     * <p>
     * MODIFIES: The window
     *
     * @return
     */
    private Scene setWishScene() {
        GridPane wishGrid = new GridPane();
        wishGrid.setPadding(new Insets(15, 15, 15, 15));
        wishGrid.setVgap(10);
        wishGrid.setHgap(20);

        viewWish = new ListView<>();
        wishItems = FXCollections.observableArrayList();
        refreshListView('w');
        GridPane.setConstraints(viewWish, 0, 2);

        Image addGameIcon = new Image("file:AddGame.png");
        Button addGame = new Button("Add Game", new ImageView(addGameIcon));
        GridPane.setConstraints(addGame, 0, 0);
        addGame.setOnAction(e -> displayToAddGame('w'));

        Button backButton = new Button("Back");
        GridPane.setConstraints(backButton, 1, 0);
        backButton.setOnAction(e -> {
            window.setScene(mainScene);
            window.setTitle("Game Trakr - Main Page");
        });

        Button remove = new Button("", new ImageView(new Image("file:Garbage.png", 25, 25, true, true)));
        GridPane.setConstraints(remove, 1, 2);
        remove.setOnAction(e -> {
            for (Game g : wishList.getGames()) {
                if (g.toString().equals(viewWish.getSelectionModel().getSelectedItem().getText())) {
                    wishList.removeGame(g.getName());
                    if (viewWish.getItems() != null) {
                        viewWish.getItems().clear();
                    }

                    refreshListView('w');
                }
            }
        });

        Button sortByName = new Button("Sort By Name");
        GridPane.setConstraints(sortByName, 0, 3);
        sortByName.setOnAction(e -> {
            wishList = nameSort.getData(0);

            if (viewWish.getItems() != null) {
                viewWish.getItems().clear();
            }

            refreshListView('w');
        });

        Button sortByYear = new Button("Sort By Year");
        GridPane.setConstraints(sortByYear, 0, 4);
        sortByYear.setOnAction(e -> {
            wishList = yearSort.getData(0);

            if (viewWish.getItems() != null) {
                viewWish.getItems().clear();
            }

            refreshListView('w');
        });

        Button sortByMetacritic = new Button("Sort By MetaCritic");
        GridPane.setConstraints(sortByMetacritic, 0, 5);
        sortByMetacritic.setOnAction(e -> {
            wishList = metacriticSort.getData(0);

            if (viewWish.getItems() != null) {
                viewWish.getItems().clear();
            }

            refreshListView('w');
        });

        Label labelOfWishList = new Label("Wish List");
        GridPane.setConstraints(labelOfWishList, 0, 1);
        wishGrid.setAlignment(Pos.CENTER);

        wishGrid.getChildren().addAll(addGame, labelOfWishList, backButton, viewWish, remove, sortByName, sortByYear, sortByMetacritic);

        return new Scene(wishGrid, 400, 600);
    }

}
