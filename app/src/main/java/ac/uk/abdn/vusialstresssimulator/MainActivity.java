package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {     // Displays an image for 4 sec before loading the Main Menu
            @Override
            public void run() {
                Intent greetIntent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(greetIntent);
                // finish;
            }
        }, SPLASH_TIME_OUT);
    }
}