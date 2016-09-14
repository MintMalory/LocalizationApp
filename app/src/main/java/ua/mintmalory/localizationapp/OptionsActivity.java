package ua.mintmalory.localizationapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Locale;

public class OptionsActivity extends AppCompatActivity {

    private RadioButton mEngRadioBtn;
    private RadioButton mUkrRadioBtn;
    private RadioButton mRusRadioBtn;

    private Button mOkBtn;

    private String mLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        initViews();

        Locale current = getResources().getConfiguration().locale;

        switch (current.toString()) {
            case "en":
                mEngRadioBtn.setChecked(true);
                break;
            case "uk":
                mUkrRadioBtn.setChecked(true);
                break;
            case "ru_RU":
                mRusRadioBtn.setChecked(true);
                break;
        }

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.restart_msg), Toast.LENGTH_LONG).show();
                mLang = "en";
                if (mUkrRadioBtn.isChecked()) {
                    mLang = "uk";
                }
                if (mRusRadioBtn.isChecked()) {
                    mLang = "ru";
                }

                Locale locale = new Locale(mLang);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, null);

                finish();
            }
        });

    }

    private void initViews() {
        mEngRadioBtn = (RadioButton) findViewById(R.id.eng_radio_btn);
        mUkrRadioBtn = (RadioButton) findViewById(R.id.ukr_radio_btn);
        mRusRadioBtn = (RadioButton) findViewById(R.id.ru_radio_btn);

        mOkBtn = (Button) findViewById(R.id.ok_button);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale locale = new Locale(mLang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, null);
    }

}
