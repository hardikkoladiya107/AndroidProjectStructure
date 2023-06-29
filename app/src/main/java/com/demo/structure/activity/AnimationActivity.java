package com.demo.structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.demo.structure.R;
import com.demo.structure.databinding.ActivityAnimationBinding;

public class AnimationActivity extends AppCompatActivity {

    ActivityAnimationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.
                setContentView(this,R.layout.activity_animation);
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
                view.startAnimation(animation);
*/

                Intent intent = new Intent(AnimationActivity.this,AdmobActivity.class);
                startActivity(intent);
              ///  overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                //finish();
                overridePendingTransition(R.anim.fadein_anim,  R.anim.fadeout_anim);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isFinishing()){

        }
    }
}