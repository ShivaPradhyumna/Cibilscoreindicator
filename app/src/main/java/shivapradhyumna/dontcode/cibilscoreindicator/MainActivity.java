package shivapradhyumna.dontcode.cibilscoreindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.dont_code.cibil_score_indicator_library.SmoothArcProgressBar;

public class MainActivity extends AppCompatActivity {

    SmoothArcProgressBar smoothArcProgressBar;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score);
        smoothArcProgressBar = findViewById(R.id.arcProgressBar);
        smoothArcProgressBar.setSmoothProgress(0.68f, 1000, new SmoothArcProgressBar.OnProgressUpdateListener() {
            @Override
            public void onProgressUpdate(float progress) {
                int prg = (int) (progress * 100);
                score.setText(String.valueOf(prg));
            }
        });
    }
}