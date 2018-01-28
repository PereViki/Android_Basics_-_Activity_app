package com.example.android.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Counter extends AppCompatActivity {

    //Declaration of values used in onSaveInstanceState and onRestoreInstanceState methods
    static final String STATE_RED_POINTS = "totalRedPoints";
    static final String STATE_GREEN_POINTS = "totalGreenPoints";
    static final String STATE_BLUE_POINTS = "totalBluePoints";
    static final String STATE_YELLOW_POINTS = "totalYellowPoints";
    static final String STATE_TIMER_SECONDS = "timer_label_string";

    //Declarations of variables
    public int totalRedPoints, totalGreenPoints, totalBluePoints, totalYellowPoints = 0;
    CountDownTimer rTimer = null;
    int maxPoint = 48;

    boolean isRedPlaying;
    boolean isGreenPlaying;
    boolean isBluePlaying;
    boolean isYellowPlaying;
    ViewGroup redTeam;
    ViewGroup greenTeam;
    ViewGroup blueTeam;
    ViewGroup yellowTeam;
    TextView totalPoints;
    TextView winnerLabel;
    ImageView red3;
    ImageView red4;
    ImageView red5;
    ImageView green3;
    ImageView green4;
    ImageView green5;
    ImageView blue3;
    ImageView blue4;
    ImageView blue5;
    ImageView yellow3;
    ImageView yellow4;
    ImageView yellow5;
    ImageView redTask;
    ImageView greenTask;
    ImageView blueTask;
    ImageView yellowTask;
    ImageView winnerCounter;
    TypedArray imgs;
    String timer_label_string;
    TextView timer_label;
    Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_counter);

        red3 = findViewById(R.id.red3);
        red4 = findViewById(R.id.red4);
        red5 = findViewById(R.id.red5);
        green3 = findViewById(R.id.green3);
        green4 = findViewById(R.id.green4);
        green5 = findViewById(R.id.green5);
        blue3 = findViewById(R.id.blue3);
        blue4 = findViewById(R.id.blue4);
        blue5 = findViewById(R.id.blue5);
        yellow3 = findViewById(R.id.yellow3);
        yellow4 = findViewById(R.id.yellow4);
        yellow5 = findViewById(R.id.yellow5);
        redTask = findViewById(R.id.redTask);
        greenTask = findViewById(R.id.greenTask);
        blueTask = findViewById(R.id.blueTask);
        yellowTask = findViewById(R.id.yellowTask);
        winnerLabel = findViewById(R.id.winnerLabel);
        winnerCounter = findViewById(R.id.winnerCounter);
        redTeam = (ViewGroup) findViewById(R.id.firstTeamGroup);
        greenTeam = (ViewGroup) findViewById(R.id.secondTeamGroup);
        blueTeam = (ViewGroup) findViewById(R.id.thirdTeamGroup);
        yellowTeam = (ViewGroup) findViewById(R.id.fourthTeamGroup);
        imgs = getResources().obtainTypedArray(R.array.tasks);
        timer_label = findViewById(R.id.timerLabel);
        timer_label_string = timer_label.getText().toString();
        start_button = findViewById(R.id.timerButton);

        //data of teams playing from main activity
        isRedPlaying = getIntent().getExtras().getBoolean("isRedPlaying");
        isGreenPlaying = getIntent().getExtras().getBoolean("isGreenPlaying");
        isBluePlaying = getIntent().getExtras().getBoolean("isBluePlaying");
        isYellowPlaying = getIntent().getExtras().getBoolean("isYellowPlaying");

        //Remove teams that are not playing
        if (!isRedPlaying) {
            ((ViewGroup) redTeam.getParent()).removeView(redTeam);
        }
        if (!isGreenPlaying) {
            ((ViewGroup) greenTeam.getParent()).removeView(greenTeam);
        }
        if (!isBluePlaying) {
            ((ViewGroup) blueTeam.getParent()).removeView(blueTeam);
        }
        if (!isYellowPlaying) {
            ((ViewGroup) yellowTeam.getParent()).removeView(yellowTeam);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save the teams current points
        if (isRedPlaying) {
            savedInstanceState.putInt(STATE_RED_POINTS, totalRedPoints);
        }
        if (isGreenPlaying) {
            savedInstanceState.putInt(STATE_GREEN_POINTS, totalGreenPoints);
        }
        if (isBluePlaying) {
            savedInstanceState.putInt(STATE_BLUE_POINTS, totalBluePoints);
        }
        if (isYellowPlaying) {
            savedInstanceState.putInt(STATE_YELLOW_POINTS, totalYellowPoints);
        }

        savedInstanceState.putInt(STATE_TIMER_SECONDS, Integer.parseInt(timer_label_string));
        resetTimer();

        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore state members from saved instance
            if (isRedPlaying) {
                totalRedPoints = savedInstanceState.getInt(STATE_RED_POINTS);
                displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);
                redTask.setImageResource(imgs.getResourceId(totalRedPoints, -1));
            }
            if (isGreenPlaying) {
                totalGreenPoints = savedInstanceState.getInt(STATE_GREEN_POINTS);
                displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);
                greenTask.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
            }
            if(isBluePlaying) {
                totalBluePoints = savedInstanceState.getInt(STATE_BLUE_POINTS);
                displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);
                blueTask.setImageResource(imgs.getResourceId(totalBluePoints, -1));
            }
            if (isYellowPlaying) {
                totalYellowPoints = savedInstanceState.getInt(STATE_YELLOW_POINTS);
                displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);
                yellowTask.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
            }

            timer_label_string = Integer.toString(savedInstanceState.getInt(STATE_TIMER_SECONDS));
            if (!timer_label_string.equals("60")) {
                resetTimer();
                reverseTimer(savedInstanceState.getInt(STATE_TIMER_SECONDS), timer_label);
                start_button.setText(R.string.reset_timer);
            }

            if (totalRedPoints >= maxPoint) {
                winnerCounter.setImageResource(R.drawable.counter_r);
            }

            if (totalGreenPoints >= maxPoint) {
                winnerCounter.setImageResource(R.drawable.counter_g);
            }

            if (totalBluePoints >= maxPoint) {
                winnerCounter.setImageResource(R.drawable.counter_b);
            }

            if (totalYellowPoints >= maxPoint) {
                winnerCounter.setImageResource(R.drawable.counter_y);
            }

        }
    }

    //Method to update points of respective teams team
    public void displayPoints(int points, int teamId, String team, int counter) {
        totalPoints = findViewById(teamId);
        totalPoints.setText(String.valueOf(points));
        maxPoint(team, counter);
        resetTimer();
    }

    //Red team cards
    public void add3Red(View view) {
        totalRedPoints += 3;
        displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);
        redTask.setImageResource(imgs.getResourceId(totalRedPoints, -1));
    }

    public void add4Red(View view) {
        totalRedPoints += 4;
        displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);
        redTask.setImageResource(imgs.getResourceId(totalRedPoints, -1));
    }

    public void add5Red(View view) {
        totalRedPoints += 5;
        displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);
        redTask.setImageResource(imgs.getResourceId(totalRedPoints, -1));
    }

    //Green team cards
    public void add3Green(View view) {
        totalGreenPoints += 3;
        displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);
        greenTask.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
    }

    public void add4Green(View view) {
        totalGreenPoints += 4;
        displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);
        greenTask.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
    }

    public void add5Green(View view) {
        totalGreenPoints += 5;
        displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);
        greenTask.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
    }

    //Blue team cards
    public void add3Blue(View view) {
        totalBluePoints += 3;
        displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);
        blueTask.setImageResource(imgs.getResourceId(totalBluePoints, -1));
    }

    public void add4Blue(View view) {
        totalBluePoints += 4;
        displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);
        blueTask.setImageResource(imgs.getResourceId(totalBluePoints, -1));
    }

    public void add5Blue(View view) {
        totalBluePoints += 5;
        displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);
        blueTask.setImageResource(imgs.getResourceId(totalBluePoints, -1));
    }

    //Yellow team cards
    public void add3Yellow(View view) {
        totalYellowPoints += 3;
        displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);
        yellowTask.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
    }

    public void add4Yellow(View view) {
        totalYellowPoints += 4;
        displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);
        yellowTask.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
    }

    public void add5Yellow(View view) {
        totalYellowPoints += 5;
        displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);
        yellowTask.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
    }

    //Quit from the game
    public void quitGame(View view) {
        resetTimer();
        Intent quitGame = new Intent(this, MainActivity.class);
        startActivity(quitGame);
    }

    //Timer function
    public void start_timer(View view) {

        TextView timer_label = findViewById(R.id.timerLabel);
        timer_label_string = timer_label.getText().toString();
        Button start_button = findViewById(R.id.timerButton);

        if ("60".equals(timer_label_string)) {
            reverseTimer(59, timer_label);
            start_button.setText(R.string.reset_timer);
        } else {
            resetTimer();
            start_button.setText(R.string.start_timer);
            timer_label.setText(R.string.timerNo);
        }

    }

    public void reverseTimer(int Seconds, final TextView tv) {

        rTimer = new CountDownTimer(Seconds * 1000 + 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                seconds = seconds % 60;
                tv.setText(String.format("%02d", seconds));
                timer_label_string = Integer.toString(seconds);
            }

            public void onFinish() {

                resetTimer();
                Toast.makeText(getApplicationContext(), R.string.timeIsUp, Toast.LENGTH_SHORT).show();
                MediaPlayer player = MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_NOTIFICATION_URI);
                player.start();

            }
        }.start();
    }

    void resetTimer() {
        if (rTimer != null){
            //Button start_button = (Button) findViewById(R.id.timerButton);
            start_button.setText(R.string.start_timer);
            timer_label.setText(R.string.timerNo);
            rTimer.cancel();
            timer_label_string = "60";
        }
    }

    //checking if the new points has reached the max points
    public void maxPoint(String team, int counter) {
        if (totalRedPoints >= maxPoint || totalGreenPoints >= maxPoint || totalBluePoints >= maxPoint || totalYellowPoints >= maxPoint) {
            Toast.makeText(this, getString(R.string.winner_toast, team), Toast.LENGTH_SHORT).show();
            removeCards(counter);
        }
    }

    // After a team reaches the max point the cards will disappear
    // and the winner will be announced
    public void removeCards(int counter) {

      if (isRedPlaying) {
            red3.setVisibility(View.GONE);
            red4.setVisibility(View.GONE);
            red5.setVisibility(View.GONE);
            redTask.setVisibility(View.GONE);
        }

        if (isGreenPlaying) {
            green3.setVisibility(View.GONE);
            green4.setVisibility(View.GONE);
            green5.setVisibility(View.GONE);
            greenTask.setVisibility(View.GONE);
        }

        if (isBluePlaying) {
            blue3.setVisibility(View.GONE);
            blue4.setVisibility(View.GONE);
            blue5.setVisibility(View.GONE);
            blueTask.setVisibility(View.GONE);
        }

        if (isYellowPlaying) {
            yellow3.setVisibility(View.GONE);
            yellow4.setVisibility(View.GONE);
            yellow5.setVisibility(View.GONE);
            yellowTask.setVisibility(View.GONE);
        }

        winnerLabel.setVisibility(View.VISIBLE);
        winnerCounter.setImageResource(counter);
        winnerCounter.setVisibility(View.VISIBLE);

    }

}
