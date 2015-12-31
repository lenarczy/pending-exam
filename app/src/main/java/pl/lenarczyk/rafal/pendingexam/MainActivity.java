package pl.lenarczyk.rafal.pendingexam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private final View.OnClickListener alarmListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Alarm alarm = new Alarm();
            switch (v.getId()) {
                case R.id.button:
                    alarm.set(getApplicationContext());
                    break;
                case R.id.button2:
                    alarm.cancel(getApplicationContext());
                    break;
                default:
                    throw new IllegalStateException("This listener does not handle id " + v.getId());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonStartingAlarm = (Button) findViewById(R.id.button);
        buttonStartingAlarm.setOnClickListener(alarmListener);
        final Button stopAlarm = (Button) findViewById(R.id.button2);
        stopAlarm.setOnClickListener(alarmListener);

    }
}
