package com.example.android.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox redCB;
    private CheckBox greenCB;
    private CheckBox blueCB;
    private CheckBox yellowCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            ;
        }

        setContentView(R.layout.activity_main);
    }

    // method to start the game
    // - it evaluates the states of team checkboxes
    // - it opens the counter activity
    public void startGame(View view) {

        redCB = findViewById(R.id.redCB);
        greenCB = findViewById(R.id.greenCB);
        blueCB = findViewById(R.id.blueCB);
        yellowCB = findViewById(R.id.yellowCB);

        int noOfTeams = 0;

        if (redCB.isChecked()) {
            noOfTeams += 1;
        }

        if (greenCB.isChecked()) {
            noOfTeams += 1;
        }

        if (blueCB.isChecked()) {
            noOfTeams += 1;
        }

        if (yellowCB.isChecked()) {
            noOfTeams += 1;
        }

        switch (noOfTeams) {
            case 0:
                teamToast();
                break;
            case 1:
                teamToast();
                break;
            default:
                Intent openGame = new Intent(this, Counter.class);
                openGame.putExtra("isRedPlaying", redCB.isChecked());
                openGame.putExtra("isGreenPlaying", greenCB.isChecked());
                openGame.putExtra("isBluePlaying", blueCB.isChecked());
                openGame.putExtra("isYellowPlaying", yellowCB.isChecked());
                startActivity(openGame);
        }
    }

    public void teamToast() {
        Toast.makeText(this, R.string.teamToast, Toast.LENGTH_SHORT).show();
    }

}
