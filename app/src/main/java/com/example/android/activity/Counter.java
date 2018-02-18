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
    public int totalRedPoints = 0;
    public int totalGreenPoints = 0;
    public int totalBluePoints = 0;
    public int totalYellowPoints = 0;
    CountDownTimer rTimer = null;
    int maxPoint = 48;

    //variables for views
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
    Button reset_button;
    Toast winnerToast;

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
        redTeam = findViewById(R.id.firstTeamGroup);
        greenTeam = findViewById(R.id.secondTeamGroup);
        blueTeam = findViewById(R.id.thirdTeamGroup);
        yellowTeam = findViewById(R.id.fourthTeamGroup);
        imgs = getResources().obtainTypedArray(R.array.tasks);
        timer_label = findViewById(R.id.timerLabel);
        timer_label_string = timer_label.getText().toString();
        start_button = findViewById(R.id.timerButton);
        reset_button = findViewById(R.id.resetButton);

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
            if (isBluePlaying) {
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

    /**
     * Method to update points of respective teams on rotation
     *
     * @param points  total points to be shown
     * @param teamId  resource id of the label of the team's points
     * @param team    name of the team
     * @param counter resource id of the counter image of the team
     */
    public void displayPoints(int points, int teamId, String team, int counter) {
        totalPoints = findViewById(teamId);
        totalPoints.setText(String.valueOf(points));
        maxPoint(team, counter);
        resetTimer();
    }

    /**
     * Method to update points of respective team
     *
     * @param points total points to be shown
     * @param teamId resource id of the label of the team's points
     */
    public void displayPoints2(int points, int teamId) {
        totalPoints = findViewById(teamId);
        totalPoints.setText(String.valueOf(points));
        resetTimer();
    }

    /**
     * Adds points to the teams based on the parameters given
     *
     * @param points            number of points to be added
     * @param totalTeamPoints   current number of points of respective team
     * @param totalTeamPointsID resource id of the label of the team's points
     * @param TeamNameID        resource id of the team name
     * @param counterDrawableID resource id of the team counter
     * @param taskID            resource id of the team task image
     * @return the new number of points
     */
    public int addPoints(int points, int totalTeamPoints, int totalTeamPointsID, int TeamNameID, int counterDrawableID, ImageView taskID) {
        totalTeamPoints += points;
        displayPoints2(totalTeamPoints, totalTeamPointsID);
        taskID.setImageResource(imgs.getResourceId(totalTeamPoints, -1));
        maxPoint2(totalTeamPoints, getString(TeamNameID), counterDrawableID);
        return totalTeamPoints;
    }

    public void onCardClick(View view) {
        switch (view.getId()) {
            case R.id.red3:         // Adds 3 points to the red team
                totalRedPoints = addPoints(3, totalRedPoints, R.id.totalPointsRed, R.string.red_team, R.drawable.counter_r, redTask);
                break;
            case R.id.red4:         // Adds 4 points to the red team
                totalRedPoints = addPoints(4, totalRedPoints, R.id.totalPointsRed, R.string.red_team, R.drawable.counter_r, redTask);
                break;
            case R.id.red5:         // Adds 5 points to the red team
                totalRedPoints = addPoints(5, totalRedPoints, R.id.totalPointsRed, R.string.red_team, R.drawable.counter_r, redTask);
                break;
            case R.id.green3:       // Adds 3 points to the green team
                totalGreenPoints = addPoints(3, totalGreenPoints, R.id.totalPointsGreen, R.string.green_team, R.drawable.counter_g, greenTask);
                break;
            case R.id.green4:       // Adds 4 points to the green team
                totalGreenPoints = addPoints(4, totalGreenPoints, R.id.totalPointsGreen, R.string.green_team, R.drawable.counter_g, greenTask);
                break;
            case R.id.green5:       // Adds 5 points to the green team
                totalGreenPoints = addPoints(5, totalGreenPoints, R.id.totalPointsGreen, R.string.green_team, R.drawable.counter_g, greenTask);
                break;
            case R.id.blue3:        // Adds 3 points to the blue team
                totalBluePoints = addPoints(3, totalBluePoints, R.id.totalPointsBlue, R.string.blue_team, R.drawable.counter_b, blueTask);
                break;
            case R.id.blue4:        // Adds 4 points to the blue team
                totalBluePoints = addPoints(4, totalBluePoints, R.id.totalPointsBlue, R.string.blue_team, R.drawable.counter_b, blueTask);
                break;
            case R.id.blue5:        // Adds 5 points to the blue team
                totalBluePoints = addPoints(5, totalBluePoints, R.id.totalPointsBlue, R.string.blue_team, R.drawable.counter_b, blueTask);
                break;
            case R.id.yellow3:      // Adds 3 points to the yellow team
                totalYellowPoints = addPoints(3, totalYellowPoints, R.id.totalPointsYellow, R.string.yellow_team, R.drawable.counter_y, yellowTask);
                break;
            case R.id.yellow4:      // Adds 4 points to the yellow team
                totalYellowPoints = addPoints(4, totalYellowPoints, R.id.totalPointsYellow, R.string.yellow_team, R.drawable.counter_y, yellowTask);
                break;
            case R.id.yellow5:      // Adds 5 points to the yellow team
                totalYellowPoints = addPoints(5, totalYellowPoints, R.id.totalPointsYellow, R.string.yellow_team, R.drawable.counter_y, yellowTask);
                break;
        }
    }

    //Quit from the game
    public void quitGame(View view) {
        resetTimer();
        Intent quitGame = new Intent(this, MainActivity.class);
        startActivity(quitGame);
    }

    //Resetting the points of the teams
    public void resetPoints(View view) {

        if (isRedPlaying) {
            totalRedPoints = 0;
            displayPoints(totalRedPoints, R.id.totalPointsRed, "Red team", R.drawable.counter_r);
            redTask.setImageResource(imgs.getResourceId(totalRedPoints, -1));
        }
        if (isGreenPlaying) {
            totalGreenPoints = 0;
            displayPoints(totalGreenPoints, R.id.totalPointsGreen, "Green team", R.drawable.counter_g);
            greenTask.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
        }
        if (isBluePlaying) {
            totalBluePoints = 0;
            displayPoints(totalBluePoints, R.id.totalPointsBlue, "Blue team", R.drawable.counter_b);
            blueTask.setImageResource(imgs.getResourceId(totalBluePoints, -1));
        }
        if (isYellowPlaying) {
            totalYellowPoints = 0;
            displayPoints(totalYellowPoints, R.id.totalPointsYellow, "Yellow team", R.drawable.counter_y);
            yellowTask.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
        }

        showCards();

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

    /**
     * @param Seconds initial time on the countdown timer
     * @param tv      TextView that shows the time left
     */
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
        if (rTimer != null) {
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
            winnerToast = Toast.makeText(this, getString(R.string.winner_toast, team), Toast.LENGTH_SHORT);
            winnerToast.show();
            removeCards(counter);
            reset_button.setText(getString(R.string.new_game));
        }
    }

    public void maxPoint2(int totalPoints, String team, int counter) {
        if (totalPoints >= maxPoint) {
            winnerToast = Toast.makeText(this, getString(R.string.winner_toast, team), Toast.LENGTH_SHORT);
            winnerToast.show();
            removeCards(counter);
            reset_button.setText(getString(R.string.new_game));
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

    // If the reset/new game button is clicked the
    // winner message has to be hidden
    // and the card has to be shown again
    public void showCards() {

        if (isRedPlaying) {
            red3.setVisibility(View.VISIBLE);
            red4.setVisibility(View.VISIBLE);
            red5.setVisibility(View.VISIBLE);
            redTask.setVisibility(View.VISIBLE);
        }

        if (isGreenPlaying) {
            green3.setVisibility(View.VISIBLE);
            green4.setVisibility(View.VISIBLE);
            green5.setVisibility(View.VISIBLE);
            greenTask.setVisibility(View.VISIBLE);
        }

        if (isBluePlaying) {
            blue3.setVisibility(View.VISIBLE);
            blue4.setVisibility(View.VISIBLE);
            blue5.setVisibility(View.VISIBLE);
            blueTask.setVisibility(View.VISIBLE);
        }

        if (isYellowPlaying) {
            yellow3.setVisibility(View.VISIBLE);
            yellow4.setVisibility(View.VISIBLE);
            yellow5.setVisibility(View.VISIBLE);
            yellowTask.setVisibility(View.VISIBLE);
        }

        winnerLabel.setVisibility(View.GONE);
        winnerCounter.setVisibility(View.GONE);
        if (winnerToast != null) {
            winnerToast.cancel();
        }
        reset_button.setText(getString(R.string.reset_points));

    }
}
