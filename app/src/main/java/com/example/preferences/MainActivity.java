package com.example.preferences;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bozapro.circularsliderrange.CircularSliderRange;
import com.bozapro.circularsliderrange.ThumbEvent;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toast toast;
    LinearLayout alertLayout, matchLayout;
    SwitchCompat switchCompatAlert, matchMaker;
    Integer startAge=18,endAge=70;
    TextView years;
    TabLayout tabLayout;
    CircularSliderRange sliderRange;
    ImageView women;
    ImageView men;
    ImageView image;
    int m=0;
    int w=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        toast= Toast.makeText(MainActivity.this, "You will not appear as a potential suggestion for others. You will only be a matchmaker.", Toast.LENGTH_SHORT);

        Context context = getApplicationContext();
        LayoutInflater inflater = getLayoutInflater();
        View toastView = inflater.inflate(R.layout.toast_layout,  null);
        toast = new Toast(context);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_LONG);

        alertLayout = (LinearLayout) findViewById(R.id.alertLayout);
        switchCompatAlert = (SwitchCompat) findViewById(R.id.switchAlerts);

        switchCompatAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    alertLayout.setBackground(getDrawable(R.drawable.curve));
                }else{
                    alertLayout.setBackground(getDrawable(R.drawable.pink));
                }
            }
        });
        matchLayout = (LinearLayout) findViewById(R.id.matchLayout);
        matchMaker = (SwitchCompat) findViewById(R.id.matchMaker);
        matchMaker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    matchLayout.setBackground(getDrawable(R.drawable.curve));
                    toast.show();


                }else{
                    matchLayout.setBackground(getDrawable(R.drawable.pink));
                }
            }
        });

        tabLayout=(TabLayout) findViewById(R.id.tabLayout);
        final TabItem match = (TabItem) findViewById(R.id.match);

        TabItem app = (TabItem) findViewById(R.id.app);
        final ConstraintLayout matchpref= (ConstraintLayout) findViewById(R.id.matchpref);
        final ConstraintLayout apppref = (ConstraintLayout) findViewById(R.id.apppref);

        apppref.setVisibility(View.INVISIBLE);

        Log.i("Info : ","yahan ");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println(tab.getPosition());
                if(tab.getPosition()==0){
                    matchpref.setAlpha(0);
                    matchpref.setVisibility(View.VISIBLE);
                    matchpref.animate().alpha(1).setDuration(200);
                    apppref.setVisibility(View.INVISIBLE);
                }else if(tab.getPosition()==1){
                    matchpref.setAlpha(0);
                    apppref.setVisibility(View.VISIBLE);
                    matchpref.animate().alpha(1).setDuration(200);
                    matchpref.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        men= (ImageView) findViewById(R.id.men);
        women= (ImageView) findViewById(R.id.women);
        image=(ImageView) findViewById(R.id.image);

        years=(TextView) findViewById(R.id.years);
        sliderRange = (CircularSliderRange) findViewById(R.id.circular);

        years.setText("18-70");
        sliderRange.setStartAngle(18*5);
        sliderRange.setEndAngle(70*5);
        sliderRange.setOnSliderRangeMovedListener(new CircularSliderRange.OnSliderRangeMovedListener() {
            @Override
            public void onStartSliderMoved(double pos) {
                startAge = (int)(pos/5);
            if(startAge<18||startAge>endAge){
                startAge=18;
                sliderRange.setStartAngle(18*5);
                years.setText(startAge+"-"+endAge);
            }
            else{
                years.setText(startAge+"-"+endAge);
            }

            if(m==1){
                if(startAge<=25){
                    image.setImageResource(R.drawable.m1);
                }
                else if(startAge<=40){
                    image.setImageResource(R.drawable.m2);
                }
                else if(startAge<=59){
                    image.setImageResource(R.drawable.m3);
                }
                else{
                    image.setImageResource(R.drawable.oldmen);
                }
            }
            else if(w==1){
                    if(startAge<=25){
                        image.setImageResource(R.drawable.f2);
                    }
                    else if(startAge<=40){
                        image.setImageResource(R.drawable.f1);
                    }
                    else if(startAge<=59){
                        image.setImageResource(R.drawable.f3);
                    }
                    else{
                        image.setImageResource(R.drawable.old);
                    }
                }
            }

            @Override
            public void onEndSliderMoved(double pos) {
                endAge = (int) (pos/5);
                if(endAge>70 || startAge>endAge){
                    endAge=70;
                    sliderRange.setEndAngle(70*5);
                    years.setText(startAge+"-"+endAge);
                }
                else{
                    years.setText(startAge+"-"+endAge);
                }

                if(m==1){
                    if(endAge<=34){
                        image.setImageResource(R.drawable.m2);
                    }
                    else if(endAge<=54){
                        image.setImageResource(R.drawable.m3);
                    }
                    else{
                        image.setImageResource(R.drawable.oldmen);
                    }
                }
                else if(w==1){
                    if(endAge<=34){
                        image.setImageResource(R.drawable.f1);
                    }
                    else if(endAge<=54){
                        image.setImageResource(R.drawable.f3);
                    }
                    else{
                        image.setImageResource(R.drawable.old);
                    }
                }
            }

            @Override
            public void onStartSliderEvent(ThumbEvent event) {

            }

            @Override
            public void onEndSliderEvent(ThumbEvent event) {

            }
        });
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar));


        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(m==0) {
                   men.setImageResource(R.drawable.m_like);

                   if(startAge<=25){
                       image.setImageResource(R.drawable.m1);
                   }
                   else if(startAge<=40){
                       image.setImageResource(R.drawable.m2);
                   }
                   else if(startAge<=59){
                       image.setImageResource(R.drawable.m3);
                   }
                   else{
                       image.setImageResource(R.drawable.oldmen);
                   }
                   m=1;//liked
               }
               else{
                   men.setImageResource(R.drawable.m);
                   m=0;
                   if(w==0||w==1){
                       women.setImageResource(R.drawable.f_like);
                       if(startAge<=25){
                           image.setImageResource(R.drawable.f2);
                       }
                       else if(startAge<=40){
                           image.setImageResource(R.drawable.f1);
                       }
                       else if(startAge<=59){
                           image.setImageResource(R.drawable.f3);
                       }
                       else{
                           image.setImageResource(R.drawable.old);
                       }
                       w=1;
                   }
               }
            }
        });
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(w==0) {
                    women.setImageResource(R.drawable.f_like);

                    if(startAge<=25){
                        image.setImageResource(R.drawable.f2);
                    }
                    else if(startAge<=40){
                        image.setImageResource(R.drawable.f1);
                    }
                    else if(startAge<=59){
                        image.setImageResource(R.drawable.f3);
                    }
                    else{
                        image.setImageResource(R.drawable.old);
                    }
                    w=1;//liked
                }
                else{
                    women.setImageResource(R.drawable.f);
                    w=0;
                    if(m==0||m==1) {
                        men.setImageResource(R.drawable.m_like);
                        if(startAge<=25){
                            image.setImageResource(R.drawable.m1);
                        }
                        else if(startAge<=40){
                            image.setImageResource(R.drawable.m2);
                        }
                        else if(startAge<=59){
                            image.setImageResource(R.drawable.m3);
                        }
                        else{
                            image.setImageResource(R.drawable.oldmen);
                        }
                        m = 1;
                    }
                }
            }
        });

        }
    }
}