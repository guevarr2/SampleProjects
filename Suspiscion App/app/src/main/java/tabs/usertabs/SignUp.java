package tabs.usertabs;

/**
 * Created by KonKon on 10/29/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    Button signUpButton;
    EditText nameReport, dob, email, password, passwordConfirm, phoneNumber, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        nameReport = findViewById(R.id.nameReport);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);

        setContentView(R.layout.sign_up);
        final Button button = findViewById(R.id.signUpButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });
    }
}
