package com.hritik.whichday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public boolean Leap_Year_Validation(int year)
    {
        if (year%4==0 || year%400==0)
        {
            if (year%100==0 && year%400!=0)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public String Day_Name_Giver(int num)
    {
        //System.out.println(num);
        switch(num)
        {
            case 0:
                return "Saturday";
            case 1:
                return "Sunday";

            case 2:
                return "Monday";
            case 3:
                return "Tuesday";

            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
        }
        return "Wrong";
    }

    public int Cent_Value(int year)
    {
        int y_count=1;
        for (int i=1900;i<=year;i+=100,y_count++)
        {

            if(y_count>6)
                y_count=0;
            if(i%400==0)
                y_count-=1;
            //System.out.println(i+"="+y_count);

        }
        //System.out.println("Ycount:"+y_count);
        return y_count;
    }
    public int Day_Remainder(int date)
    {
        return (int)Math.floor(date%7);
    }
    public int Month_Value(int month,int year)
    {
        switch(month)
        {
            case 1:
                //System.out.println(Leap_Year_Validation(year));
                if (Leap_Year_Validation(year))
                    return 0;
                else
                    return 1;


            case 2:
                //System.out.println(Leap_Year_Validation(year));
                if (Leap_Year_Validation(year))
                    return 3;
                else
                    return 4;

            case 3:case 11:
            return 4;

            case 4:case 7:
            return 0;
            case 5:
                return 2;
            case 6:
                return 5;
            case 8:
                return 3;
            case 9:case 12:
            return 6;
            case 10:
                return 1;
        }
        return -1;
    }

    public int Year_Value(int year)
    {
        System.out.println("Cent:"+Cent_Value(year));
        if (((year%7)+(((int)Math.floor(year/4))%7)-Cent_Value(year))<0)
            return (year%7)+(((int)Math.floor(year/4))%7)-Cent_Value(year)+7;
        else
            return (year%7)+(((int)Math.floor(year/4))%7)-Cent_Value(year);
    }
    Button find;
    TextView display;
    EditText date_ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        find=findViewById(R.id.find_btn);
        display=findViewById(R.id.display);
        date_ob=findViewById(R.id.date_ent);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date= String.valueOf(date_ob.getText());
                String[] dt = date.split("/");
                int Yval=Year_Value(Integer.parseInt(dt[2]));
                //System.out.println(Yval);
                int Dval=Day_Remainder(Integer.parseInt(dt[0]));
                //System.out.println(Dval);
                int Mval=Month_Value(Integer.parseInt(dt[1]),Integer.parseInt(dt[2]));
                //System.out.println(Mval);
                //System.out.println("Day is "+Day_Name_Giver((Yval+Dval+Mval)%7));
                display.setText(Day_Name_Giver((Yval+Dval+Mval)%7));
            }
        });
    }
}
