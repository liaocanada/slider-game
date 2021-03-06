package slider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SliderApplication extends Application {
    // Constants
    public static final int HEIGHT = 4;
    public static final int WIDTH = 4;
    public static final int EMPTY_BLOCK_INDEX = 15;

    public static final int WINDOW_WIDTH = 410;
    public static final int WINDOW_HEIGHT = 473;

    // Views and Models
    MenuView menuView;
    SliderModel model;
    SliderView gameView;

    @Override
    public void start(Stage primaryStage) {
        // The primaryStage is our window for the GUI
        primaryStage.setTitle("Fifteen Puzzle");

        // Setup the window and display the menu
        menuView = new MenuView();
        Scene menuScene = new Scene(menuView, WINDOW_WIDTH, WINDOW_HEIGHT);
        menuScene.getStylesheets().add("slider/css/main.css");

        primaryStage.setResizable(false);
        primaryStage.setScene(menuScene);
        primaryStage.show();

        // Switch view when start button is clicked
        menuView.getStartButton().setOnAction(event -> {
            Theme selectedTheme = menuView.getSelectedTheme();
           
            // We now have the theme input, now we can start the game
            model = new SliderModel();
            gameView = new SliderView(model, selectedTheme);

            // Display the new view
            Scene sliderScene = new Scene(gameView, WINDOW_WIDTH, WINDOW_HEIGHT);
            sliderScene.getStylesheets().add("slider/css/main.css");
            primaryStage.setScene(sliderScene);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
