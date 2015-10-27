//此為遊戲介面
package com.example.cftang.kingofbloodcells_v1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.util.HashMap;
import java.util.Map;




public class RBCKingWindows extends ActionBarActivity {

    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button startAndNext;

    private ImageView ImgOptA;
    private ImageView ImgOptB;
    private ImageView ImgOptC;
    private ImageView ImgOptD;
    private ImageView imageBlood;

    private TextView mSec;
    private TextView mScore;
    private int mScoreBoard=0;
    private int secTrace ;

    private boolean startOrNot = false;
    private int quesNum = 5;
    private int[] quesChosen = new int[quesNum];
    private int currentQues ;
    private String[] Options;
    private String ans;
    private String ansOfUser;
    private Integer[] photos;
    private Map<Integer, String[]> PhotoAndOption = new HashMap<>(29);
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST+1;

    boolean butPressed = false;
    private Handler mHandler = new Handler();
    private Thread t;
    private int[] scoreArray = {0,20,40,60,100,160,230,310,400,500};
    private ProfilePictureView profilePictureView;
    public static TextView greeting;
    private CountDownTimer mCountDown;
    private int getTimeInfo;



    private void openOptionsDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(RBCKingWindows.this);
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
    /**Setup text and images of each choice button, time and score display, and the "start and next" button.
     * Also sets HashMap PhotoAndOption: photo as key and 4 choices as value.
     * The correct answer is the last element of String[] value.
     */
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rbcking_windows);


        Profile profile = Profile.getCurrentProfile();
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        greeting = (TextView) findViewById(R.id.greeting);

        profilePictureView.setProfileId(profile.getId());
        greeting.setText(getString(R.string.hello_user, profile.getFirstName()));

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
        /**Describe the things that will happen after clicking the "start and next" button.
         * startOrNot == false: means the game hasn't started yet, or the series of 5 questions has all been shown.
         *                      Clicking the button will start a new game with new scoring.
         * startOrNot == true: means the game is now been playing. Clicking the button will enter the next question.
         */
        public void onClick(View v) {

            imageBlood = (ImageView)findViewById(R.id.imgView);



            //After answering all the questions in the series, startOrNot will be turned to false.
            if(currentQues==quesChosen.length){
                startOrNot=false;
            }

            if(!startOrNot){
                mScoreBoard=0;
                mScore.setText(getText(R.string.score).toString()+mScoreBoard);
                startOrNot=true;
                startAndNext.setText(R.string.nextQues);

                currentQues = 0;

                //create an int[] number to represent serial numbers of each questions in the database.
                int[] number = new int[PhotoAndOption.size()];
                for(int i=0; i<number.length;i++){number[i]=i;}

                //Randomly take 5 unrepeated questions from the database, and save their serial number in int[] quesChosen.
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

//            t = new Thread(runnable);
//            t.start();

            mCountDown = new CountDownTimer(10000,1000){

                @Override
                public void onFinish() {
                    // TODO Auto-generated method stub
                    mSec.setText("Done!");
                }

                @Override
                public void onTick(long millisUntilFinished) {
                    // TODO Auto-generated method stub
                    mSec.setText(getText(R.string.timeLeft).toString()+millisUntilFinished/1000);
                    getTimeInfo = (int)millisUntilFinished/1000;
                }

            }.start();





        }
    };




















//    Runnable runnable = new Runnable() {
//        /**
//         * After the user clicks the choice button, stop the time running.
//         */
//        public void run() {
//            Calendar begin = Calendar.getInstance();
//            butPressed=false;
//            do{
//                try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();};
//                Calendar now = Calendar.getInstance();
//                final int deltaSec = 60*(now.get(Calendar.MINUTE)-begin.get(Calendar.MINUTE))+(now.get(Calendar.SECOND)-begin.get(Calendar.SECOND));
//                if(deltaSec>=10){
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            mSec.setText(getText(R.string.timeLeft).toString()+Integer.toString(0));
//                        }
//                    });
//                    secTrace = 0;
//                    break;
//                }
//                mHandler.post(new Runnable() {
//                    public void run() {
//                        mSec.setText(getText(R.string.timeLeft).toString()+Integer.toString(10-deltaSec));
//                        secTrace = 10-deltaSec;
//                    }
//                });
//                if(butPressed){
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            butPressed=false;
//                        }
//                    });
//                    break;
//                }
//            }while (true);
//            mHandler.post(new Runnable() {
//                public void run() {
//                    if(ans.equalsIgnoreCase(ansOfUser)) {
//                        mScoreBoard += scoreArray[secTrace];
//                        mScore.setText(getText(R.string.score).toString() + mScoreBoard);
//                    }
//                }
//            });
//        }
//    };

    private Button.OnClickListener Abutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {

            // if startOrNot == false, clicking the choice button will lead to nothing happening.
            if(startOrNot) {
                ansOfUser = btnA.getText().toString();
                if (ans.equalsIgnoreCase(ansOfUser)) {
                    ImgOptA.setImageResource(R.drawable.correct_01);
                } else {
                    //If the user chose the incorrect answer, show "X" at the choice chosen and also show "O" at the correct answer.
                    ImgOptA.setImageResource(R.drawable.incorrect_01);
                    if (btnB.getText().toString().equalsIgnoreCase(ans)) ImgOptB.setImageResource(R.drawable.correct_01);
                    if (btnC.getText().toString().equalsIgnoreCase(ans)) ImgOptC.setImageResource(R.drawable.correct_01);
                    if (btnD.getText().toString().equalsIgnoreCase(ans)) ImgOptD.setImageResource(R.drawable.correct_01);
                }
                butPressed=true;
            }
            mCountDown.cancel();
            mScoreBoard += scoreArray[getTimeInfo];
            mScore.setText(getText(R.string.score).toString() + mScoreBoard);
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
            mCountDown.cancel();
            mScoreBoard += scoreArray[getTimeInfo];
            mScore.setText(getText(R.string.score).toString() + mScoreBoard);

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
            mCountDown.cancel();
            mScoreBoard += scoreArray[getTimeInfo];
            mScore.setText(getText(R.string.score).toString() + mScoreBoard);
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
            mCountDown.cancel();
            mScoreBoard += scoreArray[getTimeInfo];
            mScore.setText(getText(R.string.score).toString() + mScoreBoard);
        }
    };





}
