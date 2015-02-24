package me.tatarka.simplefragment.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.tatarka.simplefragment.SimpleFragment;
import me.tatarka.simplefragment.SimpleFragmentIntent;

/**
 * Since this class is constructed through reflection, we can't use a mock. Instead use a poor-man's
 * version by setting booleans when methods are called.
 */
public class TestSimpleFragment extends SimpleFragment {
    @Override
    public void onCreate(Context context, @Nullable Bundle state) {
    }

    @Override
    public ViewHolder onCreateViewHolder(final LayoutInflater inflater, final ViewGroup parent) {
        return new ViewHolder() {
            @Override
            public View getView() {
                return new TestSimpleFragmentRootView(inflater.getContext());
            }
        };
    }
}
