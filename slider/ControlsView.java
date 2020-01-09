package slider;

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

public class ControlsView extends BorderPane {

    private SliderModel model;
    private SliderView sliderView;

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
		displayFullImageButton = new Button("Hover to see full image");
		fullImageTooltip = new Tooltip();
		fullImage = new Image(imageUrl, 500, 500, true, true);


		/* ----- Spacing, padding, and CSS tags ----- */
		this.setPadding(new Insets(30, 0, 10, 10));
        counters.setAlignment(Pos.CENTER_LEFT);
        counters.setSpacing(5);
        buttons.setSpacing(5);

		shuffleButton.getStyleClass().add("shuffle-button");
		displayFullImageButton.getStyleClass().add("display-image-button");
        
        
		/* ----- Attach on-hover tooltip ----- */
		// displayFullImageButton.setOnAction(this::handleDisplayFullImage);
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
