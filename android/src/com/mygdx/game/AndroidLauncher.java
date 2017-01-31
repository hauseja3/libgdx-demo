package com.mygdx.game;

import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import java.io.IOException;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rl_main);
        try {
            layout.setBackground(new BitmapDrawable(getResources(), BitmapFactory.decodeStream(getAssets().open("akvarko.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 6. Finally, replace the AndroidLauncher activity content with the Libgdx Fragment.
        GameFragment fragment = new GameFragment();
        android.support.v4.app.FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.fl_libgdx_demo, fragment);
        trans.commit();
    }

    // 4. Create a Class that extends AndroidFragmentApplication which is the Fragment implementation for Libgdx.
    public static class GameFragment extends AndroidFragmentApplication {
        // 5. Add the initializeForView() code in the Fragment's onCreateView method.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
            cfg.r = cfg.g = cfg.b = cfg.a = 8;
            View view = initializeForView(new MyGdxGame(), cfg);
            if (graphics.getView() instanceof SurfaceView) {
                SurfaceView glView = (SurfaceView) graphics.getView();
                glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
                glView.setZOrderOnTop(true);
            }
            return view;
        }
    }


    @Override
    public void exit() {
    }
}