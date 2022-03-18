package ac.uk.abdn.vusialstresssimulator;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link AllButtonsTextSimFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class AllButtonsTextSimFragment extends Fragment  {

    private Button chooseDefaultTextButton, chooseTypeTextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootAllButtonsTSimFragmentView = inflater.inflate(R.layout.fragment_all_buttons_text_sim, container, false);
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();

        DummyTextFragment dummyTextFragment = new DummyTextFragment();
        TypeTextFragment typeTextFragment = new TypeTextFragment();

        getAllButtons(rootAllButtonsTSimFragmentView);

        chooseDefaultTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.fragmentContainerViewTextSim, dummyTextFragment, "DEFAULT_TEXT_FRAGMENT");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        chooseTypeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.replace(R.id.fragmentContainerViewTextSim, typeTextFragment, "TYPE_TEXT_FRAGMENT");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootAllButtonsTSimFragmentView;
    }

    private void getAllButtons(View view){
        chooseDefaultTextButton = view.findViewById(R.id.defaultTextButton);
        chooseTypeTextButton = view.findViewById(R.id.typeTextButton);
    }


//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public AllButtonsTextSimFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AllButtonsTextSimFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AllButtonsTextSimFragment newInstance(String param1, String param2) {
//        AllButtonsTextSimFragment fragment = new AllButtonsTextSimFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }



//    private void clickOnDefaultTextButton(){
//
//        Button chooseDefaultTextButton = rootAllButtonsTSimFragmentView.findViewById(R.id.defaultTextButton);
//        Button chooseTypeTextButton = rootAllButtonsTSimFragmentView.findViewById(R.id.typeTextButton);
//        // Button chooseScanTextButton = findViewById(R.id.scanTextButton);
//        //chooseDefaultTextButton.setOnClickListener(this);
//        // chooseTypeTextButton.setOnClickListener(this);
//    }

//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.defaultTextButton) {
////            FragmentTransaction defaultTextTransaction = getParentFragmentManager().beginTransaction();
////            defaultTextTransaction.replace(R.id.fragmentContainerViewTextSim, new DummyTextFragment());
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewTextSim,DummyTextFragment).commit();
//        }
//    }
}