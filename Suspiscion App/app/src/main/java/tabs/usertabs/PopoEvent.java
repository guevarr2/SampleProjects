package tabs.usertabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by KonKon on 10/29/2017.
 */

public class PopoEvent extends AppCompatActivity {

    TextView view1, view2, view3, view4, view5;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popo_event1);

        //Title, phone number, time, address, description
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String user = intent.getStringExtra("User");
        int phoneNumber = 2025862;
        String time = intent.getStringExtra("Time");
        String address = intent.getStringExtra("Address");
        String description = intent.getStringExtra("Description");

        view1 = findViewById(R.id.locationReport);
        view2 = findViewById(R.id.titleReport);
        view3 = findViewById(R.id.phoneReport);
        view4 = findViewById(R.id.timeReport);
        view5 = findViewById(R.id.bodyReport);
        done = findViewById(R.id.complete);

        view1.setText(address);
        view2.setText(title);
        view3.setText(String.valueOf(phoneNumber));
        view4.setText(time);
        view5.setText(description);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PopoEvent.this, PoliceView.class);
                startActivity(i);
            }

        });


        //UserReport report = new UserReport(title, description, null, time, user, phoneNumber, address);

        //DbHandler db = new DbHandler(this);
        //db.removeUserReport(report);


    }
}
