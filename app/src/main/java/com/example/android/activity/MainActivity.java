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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {

        CheckBox redCB = findViewById(R.id.redCB);
        boolean isRedPlaying = redCB.isChecked();

        CheckBox greenCB = findViewById(R.id.greenCB);
        boolean isGreenPlaying = greenCB.isChecked();

        CheckBox blueCB = findViewById(R.id.blueCB);
        boolean isBluePlaying = blueCB.isChecked();

        CheckBox yellowCB = findViewById(R.id.yellowCB);
        boolean isYellowPlaying = yellowCB.isChecked();

        int noOfTeams = 0;

        if (isRedPlaying) {
            noOfTeams += 1;
        }

        if (isGreenPlaying) {
            noOfTeams += 1;
        }

        if (isBluePlaying) {
            noOfTeams += 1;
        }

        if (isYellowPlaying) {
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
                openGame.putExtra("isRedPlaying", isRedPlaying);
                openGame.putExtra("isGreenPlaying", isGreenPlaying);
                openGame.putExtra("isBluePlaying", isBluePlaying);
                openGame.putExtra("isYellowPlaying", isYellowPlaying);
                startActivity(openGame);
        }
    }

    public void teamToast() {
        Toast.makeText(this, R.string.teamToast, Toast.LENGTH_SHORT).show();
    }

}
