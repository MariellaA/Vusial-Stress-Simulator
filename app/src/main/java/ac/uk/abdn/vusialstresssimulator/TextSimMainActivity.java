package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;

import java.util.Scanner;

public class TextSimMainActivity extends AppCompatActivity {

//    private EditText defaultText;
    private Button effectButton;
    private Button overlayButton;
    private static final String[] overlay_options = new String[]{
            "None", "Blue", "Purple", "Pink", "Green", "Yellow", "Orange"
    };
    private static final String[] effect_options = new String[]{
            "None", "Shuffle", "Blurry", "Fizzing", "Wavy"
    };
    private String overlayRadioButtonString;
    private String effectRadioButtonString;
    private final AllButtonsTextSimFragment allButtonsTextSimFragment = new AllButtonsTextSimFragment();

    private Fragment dummyTextFragmentMain;
    private Fragment typeTextFragmentMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_sim_main);

        Button startTSimButton = findViewById(R.id.startTSimButtonMain);
        effectButton = findViewById(R.id.effectTSimButtonMain);
        overlayButton = findViewById(R.id.overlayTSimButtonMain);


        FragmentManager fragmentTSimManager = getSupportFragmentManager();
        FragmentTransaction fragmentTSimTransaction = fragmentTSimManager.beginTransaction();

        fragmentTSimTransaction.add(R.id.fragmentContainerViewTextSim, allButtonsTextSimFragment, "ALL_BUTTONS_FRAGMENT").commit();


        startTSimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                shuffleEffect();
                try {
                    startSim(view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                if (allButtonsTextSimFragment.isVisible()) {
//                    Toast.makeText(getApplicationContext(), "Choose text option first", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    if (effectButton.getText().toString().equals(getResources().getString(R.string.add_effect)) || effectButton.getText().toString().equals("None")) {
//                        Toast.makeText(getApplicationContext(), "Choose effect first", Toast.LENGTH_LONG).show();
////                        llSettings.setVisibility(View.GONE);
////                        llMenu.setVisibility(View.VISIBLE);
////                        textHelp.setText("Got It");
//                    } else {
//                        if (effectButton.getText().toString().equals("Shuffle")) {
//                        //                        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
//                        //                        EditText defaultText = dummyTextFragmentMain.getView().findViewById(R.id.dummyEditTextFragment);
//                        //                        EditText defaultText = findViewById(R.id.dummyEditTextFragment);
//                        //                        String defaultTextString = defaultText.getText().toString();
////                            long timer = System.currentTimeMillis();
////                            long stopAt = timer + 20000;
////                            Log.d("OVERLAY", "NONE");
////                            // shuffleEffect(defaultText);
////                            while (System.currentTimeMillis() < stopAt) {
//
//                                try { // THE WHOLE THING ACTUALLY CHANGES THE TEXT ONLY IF BUTTON IS MANUALLY PRESSED
//                                    //                               shuffleEffect();  // shuffleEffect(defaultText);    called 13 times fo 13 sec
//                                    //                                EditText defaultText = findViewById(R.id.dummyEditTextFragment);
//                                    //                                String defaultTextString = defaultText.getText().toString();
//                                    //                                String scrambledWordHolder = new String(" ");
//                                    //                                StringBuilder scrambledStringBuilder = new StringBuilder();
//                                    //                                Scanner wordScanner = new Scanner(defaultTextString);
//                                    //                                while (wordScanner.hasNextLine()) {
//                                    //                                    String wordScrambled = wordScanner.next();
//                                    //                                    // System.out.println(wordScrambled);
//                                    //                                    // System.out.println(scramble(wordScrambled));
//                                    //                                    scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
//                                    //                                }
//                                    //                                scrambledWordHolder = scrambledStringBuilder.toString().trim();
//                                    //                                defaultText.setText(scrambledWordHolder);
//                                    //                                // Log.d("String", scrambledWordHolder);
//                                    //                                Log.d("EditText String", defaultText.getText().toString());
//                                    //                                wordScanner.close();
////                                    shuffleEffect();
//                                    startSim(view);
//                                    Thread.sleep(1000);
//
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            //}
//                        }
//
////                        try {
////                            startSim(view);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
//                    }
//                }
        //                }
        //                if (buttonPressed == 2) {
        //                    finish();
        //                }
            }
        });
//        startTSimButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (allButtonsTextSimFragment.isVisible()) {
//                    Toast.makeText(getApplicationContext(), "Choose text option first", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    if (effectButton.getText().toString().equals(getResources().getString(R.string.add_effect)) || effectButton.getText().toString().equals("None")) {
//                        Toast.makeText(getApplicationContext(), "Choose effect first", Toast.LENGTH_LONG).show();
////                        llSettings.setVisibility(View.GONE);
////                        llMenu.setVisibility(View.VISIBLE);
////                        textHelp.setText("Got It");
//                    }
//                    else {
//                        if (buttonPressed == 0) { // if pressed for the first time
//                            try {
//                                buttonPressed = 1;
//                                while (buttonPressed == 1) {
//                                    startSim(view);
//                                    // startTSimButton.setText(getResources().getString(R.string.stop_simulation));
//                                    Thread.sleep(1500);
//                                }
//                            }
//                            catch(InterruptedException e)
//                            {
//                                e.printStackTrace();
//                            }
//                        }
//                        if (buttonPressed == 1)
//                        {
//                            buttonPressed = 0;
//                            // startTSimButton.setText(getResources().getString(R.string.start_simulation));
//                            finish();
//                        }
//                    }
////                    rlOverlay.setVisibility(View.GONE);
////                    textMessage.setVisibility(View.VISIBLE);
//
//                }
//            }
//        });
    }

    // https://www.youtube.com/watch?v=DyJ4hOS3qrQ&ab_channel=AndroidRion
    public void chooseOverlay(View view) {
        int id = view.getId();  // gets button's ID
        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        // typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");
        if (allButtonsTextSimFragment.isVisible()) {
            Toast.makeText(getApplicationContext(), "Choose text option first", Toast.LENGTH_LONG).show();
        } else {
            if (id == R.id.overlayTSimButtonMain) {
                overlayRadioButtonString = overlay_options[0];
                AlertDialog.Builder overlayAlertBuilder = new AlertDialog.Builder(this);
                overlayAlertBuilder.setTitle("Choose overlay");
                overlayAlertBuilder.setSingleChoiceItems(overlay_options, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        overlayRadioButtonString = overlay_options[i];
                    }
                });

                overlayAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(view, overlayRadioButtonString, Snackbar.LENGTH_SHORT).show();
                        if (dummyTextFragmentMain.isVisible()) {     // add || typeTextFragmentMain.isVisible()
                            // Log.d("WORKS", "THIS WORKS");
                            if (overlayRadioButtonString.equals(overlay_options[0])) {
                                Log.d("OVERLAY", "NONE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[1])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                Log.d("OVERLAY", "IT IS BLUE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[2])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(204, 204, 255));
                                Log.d("OVERLAY", "IT IS PURPLE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[3])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 204, 255));
                                Log.d("OVERLAY", "IT IS PINK");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[4])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(204, 255, 204));
                                Log.d("OVERLAY", "IT IS GREEN");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[5])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 255, 204));
                                Log.d("OVERLAY", "IT IS YELLOW");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[6])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 217, 179));
                                Log.d("OVERLAY", "IT IS ORANGE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                        }
                    }
                });

                overlayAlertBuilder.setNegativeButton("CANCEL", null);
                overlayAlertBuilder.show();
            }
        }
    }

    public void chooseEffect(View view) {
        int id = view.getId();
        // dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        // typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");
        if (allButtonsTextSimFragment.isVisible()) {
            Toast.makeText(getApplicationContext(), "Choose text option first", Toast.LENGTH_LONG).show();
        } else {
            if (id == R.id.effectTSimButtonMain) {
                effectRadioButtonString = effect_options[0];
                AlertDialog.Builder effectAlertBuilder = new AlertDialog.Builder(this);
                effectAlertBuilder.setTitle("Choose effect");
                effectAlertBuilder.setSingleChoiceItems(effect_options, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        effectRadioButtonString = effect_options[i];
                    }
                });
                effectAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(view, effectRadioButtonString, Snackbar.LENGTH_SHORT).show();
                        if (effectRadioButtonString.equals(effect_options[0])) {
                            Log.d("Effect", "None");
                            effectButton.setText(effectRadioButtonString);
                        }
                        if (effectRadioButtonString.equals(effect_options[1])) {
                            Log.d("Effect", "Shuffle");
                            effectButton.setText(effectRadioButtonString);
                        }
                        if (effectRadioButtonString.equals(effect_options[4])) {
                            Log.d("Effect", "Wavy");
                            effectButton.setText(effectRadioButtonString);
                        }

                    }
                });

                effectAlertBuilder.setNegativeButton("CANCEL", null);
                effectAlertBuilder.show();
            }
        }
    }

    private static String scramble(char first, char last, String word) {
        String shuffledString = "" + first; // + first letter
        while (word.length() != 0) {
            int index = (int) Math.floor(Math.random() * word.length());
            char c = word.charAt(index);
            word = word.substring(0, index) + word.substring(index + 1);
            shuffledString += c;
        }
        return shuffledString + last; // + last letter
    }

    public static String scramble(String word) {
        if (word.length() < 3) {
            return word;
        }
        String middleLetters = word.substring(1, word.length() - 1);
        return scramble(word.charAt(0), word.charAt(word.length() - 1), middleLetters);
    }

    public void shuffleEffect() {
        EditText defaultText = findViewById(R.id.dummyEditTextFragment);
        String defaultTextString = defaultText.getText().toString();
        String scrambledWordHolder = new String(" ");
        StringBuilder scrambledStringBuilder = new StringBuilder();
        Scanner wordScanner = new Scanner(defaultTextString);
        while (wordScanner.hasNextLine()) {
            String wordScrambled = wordScanner.next();
            // System.out.println(wordScrambled);
            // System.out.println(scramble(wordScrambled));
            scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
        }
        scrambledWordHolder = scrambledStringBuilder.toString().trim();
        defaultText.setText(scrambledWordHolder);
        // Log.d("String", scrambledWordHolder);
        Log.d("EditText String", defaultText.getText().toString());
        wordScanner.close();
    }

    public void startSim(View view) throws InterruptedException {

        String currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewTextSim).getClass().getSimpleName();
        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        // typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");

//        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutDummyT);
        Log.i("Current Fragment", currentFragment);
//        if (currentFragment.getTag().equals("DEFAULT_TEXT_FRAGMENT")){
//            Log.d("YES", "DEFAULT TEXT FRAGMENT IS SHOWN!");
//        }
//        if (allButtonsTextSimFragment.isVisible()) {
//            Toast.makeText(getApplicationContext(), "Choose text option first", Toast.LENGTH_LONG).show();
//        } else {
//            if (effectButton.getText().toString().equals(getResources().getString(R.string.add_effect)) || effectButton.getText().toString().equals("None")) {
//                Toast.makeText(getApplicationContext(), "Choose effect first", Toast.LENGTH_LONG).show();
////                        llSettings.setVisibility(View.GONE);
////                        llMenu.setVisibility(View.VISIBLE);
////                        textHelp.setText("Got It");
//            } else {
//                buttonPressed +=1 ;
//                if (buttonPressed == 1) { // if pressed for the first time
        if (dummyTextFragmentMain.isVisible()) {

            EditText defaultText = findViewById(R.id.dummyEditTextFragment);
            String defaultTextString = defaultText.getText().toString();

            if (effectButton.getText().toString().equals("Shuffle")) {
//                            try {
                // buttonPressed = 1;
//                            startTSimButton.setPressed(false);
                long timer = System.currentTimeMillis();
                long stopAt = timer + 20000;
                Log.d("OVERLAY", "NONE");
                // shuffleEffect(defaultText);
                while (System.currentTimeMillis() < stopAt) {

                    StringBuilder scrambledStringBuilder = new StringBuilder();
                    String scrambledWordHolder = new String(" ");
                    Scanner wordScanner = new Scanner(defaultTextString);

//                    try {
//                        // Thread.sleep(1000);
                        while (wordScanner.hasNextLine()) {
                            String wordScrambled = wordScanner.next();
                            // System.out.println(wordScrambled);
                            // System.out.println(scramble(wordScrambled));
                            scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
                        }
                        scrambledWordHolder = scrambledStringBuilder.toString().trim();
                        Log.d("String", scrambledWordHolder);
                        defaultText.setText(scrambledWordHolder);
                        Log.d("EditText String", defaultText.getText().toString());
                        wordScanner.close();
                        defaultText.invalidate();
                        // startTSimButton.setText(getResources().getString(R.string.stop_simulation));
                        Thread.sleep(1500); // For loop

//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
            if (effectButton.getText().toString().equals("Wavy")){
                Log.d("EFFECT", "WAVY");
                YoYo.with(Techniques.Wave).duration(1000).repeat(1).playOn(defaultText);        // LAST I WAS TRYING TO DO THIS EFFECT!
            }
            else {
                Log.d("NO", "NOT OPEN OR NULL!");
            }

            //    startTSimButton.setText(getResources().getString(R.string.stop_simulation));
            // Log.d("YES", "DEFAULT TEXT FRAGMENT IS VISIBLE!");
            //                   }
        }
//                }
//                else {
//                    finish();
//                }
//            }
//        }
//            if (getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT").isVisible())
//            {
//                Log.d("YES", "TYPE TEXT FRAGMENT IS VISIBLE!");
//            }
//                    rlOverlay.setVisibility(View.GONE);
//                    textMessage.setVisibility(View.VISIBLE);

    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String chosenOverlay = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(adapterView.getContext(), chosenOverlay, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater overlayInflater = getMenuInflater();
//        overlayInflater.inflate(R.menu.overlays_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    private class buttonOnClicks implements View.OnClickListener {
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.overlayTSimButtonMain: {
//                    showOverlays(view);
//                    break;
//                }
////                case R.id.effectTSimButtonMain: {
////                    // showEffects(view);
////                    break;
////                }
////                case R.id.startTSimButton: {
////                    // startSim(view);
////                    break;
////                }
//            }
//        }
//    }

}