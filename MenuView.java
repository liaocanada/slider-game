import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuView extends Pane {

    // Constants
    public static final String TITLE_TEXT = "Insert title here";

    public static final String INSTRUCTION_TEXT = "The fifteen puzzle is a game composed of a 4 by 4 grid. "
        + "Each piece of the grid is a part of a bigger picture, but with one piece missing. "
        + "The objective of the game is to rearrange the pieces so that the pieces form "
        + "the larger picture. To move a piece, click on a piece adjacent to the empty piece. "
        + "\nReady? Just click OK and then START!";
        
    public static final int NUM_THEMES = 3;

    // View components
    private VBox root;

    private Label title;
    private Label instructions;
    private HBox radioButtonBox;
    private Button startGameButton;
    private Button instructionsButton;
    
    private RadioButton[] selectionButtons;

    public MenuView() {
        root = new VBox();
        root.setSpacing(20);

        /* ----- Initialize components ----- */
        title = new Label(TITLE_TEXT);        
        instructions = new Label(INSTRUCTION_TEXT);   
        
        selectionButtons = new RadioButton[NUM_THEMES];
        selectionButtons[0] = new RadioButton("Button1!");
        selectionButtons[1] = new RadioButton("Button2!");
        selectionButtons[2] = new RadioButton("Button3!");
        selectionButtons[0].setSelected(true);
        radioButtonBox = new HBox(selectionButtons[0], selectionButtons[1], selectionButtons[2]);

        startGameButton = new Button("Start Game");
        instructionsButton = new Button("Instructions");
        instructionsButton.setScaleX(0.75);
        instructionsButton.setScaleX(0.5);

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

    public Button getStartButton() {
        return startGameButton;
    }
    public int getSelectedTheme() {
        for (int i = 0; i < selectionButtons.length; i++) {
            if (selectionButtons[i].isSelected()) return i;
        }
        System.out.println("Error: no selected theme");
        return -1;
    }
}
