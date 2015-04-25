package me.tatarka.simplefragment.activity;

import android.app.Activity;
import android.os.Bundle;

import me.tatarka.simplefragment.SimpleFragmentContainerManager;
import me.tatarka.simplefragment.SimpleFragmentContainerManagerProvider;
import me.tatarka.simplefragment.SimpleFragmentManager;
import me.tatarka.simplefragment.SimpleFragmentManagerProvider;

/**
 * Created by evan on 3/7/15.
 */
public class SimpleFragmentActivity extends Activity implements SimpleFragmentManagerProvider, SimpleFragmentContainerManagerProvider {
    private SimpleFragmentDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSimpleFragmentDelegate().installViewFactory(null);
        super.onCreate(savedInstanceState);
        getSimpleFragmentDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSimpleFragmentDelegate().onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSimpleFragmentDelegate().onSaveInstanceState(outState);
    }

    @Override
    public final Object onRetainNonConfigurationInstance() {
        return getSimpleFragmentDelegate().onRetainNonConfigurationInstance(onRetainCustomNonConfigurationInstance());
    }

    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public Object getLastCustomNonCofigurationInstance() {
        return getSimpleFragmentDelegate().getLastCustomNonConfigurationInstance(getLastNonConfigurationInstance());
    }

    @Override
    public void onBackPressed() {
        if (!getSimpleFragmentDelegate().onBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public SimpleFragmentManager getSimpleFragmentManager() {
        return getSimpleFragmentDelegate().getSimpleFragmentManager();
    }

    @Override
    public SimpleFragmentContainerManager getSimpleFragmentContainerManager() {
        return getSimpleFragmentDelegate().getSimpleFragmentContainerManager();
    }

    public SimpleFragmentDelegate getSimpleFragmentDelegate() {
        if (delegate == null) {
            delegate = SimpleFragmentDelegate.create(this);
        }
        return delegate;
    }
}
