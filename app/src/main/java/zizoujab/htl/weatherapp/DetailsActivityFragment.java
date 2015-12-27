package zizoujab.htl.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        Intent extra = getActivity().getIntent();
        TextView detailTextView = (TextView)rootView.findViewById(R.id.detailTextView);
        detailTextView.setText(extra.getExtras().getString("cle"));
       // Log.i("DetailsActivityFragment",extra.getExtras().getString(Intent.EXTRA_TEXT));
        return rootView;
    }
}
