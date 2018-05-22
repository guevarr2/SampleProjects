package tabs.usertabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Account Tab Fragment: Controls the buttons within the tab view.
 *
 * Created by: Aaron Guevarra
 * Date: 10/29/2017
 */
public class AccountFragment extends Fragment {
    private static final String TAG = "AccountFragment";

    private Button saveBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment, container, false);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Settings Saved",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
