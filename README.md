# Fifteen Slider Puzzle
In this challenge, you are provided with an already-working puzzle game built with JavaFX, which is a framework for building Java desktop applications. This means you can play around with it as you like, to get somewhat familiar with the game as well as the code. However, it isn't perfect... You are given a few additional feature requests (see the Challenges section) to implement which will make the game even better! 

## Getting Started
You can run this Java program either through an IDE, or through the command line:
1. Download the files onto your computer
2. `cd ` to the directory above the `slider` package folder
3. In a terminal, run `javac slider/SliderApplication.java`
NOTE: If this produces some errors, you are likely either missing Java or JavaFX -- see the below "Java/JavaFX installation" collapsable for further instructions. 
Otherwise, proceed onto the next final step.
5. Run `java slider.SliderApplication`

<details>
<summary>Java/JavaFX installation</summary>

### Java/JavaFX installation
Open a command prompt and type `javac -version`. Java 11 or later does not come with JavaFX (a library needed for this program) installed, so I suggest installing Oracle Java (JDK) 8 [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) if you do not have Java installed or you have a later version. Then follow the steps in the install wizard.

#### Extra steps - Windows
After installation, you might need to add the install location to the PATH variable. Instructions for how to do this are shown in [this link](https://www.java.com/en/download/help/path.xml). 

You may also need to remove other versions of Java, or just remove the old directory from the PATH variable.

Finally repeat the steps in the Getting Started section above.

#### Extra steps - Mac
To remove the other versions of Java (if applicable), go to: `/Library/Java/JavaVirtualMachines/` and move the folders which aren't `jdk1.8.0_231.jdk` to the Trash or somewhere else.

Finally repeat the steps in the Getting Started section above.
</details>


## Challenges
Here are the challenges, they may be done in any order. If you are stuck or have questions, just ask and we might be able to give you some hints :)

#### Sliding multiple tiles at once
Currently we can only click on tiles adjacent to the empty tile. This feels slow when we want to move multiple blocks in the same row/column at once! Add a feature so that if we click on a tile which is on the same row/column as the empty tile, it moves ALL tiles over one spot. 

It's up to you whether this should count as one move or multiple moves!

#### Undo button
Sometimes I *really* want to minimize the number of moves... if I make a mistake, clicking the same tile again to move it back just won't do! Implement an "undo" button so that it will undo past moves, making sure the moves counter gets reverted too. We should be able to click this repeatedly, until we reach the starting "shuffled" layout. At this point, the button should be disabled so we can't click it anymore. 

#### Arrow key controls
Moving your cursor around all the time is getting tedious -- let's add in some support for arrow keys. If I press the up arrow on my keyboard, it should move the tile below the empty tile upwards. And if there is nothing below the empty tile (i.e. empty tile is on the bottom row already), nothing should happen. 

#### Extra-challenging challenge: Auto-solve
Add a "Solve" button. When clicked, an "animation" should begin of the tiles automatically moving around in order to solve the puzzle. There should be a short delay between slides of each tile so we can actually see what is going on, and the tiles shouldn't be clickable to avoid interfering with the auto-solving process. 

For extra points, the number of moves should minimized. You can look up an algorithm to this if you want ([this](http://www.math.ubc.ca/~cass/courses/m308-02b/projects/grant/fifteen.html) or [this](http://jamie-wong.com/2011/10/16/fifteen-puzzle-algorithm/) might be useful).


## Solving the puzzle
If you want to learn how to actually solve the puzzle, here is an [instructable](https://www.instructables.com/id/How-To-Solve-The-15-Puzzle/) which shows you how!

Or if you are lazy and want to cheat, comment out the lines of code which re-shuffle at the beginning, so that it starts out unshuffled every time.
