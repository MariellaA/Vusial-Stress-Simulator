package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ImageSimMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sim_main);
        getSupportActionBar().setTitle("Image Simulation");
    }
}