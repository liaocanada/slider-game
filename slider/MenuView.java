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
import javafx.geometry.Pos;

public class MenuView extends VBox {

    // Constants
    public static final String TITLE_TEXT = "Fifteen Slider Puzzle";

    public static final String INSTRUCTION_TEXT = "The fifteen puzzle is a game composed of a 4 by 4 grid. "
        + "Each piece of the grid is a part of a bigger picture, but there is one piece missing. "
        + "The objective of the puzzle is to rearrange the pieces so that the pieces form "
        + "the larger picture. You may only move pieces adjacent to the empty piece. "
        + "\n\nTo see the larger picture, hold your cursor over the \"Hover for full image\" button."
        + "\nThere are three different themes you can try, with the basic theme being the easiest."
        + "\n\nReady? Just click OK and then Start Game!";
        
    // View components
    private Label title;
    private Label instructions;
    private HBox radioButtonBox;
    private Button startGameButton;
    private Button instructionsButton;
    
    private ToggleGroup themeButtonsGroup;
    private RadioButton[] themeButtons;

    public MenuView() {
        /* ----- Initialize components ----- */
        title = new Label(TITLE_TEXT);
        instructions = new Label("Please select a theme:");   
        
        themeButtons = new RadioButton[3];
        themeButtons[0] = new RadioButton("Basic theme");
        themeButtons[1] = new RadioButton("Bee Movie theme");
        themeButtons[2] = new RadioButton("Mr Bean theme");
        radioButtonBox = new HBox(themeButtons[0], themeButtons[1], themeButtons[2]);
        
        startGameButton = new Button("Start Game");  // ActionHandler defined in SliderApplication.java
        instructionsButton = new Button("Instructions");

        /*----- Configure radio buttons -----*/
        themeButtonsGroup = new ToggleGroup();
        for (int i = 0; i < themeButtons.length; i++) {
            themeButtons[i].setToggleGroup(themeButtonsGroup);
        }
        themeButtons[0].setSelected(true);
        themeButtons[0].setUserData(Theme.PLAIN_NUMBERS);
        themeButtons[1].setUserData(Theme.BEE_MOVIE);
        themeButtons[2].setUserData(Theme.MR_BEAN); 

        /*----- Configure buttons -----*/
        startGameButton.setPrefSize(200, 40);
        instructionsButton.setPrefSize(100, 25);
        instructionsButton.setOnAction(this::displayInstructions);
        
        /*----- Add CSS tags -----*/
        startGameButton.getStyleClass().add("btn-success");
        instructionsButton.getStyleClass().add("btn-info");
        title.getStyleClass().add("heading");

        /*----- Spacing -----*/
        radioButtonBox.setSpacing(10);
        radioButtonBox.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        /* ----- Add components ----- */
        this.getChildren().addAll(
            title, 
            instructions, 
            radioButtonBox,
            startGameButton, 
            instructionsButton
        );
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
    public Theme getSelectedTheme() {
        return (Theme) themeButtonsGroup.getSelectedToggle().getUserData();
    }
}
