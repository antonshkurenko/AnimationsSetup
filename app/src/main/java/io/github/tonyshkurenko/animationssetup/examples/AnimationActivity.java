package io.github.tonyshkurenko.animationssetup.examples;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.tonyshkurenko.animationssetup.R;

public class AnimationActivity extends BaseExampleActivity {

  @BindView(R.id.button_example) Button mButtonExample;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.button_example) public void onClick() {

    if (mRandom.nextBoolean()) {
      final Animation hyperspaceJumpAnimation =
          AnimationUtils.loadAnimation(this, R.anim.anim_hyperspace_jump);
      mButtonExample.startAnimation(hyperspaceJumpAnimation);

      Toast.makeText(AnimationActivity.this, "From xml", Toast.LENGTH_SHORT).show();
    } else {
      /**
       * AlphaAnimation, AnimationSet, RotateAnimation, ScaleAnimation, TranslateAnimation
       */
      final Animation animation = new ScaleAnimation(
          1f, 1f,
          1f, 0.6f,
          Animation.RELATIVE_TO_SELF, 0.5f,
          Animation.RELATIVE_TO_SELF, 0.5f);

      animation.setDuration(ANIMATION_DURATION);

      // if fill after, changes will be applied
      //animation.setFillAfter(true);

      mButtonExample.startAnimation(animation);

      Toast.makeText(AnimationActivity.this, "From code", Toast.LENGTH_SHORT).show();
    }
  }
}
