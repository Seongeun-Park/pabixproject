package com.memo.pcw69.pabixreproject;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.memo.pcw69.pabixreproject.addword.MakeFragment;
import com.memo.pcw69.pabixreproject.words.RecyclerFragment;


public class ListFragment extends Fragment {
    View view;
    FragmentManager fm;
    FragmentTransaction tran;
    private RecyclerFragment recyclerFragment = new RecyclerFragment();
    private MakeFragment makeFragment= new MakeFragment();
    Button list;
    Button make;
    public static Context context;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        list = (Button) view.findViewById(R.id.list);
        make = (Button) view.findViewById(R.id.make);
        // 첫 화면 지정
        setFrag(0);
        // Button의 아이템이 선택될 때 호출될 리스너 등록
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentTransaction transaction = fragmentManager2.beginTransaction();
                switch (v.getId()) {
                    case R.id.list:
                        setFrag(0);
                        list.setTextColor(Color.parseColor("#000000"));
                        make.setTextColor(Color.parseColor("#FFAAAAAA"));
                        break;sdfghjkhjgfdsaadfsghjkl;jhugyftdrs
                    case R.id.make:
                        setFrag(1);
                        make.setTextColor(Color.parseColor("#000000"));
                        list.setTextColor(Color.parseColor("#FFAAAAAA"));
                        break;
                }

                Intent intent = new Intent(getActivity(), NewAppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                getActivity().sendBroadcast(intent);
            }
        };
        list.setOnClickListener(onClickListener);
        make.setOnClickListener(onClickListener);

        return view;
    }
    public void  changeColor(){
        list.setTextColor(Color.parseColor("#000000"));
        make.setTextColor(Color.parseColor("#FFAAAAAA"));
    }

    public void setFrag(int n) {    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
        fm = getFragmentManager();
        tran = fm.beginTransaction();
        switch (n) {
            case 0:
                tran.replace(R.id.frame, recyclerFragment);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                tran.commit();
                break;
            case 1:
                tran.replace(R.id.frame, makeFragment);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                //startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                tran.commit();
                break;
        }
    }
}
