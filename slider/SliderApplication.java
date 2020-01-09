package slider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SliderApplication extends Application {

    // TODO Look into CSS and prettify https://docs.oracle.com/javase/8/javafx/get-started-tutorial/css.htm
    // TODO make window resizeable

    public static final int HEIGHT = 4;
    public static final int WIDTH = 4;
    public static final int EMPTY_BLOCK_INDEX = 15;

    public static final int WINDOW_WIDTH = 380;
    public static final int WINDOW_HEIGHT = 450;

    MenuView menuView;
    SliderModel model;
    SliderView gameView;

    @Override
    public void start(Stage primaryStage) {
        // The primaryStage is our main Window for the GUI
        primaryStage.setTitle("Fifteenth Puzzle");

        // Setup the window and display the menu
        menuView = new MenuView();
        Scene menuScene = new Scene(menuView, WINDOW_WIDTH, WINDOW_HEIGHT);
        menuScene.getStylesheets().add("slider/css/menu.css");

        primaryStage.setScene(menuScene);
        primaryStage.show();

        // Switch view when start button is clicked
        menuView.getStartButton().setOnAction(event -> {
            int selectedTheme = menuView.getSelectedTheme();
           
            // We now have the theme input, now we can start the game
            model = new SliderModel(selectedTheme);
            gameView = new SliderView(model);

            // Display the new view
            Scene sliderScene = new Scene(gameView, WINDOW_WIDTH, WINDOW_HEIGHT);
            sliderScene.getStylesheets().add("slider/css/slider.css");
            primaryStage.setScene(sliderScene);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
