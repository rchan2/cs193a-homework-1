package com.example.raymondchan1994.randomnumberguessinggame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar maxNumberSlider = (SeekBar) findViewById(R.id.seekBar);
        maxNumberSlider.setOnSeekBarChangeListener(maxNumberSliderListener);

        TextView result = (TextView) findViewById(R.id.resultTextView);
        result.setText("");
    }

    private SeekBar.OnSeekBarChangeListener maxNumberSliderListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    updateMaxNumber(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private void updateMaxNumber(int progress) {
        TextView maxNumberDisplay = (TextView) findViewById(R.id.maxNumberDisplay);
        maxNumberDisplay.setText("The computer is currently choosing numbers between (0) and (" + String.valueOf(progress) + ")");
    }

    private int scoreValue = 0;

    public void submitGuess(View view) {
        EditText enteredGuess = (EditText) findViewById(R.id.editText);
        TextView result = (TextView) findViewById(R.id.resultTextView);
        TextView score = (TextView) findViewById(R.id.scoreTextView);

        try {
            Integer parsed = Integer.parseInt(enteredGuess.getText().toString());
        } catch (NumberFormatException e) {
            result.setTextColor(Color.RED);
            result.setText("You must enter an integer guess!");
            return;
        }

        SeekBar maxNumberSlider = (SeekBar) findViewById(R.id.seekBar);
        int maxNumber = maxNumberSlider.getProgress();
        Random rand = new Random();
        int computersPick = rand.nextInt(maxNumber + 1);


        if (Integer.valueOf(enteredGuess.getText().toString()) == computersPick) {
            result.setTextColor(Color.GREEN);
            result.setText("Congrats! You guessed correctly.");
            scoreValue++;
        } else {
            result.setTextColor(Color.RED);
            result.setText("Aw, you guessed wrong. The actual number was " + String.valueOf(computersPick));
            scoreValue--;
        }

        score.setText("Score: " + String.valueOf(scoreValue));
    }
}
