package com.memo.pcw69.pabixreproject.addword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.memo.pcw69.pabixreproject.ListFragment;
import com.memo.pcw69.pabixreproject.R;
import com.memo.pcw69.pabixreproject.words.RecyclerFragment;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MakeFragment extends Fragment {
    View view;
    Button go;
    EditText title;
    EditText contnet;
    FragmentManager fm;
    FragmentTransaction tran;
    private RecyclerFragment recyclerFragment = new RecyclerFragment();
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_make, container, false);
        go = (Button) view.findViewById(R.id.go);
        title = (EditText) view.findViewById(R.id.title);
        contnet = (EditText) view.findViewById(R.id.content);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(title.getText())) {
                    getActivity().setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = title.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    getActivity().setResult(RESULT_OK, replyIntent);
                }
                //getActivity().finish();

                fm = getFragmentManager();
                //글자색 변경
                ListFragment listFragment = (ListFragment) fm.findFragmentById(R.id.frame_layout);
                listFragment.changeColor();

                //fragment 변경
                tran = fm.beginTransaction();
                tran.replace(R.id.frame, recyclerFragment);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();

            }
        });
        return view;
    }
}
