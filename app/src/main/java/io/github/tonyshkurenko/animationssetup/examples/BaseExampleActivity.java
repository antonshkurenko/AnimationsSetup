package io.github.tonyshkurenko.animationssetup.examples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import java.util.Random;

/**
 * Created by: Anton Shkurenko (tonyshkurenko)
 * Project: AnimationsSetup
 * Date: 6/29/16
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public abstract class BaseExampleActivity extends AppCompatActivity {

  protected static final int ANIMATION_DURATION = 500;

  protected final Random mRandom = new Random();

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);

    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }

    return super.onOptionsItemSelected(item);
  }
}
