import java.util.Arrays;

public class SliderModel {

    private final int selectedTheme;
    private int numWins;
    private int numMoves;

    // Stores the order of the buttons, with buttons 0 to 14 representing
    //     the correct ordering of the buttons. Button 15 represents the empty space.
    private int[] buttonLayout;

    /** Constructor */
    public SliderModel(int selectedTheme) {
        // Initialize variables
        this.selectedTheme = selectedTheme;
        this.numWins = 0;
        this.numMoves = 0;

        // buttonLayout starts out in sorted order, then shuffles (500 times). 
        //     This ensures the puzzle is solveable.
        buttonLayout = new int[16];
        for (int i = 0; i < buttonLayout.length; i++)
            buttonLayout[i] = i;

        shuffle();
    }

    /** 
     * Tries to slide a tile to the empty spot. If successful, returns true and increments numMoves.
     * @return true if the slide was successful, false otherwise.
     */
    public boolean slide(int index) {
        // TODO implement
        if (checkWin()) {} 
        return false;
    }
    private boolean checkWin() {
        for (int i = 0; i < buttonLayout.length; i++) {
            if (buttonLayout[i] != i) return false;
        }
        return true;  // All buttons are in the correct order
    }

    public void shuffle() {
        for (int i = 0; i < 500; i++) shuffleOnce();
        System.out.println("Everyday I'm shuffling");
        if (checkWin()) shuffle();
    }
    private void shuffleOnce() {
        // TODO implement
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
