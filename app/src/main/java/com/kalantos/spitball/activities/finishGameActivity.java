package com.kalantos.spitball.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kalantos.spitball.R;
import com.kalantos.spitball.engine.Timer;

public class finishGameActivity extends AppCompatActivity {

    ImageView winnerImage;
    TextView winnerBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        winnerBanner = (TextView) findViewById(R.id.textView);
        chooseWinner();

	    final View decorView = getWindow().getDecorView();
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE;

        getWindow().getDecorView().setSystemUiVisibility(flags);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility)
                    {
                        if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                        {
                            Thread thread = new Thread(new Timer());
                            thread.start();
                            try {
                                thread.join();
                            } catch (InterruptedException e) {
                                Log.e("AUTO-HIDE BAR", e.getMessage());
                            }

                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
        Thread adThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Log.e("ADS", e.getMessage());
                }
                restartGame();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    Log.e("ADS", e.getMessage());
                }
            }
        });
        adThread.start();
    }
    @Override
    public void onBackPressed() {
        restartGame();
        }


    private void chooseWinner(){
    /*
    * Choose a winner and show the stats
    * */
        winnerImage = (ImageView)findViewById(R.id.imageView);
        winnerBanner = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        int green = extras.getInt("green");
        int pink = extras.getInt("pink");
        if(pink == 0){
            if(winnerBanner != null){
                winnerBanner.setText(R.string.winnerGreen);
            }
            Drawable pic = getResources().getDrawable(R.drawable.ballgreen);
            winnerImage.setImageDrawable(pic);
        }
        else if(green == 0){
            if(winnerBanner!=null) {
                winnerBanner.setText(R.string.winnerPink);
            }
            Drawable pic = getResources().getDrawable(R.drawable.ballpink);
            winnerImage.setImageDrawable(pic);
        }else{
            if(winnerBanner != null) {
                winnerBanner.setText(R.string.connectionError);
            }
            Drawable pic = getResources().getDrawable(R.drawable.settings);
            winnerImage.setImageDrawable(pic);
        }

    }

    private void restartGame(){
   /*
    * Returns to menu.
    * */
        Intent intent = new Intent(finishGameActivity.this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();

    }

}
