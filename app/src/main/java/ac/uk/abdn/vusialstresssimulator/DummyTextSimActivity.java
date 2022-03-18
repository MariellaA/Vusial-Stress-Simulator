package ac.uk.abdn.vusialstresssimulator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Scanner;

public class DummyTextSimActivity extends AppCompatActivity {

    String scrambledWordHolder = new String(" ");
    EditText defaultTextt;
    Button animate;
    Animation animFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CREATE", "OnCreate Stage");
        setContentView(R.layout.activity_dummy_text_sim);

        defaultTextt = findViewById(R.id.editTextTSim);
        animate = findViewById(R.id.startTSimButton);



        // animFadeOut = new AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

//        animate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // ObjectAnimator anim = ObjectAnimator.ofObject(defaultTextt, "translationX", 100f);
////                ObjectAnimator anim = ObjectAnimator.ofFloat(defaultTextt, "rotation", 0f, 90f);
////                anim.setDuration(2000);
////                anim.start();
//                // String text = "Text";
//                EditText defaultText = findViewById(R.id.editTextTSim);
//                String defaultTextString = defaultText.getText().toString();
//                Scanner wordScanner = new Scanner(defaultTextString);
//                StringBuilder scrambledStringBuilder = new StringBuilder();
//
//                while(wordScanner.hasNextLine()) {
//                    String wordScrambled = wordScanner.next();
//                    // System.out.println(wordScrambled);
//                    // System.out.println(scramble(wordScrambled));
//                    scrambledStringBuilder.append(wordScrambled).append(" ");
//                }
//                scrambledWordHolder = scrambledStringBuilder.toString().trim();
//                defaultText.setText(scrambledWordHolder);
//                Log.d("String", scrambledWordHolder);
//                scrambledWordHolder = " ";
//                wordScanner.close();
//
//                YoYo.with(Techniques.FadeOut).duration(1000).repeat(1).playOn(defaultTextt);


            }
//        });




//        EditText defaultText = findViewById(R.id.editTextTSim);
//        String defaultTextString = defaultText.getText().toString();
//        Scanner wordScanner = new Scanner(defaultTextString);
//        StringBuilder scrambledStringBuilder = new StringBuilder();
//        Button startTSim = findViewById(R.id.startTSimButton);
//        startTSim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                while(wordScanner.hasNextLine()) {
//                    String wordScrambled = wordScanner.next();
//                    //System.out.println(wordScrambled);
//                    //System.out.println(scramble(wordScrambled));
//                    scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
//                }
//                scrambledWordHolder = scrambledStringBuilder.toString().trim();
//                defaultText.setText(scrambledWordHolder);
//                Log.d("String", scrambledWordHolder);
//                scrambledWordHolder = " ";
//                wordScanner.close();
//            }
//        });

//    }

    // https://stackoverflow.com/questions/27535402/shuffle-middle-characters-of-words-in-a-string
    public void runTSim(View view) {
        EditText defaultText = findViewById(R.id.editTextTSim);
        String defaultTextString = defaultText.getText().toString();
        Scanner wordScanner = new Scanner(defaultTextString);
        StringBuilder scrambledStringBuilder = new StringBuilder();
        Button startTSim = findViewById(R.id.startTSimButton);

        while(wordScanner.hasNextLine()) {
            String wordScrambled = wordScanner.next();
            // System.out.println(wordScrambled);
            // System.out.println(scramble(wordScrambled));
            scrambledStringBuilder.append(scramble(wordScrambled)).append(" ");
        }
        scrambledWordHolder = scrambledStringBuilder.toString().trim();
        defaultText.setText(scrambledWordHolder);
        Log.d("String", scrambledWordHolder);
        scrambledWordHolder = " ";
        wordScanner.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RESUME", "OnResume Stage");
    }


    private static String scramble(char first, char last, String word) {
        String shuffledString = "" + first; // first letter
        while (word.length() != 0) {
            int index = (int) Math.floor(Math.random() * word.length());
            char c = word.charAt(index);
            word = word.substring(0,index) + word.substring(index+1);
            shuffledString += c;
        }
        return shuffledString + last; // last letter
    }

    public static String scramble(String word) {
        if (word.length() < 3) {
            return word;
        }
        String middleLetters = word.substring(1, word.length() - 1);
        return scramble(word.charAt(0), word.charAt(word.length() - 1), middleLetters);
    }

//    private class buttonOnClicks implements View.OnClickListener {
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.overlayTSimButtonMain: {
//                    // Do something
//                }
//            }
//        }
//    }


}