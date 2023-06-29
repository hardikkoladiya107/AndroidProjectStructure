package com.demo.structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityGridViewWithDividerBinding;

import java.util.ArrayList;

public class GridViewWithDividerActivity extends AppCompatActivity {

    ActivityGridViewWithDividerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid_view_with_divider);
        tryAnother();
    }

    private void tryAnother() {
        ArrayList<String> ss = new ArrayList<>();
        ss.add("Hardik");
        ss.add("Kishan");
        ss.add("vijo");
        ss.add("chotu");
        ss.add("munno");
        ss.add("ayushi");
        ss.add("vijay");
        ss.add("rutvik");
        ss.add("milan");
        ss.add("muko");
        ss.add("chappal");

        try {
            for (int i = 0; i < ss.size(); i = i + 2) {
                binding.mainLayout.addView(getHorizontalDivider());
                if ((i + 1) > ss.size()-1) {
                    binding.mainLayout.addView(getInnerLayout(ss.get(i), null));
                } else {
                    binding.mainLayout.addView(getInnerLayout(ss.get(i), ss.get(i + 1)));
                }
            }
        } catch (Exception e) {
            Log.e("TAG", "tryAnother: "+e);
        }

    }

    private LinearLayout getInnerLayout(String left, String right) {
        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        ll.setLayoutParams(param);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        ll.addView(getLayoutview(ll, left));
        ll.addView(getVerticleDivider());
        ll.addView(getLayoutview(ll, right));
        return ll;
    }

    private View getHorizontalDivider() {
        Drawable divider = ResourcesCompat.getDrawable(getResources(), R.drawable.horizontal_line, null);
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                5
        );
        imageView.setLayoutParams(param);
        imageView.setBackground(divider);
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return imageView;
    }

    private View getVerticleDivider() {
        Drawable divider = ResourcesCompat.getDrawable(getResources(), R.drawable.vertical_line, null);
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        param.setMargins(0, 30, 0, 30);
        imageView.setLayoutParams(param);
        imageView.setBackground(divider);
        imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        return imageView;
    }

    private View getLayoutview(LinearLayout parent, String data) {
        View view = LayoutInflater.from(this).inflate(R.layout.gridlayout_sample_layout, parent, false);
        if (data != null) {
            AppCompatTextView textView =  ((AppCompatTextView) view.findViewById(R.id.titletext));
            textView.setText(data.toString());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), textView.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            CardView cardView =  ((CardView) view.findViewById(R.id.cardlayout));
            cardView.setVisibility(View.GONE);
        }
        return view;
    }


}