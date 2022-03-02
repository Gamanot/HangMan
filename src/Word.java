import acm.graphics.GLabel;

import java.util.Random;

public class Word{
    GLabel wordOnScreen = new GLabel("");
    public String blanks = "";
    String library[] = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel", "cat", "clam", "cobra", "cougar",
            "coyote", "crow", "deer", "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose", "mouse", "mule", "newt",
            "otter", "owl", "panda", "parrot", "pigeon", "python", "rabbit", "ram", "rat", "raven",
            "rhino", "salmon", "seal", "shark", "sheep", "skunk", "sloth", "snake", "spider",
            "stork", "swan", "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };
    Random random = new Random();
    public int randomIdx = random.nextInt(library.length);
    public String word = (library[randomIdx]);
    int wordLength = word.length();
    }

