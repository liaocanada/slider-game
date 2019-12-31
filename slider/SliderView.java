package slider;

import static slider.SliderApplication.HEIGHT;
import static slider.SliderApplication.WIDTH;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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
                // sliderButtons[row*WIDTH + col].setBackground(new Background(new BackgroundImage(image, repeatX, repeatY, position, size)));
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
        root.getChildren().remove(sliderButtons[WIDTH*HEIGHT-1]);  // Last one should be removed

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
            System.out.println("Win!");
        }
        this.updateGui();
    }

    private void handleShuffle(ActionEvent event) {
        model.shuffle();
        this.updateGui();
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
            // sliderButtons[i].setBackground(value);  // TODO image

            sliderButtons[i].setText(String.valueOf(imageIndex));
        }
        
        // Update the counters
        movesCounterLabel.setText(this.formatMovesLabel(model.getNumMoves()));
        movesCounterLabel.setText(this.formatWinsLabel(model.getNumWins()));
    }
    private String formatMovesLabel(int moves) {
        return "Moves: " + moves;
    }
    private String formatWinsLabel(int wins) {
        return "Wins: " + wins;
    }
}