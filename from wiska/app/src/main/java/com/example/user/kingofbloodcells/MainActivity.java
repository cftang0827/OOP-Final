package com.example.user.kingofbloodcells;

import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
//import android.content.res.Resources;
//import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.widget.TextView;
//import android.os.Message;


public class MainActivity extends ActionBarActivity {

    Button btnA;
    Button btnB;
    Button btnC;
    Button btnD;
    Button startAndNext;

    ImageView ImgOptA;
    ImageView ImgOptB;
    ImageView ImgOptC;
    ImageView ImgOptD;
    ImageView imageBlood;

    TextView mSec;
    TextView mScore;
    int mScoreBoard=0;
    int secTrace ;

    boolean startOrNot = false;
    int quesNum = 5;
    int[] quesChosen = new int[quesNum];
    int currentQues ;
    String[] Options;
    String ans;
    String ansOfUser;
    Integer[] photos;
    Map<Integer, String[]> PhotoAndOption = new HashMap<>(29);
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST+1;

    boolean butPressed = false;
    Handler mHandler = new Handler();
    Thread t;
    int[] scoreArray = {0,20,40,60,100,160,230,310,400,500};



//    private Handler handler=new Handler() {
//
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (ans.equalsIgnoreCase(btnA.getText().toString())) ImgOptA.setImageResource(R.drawable.correct_01);
//            if (ans.equalsIgnoreCase(btnB.getText().toString())) ImgOptB.setImageResource(R.drawable.correct_01);
//            if (ans.equalsIgnoreCase(btnC.getText().toString())) ImgOptC.setImageResource(R.drawable.correct_01);
//            if (ans.equalsIgnoreCase(btnD.getText().toString())) ImgOptD.setImageResource(R.drawable.correct_01);
//            mSec.setImageResource(R.drawable.sec_00);
//        }
//    };

    private void openOptionsDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(R.string.about_title);
        dialog.setMessage(R.string.about_msg);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                });
        dialog.setNegativeButton(R.string.homepage_label,
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialoginterface, int i) {
                        //go to url
                        Uri uri = Uri.parse("https://www.facebook.com/wsxswqazaa?fref=ts");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

        dialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnA = (Button)findViewById(R.id.OptionA);
        btnB = (Button)findViewById(R.id.OptionB);
        btnC = (Button)findViewById(R.id.OptionC);
        btnD = (Button)findViewById(R.id.OptionD);

        ImgOptA = (ImageView)findViewById(R.id.ImgOptA);
        ImgOptB = (ImageView)findViewById(R.id.ImgOptB);
        ImgOptC = (ImageView)findViewById(R.id.ImgOptC);
        ImgOptD = (ImageView)findViewById(R.id.ImgOptD);
        mSec = (TextView)findViewById(R.id.secRemain);
        mScore = (TextView)findViewById(R.id.scoreRecord);

        startAndNext = (Button)findViewById(R.id.startOrNext);
        startAndNext.setOnClickListener(startNext);
        btnA.setOnClickListener(Abutton);
        btnB.setOnClickListener(Bbutton);
        btnC.setOnClickListener(Cbutton);
        btnD.setOnClickListener(Dbutton);

        photos = new Integer[]{
                R.drawable.bcp_01,R.drawable.bcp_02,R.drawable.bcp_03,R.drawable.bcp_04,R.drawable.bcp_05,R.drawable.bcp_06,
                R.drawable.bcp_07,R.drawable.bcp_08,R.drawable.bcp_09,R.drawable.bcp_10,R.drawable.bcp_11,R.drawable.bcp_12,
                R.drawable.bcp_13,R.drawable.bcp_14,R.drawable.bcp_15,R.drawable.bcp_16,R.drawable.bcp_17,R.drawable.bcp_18,
                R.drawable.bcp_19,R.drawable.bcp_20,R.drawable.bcp_21,R.drawable.bcp_22,R.drawable.bcp_23,R.drawable.bcp_24,
                R.drawable.bcp_25,R.drawable.bcp_26,R.drawable.bcp_27,R.drawable.bcp_28,R.drawable.bcp_29};
        PhotoAndOption.put(photos[0], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[1], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[2], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.baso)});
        PhotoAndOption.put(photos[3], new String[]{getString(R.string.sLym), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.sLym)});
        PhotoAndOption.put(photos[4], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[5], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[6], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[7], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[8], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.baso)});
        PhotoAndOption.put(photos[9], new String[]{getString(R.string.sLym), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.sLym)});
        PhotoAndOption.put(photos[10], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[11], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[12], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[13], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[14], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.baso)});
        PhotoAndOption.put(photos[15], new String[]{getString(R.string.sLym), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.sLym)});
        PhotoAndOption.put(photos[16], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[17], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[18], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[19], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[20], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.baso)});
        PhotoAndOption.put(photos[21], new String[]{getString(R.string.sLym), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.sLym)});
        PhotoAndOption.put(photos[22], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[23], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[24], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
        PhotoAndOption.put(photos[25], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
        PhotoAndOption.put(photos[26], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.baso)});
        PhotoAndOption.put(photos[27], new String[]{getString(R.string.sLym), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.sLym)});
        PhotoAndOption.put(photos[28], new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});

    }



    private Button.OnClickListener startNext = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            imageBlood = (ImageView)findViewById(R.id.imgView);
            if(currentQues==quesChosen.length){
                startOrNot=false;
            }
            if(!startOrNot){
                mScoreBoard=0;
                mScore.setText(getText(R.string.score).toString()+mScoreBoard);
                startOrNot=true;
                startAndNext.setText(R.string.nextQues);

                currentQues = 0;
                int[] number = new int[PhotoAndOption.size()];
                for(int i=0; i<number.length;i++){number[i]=i;}
                for(int i=1; i<=quesNum; i++) {
                    int num = (int) (Math.random() * (number.length-i+1));
                    quesChosen[i-1]=number[num];
                    int temp = number[number.length -i];
                    number[number.length - 1] = number[num];
                    number[num] = temp;
                }
            }

            imageBlood.setImageResource(photos[quesChosen[currentQues]]);
            Options = PhotoAndOption.get(photos[quesChosen[currentQues]]);
            currentQues++;
            ImgOptA.setImageResource(R.drawable.gray_01);
            ImgOptB.setImageResource(R.drawable.gray_01);
            ImgOptC.setImageResource(R.drawable.gray_01);
            ImgOptD.setImageResource(R.drawable.gray_01);
            btnA.setText(Options[0]);
            btnB.setText(Options[1]);
            btnC.setText(Options[2]);
            btnD.setText(Options[3]);
            ans = Options[4];

            t = new Thread(runnable);
            t.start();
        }
    };

    Runnable runnable = new Runnable() {
        public void run() {
            Calendar begin = Calendar.getInstance();
            butPressed=false;
            do{
                try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();};
                Calendar now = Calendar.getInstance();
                final int deltaSec = 60*(now.get(Calendar.MINUTE)-begin.get(Calendar.MINUTE))+(now.get(Calendar.SECOND)-begin.get(Calendar.SECOND));
                if(deltaSec>=10){
                    mHandler.post(new Runnable() {
                        public void run() {
                            mSec.setText(getText(R.string.timeLeft).toString()+Integer.toString(0));
                        }
                    });
                    secTrace = 0;
                    break;
                }
                mHandler.post(new Runnable() {
                    public void run() {
                        mSec.setText(getText(R.string.timeLeft).toString()+Integer.toString(10-deltaSec));
                        secTrace = 10-deltaSec;
                    }
                });
                if(butPressed){
                    mHandler.post(new Runnable() {
                        public void run() {
                            butPressed=false;
                        }
                    });
                    break;
                }
            }while (true);
            mHandler.post(new Runnable() {
                public void run() {
                    if(ans.equalsIgnoreCase(ansOfUser)) {
                        mScoreBoard += scoreArray[secTrace];
                        mScore.setText(getText(R.string.score).toString() + mScoreBoard);
                    }
                }
            });
        }
    };

    private Button.OnClickListener Abutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(startOrNot) {
                ansOfUser = btnA.getText().toString();
                if (ans.equalsIgnoreCase(ansOfUser)) {
                    ImgOptA.setImageResource(R.drawable.correct_01);
                } else {
                    ImgOptA.setImageResource(R.drawable.incorrect_01);
                    if (btnB.getText().toString().equalsIgnoreCase(ans)) ImgOptB.setImageResource(R.drawable.correct_01);
                    if (btnC.getText().toString().equalsIgnoreCase(ans)) ImgOptC.setImageResource(R.drawable.correct_01);
                    if (btnD.getText().toString().equalsIgnoreCase(ans)) ImgOptD.setImageResource(R.drawable.correct_01);
                }
                butPressed=true;
            }
        }
    };

    private Button.OnClickListener Bbutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(startOrNot) {
                ansOfUser = btnB.getText().toString();
                if (ans.equalsIgnoreCase(ansOfUser)) {
                    ImgOptB.setImageResource(R.drawable.correct_01);
                } else {
                    ImgOptB.setImageResource(R.drawable.incorrect_01);
                    if (btnA.getText().toString().equalsIgnoreCase(ans)) ImgOptA.setImageResource(R.drawable.correct_01);
                    if (btnC.getText().toString().equalsIgnoreCase(ans)) ImgOptC.setImageResource(R.drawable.correct_01);
                    if (btnD.getText().toString().equalsIgnoreCase(ans)) ImgOptD.setImageResource(R.drawable.correct_01);
                }
                butPressed=true;
            }

        }
    };

    private Button.OnClickListener Cbutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(startOrNot) {
                ansOfUser = btnC.getText().toString();
                if (ans.equalsIgnoreCase(ansOfUser)) {
                    ImgOptC.setImageResource(R.drawable.correct_01);
                } else {
                    ImgOptC.setImageResource(R.drawable.incorrect_01);
                    if (btnA.getText().toString().equalsIgnoreCase(ans)) ImgOptA.setImageResource(R.drawable.correct_01);
                    if (btnB.getText().toString().equalsIgnoreCase(ans)) ImgOptB.setImageResource(R.drawable.correct_01);
                    if (btnD.getText().toString().equalsIgnoreCase(ans)) ImgOptD.setImageResource(R.drawable.correct_01);
                }
                butPressed=true;
            }
        }
    };

    private Button.OnClickListener Dbutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(startOrNot) {
                ansOfUser = btnD.getText().toString();
                if (ans.equalsIgnoreCase(ansOfUser)) {
                    ImgOptD.setImageResource(R.drawable.correct_01);
                } else {
                    ImgOptD.setImageResource(R.drawable.incorrect_01);
                    if (btnA.getText().toString().equalsIgnoreCase(ans)) ImgOptA.setImageResource(R.drawable.correct_01);
                    if (btnC.getText().toString().equalsIgnoreCase(ans)) ImgOptC.setImageResource(R.drawable.correct_01);
                    if (btnB.getText().toString().equalsIgnoreCase(ans)) ImgOptB.setImageResource(R.drawable.correct_01);
                }
                butPressed=true;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_ABOUT, 0, R.string.menu_about);
        menu.add(0, MENU_Quit, 0, R.string.menu_quit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
            switch (item.getItemId()) {
                case MENU_ABOUT:
                    openOptionsDialog();
                    break;
                case MENU_Quit:
                    finish();
                    break;
            }
            return true;
        }

    }
