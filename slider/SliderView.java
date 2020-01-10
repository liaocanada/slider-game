package slider;

import static slider.SliderApplication.HEIGHT;
import static slider.SliderApplication.WIDTH;
import static slider.SliderApplication.EMPTY_BLOCK_INDEX;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SliderView extends BorderPane {

	// Model which keep track of state of the game
	private SliderModel model;

	private Theme selectedTheme;

	// View components
	private GridPane grid;
	private Button[] sliderButtons;
	private ImageView[] images;
	
	private ControlsView controlsBar;


	public SliderView(SliderModel model, Theme selectedTheme) {
		this.model = model;
		this.selectedTheme = selectedTheme;

		/* ----- Initialize components ----- */
		grid = new GridPane();
		sliderButtons = new Button[HEIGHT*WIDTH];
		images = new ImageView[HEIGHT*WIDTH];

		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				sliderButtons[row*WIDTH + col] = new Button();

				if (selectedTheme != Theme.PLAIN_NUMBERS) {
					String pieceUrl = String.format("slider/images/pieces-%d/piece%d%d.jpeg", selectedTheme.ordinal(), row, col); 
					images[row*WIDTH + col] = new ImageView(new Image(pieceUrl, 94, 94, true, true));
				}
			}
		}
		
		controlsBar = new ControlsView(model, selectedTheme);
		

		/* ----- Spacing, padding, and CSS tags ----- */
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				sliderButtons[row*WIDTH + col].setPrefWidth(95);
				sliderButtons[row*WIDTH + col].setPrefHeight(95);
				sliderButtons[row*WIDTH + col].getStyleClass().add("slider-button");
			}
		}
		BorderPane.setAlignment(grid, Pos.TOP_CENTER);
		grid.setAlignment(Pos.CENTER);

		/* ----- Attach EventListeners to all Buttons ----- */
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				sliderButtons[row*WIDTH + col].setId(String.valueOf(row*WIDTH + col));
				sliderButtons[row*WIDTH + col].setOnAction(this::handleSliderButtonClicked);
			}
		}

		controlsBar.getShuffleButton().setOnAction(this::handleShuffle);


		/* ----- Add components ----- */
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				grid.add(sliderButtons[row*WIDTH + col], col, row);
			}
		}
		this.setTop(grid);
		this.setBottom(controlsBar);

		this.updateGui();
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

	/** Updates the view components based on the state stored by the model */
	public void updateGui() {
		// Update the button images
		for (int i = 0; i < sliderButtons.length; i++) {
			int imageIndex = model.getButtonLayout()[i];
			if (selectedTheme == Theme.PLAIN_NUMBERS)
				sliderButtons[i].setText(String.valueOf(imageIndex + 1));  // 0 to 14 -> 1 to 15
			else {
				sliderButtons[i].setGraphic(images[imageIndex]);
			}
			
			// Update visibility of buttons
			if (imageIndex == EMPTY_BLOCK_INDEX)
				sliderButtons[i].setVisible(false);
			else
				sliderButtons[i].setVisible(true);
		}

		// Update the counters
		controlsBar.updateGui();
	}
}
