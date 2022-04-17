package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static int TIME_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {     // Displays text/image for 4 sec before loading the Main Menu
            @Override
            public void run() {
                Intent greetIntent = new Intent(MainActivity.this, MainMenuActivity.class);
                startActivity(greetIntent);
                overridePendingTransition(R.anim.zoom, R.anim.static_anim);
                // finish;
            }
        }, TIME_DELAY);
    }
}