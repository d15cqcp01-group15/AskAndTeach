package com.example.askandteach.EventDetail;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.askandteach.AsyncTaskLoadImage;
        import com.example.askandteach.R;
        import com.example.askandteach.createCourse.CreateCourseActivity;
        import com.example.askandteach.models.Course;
        import com.example.askandteach.models.Event;
        import com.example.askandteach.profile.ViewProfileActivity;
        import com.example.askandteach.utils;

        import java.io.Serializable;

public class EventDetail extends AppCompatActivity {

    TextView tvCreator, txtAddress, txtTime, txtJoined, txtPrice, txtDescripton, txtTopicname;
    Button btnRegisterEvent;
    String event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        addControls();
    }

    public static void start(Context context, Event event){
        Intent intent = new Intent(context, EventDetail.class);
        intent.putExtra("EVENT", event);
        context.startActivity(intent);
    }

    private void addControls(){
        tvCreator = findViewById(R.id.txtCreator);
        txtTopicname = findViewById(R.id.txtTopicName);
        txtAddress = findViewById(R.id.txtAddress);
        txtTime = findViewById(R.id.txtTime);
        txtJoined = findViewById(R.id.txtJoined);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescripton = findViewById(R.id.txtDescription);
        btnRegisterEvent = findViewById(R.id.btnRegisterEvent);

        final Event ev = (Event) getIntent().getSerializableExtra("EVENT");

        tvCreator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(getParent(), ev.getUser().getId());
            }
        });

        event_id = ev.getId().toString();
        txtTopicname.setText(ev.getTitle());
        tvCreator.setText(ev.getUser().getUsername());
        txtAddress.setText(ev.getAddress().toString());
        txtTime.setText(utils.timestampToDatestring(ev.getOpenTime()));
        txtDescripton.setText(ev.getDescription());
        txtJoined.setText(ev.getAmountStudent().toString());
        txtPrice.setText(ev.getPrice().toString());
    }

}
