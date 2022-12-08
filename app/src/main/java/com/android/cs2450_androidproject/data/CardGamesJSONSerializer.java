package com.android.cs2450_androidproject.data;

import android.content.Context;

import com.android.cs2450_androidproject.high_scores.Score;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * A class that allows scores to be written/read into a JSON file
 * @author Miguel Geronimo
 */
public class CardGamesJSONSerializer {
    private Context mContext;
    private String mFileName;

    /**
     * Creates a serializer that handles read/write from save file
     * @param ctx The context (Which might be "this" most of the time)
     * @param fileName The file that the scores will be read/written to
     */
    public CardGamesJSONSerializer(Context ctx, String fileName) {
        mContext = ctx;
        mFileName = fileName;
    }

    /**
     * Saves scores to the JSON file by dumping all the array's contents into it
     * Will overwrite the file with the new data, so it is important to load existing
     * data beforehand
     * @param scoreArray
     * @throws JSONException
     * @throws IOException
     */
    public void saveScores(ArrayList<Score> scoreArray) throws JSONException, IOException {
        // Build an Array in JSON
        JSONArray array = new JSONArray();
        for (Score scoreIterable : scoreArray) {
            // array.put(score.toJSON());
            array.put(scoreIterable.toJSON());

            // Write to the file on the disk
            Writer writer = null;
            try {
                OutputStream outStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
                writer = new OutputStreamWriter(outStream);
                writer.write(array.toString());
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }

    /**
     * Loads scores from the file and puts them into an Array List
     * Useful to call before saveScores() to prevent previous scores from being
     * deleted
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public ArrayList<Score> loadScores() throws IOException, JSONException {
        ArrayList<Score> scoreLoad = new ArrayList<Score>();
        BufferedReader reader = null;
        try {
            // OPen and read into StringBuilder
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Line breaks are omitted
                jsonString.append(line);
            }
            // Parse the JSON with JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // Build
            for (int i = 0; i < array.length(); i++) {
                scoreLoad.add(new Score(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // Ignore
        } finally {
            if (reader != null)
                reader.close();
        }
        return scoreLoad;
    }
}
