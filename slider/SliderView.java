package slider;

import static slider.SliderApplication.HEIGHT;
import static slider.SliderApplication.WIDTH;
import static slider.SliderApplication.EMPTY_BLOCK_INDEX;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SliderView extends Pane {

    // Model which keep track of state of the game
    private SliderModel model;

    // View components
    private GridPane root;

    private Button[] sliderButtons;
    private Label movesCounterLabel;
    private Label winsCounterLabel;
    private Button shuffleButton;
    private Button displayFullImageButton;

    public SliderView(SliderModel model) {
        this.model = model;
        
        root = new GridPane();
        
        /* ----- Initialize components ----- */
        sliderButtons = new Button[HEIGHT*WIDTH];

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                sliderButtons[row*WIDTH + col] = new Button();
                sliderButtons[row*WIDTH + col].setText(String.valueOf(row*WIDTH + col));
            }
        }

        movesCounterLabel = new Label(this.formatMovesLabel(0));
        winsCounterLabel = new Label(this.formatWinsLabel(0));
        shuffleButton = new Button("Reset & Shuffle");
        displayFullImageButton = new Button("Display Full Image");

        /* ----- Attach EventListeners to all Buttons ----- */
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                sliderButtons[row*WIDTH + col].setId(String.valueOf(row*WIDTH + col));
                sliderButtons[row*WIDTH + col].setOnAction(this::handleSliderButtonClicked);
            }
        }

        shuffleButton.setOnAction(this::handleShuffle);
        displayFullImageButton.setOnAction(this::handleDisplayFullImage);

        /* ----- Add components ----- */
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                root.add(sliderButtons[row*WIDTH + col], col, row);
            }
        }
        sliderButtons[WIDTH*HEIGHT-1].setVisible(false);  // Last one should be hidden

        root.add(movesCounterLabel, 0, HEIGHT);
        root.add(winsCounterLabel, 1, HEIGHT);
        root.add(shuffleButton, 2, HEIGHT);
        root.add(displayFullImageButton, 3, HEIGHT);

        this.getChildren().add(root);
    }

    private void handleSliderButtonClicked(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        int buttonIndex = Integer.parseInt(clicked.getId());
        
        model.slide(buttonIndex);
        if (model.hasWon()) {
            /* ----- Add a win and display all buttons----- */
            model.incrementWin();
            this.updateGui();
            for (Button b : sliderButtons) b.setVisible(true);

            /* ----- Display alert ----- */
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Winner!");
            alert.setHeaderText("Winner!");
            int numWins = model.getNumWins();
            alert.setContentText("You won with " + model.getNumMoves() + " moves! " + 
                "\nYou have accumulated " + numWins + (numWins == 1 ? " win! " : " wins! ") + 
                "\nPress OK to start a new game, or close this alert to exit.");

            Optional<ButtonType> result = alert.showAndWait();

            /* ----- User clicked OK: start new game ----- */
            if (result.isPresent() && result.get() == ButtonType.OK){
                model.shuffle();
            }
            /* ----- User clicked something else: exit ----- */
            else System.exit(0);
        }
        this.updateGui();
    }

    private void handleShuffle(ActionEvent event) {
        /* ----- Display confirmation alert ----- */
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("You are about to perform a shuffle. This will randomly reset " + 
            "the slider game and set your moves to 0. Select OK to confirm...");

        Optional<ButtonType> result = alert.showAndWait();

        /* ----- User clicked OK: shuffle ----- */
        if (result.isPresent() && result.get() == ButtonType.OK){
            model.shuffle();
            this.updateGui();
        }
    }

    private void handleDisplayFullImage(ActionEvent event) {
        // TODO implement Dialog stuff or display separate window
        System.out.println("TODO implement");
    }

    /** Updates the view components based on the state stored by the model */
    public void updateGui() {
        // Update the button images
        for (int i = 0; i < sliderButtons.length; i++) {
            int imageIndex = model.getButtonLayout()[i];
            sliderButtons[i].setText(String.valueOf(imageIndex));

            // Update visibility of buttons
            if (imageIndex == EMPTY_BLOCK_INDEX)
                sliderButtons[i].setVisible(false);
            else
                sliderButtons[i].setVisible(true);
        }
        
        // Update the counters
        movesCounterLabel.setText(this.formatMovesLabel(model.getNumMoves()));
        winsCounterLabel.setText(this.formatWinsLabel(model.getNumWins()));
    }
    private String formatMovesLabel(int moves) {
        return "Moves: " + moves;
    }
    private String formatWinsLabel(int wins) {
        return "Wins: " + wins;
    }
}