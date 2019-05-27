package com.example.askandteach.TopicDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.askandteach.R;
import com.example.askandteach.models.Topic;
import com.example.askandteach.profile.ViewProfileActivity;
import com.example.askandteach.utils;

public class TopicDetail extends AppCompatActivity {

    TextView txtTopicName , txtTeacher , txtContent , txtTime , txtType , txtCreator;
    String topic_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        addControls();

    }

    public static void start(Context context, Topic topic){
        Intent intent = new Intent(context, TopicDetail.class);
        intent.putExtra("TOPIC", topic);
        context.startActivity(intent);
    }

    private void addControls(){
        txtCreator = findViewById(R.id.txtCreator);
        txtTopicName = findViewById(R.id.txtTopicName);
        txtContent = findViewById(R.id.txtContent);
        txtTime = findViewById(R.id.txtTime);
        txtType = findViewById(R.id.txtType);

        final Topic to = (Topic) getIntent().getSerializableExtra("TOPIC");

        txtCreator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProfileActivity.start(getParent(),to.getCreator().getId());
            }
        });

        topic_id = to.getId().toString();
        txtTopicName.setText(to.getTitle());
        txtContent.setText(to.getContent());
        txtTime.setText(to.getCreatedAt());
        txtType.setText(to.getTopicType());
        txtCreator.setText(to.getCreator().getUsername());


    }

}
