import acm.graphics.*;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;


public class Hangman extends GraphicsProgram {

    public int lives = 6;
    Word word = new Word();
    int wordLength = word.wordLength;
    String letterLibrary = "";
    String slashes = "";
    GLabel wordBank = new GLabel("Word bank: _________");
    GLabel wrong = new GLabel(slashes);
    GLabel Library = new GLabel(letterLibrary);
    GLabel guessedLetters = new GLabel("Word bank: ");
    GLabel livesLabel = new GLabel("lives left: " + lives);
    GLabel lose = new GLabel("You lose");
    GLine body = new GLine(376.5, 196, 376.5, 196 + 100);
    GOval head = new GOval(50, 50);
    GLine armL = new GLine(376.5, 196.0, 376.5 - 100, 196.0 + 50);
    GLine armR = new GLine(376.5, 196.0, 376.5 + 100, 196.0 + 50);
    GLine legL = new GLine(376.5, 196.0 + 196.0 / 2, 376.5 - 100, 196.0 + 150);
    GLine legR = new GLine(376.5, 196.0 + 196.0 / 2, 376.5 + 100, 196.0 + 150);
    String letters = "";
    int countdown  = wordLength;


    @Override
    public void init(){
        drawHangman();
        add(livesLabel, getWidth()/2 - livesLabel.getWidth()/2, getHeight()/2 - livesLabel.getHeight()/2 + 200);
        add(Library, getWidth()/2 - Library.getWidth()/2, getHeight()/2 - Library.getHeight()/2 + 220);
        add(wordBank, Library.getX() - 65, Library.getY());
        add(wrong, Library.getX() + Library.getWidth(), Library.getY());
        setLabel();
        playerGuess(Dialog.getString("Please guess a letter"));
    }

    public void playerGuess(String input){

            if (input.length() > 1) {
                Dialog.showMessage("One letter at a time");
                playerGuess(Dialog.getString("Please guess a letter"));
            } else if (input.length() < 0 ) {
                Dialog.showMessage("Please put in one letter");
            } else if (word.word.contains(input)) {
                int count = 0;                        // count initially 0
                char inPot = input.charAt(0);
                for (int i = 0; i < wordLength; i++)  // loop through the whole string
                    if (word.word.charAt(i) == inPot) {
                        count++;
                    }
                Dialog.showMessage("Good job! the word does contain the letter " + input + ".");
                for (int i = 0; i < count; i++) {

                    letters = inPot + " ";

                    int index = word.word.indexOf(inPot);
                    while(index >= 0) {
                        GLabel e = new GLabel("");
                        e.setLabel(letters);
                        add(e, (word.wordOnScreen.getX() + (word.wordOnScreen.getWidth()/wordLength) * index), word.wordOnScreen.getY());
                        index = word.word.indexOf(inPot, index + 1);
                    }


                }
                countdown -= count;
                System.out.println(count);
                if(countdown == 0){
                    win();
                }
            } else {
                Dialog.showMessage("Oops, the word does not contain the letter " + input + ".");
                letterLibrary += input + " ";
                Library.setLabel(letterLibrary);
                guessedLetters.setLabel(input);
                slashes += "-- ";
                wrong.setLabel(slashes);
                lives -= 1;
                livesLabel.setLabel("lives left: " + lives);

                delete();
            }
            if (lives == 0) {
                lose();
            } else {
                playerGuess(Dialog.getString("Please guess a letter"));
            }

    }

    public void setLabel(){
        for (int i = 0; i < word.wordLength; i++) {
            word.blanks += "_ ";
            word.wordOnScreen.setLabel(word.blanks);
        }
        System.out.println(word.word);
        add(word.wordOnScreen, getWidth()/2 - word.wordOnScreen.getWidth()/2, getHeight()/2 - word.wordOnScreen.getHeight()/2 + 150);

    }

    public void drawHangman(){
            add(head, 376.5 - head.getWidth() / 2, 196.0 - head.getHeight());
            add(legL);
            add(legR);
            add(armL);
            add(armR);
            add(body);

    }

    public void win(){
        Dialog.showMessage("You win");
        removeAll();
        exit();
    }
    public void delete(){
        switch (lives){
            case 0:
                remove(head);
                break;
            case 1:
                remove(body);
                break;
            case 2:
                remove(armL);
                break;
            case 3:
                remove(armR);
                break;
            case 4:
                remove(legL);
                break;
            case 5:
                remove(legR);
                break;
        }
    }

    public void lose(){
        remove(livesLabel);
        remove(guessedLetters);
        add(lose, getWidth()/2 - lose.getWidth()/2, getHeight()/2 - lose.getHeight()/2 + 200);
        Dialog.showMessage("You Lose!");
        removeAll();
        exit();
    }

    public static void main(String[] args) {
        Hangman man = new Hangman();
        man.start();

    }
}
