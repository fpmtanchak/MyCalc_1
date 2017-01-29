package com.example.mycalc_1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

/**
 * Created by Админ on 05.01.2017.
 */

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_calc);
    }

    public void onLink(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100010670018444"));
        startActivity(intent);
    }

    public void onGood(View view) {
        finish();
    }
}

