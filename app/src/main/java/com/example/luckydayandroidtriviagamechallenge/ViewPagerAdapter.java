package com.example.luckydayandroidtriviagamechallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Result> results;
    private String answer;
    private GenericCallback<Boolean> genericCallback;
    private Boolean isButtonsDisabled = false;

    public ViewPagerAdapter(Context context, List<Result> results, GenericCallback<Boolean> genericCallback) {
        this.context = context;
        this.results = results;
        this.genericCallback = genericCallback;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_pager_row, container, false);
        TextView textTitle = view.findViewById(R.id.text_title);
        TextView textViewQuestion = view.findViewById(R.id.text_view_question);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        final RadioButton radioButtonTrue = view.findViewById(R.id.radio_button_true);
        final RadioButton radioButtonFalse = view.findViewById(R.id.radio_button_false);
        final Button buttonConfirmNext = view.findViewById(R.id.button_confirm_next);
        final TextView textResult = view.findViewById(R.id.text_result);
        MainActivity mainActivity = new MainActivity();
        if (position == 0)
            textTitle.setVisibility(View.VISIBLE);
        else
            textTitle.setVisibility(View.INVISIBLE);
        textViewQuestion.setText(results.get(position).getQuestion());
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!radioButtonTrue.isChecked() && !radioButtonFalse.isChecked()) {
                    Toast.makeText(view.getContext(), "Please select your answer", Toast.LENGTH_SHORT).show();
                    answer = null;
                    return;
                } else if (radioButtonTrue.isChecked())
                    answer = "True";
                else if (radioButtonFalse.isChecked())
                    answer = "False";
                if (!answer.isEmpty()) {
                    if (answer.equals(results.get(position).getCorrectAnswer()))
                        textResult.setText("Correct");
                    else
                        textResult.setText("Wrong");
                    radioButtonTrue.setEnabled(false);
                    radioButtonFalse.setEnabled(false);
                    buttonConfirmNext.setEnabled(false);
                    isButtonsDisabled = true;
                    genericCallback.onResponse(isButtonsDisabled);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

}
