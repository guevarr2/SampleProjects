package tabs.usertabs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by KonKon on 10/29/2017.
 */

public class PoliceView extends AppCompatActivity{

    Button report1, report2, report3, report4;

    ArrayList<UserReport> reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popo_view);
        //DbHandler db = new DbHandler(this);

        final UserReport reportUno = new UserReport("Suspicious Van", "Van driving in neighborhood and stopping" +
                "at each house", null, "00:00", "personInDistress", 3181085, "5522 Bon Ave");
        final UserReport reportDos = new UserReport("Suspicious Van2", "Van driving in neighborhood and stopping" +
                "at each house", null, "00:00", "personInDistress2", 3181085, "5522 Bon Ave");
        final UserReport reportTres = new UserReport("Suspicious Van3", "Van driving in neighborhood and stopping" +
                "at each house", null, "00:00", "personInDistress3", 3181085, "5522 Bon Ave");
        final UserReport reportQuatro = new UserReport("Suspicious Va4", "Van driving in neighborhood and stopping" +
                "at each house", null, "00:00", "personInDistress4", 3181085, "5522 Bon Ave");

        //db.insertUserReport(reportUno);
        //db.insertUserReport(reportDos);
        //db.insertUserReport(reportTres);
        //db.insertUserReport(reportQuatro);

        //reports = db.getAllRecords();

        report1 = findViewById(R.id.button1);
        report2 = findViewById(R.id.button2);
        report3 = findViewById(R.id.button3);
        report4 = findViewById(R.id.button4);

        report1.setText(reportUno.getReportTitle());
        report1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
                    i.putExtra("User", reportUno.getReportUser());
                    i.putExtra("Title", reportUno.getReportTitle());
                    i.putExtra("Phone Number", Integer.toString(reportUno.getReportNumber()));
                    i.putExtra("Time", reportUno.getReportTime());
                    i.putExtra("Address", reportUno.getReportAddress());
                    i.putExtra("Description", reportUno.getReportDescription());
                    //Title, phone number, time, address, description
                    startActivity(i);
                }
            });
        report2.setText(reportDos.getReportTitle());
        report2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PoliceView.this, PopoEvent.class);
                i.putExtra("User", reportDos.getReportUser());
                i.putExtra("Title", reportDos.getReportTitle());
                i.putExtra("Phone Number", Integer.toString(reportDos.getReportNumber()));
                i.putExtra("Time", reportDos.getReportTime());
                i.putExtra("Address", reportDos.getReportAddress());
                i.putExtra("Description", reportDos.getReportDescription());
                //Title, phone number, time, address, description
                startActivity(i);
            }
        });
        report3.setText(reportTres.getReportTitle());
        report3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PoliceView.this, PopoEvent.class);
                i.putExtra("User", reportTres.getReportUser());
                i.putExtra("Title", reportTres.getReportTitle());
                i.putExtra("Phone Number", Integer.toString(reportTres.getReportNumber()));
                i.putExtra("Time", reportTres.getReportTime());
                i.putExtra("Address", reportTres.getReportAddress());
                i.putExtra("Description", reportTres.getReportDescription());
                //Title, phone number, time, address, description
                startActivity(i);
            }
        });
        report4.setText(reportQuatro.getReportTitle());
        report4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PoliceView.this, PopoEvent.class);
                i.putExtra("User", reportQuatro.getReportUser());
                i.putExtra("Title", reportQuatro.getReportTitle());
                i.putExtra("Phone Number", Integer.toString(reportQuatro.getReportNumber()));
                i.putExtra("Time", reportQuatro.getReportTime());
                i.putExtra("Address", reportQuatro.getReportAddress());
                i.putExtra("Description", reportQuatro.getReportDescription());
                //Title, phone number, time, address, description
                startActivity(i);
            }
        });

//        if(reports.size() >= 4)
//        {
//            report1.setText(reports.get(0).getReportTitle());
//            report1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(0).getReportUser());
//                    i.putExtra("Title", reports.get(0).getReportTitle());
//                    i.putExtra("Phone Number", Integer.toString(reports.get(0).getReportNumber()));
//                    i.putExtra("Time", reports.get(0).getReportTime());
//                    i.putExtra("Address", reports.get(0).getReportAddress());
//                    i.putExtra("Description", reports.get(0).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//            report2.setText(reports.get(1).getReportTitle());
//            report2.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(1).getReportUser());
//                    i.putExtra("Title", reports.get(1).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(1).getReportNumber());
//                    i.putExtra("Time", reports.get(1).getReportTime());
//                    i.putExtra("Address", reports.get(1).getReportAddress());
//                    i.putExtra("Description", reports.get(1).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//            report3.setText(reports.get(2).getReportTitle());
//            report3.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(2).getReportUser());
//                    i.putExtra("Title", reports.get(2).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(2).getReportNumber());
//                    i.putExtra("Time", reports.get(2).getReportTime());
//                    i.putExtra("Address", reports.get(2).getReportAddress());
//                    i.putExtra("Description", reports.get(2).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//            report4.setText(reports.get(3).getReportTitle());
//            report4.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(3).getReportUser());
//                    i.putExtra("Title", reports.get(3).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(3).getReportNumber());
//                    i.putExtra("Time", reports.get(3).getReportTime());
//                    i.putExtra("Address", reports.get(3).getReportAddress());
//                    i.putExtra("Description", reports.get(3).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//        }
//        else if(reports.size() == 3)
//        {
//            report1.setText(reports.get(0).getReportTitle());
//            report1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(0).getReportUser());
//                    i.putExtra("Title", reports.get(0).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(0).getReportNumber());
//                    i.putExtra("Time", reports.get(0).getReportTime());
//                    i.putExtra("Address", reports.get(0).getReportAddress());
//                    i.putExtra("Description", reports.get(0).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//            report2.setText(reports.get(1).getReportTitle());
//            report2.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(1).getReportUser());
//                    i.putExtra("Title", reports.get(1).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(1).getReportNumber());
//                    i.putExtra("Time", reports.get(1).getReportTime());
//                    i.putExtra("Address", reports.get(1).getReportAddress());
//                    i.putExtra("Description", reports.get(1).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//            report3.setText(reports.get(2).getReportTitle());
//            report3.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(2).getReportUser());
//                    i.putExtra("Title", reports.get(2).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(2).getReportNumber());
//                    i.putExtra("Time", reports.get(2).getReportTime());
//                    i.putExtra("Address", reports.get(2).getReportAddress());
//                    i.putExtra("Description", reports.get(2).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//        }
//        else if(reports.size() == 2)
//        {
//            report1.setText(reports.get(0).getReportTitle());
//            report1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(0).getReportUser());
//                    i.putExtra("Title", reports.get(0).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(0).getReportNumber());
//                    i.putExtra("Time", reports.get(0).getReportTime());
//                    i.putExtra("Address", reports.get(0).getReportAddress());
//                    i.putExtra("Description", reports.get(0).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//            report2.setText(reports.get(1).getReportTitle());
//            report2.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(1).getReportUser());
//                    i.putExtra("Title", reports.get(1).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(1).getReportNumber());
//                    i.putExtra("Time", reports.get(1).getReportTime());
//                    i.putExtra("Address", reports.get(1).getReportAddress());
//                    i.putExtra("Description", reports.get(1).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//        }
//        else if(reports.size() == 1)
//        {
//            report1.setText(reports.get(0).getReportTitle());
//            report1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v)
//                {
//                    Toast.makeText(getApplicationContext(), "Redirectiing...", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(PoliceView.this, PopoEvent.class);
//                    i.putExtra("User", reports.get(0).getReportUser());
//                    i.putExtra("Title", reports.get(0).getReportTitle());
//                    i.putExtra("Phone Number",reports.get(0).getReportNumber());
//                    i.putExtra("Time", reports.get(0).getReportTime());
//                    i.putExtra("Address", reports.get(0).getReportAddress());
//                    i.putExtra("Description", reports.get(0).getReportDescription());
//                    //Title, phone number, time, address, description
//                    startActivity(i);
//                }
//            });
//        }
    }
}
