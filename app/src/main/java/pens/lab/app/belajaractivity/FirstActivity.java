package pens.lab.app.belajaractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collection;

public class FirstActivity extends AppCompatActivity {
    TextView tvFirstActivity;
    Button btnCounter;
    Button btnMove;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        tvFirstActivity = findViewById(R.id.tvFirstActivity);
        btnMove = findViewById(R.id.btnMove);
        btnCounter = findViewById(R.id.btnCounter);

        tvFirstActivity.setText("Counter = "+counter);

        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementCounter();
            }
        });
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move();
            }
        });
    }

    public void move(){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("counter", counter);
        startActivity(intent);
    }

    public void incrementCounter(){
        counter++;
        tvFirstActivity.setText("Counter = "+counter);
    }
}