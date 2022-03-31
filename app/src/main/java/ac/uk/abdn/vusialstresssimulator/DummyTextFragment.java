package ac.uk.abdn.vusialstresssimulator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DummyTextFragment extends Fragment {

    View rootDummyTextFragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootDummyTextFragmentView = inflater.inflate(R.layout.fragment_dummy_text, container, false);
        return rootDummyTextFragmentView;
    }


}