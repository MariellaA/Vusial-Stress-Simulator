package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TextSimMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_sim_main);

        Button chooseDefaultTextButton = findViewById(R.id.useDefaultTextButton);
        Button chooseTypeTextButton = findViewById(R.id.typeTextButton);
        // Button chooseScanTextButton = findViewById(R.id.scanTextButton);
        // Button chooseEffectButton = findViewById(R.id.effectTSimButtonMain);
        // Button chooseOverlayButton = findViewById(R.id.overlayTSimButtonMain);
        chooseDefaultTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent defaultTextIntent = new Intent(TextSimMainActivity.this, DummyTextSimActivity.class);
                startActivity(defaultTextIntent);
            }
        });
    }
}