package ac.uk.abdn.vusialstresssimulator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TypeTextFragment extends Fragment {

    View rootTypeTextFragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootTypeTextFragmentView = inflater.inflate(R.layout.fragment_type_text, container, false);

//        String widgetString = getResources().getString(R.string.dummy_text);
//        String[] splitWidgetString = widgetString.split(" ");
//        LinearLayout linearLayout = new LinearLayout(rootTypeTextFragmentView.getContext());
        return rootTypeTextFragmentView;
    }
}