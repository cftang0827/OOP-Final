package com.example.user.kingofbloodcells;

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
import android.widget.TextView;
import java.util.Map;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    Button btnA;
    Button btnB;
    Button btnC;
    Button btnD;
    Button startAndNext;
    TextView ansOnScreen;
    ImageView imageBlood;
    boolean startOrNot = false;
    String[] Options;
    String ans;
    String ansOfUser;
    Map<Integer, String[]> PhotoAndOption = new HashMap<Integer, String[]>(6);
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST+1;


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
        startAndNext = (Button)findViewById(R.id.startOrNext);
        startAndNext.setOnClickListener(startNext);
        btnA.setOnClickListener(Abutton);
        btnB.setOnClickListener(Bbutton);
        btnC.setOnClickListener(Cbutton);
        btnD.setOnClickListener(Dbutton);

    }

    private Button.OnClickListener startNext = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            imageBlood = (ImageView)findViewById(R.id.imgView);
            if(startOrNot==false){
                PhotoAndOption.put(R.drawable.bcp_01, new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
                PhotoAndOption.put(R.drawable.bcp_02, new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});
                PhotoAndOption.put(R.drawable.bcp_03, new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.baso)});
                PhotoAndOption.put(R.drawable.bcp_04, new String[]{getString(R.string.sLym), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.sLym)});
                PhotoAndOption.put(R.drawable.bcp_06, new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.neu)});
                PhotoAndOption.put(R.drawable.bcp_08, new String[]{getString(R.string.neu), getString(R.string.baso), getString(R.string.eos), getString((R.string.mono)), getString(R.string.eos)});

                startOrNot=true;
                startAndNext.setText(R.string.nextQues);}
            ansOnScreen = (TextView)findViewById(R.id.answer);
            ansOnScreen.setText("");
            int num= (int)(Math.random()*6+1);
            switch(num){
                case 1:
                    imageBlood.setImageResource(R.drawable.bcp_01);
                    Options=PhotoAndOption.get(R.drawable.bcp_01);
                    break;
                case 2:
                    imageBlood.setImageResource(R.drawable.bcp_02);
                    Options=PhotoAndOption.get(R.drawable.bcp_02);
                    break;
                case 3:
                    imageBlood.setImageResource(R.drawable.bcp_03);
                    Options=PhotoAndOption.get(R.drawable.bcp_03);
                    break;
                case 4:
                    imageBlood.setImageResource(R.drawable.bcp_04);
                    Options=PhotoAndOption.get(R.drawable.bcp_04);
                    break;
                case 5:
                    imageBlood.setImageResource(R.drawable.bcp_06);
                    Options=PhotoAndOption.get(R.drawable.bcp_06);
                    break;
                case 6:
                    imageBlood.setImageResource(R.drawable.bcp_08);
                    Options=PhotoAndOption.get(R.drawable.bcp_08);
                    break;
                default:
                    break;
            }
            btnA.setText(Options[0]);
            btnB.setText(Options[1]);
            btnC.setText(Options[2]);
            btnD.setText(Options[3]);
            ans = Options[4];
        }
    };

    private Button.OnClickListener Abutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            ansOfUser = btnA.getText().toString();
            ansOnScreen = (TextView)findViewById(R.id.answer);
            if(ans.equals(ansOfUser)){
                ansOnScreen.setText(R.string.correctString);
            }else ansOnScreen.setText(getString(R.string.wrongString) + ans);
        }
    };

    private Button.OnClickListener Bbutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            ansOfUser = btnB.getText().toString();
            ansOnScreen = (TextView)findViewById(R.id.answer);
            if(ans.equals(ansOfUser)){
                ansOnScreen.setText(R.string.correctString);
            }else ansOnScreen.setText(getString(R.string.wrongString) + ans);
        }
    };

    private Button.OnClickListener Cbutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            ansOfUser = btnC.getText().toString();
            ansOnScreen = (TextView)findViewById(R.id.answer);
            if(ans.equals(ansOfUser)){
                ansOnScreen.setText(R.string.correctString);
            }else ansOnScreen.setText(getString(R.string.wrongString) + ans);
        }
    };

    private Button.OnClickListener Dbutton = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            ansOfUser = btnD.getText().toString();
            ansOnScreen = (TextView)findViewById(R.id.answer);
            if(ans.equals(ansOfUser)){
                ansOnScreen.setText(R.string.correctString);
            }else ansOnScreen.setText(getString(R.string.wrongString) + ans);
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
