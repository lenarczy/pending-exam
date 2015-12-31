package pl.lenarczyk.rafal.pendingexam;

import android.os.Bundle;
import android.widget.TextView;

public class ExamInfo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_info);
        final TextView msgView = (TextView) findViewById(R.id.tv_exam_info_msg);
        String msg = getApplicationContext().getString(R.string.exam_info_msg);
        msg = String.format(msg, new DayCounter().days());
        msgView.setText(msg);
    }
}
