package com.android.cs2450_androidproject.game;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Makes a Score object that can track player details
 * @author Miguel Geronimo
 */
public class Score {
    private static final String JSON_PLAYER_NAME = "player_name";
    private static final String JSON_SCORE = "score";
    private static final String JSON_CARD_NUMBER = "card_number";

    private String playerName;
    private String score;
    private String cardNumber;

    /**
     * Initializes fields to desired values
     * @param nameIn
     * @param scoreIn
     */
    public Score(String nameIn, int scoreIn, int numberOfCards) {
        playerName = nameIn;
        score = scoreIn + "";
        cardNumber = numberOfCards + "";
    }

    /**
     * Alt. Default Constructor
     */
    public Score() {
        this("Unknown", 0, 0);
    }

    /**
     * Makes a new Score from a JSON object derived from a JSON file
     * @param jsonObject
     * @throws JSONException
     */
    public Score(JSONObject jsonObject) throws JSONException {
        playerName = jsonObject.getString(JSON_PLAYER_NAME);
        score = jsonObject.getString(JSON_SCORE);
        cardNumber = jsonObject.getString(JSON_CARD_NUMBER);
    }

    /**
     * Gets the score
     * @return score
     */
    public String getScore() {
        return this.score;
    }

    /**
     * Gets the player name
     * @return player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Gets the number of cards that the game was played with
     * @return cardNumber
     */
    public String getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Converts this score to a JSON object
     * @return
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_PLAYER_NAME, this.getPlayerName());
        json.put(JSON_SCORE, this.getScore());
        json.put(JSON_CARD_NUMBER, this.getCardNumber());
        return json;
    }
}
