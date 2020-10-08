package pens.lab.app.belajaractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int counter = intent.getIntExtra("counter", 999);

        TextView tvSecondActivity = findViewById(R.id.tvSecondActivity);
        tvSecondActivity.setText("Nilai counter dari first activity adalah = "+ counter);


    }

}
