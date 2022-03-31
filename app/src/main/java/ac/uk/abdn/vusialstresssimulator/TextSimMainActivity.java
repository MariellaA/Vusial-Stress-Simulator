package ac.uk.abdn.vusialstresssimulator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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

    private String typeTextStringOriginal;
    private String[] splitDummyText;
    private String[] splitTypeText;
    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String TEXT_ORIGINAL = "textOriginal";
    private int changed = 0;


    private final Handler mHandlerShuffle = new Handler(Looper.getMainLooper());
    private final Handler mHandlerFade = new Handler(Looper.getMainLooper());
    private final Handler mHandlerButtonReset = new Handler(Looper.getMainLooper());
    private final Handler mHandlerDouble = new Handler(Looper.getMainLooper());
    private final int duration = 10000;
    private final int durationFadeReverse = 250;
    private final int durationButtonReset = 11000;
    private long startTime = System.currentTimeMillis();

// --------------------------- Effects - Start -------------------------------------
    final Runnable rShuffleDefault = new Runnable() {
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            effectButton = findViewById(R.id.effectTSimButtonMain);
            overlayButton = findViewById(R.id.overlayTSimButtonMain);
            if (System.currentTimeMillis() - startTime < duration){         // runs until duration(6 seconds) is reached
                mHandlerShuffle.postDelayed(this,700);         // delay every text shuffling


                startTSimButton.setVisibility(View.INVISIBLE);
                effectButton.setClickable(false);
                overlayButton.setClickable(false);

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

    final Runnable rShuffleType = new Runnable() {
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            effectButton = findViewById(R.id.effectTSimButtonMain);
            overlayButton = findViewById(R.id.overlayTSimButtonMain);

            if (System.currentTimeMillis() - startTime < duration){
                mHandlerShuffle.postDelayed(this,700);


                startTSimButton.setVisibility(View.INVISIBLE);
                effectButton.setClickable(false);
                overlayButton.setClickable(false);

                String defaultTextString = typeText.getText().toString();

                StringBuilder scrambledStringBuilder = new StringBuilder();
                String scrambledWordHolder = new String(" ");
                Scanner wordScanner = new Scanner(defaultTextString);

                while (wordScanner.hasNextLine()) {
                    String wordScrambled = wordScanner.next();
                    // System.out.println(wordScrambled);
                    // System.out.println(scramble(wordScrambled));
                    scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
                }
                scrambledWordHolder = scrambledStringBuilder.toString().trim();
                Log.d("String", scrambledWordHolder);
                typeText.setText(scrambledWordHolder);
                Log.d("EditText String", typeText.getText().toString());
                wordScanner.close();
                typeText.invalidate();
            }
        }
    };

    final Runnable rButtonReset = new Runnable() {
        public void run() {
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            effectButton = findViewById(R.id.effectTSimButtonMain);
            overlayButton = findViewById(R.id.overlayTSimButtonMain);
            if (System.currentTimeMillis() - startTime < 11000){
                //mHandlerShuffle.postDelayed(this,10500);
                startTSimButton.setVisibility(View.VISIBLE);
                effectButton.setClickable(true);
                overlayButton.setClickable(true);
                Log.d("RESET BUTTON", "RESET THAT GOD DAMN BUTTON");
            }
        }
    };

    final Runnable rFadeDefault = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerFade.postDelayed(this,500);

                String defaultTextString = defaultText.getText().toString();
                startTSimButton.setVisibility(View.INVISIBLE);

                splitDummyText = defaultTextString.split(" ");     // splits the text and stores the original in a list
                // String[] temporaryText = splitDummyText;    // Temporary list

                int wordIndex = (int) (Math.random() * splitDummyText.length-1);    // random word index
                String currentWord = splitDummyText[wordIndex];

                if (currentWord.matches("\\s+")) {       // if it contains white space
                    wordIndex = (int) (Math.random() * splitDummyText.length-1);
                    Log.d("Word EMPTY", "Empty");
                }
                if (!currentWord.matches("\\s") && currentWord.length() > 3){       // if it doesn't contain any white spaces

                    StringBuilder wordAtIndexCharsReplace = new StringBuilder();    // holds current word?
                    // if (currentWord.substring(currentWord.length() - 1).equals(".")) {
                    if (currentWord.endsWith(".")) {
                        // wordlength = currentWord.length() -2;
                        Log.d("Word with dot", splitDummyText[wordIndex]);
                        for (int i = 0; i < currentWord.length() - 1; i++) {   // for every letter in word -> replace it with "-"
                            wordAtIndexCharsReplace.append("" + "¬");

                            // System.out.println("last character: " + currentWord.substring(currentWord.length() - 2));
                        }
                        wordAtIndexCharsReplace.append("" + ".");
                    }
                    //if (currentWord.substring(currentWord.length() - 1).equals(",")) {
                    if (currentWord.endsWith(",")) {
                        // wordlength = currentWord.length() -2;
                        Log.d("Word with comma", splitDummyText[wordIndex]);
                        for (int i = 0; i < currentWord.length() - 1; i++) {   // for every letter in word -> replace it with "-"
                            wordAtIndexCharsReplace.append("" + "¬");

                            // System.out.println("last character: " + currentWord.substring(currentWord.length() - 2));
                        }
                        wordAtIndexCharsReplace.append("" + ",");
                    } else {
                        // wordlength = currentWord.length();
                        Log.d("Word", splitDummyText[wordIndex]);
                        for (int i = 0; i < currentWord.length() - 1; i++) {   // for every letter in word -> replace it with "-"
                            wordAtIndexCharsReplace.append("" + "¬");

                        }
                    }

                    splitDummyText[wordIndex] = wordAtIndexCharsReplace.toString();  // changes the word in the list to its new form

                    StringBuilder finalTextBuilder = new StringBuilder();
                    for (String word : splitDummyText) {
                        finalTextBuilder.append(word + " ");
                    }

                    String newTextString = finalTextBuilder.toString();
                    String finalText = newTextString.replaceAll("¬", "  ");

                    // defaultText.setText(Arrays.toString(finalText));
                    defaultText.setText(finalText);
                }
            }
        }
    };

    final Runnable rFadeType = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerFade.postDelayed(this,500);

                String typeTextString = typeText.getText().toString();
                startTSimButton.setVisibility(View.INVISIBLE);

                splitTypeText = typeTextString.split(" ");     // splits the text and stores the original in a list
                // String[] temporaryText = splitDummyText;    // Temporary list

                int wordIndex = (int) (Math.random() * splitTypeText.length-1);    // random word index
                String currentWord = splitTypeText[wordIndex];

                if (currentWord.matches("\\s") || currentWord.length() < 3) {       // if it contains white space
                    wordIndex = (int) (Math.random() * splitTypeText.length-1);
                    Log.d("Word EMPTY", "Empty");
                }
                if (!currentWord.matches("\\s") && currentWord.length() > 3){       // if it doesn't contain any white spaces

                    StringBuilder wordAtIndexCharsReplace = new StringBuilder();    // holds current word?
                    // if (currentWord.substring(currentWord.length() - 1).equals(".")) {
                    if (currentWord.endsWith(".")) {
                        // wordlength = currentWord.length() -2;
                        Log.d("Word with dot", splitTypeText[wordIndex]);
                        for (int i = 0; i < currentWord.length() - 1; i++) {   // for every letter in word -> replace it with "-"
                            wordAtIndexCharsReplace.append("" + "¬");

                            // System.out.println("last character: " + currentWord.substring(currentWord.length() - 2));
                        }
                        wordAtIndexCharsReplace.append("" + ".");
                    }
                    //if (currentWord.substring(currentWord.length() - 1).equals(",")) {
                    if (currentWord.endsWith(",")) {
                        // wordlength = currentWord.length() -2;
                        Log.d("Word with comma", splitTypeText[wordIndex]);
                        for (int i = 0; i < currentWord.length() - 1; i++) {   // for every letter in word -> replace it with "-"
                            wordAtIndexCharsReplace.append("" + "¬");

                            // System.out.println("last character: " + currentWord.substring(currentWord.length() - 2));
                        }
                        wordAtIndexCharsReplace.append("" + ",");
                    } else {
                        // wordlength = currentWord.length();
                        Log.d("Word", splitTypeText[wordIndex]);
                        for (int i = 0; i < currentWord.length() - 1; i++) {   // for every letter in word -> replace it with "-"
                            wordAtIndexCharsReplace.append("" + "¬");

                        }
                    }

                    splitTypeText[wordIndex] = wordAtIndexCharsReplace.toString();  // changes the word in the list to its new form

                    StringBuilder finalTextBuilder = new StringBuilder();
                    for (String word : splitTypeText) {
                        finalTextBuilder.append(word + " ");
                    }

                    String newTextString = finalTextBuilder.toString();
                    String finalText = newTextString.replaceAll("¬", "  ");

                    typeText.setText(finalText);
                }
            }
        }
    };

    final Runnable rFadeReverseDefault = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - startTime < duration) {
                mHandlerFade.postDelayed(this, 200);
                TextView defaultText = findViewById(R.id.dummyEditTextFragment);
                defaultText.setText(getResources().getString(R.string.dummy_text));
                Log.d("SHAHETHRTHSRTH", "GRGEBTBHRTHTAHTRSHBT");
            }
        }
    };

    final Runnable rDoubleDefault1 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            effectButton = findViewById(R.id.effectTSimButtonMain);
            overlayButton = findViewById(R.id.overlayTSimButtonMain);

            if (System.currentTimeMillis() - startTime < duration){         // runs until duration(6 seconds) is reached
                mHandlerDouble.postDelayed(this,50);         // delay every text shuffling

                defaultText.setShadowLayer(3.0f, 1.5f, 1.0f, Color.BLACK);

                startTSimButton.setVisibility(View.INVISIBLE);
                effectButton.setClickable(false);
                overlayButton.setClickable(false);
            }
        }
    };

    final Runnable rDoubleDefault2 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,125);
//                mHandlerDouble.postDelayed(this, 2000);
                defaultText.setShadowLayer(3.0f, 2.0f, 2.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleDefault3 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,250);
                defaultText.setShadowLayer(3.0f, 3.0f, 3.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleDefault4 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,375);
                defaultText.setShadowLayer(3.0f, 4.0f, 4.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleDefault5 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,500);
                defaultText.setShadowLayer(3.0f, 5.0f, 5.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleDefault6 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,725);
                defaultText.setShadowLayer(3.0f, 5.5f, 6.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleDefault7 = new Runnable() {
        @Override
        public void run() {
            TextView defaultText = findViewById(R.id.dummyEditTextFragment);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,800);
                defaultText.setShadowLayer(3.0f, 6.0f, 7.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleType1 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            Button startTSimButton = findViewById(R.id.startTSimButtonMain);
            effectButton = findViewById(R.id.effectTSimButtonMain);
            overlayButton = findViewById(R.id.overlayTSimButtonMain);

            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,50);

                typeText.setShadowLayer(3.0f, 1.5f, 1.0f, Color.BLACK);

                startTSimButton.setVisibility(View.INVISIBLE);
                effectButton.setClickable(false);
                overlayButton.setClickable(false);
            }
        }
    };

    final Runnable rDoubleType2 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,125);
                typeText.setShadowLayer(3.0f, 2.0f, 2.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleType3 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,250);
                typeText.setShadowLayer(3.0f, 3.0f, 3.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleType4 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,375);
                typeText.setShadowLayer(3.0f, 4.0f, 4.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleType5 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,500);
                typeText.setShadowLayer(3.0f, 5.0f, 5.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleType6 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,725);
                typeText.setShadowLayer(3.0f, 5.5f, 6.0f, Color.BLACK);
            }
        }
    };

    final Runnable rDoubleType7 = new Runnable() {
        @Override
        public void run() {
            EditText typeText = findViewById(R.id.typeTextFragmentEditText);
            if (System.currentTimeMillis() - startTime < duration){
                mHandlerDouble.postDelayed(this,800);
                typeText.setShadowLayer(3.0f, 6.0f, 7.0f, Color.BLACK);
                Log.d("FADE", "FADEEEEEEEEEEEEEEEEEE");
            }
        }
    };

// --------------------------- Effects - End -------------------------------------


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
                try {
                    startSim(view);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // https://www.youtube.com/watch?v=DyJ4hOS3qrQ&ab_channel=AndroidRion
    public void chooseOverlay(View view) {
        int id = view.getId();  // gets button's ID
        String currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewTextSim).getClass().getSimpleName();
        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");
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
                        if (currentFragment.equals("DummyTextFragment")) {     // add || typeTextFragmentMain.isVisible()
                            // Log.d("WORKS", "THIS WORKS");
                            if (overlayRadioButtonString.equals(overlay_options[0])) {
                                dummyTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 255, 255));
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
                        if (currentFragment.equals("TypeTextFragment")) {
                            if (overlayRadioButtonString.equals(overlay_options[0])) {
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 255, 255));
                                Log.d("OVERLAY", "NONE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[1])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                Log.d("OVERLAY", "IT IS BLUE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[2])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(204, 204, 255));
                                Log.d("OVERLAY", "IT IS PURPLE");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[3])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 204, 255));
                                Log.d("OVERLAY", "IT IS PINK");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[4])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(204, 255, 204));
                                Log.d("OVERLAY", "IT IS GREEN");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[5])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 255, 204));
                                Log.d("OVERLAY", "IT IS YELLOW");
                                overlayButton.setText(overlayRadioButtonString);
                            }
                            if (overlayRadioButtonString.equals(overlay_options[6])) {
                                // allButtonsTextSimFragment.getView().setBackgroundColor(Color.rgb(204, 255, 255));
                                typeTextFragmentMain.getView().setBackgroundColor(Color.rgb(255, 217, 179));
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
        String currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewTextSim).getClass().getSimpleName();
        Log.i("Current Fragment", currentFragment);
        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");
        TextView defaultText = findViewById(R.id.dummyEditTextFragment);
        EditText typeText = findViewById(R.id.typeTextFragmentEditText);

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
                        else {
                            if (currentFragment.equals("DummyTextFragment")) {
                                if (effectRadioButtonString.equals(effect_options[0])) {

                                    // Resets text shadow
                                    defaultText.setShadowLayer(0.0f, 0.0f, 0.0f, Color.BLACK);

                                    // Remove Blurry effect
                                    defaultText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                                    defaultText.getPaint().setMaskFilter(null);

                                    // Resets EditText text
                                    defaultText.setText(getResources().getText(R.string.dummy_text));

                                    Log.d("Effect", "None");
                                    effectButton.setText(effectRadioButtonString);
                                }
                            }
                            if (currentFragment.equals("TypeTextFragment")){
                                if (effectRadioButtonString.equals(effect_options[0])) {

                                    // Resets text shadow
                                    typeText.setShadowLayer(0.0f, 0.0f, 0.0f, Color.BLACK);

                                    // Remove Blurry effect
                                    typeText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                                    typeText.getPaint().setMaskFilter(null);

                                    // Resets EditText text
                                    // typeText.setText(getResources().getText(R.string.dummy_text));
                                    if (changed == 2) {
                                        Log.i("Check if changed ==2", "Reset Text and Prefs");
                                        SharedPreferences sharedPreferencesMain = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
                                        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferencesMain.edit();
                                        typeText.setText(sharedPreferencesMain.getString(TEXT_ORIGINAL, ""));   // resets the text to the original user input
                                        changed = 0;
                                        sharedPreferencesEditor.clear();
                                        sharedPreferencesEditor.commit();

                                    }

                                    Log.d("Effect", "None");
                                    effectButton.setText(effectRadioButtonString);
                                }
                            }
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

    public void blurEffectDefault(View view) {
        TextView defaultText = findViewById(R.id.dummyEditTextFragment);
        defaultText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        float filterRadius = defaultText.getTextSize() / 9;
        BlurMaskFilter blurryFilter = new BlurMaskFilter(filterRadius, BlurMaskFilter.Blur.NORMAL);
        defaultText.getPaint().setMaskFilter(blurryFilter);
    }

    public void blurEffectType(View view) {
        EditText typeText = findViewById(R.id.typeTextFragmentEditText);
        typeText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        float filterRadius = typeText.getTextSize() / 9;
        BlurMaskFilter blurryFilter = new BlurMaskFilter(filterRadius, BlurMaskFilter.Blur.NORMAL);
        typeText.getPaint().setMaskFilter(blurryFilter);
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

//        public void animShadow(Integer toRadius,Integer toDx,Integer toDy) {
//            EditText defaultText = findViewById(R.id.dummyEditTextFragment);
//        // Animation doubleTextAnimatorXY = AnimationUtils.loadAnimation(this, R.anim.fade_out);
//        // defaultText.animShadowXY(doubleTextAnimatorXY);
////            shadowRadius = PropertyValuesHolder.ofFloat("shadowRadius", fromRadius, toRadius);
////            shadowDx = PropertyValuesHolder.ofFloat("shadowDx",fromDx, toDx);
////            shadowDy = PropertyValuesHolder.ofFloat("shadowDy", fromDy, toDy);
////
////            ValueAnimator.ofPropertyValuesHolder(animShadow(shadowR))
//            ObjectAnimator shadowRadius = ObjectAnimator.ofInt(defaultText, "shadowRadius", toRadius);
//            ObjectAnimator shadowDx = ObjectAnimator.ofInt(defaultText, "shadowDx", toDx);
//            ObjectAnimator shadowDy = ObjectAnimator.ofInt(defaultText, "shadowDy", toDy);
//            AnimatorSet animSetShadow = new AnimatorSet();
//            animSetShadow.playTogether(shadowRadius, shadowDx, shadowDy);
//            animSetShadow.start();
//    }

    public void saveOriginalText() {
        EditText typeText = findViewById(R.id.typeTextFragmentEditText);
        SharedPreferences sharedPreferencesMain = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferencesMain.edit();

        sharedPreferencesEditor.putString(TEXT_ORIGINAL, typeText.getText().toString());
        sharedPreferencesEditor.apply();
        Toast.makeText(this, "Text is saved", Toast.LENGTH_SHORT).show();
    }


    public void startSim(View view) throws InterruptedException {

        String currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerViewTextSim).getClass().getSimpleName();
        dummyTextFragmentMain = getSupportFragmentManager().findFragmentByTag("DEFAULT_TEXT_FRAGMENT");
        typeTextFragmentMain = getSupportFragmentManager().findFragmentByTag("TYPE_TEXT_FRAGMENT");
        Button startTSimButton = findViewById(R.id.startTSimButtonMain);


        Log.i("Current Fragment", currentFragment);

        if (allButtonsTextSimFragment.isVisible()) {
            Toast.makeText(getApplicationContext(), "Choose text option", Toast.LENGTH_SHORT).show();
        }
        else {
            if (effectButton.getText().toString().equals(getResources().getString(R.string.add_effect)) || effectButton.getText().toString().equals("None")) {
                Toast.makeText(getApplicationContext(), "Choose effect", Toast.LENGTH_SHORT).show();
            } else {
                if (currentFragment.equals("DummyTextFragment")) {

                    TextView defaultText = findViewById(R.id.dummyEditTextFragment);
                    String defaultTextString = defaultText.getText().toString();
                    startTSimButton.setVisibility(View.VISIBLE);

                    if (effectButton.getText().toString().equals("Shuffle")) {
                        defaultText.setText(getResources().getString(R.string.dummy_text));
                        startTime = System.currentTimeMillis();
                        mHandlerShuffle.post(rShuffleDefault);
                        mHandlerShuffle.postDelayed(rButtonReset, 9950);
                    }
                    if (effectButton.getText().toString().equals("Blurry")) {
                        blurEffectDefault(view);
                        Log.d("StartTime", "I am HERE");
                    }
                    if (effectButton.getText().toString().equals("Fade")) {
                        startTime = System.currentTimeMillis();
                        mHandlerFade.post(rFadeDefault);
                        mHandlerShuffle.postDelayed(rButtonReset, 9990);
                        // mHandlerFade.postDelayed(rFadeReverse, 9820); // Returns the text to its previous state
                        Log.d("StartTime", "I am HERE");
                    }
                    if (effectButton.getText().toString().equals("Double")) {
                        Log.d("EFFECT", "DOUBLE");
                        // YoYo.with(Techniques.Wave).duration(1000).repeat(1).playOn(defaultText);
                        startTime = System.currentTimeMillis();
                        mHandlerDouble.post(rDoubleDefault1);
                        mHandlerDouble.postDelayed(rDoubleDefault2, 250);
                        mHandlerDouble.postDelayed(rDoubleDefault3, 300);
                        mHandlerDouble.postDelayed(rDoubleDefault4, 350);
                        mHandlerDouble.postDelayed(rDoubleDefault5, 400);
                        mHandlerDouble.postDelayed(rDoubleDefault6, 450);
                        mHandlerDouble.postDelayed(rDoubleDefault7, 500);
                        mHandlerShuffle.postDelayed(rButtonReset, 10500);
                    } else {
                        Log.d("NO", "NOT OPEN OR NULL!");
                    }
                }
                if (currentFragment.equals("TypeTextFragment")) {

                    EditText typeText = findViewById(R.id.typeTextFragmentEditText);
                    // String typeTextStringModify = typeTextStringOriginal;
                    String typeTextString = typeText.getText().toString();
                    splitTypeText = typeTextString.split(" ");
                    startTSimButton.setVisibility(View.VISIBLE);

                    if (typeTextString.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Add text", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (effectButton.getText().toString().equals("Shuffle")) {
                            if (splitTypeText.length <= 1) {
                                Toast.makeText(getApplicationContext(), " The Shuffle effect requires more text", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (changed <= 1){
                                    saveOriginalText();
                                    Log.i("Check if changed <=1", "Save Text");
                                }
                                changed = 2;
                                Log.i("Set changed = 2", "Effect applied and text shuffled");
                                startTime = System.currentTimeMillis();
                                mHandlerShuffle.post(rShuffleType);
                                mHandlerShuffle.postDelayed(rButtonReset, 9950);
                            }
                        }
                        if (effectButton.getText().toString().equals("Blurry")) {
                            blurEffectType(view);
                            Log.d("StartTime (TYPE)", "I am HERE");
                        }
                        if (effectButton.getText().toString().equals("Fade")) {
                            if (splitTypeText.length <= 1) {
                                Toast.makeText(getApplicationContext(), " The Fade effect requires more text", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                if (changed <= 1){
                                    saveOriginalText();
                                    Log.i("Check if changed <=1", "Save Text");
                                }
                                changed = 2;
                                Log.i("Set changed = 2", "Effect applied and text shuffled");
                                startTime = System.currentTimeMillis();
                                mHandlerFade.post(rFadeType);
                                mHandlerShuffle.postDelayed(rButtonReset, 9990);
                                // mHandlerFade.postDelayed(rFadeReverse, 9820); // Returns the text to its previous state
                                Log.d("StartTime TYPE", "I am HERE");
                            }
                        }
                        if (effectButton.getText().toString().equals("Double")) {
                            Log.d("EFFECT (TYPE)", "DOUBLE");
                            startTime = System.currentTimeMillis();
                            mHandlerDouble.post(rDoubleType1);
                            mHandlerDouble.postDelayed(rDoubleType2, 250);
                            mHandlerDouble.postDelayed(rDoubleType3, 300);
                            mHandlerDouble.postDelayed(rDoubleType4, 350);
                            mHandlerDouble.postDelayed(rDoubleType5, 400);
                            mHandlerDouble.postDelayed(rDoubleType6, 450);
                            mHandlerDouble.postDelayed(rDoubleType7, 500);
                            mHandlerShuffle.postDelayed(rButtonReset, 10500);
                        } else {
                            Log.d("NO (Type)", "NOT OPEN OR NULL!");
                        }

                    }
                }
            }
        }
    }
}