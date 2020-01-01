package slider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SliderApplication extends Application {

    // TODO Look into CSS https://docs.oracle.com/javase/8/javafx/get-started-tutorial/css.htm
    // TODO Fix radio buttons
    // TODO make window resizeable
    // TODO Create tasks
    // TODO Comment

    public static final int HEIGHT = 4;
    public static final int WIDTH = 4;
    public static final int EMPTY_BLOCK_INDEX = 15;

    public static final int DEFAULT_WINDOW_WIDTH = 300;
    public static final int DEFAULT_WINDOW_HEIGHT = 275;

    MenuView menuView;
    SliderModel model;
    SliderView gameView;

    @Override
    public void start(Stage primaryStage) {
        // The primaryStage is our main Window for the GUI
        primaryStage.setTitle("Fifteenth Puzzle");

        // Display the Menu first
        menuView = new MenuView();
        primaryStage.setScene(new Scene(menuView, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
        primaryStage.show();

        menuView.getStartButton().setOnAction(event -> {
            int selectedTheme = menuView.getSelectedTheme();
           
            // We now have the theme input, now we can start the game
            model = new SliderModel(selectedTheme);
            gameView = new SliderView(model);

            // Display gameView
            primaryStage.setScene(new Scene(gameView, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
