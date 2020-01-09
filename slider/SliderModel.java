package slider;

import java.util.Arrays;

import static slider.SliderApplication.HEIGHT;
import static slider.SliderApplication.WIDTH;
import static slider.SliderApplication.EMPTY_BLOCK_INDEX;

public class SliderModel {

    private int numWins;
    private int numMoves;

    // Maps button locations to button contents. 
    // For example if buttonLayout[0] == 5 then the top left button would be 
    //     containing the number 5 (or the image with ID 5).
    private int[] buttonLayout;

    // Keeps track of the button location of the empty block
    private int emptyBlockLocation = 15;

    /** Constructor */
    public SliderModel() {
        this.numWins = 0;
        this.numMoves = 0;

        // buttonLayout starts out in sorted order, then shuffles (500 times). 
        //     This ensures the puzzle is solveable.
        buttonLayout = new int[WIDTH*HEIGHT];
        for (int i = 0; i < buttonLayout.length; i++)
            buttonLayout[i] = i;

        // shuffle();  // TODO To make it start out unshuffled comment this line out
    }

    /** 
     * Tries to slide a tile to the empty spot. If successful, returns true and increments numMoves.
     * @return true if the slide was successful, false otherwise.
     */
    public boolean slide(int targetButtonIndex) {
        if (targetButtonIndex < 0 || targetButtonIndex >= WIDTH*HEIGHT) return false;
        if (buttonLayout[targetButtonIndex] == EMPTY_BLOCK_INDEX) return false;

        // System.out.println("Sliding button " + targetButtonIndex + " with value " + buttonLayout[targetButtonIndex]);

        // Determine coordinates of the target button and of the empty button
        int targetRow = targetButtonIndex / WIDTH;
        int targetCol = targetButtonIndex % WIDTH;
        int emptyRow = emptyBlockLocation / WIDTH;
        int emptyCol = emptyBlockLocation % WIDTH;
        
        // Empty block is above/below/left of/right of clicked button
        if (
            targetRow - 1 == emptyRow && targetCol == emptyCol ||
            targetRow + 1 == emptyRow && targetCol == emptyCol ||
            targetRow == emptyRow && targetCol - 1 == emptyCol ||
            targetRow == emptyRow && targetCol + 1 == emptyCol
        ) {
            swap(targetButtonIndex, emptyBlockLocation);
            return true;
        }

        return false;
    }
    private void swap(int clicked, int empty) {
        buttonLayout[empty] = buttonLayout[clicked];
        buttonLayout[clicked] = EMPTY_BLOCK_INDEX;

        numMoves++;
        this.emptyBlockLocation = clicked;
    }

    public boolean hasWon() {
        for (int i = 0; i < buttonLayout.length; i++) {
            if (buttonLayout[i] != i) return false;
        }
        return true;  // All buttons are in the correct order
    }

    public void shuffle() {
        for (int i = 0; i < 500; i++) shuffleOnce();
        this.numMoves = 0;
    }
    /** 
     * Moves a tile randomly 
     * (there is a possiblility of this doing nothing, if we choose a direction which is out of bounds)
     */ 
    private void shuffleOnce() {  
        int randomDirection = (int)(4 * Math.random());  // 0 to 3

        if (randomDirection == 0) slide(emptyBlockLocation - 1);
        else if (randomDirection == 1) slide(emptyBlockLocation + 1);
        else if (randomDirection == 2) slide(emptyBlockLocation - WIDTH);
        else if (randomDirection == 3) slide(emptyBlockLocation + WIDTH);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < HEIGHT; i++) {
            int[] thisRow = Arrays.copyOfRange(buttonLayout, WIDTH*i, WIDTH*(i+1));
            sb.append(Arrays.toString(thisRow));
            sb.append('\n');
        }
        return sb.toString();
    }

    /* ----- Getters and setters ----- */
    public int getNumWins() {
        return numWins;
    }
    public void incrementWin() {
        this.numWins++;
    }
    public int getNumMoves() {
        return numMoves;
    }
    public int[] getButtonLayout() {  // Returns a copy of buttonLayout for immutability
        return Arrays.copyOf(buttonLayout, buttonLayout.length);
    }
}
