package com.example.android.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.CountDownTimer;
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

    public int totalRedPoints, totalGreenPoints, totalBluePoints, totalYellowPoints = 0;
    CountDownTimer rTimer = null;
    int maxPoint = 48;

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

        //data of teams playing from main activity
        boolean isRedPlaying = getIntent().getExtras().getBoolean("isRedPlaying");
        boolean isGreenPlaying = getIntent().getExtras().getBoolean("isGreenPlaying");
        boolean isBluePlaying = getIntent().getExtras().getBoolean("isBluePlaying");
        boolean isYellowPlaying = getIntent().getExtras().getBoolean("isYellowPlaying");

        //Remove teams that are not playing

        if (!isRedPlaying) {

            ViewGroup redTeam = (ViewGroup) findViewById(R.id.firstTeamGroup);
            ((ViewGroup) redTeam.getParent()).removeView(redTeam);

        }

        if (!isGreenPlaying) {

            ViewGroup greenTeam = (ViewGroup) findViewById(R.id.secondTeamGroup);
            ((ViewGroup) greenTeam.getParent()).removeView(greenTeam);

        }

        if (!isBluePlaying) {

            ViewGroup blueTeam = (ViewGroup) findViewById(R.id.thirdTeamGroup);
            ((ViewGroup) blueTeam.getParent()).removeView(blueTeam);

        }

        if (!isYellowPlaying) {

            ViewGroup yellowTeam = (ViewGroup) findViewById(R.id.fourthTeamGroup);
            ((ViewGroup) yellowTeam.getParent()).removeView(yellowTeam);

        }

    }

    //Method to update points of respective teams team
    public void displayPoints(int points, int teamId, String team, int counter) {
        TextView totalPoints = findViewById(teamId);
        totalPoints.setText(String.valueOf(points));
        maxPoint(team, counter);
    }

    //Red team cards
    public void add3Red(View view) {
        totalRedPoints += 3;
        displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);

        ImageView taskImg = findViewById(R.id.redTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalRedPoints, -1));
    }

    public void add4Red(View view) {
        totalRedPoints += 4;
        displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);

        ImageView taskImg = findViewById(R.id.redTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalRedPoints, -1));
    }

    public void add5Red(View view) {
        totalRedPoints += 5;
        displayPoints(totalRedPoints, R.id.totalPointsRed, getString(R.string.red_team), R.drawable.counter_r);

        ImageView taskImg = findViewById(R.id.redTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalRedPoints, -1));
    }

    //Green team cards
    public void add3Green(View view) {
        totalGreenPoints += 3;
        displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);

        ImageView taskImg = findViewById(R.id.greenTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
    }

    public void add4Green(View view) {
        totalGreenPoints += 4;
        displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);

        ImageView taskImg = findViewById(R.id.greenTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
    }

    public void add5Green(View view) {
        totalGreenPoints += 5;
        displayPoints(totalGreenPoints, R.id.totalPointsGreen, getString(R.string.green_team), R.drawable.counter_g);

        ImageView taskImg = findViewById(R.id.greenTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalGreenPoints, -1));
    }

    //Blue team cards
    public void add3Blue(View view) {
        totalBluePoints += 3;
        displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);

        ImageView taskImg = findViewById(R.id.blueTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalBluePoints, -1));
    }

    public void add4Blue(View view) {
        totalBluePoints += 4;
        displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);

        ImageView taskImg = findViewById(R.id.blueTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalBluePoints, -1));
    }

    public void add5Blue(View view) {
        totalBluePoints += 5;
        displayPoints(totalBluePoints, R.id.totalPointsBlue, getString(R.string.blue_team), R.drawable.counter_b);

        ImageView taskImg = findViewById(R.id.blueTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalBluePoints, -1));
    }

    //Yellow team cards
    public void add3Yellow(View view) {
        totalYellowPoints += 3;
        displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);

        ImageView taskImg = findViewById(R.id.yellowTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
    }

    public void add4Yellow(View view) {
        totalYellowPoints += 4;
        displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);

        ImageView taskImg = findViewById(R.id.yellowTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
    }

    public void add5Yellow(View view) {
        totalYellowPoints += 5;
        displayPoints(totalYellowPoints, R.id.totalPointsYellow, getString(R.string.yellow_team), R.drawable.counter_y);

        ImageView taskImg = findViewById(R.id.yellowTask);
        TypedArray imgs = getResources().obtainTypedArray(R.array.tasks);
        taskImg.setImageResource(imgs.getResourceId(totalYellowPoints, -1));
    }

    //Quit from the game
    public void quitGame(View view) {
        Intent quitGame = new Intent(this, MainActivity.class);
        startActivity(quitGame);
    }

    //Timer function
    public void start_timer(View view) {
        TextView timer_label = findViewById(R.id.timerLabel);
        String st1 = timer_label.getText().toString();
        Button start_button = findViewById(R.id.timerButton);

        if ("60".equals(st1)) {
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
            }

            public void onFinish() {

                Button start_button = (Button) findViewById(R.id.timerButton);
                start_button.setText(R.string.start_timer);
                tv.setText(R.string.timerNo);
                resetTimer();
                Toast.makeText(getApplicationContext(), R.string.timeIsUp, Toast.LENGTH_SHORT).show();

            }
        }.start();
    }

    void resetTimer() {
        if (rTimer != null)
            rTimer.cancel();
    }

    //checking if the new points has reached the max points
    public void maxPoint(String team, int counter) {
        if (totalRedPoints >= maxPoint || totalGreenPoints >= maxPoint || totalBluePoints >= maxPoint || totalYellowPoints >= maxPoint) {
            Toast.makeText(this, "Congratulations! The " + team + " won the game!", Toast.LENGTH_SHORT).show();
            removeCards(counter);
        }
    }

    // After a team reaches the max point the cards will disappear
    // and the winner will be announced
    public void removeCards(int counter) {

        boolean isRedPlaying = getIntent().getExtras().getBoolean("isRedPlaying");
        boolean isGreenPlaying = getIntent().getExtras().getBoolean("isGreenPlaying");
        boolean isBluePlaying = getIntent().getExtras().getBoolean("isBluePlaying");
        boolean isYellowPlaying = getIntent().getExtras().getBoolean("isYellowPlaying");

        if (isRedPlaying) {

            ImageView red3 = findViewById(R.id.red3);
            red3.setVisibility(View.GONE);

            ImageView red4 = findViewById(R.id.red4);
            red4.setVisibility(View.GONE);

            ImageView red5 = findViewById(R.id.red5);
            red5.setVisibility(View.GONE);

            ImageView task = findViewById(R.id.redTask);
            task.setVisibility(View.GONE);
        }

        if (isGreenPlaying) {

            ImageView green3 = findViewById(R.id.green3);
            green3.setVisibility(View.GONE);

            ImageView green4 = findViewById(R.id.green4);
            green4.setVisibility(View.GONE);

            ImageView green5 = findViewById(R.id.green5);
            green5.setVisibility(View.GONE);

            ImageView task = findViewById(R.id.greenTask);
            task.setVisibility(View.GONE);
        }

        if (isBluePlaying) {

            ImageView blue3 = findViewById(R.id.blue3);
            blue3.setVisibility(View.GONE);

            ImageView blue4 = findViewById(R.id.blue4);
            blue4.setVisibility(View.GONE);

            ImageView blue5 = findViewById(R.id.blue5);
            blue5.setVisibility(View.GONE);

            ImageView task = findViewById(R.id.blueTask);
            task.setVisibility(View.GONE);
        }

        if (isYellowPlaying) {

            ImageView yellow3 = findViewById(R.id.yellow3);
            yellow3.setVisibility(View.GONE);

            ImageView yellow4 = findViewById(R.id.yellow4);
            yellow4.setVisibility(View.GONE);

            ImageView yellow5 = findViewById(R.id.yellow5);
            yellow5.setVisibility(View.GONE);

            ImageView task = findViewById(R.id.yellowTask);
            task.setVisibility(View.GONE);
        }

        TextView winnerLabel = findViewById(R.id.winnerLabel);
        winnerLabel.setVisibility(View.VISIBLE);

        ImageView winnerCounter = findViewById(R.id.winnerCounter);
        winnerCounter.setImageResource(counter);
        winnerCounter.setVisibility(View.VISIBLE);

    }

}
