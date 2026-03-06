package minerva;

import minerva.data.chatbot.Chatbot;

/**
 * Entry point of the Minerva Chatbot application.
 * Instantiates a chatbot instance and starts the interaction with the user.
 */
public class Minerva {
    public static void main(String[] args) {
        Chatbot minerva = new Chatbot();
        minerva.run();
    }

}
