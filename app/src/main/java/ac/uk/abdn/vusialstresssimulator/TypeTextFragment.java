package ac.uk.abdn.vusialstresssimulator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TypeTextFragment extends Fragment {

    View rootTypeTextFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootTypeTextFragmentView = inflater.inflate(R.layout.fragment_type_text, container, false);
        return rootTypeTextFragmentView;
    }
}