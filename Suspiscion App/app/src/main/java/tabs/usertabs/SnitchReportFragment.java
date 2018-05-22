package tabs.usertabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Snitch Report Fragment: Mainly handles the button functionally in the report tab
 *
 * Created by: Aaron Guevarra
 * Date: 10/29/2017
 */
public class SnitchReportFragment extends Fragment {
    private static final String TAG = "SnitchReportFragment";

    private Button sendBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.snitch_report, container, false);
        sendBtn = (Button) view.findViewById(R.id.saveBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Report Sent!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
