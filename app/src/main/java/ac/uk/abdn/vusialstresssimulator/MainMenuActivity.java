package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button chooseTextSimButton = findViewById(R.id.textButton);
        Button chooseImageSimButton = findViewById(R.id.imageButton);
        chooseTextSimButton.setOnClickListener(new View.OnClickListener() {   // attaches a onClick listener on the button
            @Override
            public void onClick(View view) {
                Intent doTextSimIntent = new Intent(MainMenuActivity.this, TextSimMainActivity.class);
                startActivity(doTextSimIntent);
                Log.d("Editable", "The button got clicked");
            }
        });
        chooseImageSimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent doImageSimIntent = new Intent(MainMenuActivity.this, ImageSimMainActivity.class);
                startActivity(doImageSimIntent);
            }
        });
    }
}