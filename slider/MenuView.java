package slider;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuView extends Pane {

    // Constants
    public static final String TITLE_TEXT = "Fifteenth Puzzle";

    public static final String INSTRUCTION_TEXT = "The fifteenth puzzle is a game composed of a 4 by 4 grid. "
        + "Each piece of the grid is a part of a bigger picture, but with one piece missing. "
        + "The objective of the game is to rearrange the pieces so that the pieces form "
        + "the larger picture. To move a piece, click on a piece adjacent to the empty piece. "
        + "\n\nReady? Just click OK and then Start Game!";
        
    // View components
    private VBox root;

    private Label title;
    private Label instructions;
    private HBox radioButtonBox;
    private Button startGameButton;
    private Button instructionsButton;
    
    private ToggleGroup themeButtonsGroup;
    private RadioButton[] themeButtons;

    public MenuView() {
        root = new VBox();
        root.setSpacing(20);

        /* ----- Initialize components ----- */
        title = new Label(TITLE_TEXT);
        instructions = new Label("Please select a theme:");   
        
        themeButtons = new RadioButton[3];
        themeButtons[0] = new RadioButton("Button1!");
        themeButtons[1] = new RadioButton("Button2!");
        themeButtons[2] = new RadioButton("Button3!");
        radioButtonBox = new HBox(themeButtons[0], themeButtons[1], themeButtons[2]);
        
        /*----- Configure radio buttons -----*/
        themeButtonsGroup = new ToggleGroup();
        for (int i = 0; i < themeButtons.length; i++) {
            themeButtons[i].setUserData(i);
            themeButtons[i].setToggleGroup(themeButtonsGroup);
        }
        themeButtons[0].setSelected(true);

        /*----- Configure buttons -----*/
        startGameButton = new Button("Start Game");  // ActionHandler defined in SliderApplication.java
        instructionsButton = new Button("Instructions");
        instructionsButton.setScaleX(0.75);
        instructionsButton.setScaleY(0.5);
        instructionsButton.setOnAction(this::displayInstructions);

        /* ----- Add components ----- */
        root.getChildren().addAll(
            title, 
            instructions, 
            radioButtonBox,
            startGameButton, 
            instructionsButton
        );
        this.getChildren().add(root);
    }

    private void displayInstructions(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Instructions");
        alert.setHeaderText("Instructions");
        alert.setContentText(INSTRUCTION_TEXT);
        alert.show();
    }

    /*----- Getters -----*/
    public Button getStartButton() {
        return startGameButton;
    }
    public int getSelectedTheme() {
        return (int) themeButtonsGroup.getSelectedToggle().getUserData();
    }
}
