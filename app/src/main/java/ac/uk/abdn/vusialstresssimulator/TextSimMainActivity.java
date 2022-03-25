package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.icu.lang.UProperty;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;

import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Scanner;

public class TextSimMainActivity extends AppCompatActivity {

    //    private EditText defaultText;
    private Button effectButton;
    private Button overlayButton;

    private static final String[] overlay_options = new String[]{
            "None", "Blue", "Purple", "Pink", "Green", "Yellow", "Orange"
    };
    private static final String[] effect_options = new String[]{
            "None", "Shuffle", "Blurry", "Fade", "Double"
    };
    private String overlayRadioButtonString;
    private String effectRadioButtonString;
    private final AllButtonsTextSimFragment allButtonsTextSimFragment = new AllButtonsTextSimFragment();

    private Fragment dummyTextFragmentMain;
    private Fragment typeTextFragmentMain;
    private ObjectAnimator doubleTextAnimatorX;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int duration = 6000;
    private long startTime = System.currentTimeMillis();
    final Runnable rShuffle = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() - startTime < duration){         // runs until duration(6 seconds) is reached
                mHandler.postDelayed(this,250);         // delay every text shuffling
                EditText defaultText = findViewById(R.id.dummyEditTextFragment);
                String defaultTextString = defaultText.getText().toString();

                StringBuilder scrambledStringBuilder = new StringBuilder();     // Creates a String Builder which
                String scrambledWordHolder = new String(" ");         // creates a new empty string
                Scanner wordScanner = new Scanner(defaultTextString);       // creates a Scanner which goes through the whole string (scans)

                while (wordScanner.hasNextLine()) {
                    String wordScrambled = wordScanner.next();
                    // System.out.println(wordScrambled);
                    // System.out.println(scramble(wordScrambled));
                    scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
                }
                scrambledWordHolder = scrambledStringBuilder.toString().trim();     // removes the empty space at the end of the string
                Log.d("String", scrambledWordHolder);
                defaultText.setText(scrambledWordHolder);      // Sets the EditText Text to the new shuffled text
                Log.d("EditText String", defaultText.getText().toString());
                wordScanner.close();
                defaultText.invalidate();   // updates this part of the UI
            }
        }
    };

//    final Runnable rBlurry = new Runnable() {
//        @Override
//        public void run() {
//
//            if (System.currentTimeMillis() - startTime < duration) {         // runs until duration(6 seconds) is reached
//                mHandler.postDelayed(this, 250);         // delay every text shuffling
//                EditText defaultText = findViewById(R.id.dummyEditTextFragment);
//                defaultText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//                float filterRadius = defaultText.getTextSize() / 3;
//                BlurMaskFilter blurryFilter = new BlurMaskFilter(filterRadius, BlurMaskFilter.Blur.NORMAL);
//                defaultText.getPaint().setMaskFilter(blurryFilter);
//            }
////            defaultText.getPaint().setMaskFilter(null);
////            defaultText.invalidate();
////            defaultText.getPaint().reset();
////            defaultText.invalidate();   // updates this part of the UI
//        }
//
//    };


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
                        if (effectRadioButtonString.equals(effect_options[2])) {
                            Log.d("Effect", "Blurry");
                            effectButton.setText(effectRadioButtonString);
                        }
                        if (effectRadioButtonString.equals(effect_options[3])) {
                            Log.d("Effect", "Fade");
                            effectButton.setText(effectRadioButtonString);
                        }
                        if (effectRadioButtonString.equals(effect_options[4])) {
                            Log.d("Effect", "Double");
                            effectButton.setText(effectRadioButtonString);
                        }
                    }
                });

                effectAlertBuilder.setNegativeButton("CANCEL", null);
                effectAlertBuilder.show();
            }
        }
    }

    private static String scramble(char firstLetter, char lastLetter, String word) {        // Second Scramble Part
        String shuffledString = "" + firstLetter; // + first letter
        while (word.length() != 0) {
            int index = (int) Math.floor(Math.random() * word.length());
            char middleLetter = word.charAt(index);
            word = word.substring(0, index) + word.substring(index + 1);
            shuffledString += middleLetter;
        }
        return shuffledString + lastLetter; // + last letter
    }

    public static String scramble(String word) {        // First Scramble Part
        if (word.length() < 3) {        // if a word has <= 3 letters, it stays the same
            return word;
        }
        String middleLetters = word.substring(1, word.length() - 1);    // gets the middle letters of the word
        return scramble(word.charAt(0), word.charAt(word.length() - 1), middleLetters);
    }

    public void blurEffect(View view) {
        EditText defaultText = findViewById(R.id.dummyEditTextFragment);
        defaultText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        float filterRadius = defaultText.getTextSize() / 9;
        BlurMaskFilter blurryFilter = new BlurMaskFilter(filterRadius, BlurMaskFilter.Blur.NORMAL);
        defaultText.getPaint().setMaskFilter(blurryFilter);
    }

//    public void setShadowXY(Target view,  Property<View, Float>, float from, float to, Duration secs, Interpolator TimeInterpolator) {
//        EditText defaultText = findViewById(R.id.dummyEditTextFragment);
//        doubleTextAnimatorX = ObjectAnimator.ofFloat(view, Property,0.0f, 6.0f);
//        defaultText.setShadowLayer(3.0f, shadowX, 0.0f, Color.BLACK);
//    }

//    public void animShadowXY() {
//        EditText defaultText = findViewById(R.id.dummyEditTextFragment);
//        Animation doubleTextAnimatorXY = AnimationUtils.loadAnimation(this, R.anim.fade_out);
//        defaultText.animShadowXY(doubleTextAnimatorXY);
//    }


    public void startSim(View view) throws InterruptedException {

        String currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewTextSim).getClass().getSimpleName();
        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        // typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");

//        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frameLayoutDummyT);
        Log.i("Current Fragment", currentFragment);
//        if (currentFragment.getTag().equals("DEFAULT_TEXT_FRAGMENT")){
//            Log.d("YES", "DEFAULT TEXT FRAGMENT IS SHOWN!");
//        }
        if (allButtonsTextSimFragment.isVisible()) {
            // effectButton.setText("None");
            Toast.makeText(getApplicationContext(), "Choose text option first", Toast.LENGTH_LONG).show();
        }
        else {

            if (effectButton.getText().toString().equals(getResources().getString(R.string.add_effect)) || effectButton.getText().toString().equals("None")) {
                Toast.makeText(getApplicationContext(), "Choose effect first", Toast.LENGTH_LONG).show();
    //                        llSettings.setVisibility(View.GONE);
    //                        llMenu.setVisibility(View.VISIBLE);
    //                        textHelp.setText("Got It");
            } else {
    //                buttonPressed +=1 ;
    //                if (buttonPressed == 1) { // if pressed for the first time
                if (dummyTextFragmentMain.isVisible()) {

                    EditText defaultText = findViewById(R.id.dummyEditTextFragment);
                    String defaultTextString = defaultText.getText().toString();



                    if (effectButton.getText().toString().equals("Shuffle")) {
                        startTime = System.currentTimeMillis();
                        mHandler.post(rShuffle);
                    }
                    if (effectButton.getText().toString().equals("Blurry")) {
//                        startTime = System.currentTimeMillis();
//                        mHandler.post(rBlurry);
                        blurEffect(view);
                        // mHandler.postDelayed(rRemoveBlur, 6000);
                        Log.d("StartTime", "I am HERE");

//                        defaultText.getPaint().setMaskFilter(null);
//                        defaultText.invalidate();   // updates this part of the UI
                    }
//                    if (effectButton.getText().toString().equals("Fade")) {
//                        startTime = System.currentTimeMillis();
//                        mHandler.post(rTR);
//                    }
                    if (effectButton.getText().toString().equals("Double")) {
                        Log.d("EFFECT", "DOUBLE");
                        // YoYo.with(Techniques.Wave).duration(1000).repeat(1).playOn(defaultText);        // LAST I WAS TRYING TO DO THIS EFFECT!
//                        doubleTextAnimatorX = ObjectAnimator.ofFloat(defaultText.getShadowDx(), "shadowX",0.0f, 6.0f);
//                        doubleTextAnimatorX.setDuration(1000);
//                        doubleTextAnimatorX.setInterpolator(new LinearInterpolator());
//                        doubleTextAnimatorX.start();


                        defaultText.setShadowLayer(3.0f, 6.0f, 7.0f, Color.BLACK);
                        // defaultText.setShadowLayer();
                    } else {
                        Log.d("NO", "NOT OPEN OR NULL!");
                    }
                }
            }
        }
    }
}