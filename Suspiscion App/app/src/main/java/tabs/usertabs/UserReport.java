package tabs.usertabs;

/**
 * Created by KonKon on 10/28/2017.
 */

public class UserReport {

    //Variables for storage
    private String reportTitle, reportDescription, reportLocation, reportTime, reportUser, reportAddress;
    private int reportNumber;

    //Constructor
    public UserReport(String title, String description, String location,
                      String time, String user, int phoneNum, String address)
    {
        reportTitle = title;
        reportDescription = description;
        reportLocation = location;
        reportTime = time;
        reportUser = user;
        reportNumber = phoneNum;
        reportAddress = address;
    }

    //Accessor methods
    public String getReportTitle()
    {
        return reportTitle;
    }

    public String getReportDescription()
    {
        return reportDescription;
    }

    public String getReportLocation()
    {
        return reportLocation;
    }

    public String getReportTime()
    {
        return reportTime;
    }

    public String getReportUser()
    {
        return reportUser;
    }

    public int getReportNumber()
    {
        return reportNumber;
    }

    public String getReportAddress()
    {
        return reportAddress;
    }
}
