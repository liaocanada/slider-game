package slider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SliderApplication extends Application {

    // TODO Edit this file
    // TODO Look into CSS https://docs.oracle.com/javase/8/javafx/get-started-tutorial/css.htm
    // TODO Create tasks
    // TODO Comment

    public static final int HEIGHT = 4;
    public static final int WIDTH = 4;
    public static final int EMPTY_BLOCK_INDEX = 15;

    MenuView menuView;
    SliderModel model;
    SliderView view;

    @Override
    public void start(Stage primaryStage) {
        // The primaryStage is our main Window for the GUI
        primaryStage.setTitle("Fifteenth Puzzle");

        // Display the Menu first
        menuView = new MenuView();
        primaryStage.setScene(new Scene(menuView, 300, 275));
        primaryStage.show();

        menuView.getStartButton().setOnAction(event -> {
            int selectedTheme = menuView.getSelectedTheme();
           
            // We now have the theme input, now we can start the game
            model = new SliderModel(selectedTheme);
            view = new SliderView(model);

            // Display the new view
            primaryStage.setScene(new Scene(view, 300, 275));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
