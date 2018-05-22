package tabs.usertabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Report Feed Fragment: Handles the list view for the fragment.
 *
 * Created by: Aaron Guevarra
 * Date: 10/29/2017
 */
public class ReportFeedFragment extends Fragment {
    private static final String TAG = "ReportFeedFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_feed, container, false);

        return view;
    }
}
