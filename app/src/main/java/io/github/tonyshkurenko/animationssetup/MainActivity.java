package io.github.tonyshkurenko.animationssetup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.github.tonyshkurenko.animationssetup.examples.ActivityTransitionsActivity;
import io.github.tonyshkurenko.animationssetup.examples.AnimationActivity;
import io.github.tonyshkurenko.animationssetup.examples.AnimationDrawableActivity;
import io.github.tonyshkurenko.animationssetup.examples.FragmentTransitionsActivity;
import io.github.tonyshkurenko.animationssetup.examples.ObjectAnimatorActivity;
import io.github.tonyshkurenko.animationssetup.examples.VectorAnimationActivity;
import io.github.tonyshkurenko.animationssetup.examples.ViewPropertyAnimatorActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final List<Demo> EXAMPLES = new ArrayList<Demo>() {{
    add(new Demo("AnimationDrawable", AnimationDrawableActivity.class));
    add(new Demo("Animation", AnimationActivity.class));
    add(new Demo("ObjectAnimator", ObjectAnimatorActivity.class));
    add(new Demo("ViewPropertyAnimator", ViewPropertyAnimatorActivity.class));
    add(new Demo("VectorAnimation", VectorAnimationActivity.class));
    add(new Demo("FragmentTransition", FragmentTransitionsActivity.class));
    add(new Demo("ActivityTransition", ActivityTransitionsActivity.class));
  }};

  @BindView(R.id.list_view_chooser) ListView mListViewChooser;
  private ArrayAdapter<Demo> mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, EXAMPLES);

    mListViewChooser.setAdapter(mAdapter);
  }

  @OnItemClick(R.id.list_view_chooser) void onExampleSelected(int position) {
    startActivity(new Intent(this, mAdapter.getItem(position).activity));
  }

  private static final class Demo {
    final String name;
    final Class activity;

    public Demo(String name, Class activity) {
      this.name = name;
      this.activity = activity;
    }

    @Override public String toString() {
      return name;
    }
  }
}
