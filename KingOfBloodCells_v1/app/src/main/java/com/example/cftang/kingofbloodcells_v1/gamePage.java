//此為遊戲選擇頁面
package com.example.cftang.kingofbloodcells_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;


public class gamePage extends ActionBarActivity {

    private ProfilePictureView profilePictureView;

    private Button RBCKing;
    public static TextView greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_page);

        Profile profile = Profile.getCurrentProfile();
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        greeting = (TextView) findViewById(R.id.greeting);

        profilePictureView.setProfileId(profile.getId());
        greeting.setText(getString(R.string.hello_user, profile.getFirstName()));
        RBCKing = (Button)findViewById(R.id.rbcking);
        RBCKing.setOnClickListener(new View.OnClickListener()
        {
            public  void onClick(View view)
            {
                //new Activity
                Intent intentFromMain2game1 = new Intent();
                intentFromMain2game1.setClass(gamePage.this, RBCKingWindows.class);
                startActivity(intentFromMain2game1);

            }
        }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateUI() {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;



//        postStatusUpdateButton.setEnabled(enableButtons || canPresentShareDialog);
//        postPhotoButton.setEnabled(enableButtons || canPresentShareDialogWithPhotos);
//
        Profile profile = Profile.getCurrentProfile();
        profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        greeting = (TextView) findViewById(R.id.greeting);

            profilePictureView.setProfileId(profile.getId());
            greeting.setText(getString(R.string.hello_user, profile.getFirstName()));

    }

}



