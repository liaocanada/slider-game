package slider;

import java.util.Arrays;

import static slider.SliderApplication.HEIGHT;
import static slider.SliderApplication.WIDTH;

public class SliderModel {

    private static final int EMPTY_BLOCK_INDEX = 15;

    private int numWins;
    private int numMoves;

    // Stores the order of the buttons, with buttons 0 to 14 representing
    //     the correct ordering of the buttons. Button 15 represents the empty space.
    private int[] buttonLayout;
    private int emptyBlockLocation = 15;

    /** Constructor */
    public SliderModel(int selectedTheme) {
        // Initialize variables
        this.numWins = 0;
        this.numMoves = 0;

        // buttonLayout starts out in sorted order, then shuffles (500 times). 
        //     This ensures the puzzle is solveable.
        buttonLayout = new int[WIDTH*HEIGHT];
        for (int i = 0; i < buttonLayout.length; i++)
            buttonLayout[i] = i;

        shuffle();
    }

    /** 
     * Tries to slide a tile to the empty spot. If successful, returns true and increments numMoves.
     * @return true if the slide was successful, false otherwise.
     */
    public boolean slide(int targetButtonIndex) {
        if (targetButtonIndex < 0 || targetButtonIndex >= WIDTH*HEIGHT) return false;
        if (buttonLayout[targetButtonIndex] == EMPTY_BLOCK_INDEX) return false;

        // Determine coordinates of the target button and of the empty button
        int targetRow = targetButtonIndex / WIDTH;
        int targetCol = targetButtonIndex % WIDTH;
        int emptyRow = emptyBlockLocation / WIDTH;
        int emptyCol = emptyBlockLocation / HEIGHT;
        
        // Empty block is above/below/left of/right of clicked button
        if (
            targetRow - 1 == emptyRow && targetCol == emptyCol ||
            targetRow + 1 == emptyRow && targetCol == emptyCol ||
            targetRow == emptyRow && targetCol - 1 == emptyCol ||
            targetRow == emptyRow && targetCol + 1 == emptyCol
        ) {
            swap(targetRow, targetCol, emptyRow, emptyCol);
            return true;
        }

        return false;
    }
    private void swap(int targetRow, int targetCol, int emptyRow, int emptyCol) {
        buttonLayout[emptyRow*WIDTH+emptyCol] = buttonLayout[targetRow*WIDTH+targetCol];
        buttonLayout[targetRow*WIDTH+targetCol] = EMPTY_BLOCK_INDEX;

        numMoves++;
        emptyBlockLocation = targetRow*WIDTH+targetCol;
    }

    public boolean hasWon() {
        for (int i = 0; i < buttonLayout.length; i++) {
            if (buttonLayout[i] != i) return false;
        }
        return true;  // All buttons are in the correct order
    }

    public void shuffle() {
        for (int i = 0; i < 500; i++) shuffleOnce();
        System.out.println("Everyday I'm shuffling");
        if (hasWon()) shuffle();
    }
    private void shuffleOnce() {  // Moves a tile randomly (there is a possiblility of this doing nothing)
        int randomDirection = (int)(4 * Math.random());  // 0 to 3

        if (randomDirection == 0) slide(emptyBlockLocation - 1);
        else if (randomDirection == 1) slide(emptyBlockLocation + 1);
        else if (randomDirection == 2) slide(emptyBlockLocation - WIDTH);
        else if (randomDirection == 3) slide(emptyBlockLocation + WIDTH);
    }

    /* ----- Getters and setters ----- */
    public int getNumWins() {
        return numWins;
    }
    public int getNumMoves() {
        return numMoves;
    }
    public int[] getButtonLayout() {  // Returns a copy of buttonLayout
        return Arrays.copyOf(buttonLayout, buttonLayout.length);
    }
}