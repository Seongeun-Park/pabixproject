package com.memo.pcw69.pabixreproject;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EditFragment extends Fragment {
    View view;
    EditText editText;
    TextView textView;
    Button transparent;
    Button text_bt;
    Button background_bt;
    Button button;
    Button black;
    Button gray;
    Button white;
    Button red;
    Button yellow;
    Button green;
    Button blue;
    Button purple;
    SeekBar seekBar;
    SeekBar seekBar2;
    ConstraintLayout layout;
    int check = 0;
    int size = 20;
    int clear;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit, container, false);
        editText = (EditText) view.findViewById(R.id.editText);
        textView = (TextView) view.findViewById(R.id.textView);
        transparent = (Button) view.findViewById(R.id.transparent);
        text_bt = (Button) view.findViewById(R.id.text);
        background_bt = (Button) view.findViewById(R.id.background);
        button = (Button) view.findViewById(R.id.button);
        black = (Button) view.findViewById(R.id.black);
        gray = (Button) view.findViewById(R.id.gray);
        white = (Button) view.findViewById(R.id.white);
        red = (Button) view.findViewById(R.id.red);
        yellow = (Button) view.findViewById(R.id.yellow);
        green = (Button) view.findViewById(R.id.green);
        blue = (Button) view.findViewById(R.id.blue);
        purple = (Button) view.findViewById(R.id.purple);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) view.findViewById(R.id.seekBar2);
        layout = (ConstraintLayout) view.findViewById(R.id.layout);
        check = 0;
        SharedPreferences sharedPreferences
                = getActivity().getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);
        textView.setText(sharedPreferences.getString("textbox", "빠른메모"));
        textView.setMovementMethod(new ScrollingMovementMethod());//미리보기 스크롤
        editText.setText(sharedPreferences.getString("textbox", "빠른메모"));
        textView.setTextColor(sharedPreferences.getInt("color", ContextCompat.getColor(getActivity(), R.color.black)));
        textView.setBackgroundColor(sharedPreferences.getInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.transparent)));
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidekeyboard();
            }
        });
        text_bt.setOnClickListener(new View.OnClickListener() {
            //글자 버튼
            @Override
            public void onClick(View v) {
                text_bt.setTextColor(Color.parseColor("#000000"));
                background_bt.setTextColor(Color.parseColor("#FFAAAAAA"));

                check = 0;
                seekBar.setVisibility(View.VISIBLE);
                seekBar2.setVisibility(View.INVISIBLE);
            }
        });
        background_bt.setOnClickListener(new View.OnClickListener() {
            //배경 버튼
            @Override
            public void onClick(View v) {
                text_bt.setTextColor(Color.parseColor("#FFAAAAAA"));
                background_bt.setTextColor(Color.parseColor("#000000"));
                check = 1;
                seekBar2.setVisibility(View.VISIBLE);
                seekBar.setVisibility(View.INVISIBLE);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidekeyboard();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidekeyboard();
                textView.setText(editText.getText());
                Toast.makeText(getActivity(), "저장되었습니다", Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences
                        = getActivity().getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("textbox", textView.getText().toString());
                editor.commit();

                Intent intent = new Intent(getActivity(), NewAppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                getActivity().sendBroadcast(intent);
            }
        });
        size = sharedPreferences.getInt("size", 35);
        seekBar.setMax(30);
        seekBar.setProgress(size - 20);
        changesize(size);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changesize(progress + 20);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences sharedPreferences
                        = getActivity().getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("size", seekBar.getProgress() + 20);
                editor.commit();

                Intent intent = new Intent(getActivity(), NewAppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                getActivity().sendBroadcast(intent);
            }
        });
        seekBar2.setMax(255);
        seekBar2.setProgress(sharedPreferences.getInt("progress", 0));
        textView.setBackgroundColor(sharedPreferences.getInt("clear", ContextCompat.getColor(getActivity(), R.color.transparent)));
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences sharedPreferences
                        = getActivity().getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);
                clear = progress * 16777216 + sharedPreferences.getInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.transparent));
                textView.setBackgroundColor(clear);
                if (progress == 0) {
                    textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.transparent));
                    clear = 16777216 + sharedPreferences.getInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.transparent));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences sharedPreferences
                        = getActivity().getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("progress", seekBar2.getProgress());
                editor.putInt("clear", clear);
                editor.commit();

                Intent intent = new Intent(getActivity(), NewAppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                getActivity().sendBroadcast(intent);
            }
        });
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar2.setProgress(255);
                SharedPreferences sharedPreferences
                        = getActivity().getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                switch (v.getId()) {
                    case R.id.black:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.black));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.black));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.black));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.black));
                        }
                        break;
                    case R.id.gray:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.gray));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.gray));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.gray));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
                        }
                        break;
                    case R.id.white:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.white));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.white));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.white));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                        }
                        break;
                    case R.id.red:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.red));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.red));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.red));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.red));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.red));
                        }
                        break;
                    case R.id.yellow:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.yellow));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.yellow));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.yellow));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.yellow));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.yellow));
                        }
                        break;
                    case R.id.green:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.green));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.green));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.green));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.green));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green));
                        }
                        break;
                    case R.id.blue:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.blue));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.blue));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.blue));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue));
                        }
                        break;
                    case R.id.purple:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.purple));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.purple));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.purple));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.purple));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.purple));
                        }
                        break;
                    case R.id.transparent:
                        if (check == 0) {
                            editor.putInt("color", ContextCompat.getColor(getActivity(), R.color.transparent));
                            textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.transparent));
                        } else {
                            editor.putInt("bgcolor", ContextCompat.getColor(getActivity(), R.color.transparent));
                            editor.putInt("clear", ContextCompat.getColor(getActivity(), R.color.transparent));
                            textView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.transparent));
                        }
                        break;
                }
                editor.putInt("progress", 255);
                editor.commit();

                Intent intent = new Intent(getActivity(), NewAppWidget.class);
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                getActivity().sendBroadcast(intent);
            }
        };
        black.setOnClickListener(onClickListener);
        gray.setOnClickListener(onClickListener);
        white.setOnClickListener(onClickListener);
        red.setOnClickListener(onClickListener);
        yellow.setOnClickListener(onClickListener);
        green.setOnClickListener(onClickListener);
        blue.setOnClickListener(onClickListener);
        purple.setOnClickListener(onClickListener);
        transparent.setOnClickListener(onClickListener);
        return view;
    }

    public void changesize(int size) {
        //글자 크기 바꿔주는 함수
        textView.setTextSize((float) size);
    }

    public void hidekeyboard() {
        InputMethodManager imm;
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
