package dontbefa.hcmut.dontbefa;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);


        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    return;
                }

                sendMessage(message);
                if (count == 1) {
                    mimicOtherMessage("Chào!");
                    count++;
                } else if (count == 2) {
                    mimicOtherMessage("Mình tên là Mai");
                    count++;
                } else if (count == 3) {
                    mimicOtherMessage("Uhm, mình làm quen nha :v");
                    mimicOtherMessage();
                    count++;
                } else if (count == 4) {
                    mimicOtherMessage();
                    count++;
                }
                mEditTextMessage.setText("");
            }








        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

    }

    private ListView mListView;
    private Button mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;


    private ChatMessageAdapter mAdapter;




     static int count =1;

    private void sendMessage(String message){
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        mAdapter.notifyDataSetChanged();
    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
        mAdapter.notifyDataSetChanged();
    }

    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true, true);
        mAdapter.add(chatMessage);
        mAdapter.notifyDataSetChanged();
        //mimicOtherMessage();
    }

    private void mimicOtherMessage() {
        ChatMessage chatMessage = new ChatMessage(null, false, true);
        mAdapter.add(chatMessage);
        mAdapter.notifyDataSetChanged();
    }



}
