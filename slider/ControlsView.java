package slider;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ControlsView extends BorderPane {

	// Model which keeps track of state of the game
    private SliderModel model;

	// View components
    private HBox counters;
    private HBox buttons;

    private Label movesCounterLabel;
	private Label winsCounterLabel;

	private Button shuffleButton;
	private Button displayFullImageButton;
	private Tooltip fullImageTooltip;
	private Image fullImage;


    public ControlsView(SliderModel model, Theme selectedTheme) {
        this.model = model;

		/* ----- Initialize components ----- */
        counters = new HBox();
		movesCounterLabel = new Label(this.formatMovesLabel(0));
        winsCounterLabel = new Label(this.formatWinsLabel(0));
        
        buttons = new HBox();
		shuffleButton = new Button("Reset & Shuffle");
		String imageUrl = "slider/images/full-" + selectedTheme.ordinal() + ".png";
		displayFullImageButton = new Button("Hover for full image");
		fullImageTooltip = new Tooltip();
		fullImage = new Image(imageUrl, 400, 400, true, true);


		/* ----- Spacing, padding, and CSS tags ----- */
		this.setPadding(new Insets(10, 10, 10, 10));
        counters.setAlignment(Pos.CENTER_LEFT);
        counters.setSpacing(5);
        buttons.setSpacing(5);

		shuffleButton.getStyleClass().add("btn-warning");
		displayFullImageButton.getStyleClass().add("btn-info");
        this.getStyleClass().add("controls");
        
		/* ----- Attach on-hover tooltip ----- */
		fullImageTooltip.setGraphic(new ImageView(fullImage));
		displayFullImageButton.setTooltip(fullImageTooltip);


		/* ----- Add components ----- */
        counters.getChildren().addAll(movesCounterLabel, winsCounterLabel);
        buttons.getChildren().addAll(shuffleButton, displayFullImageButton);
        this.setLeft(counters);
        this.setRight(buttons);

        this.updateGui();
    }

    public Button getShuffleButton() {
        return this.shuffleButton;
    }

    public void updateGui() {
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
