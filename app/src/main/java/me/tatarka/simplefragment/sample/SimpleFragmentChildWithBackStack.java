package me.tatarka.simplefragment.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.tatarka.simplefragment.SimpleFragment;

/**
 * Created by evan on 2/2/15.
 */
public class SimpleFragmentChildWithBackStack extends SimpleFragment<SimpleFragmentChildWithBackStack.ChildWithBackStackViewHolder> {
    public static final String ARG_VIEW_ID = "view_id";
    public static final String ARG_STACK_COUNT = "stack_count";

    private int stackCount;
    private int viewId;
    private OnAddListener addListener;
    private OnRemoveListener removeListener;

    public void setListeners(OnAddListener addListener, OnRemoveListener removeListener) {
        this.addListener = addListener;
        this.removeListener = removeListener;
    }

    @Override
    public void onCreate(Context context, @Nullable Bundle state) {
        stackCount = getIntent().getIntArg(ARG_STACK_COUNT, 0);
        viewId = getIntent().getIntArg(ARG_VIEW_ID);
    }

    @Override
    public ChildWithBackStackViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ChildWithBackStackViewHolder(inflater.inflate(R.layout.fragment_child_with_backstack, parent, false));
    }

    class ChildWithBackStackViewHolder implements SimpleFragment.ViewHolder {
        private View view;

        public ChildWithBackStackViewHolder(View view) {
            this.view = view;

            TextView countView = (TextView) view.findViewById(R.id.backstack_count);
            countView.setText("" + stackCount);

            Button addButton = (Button) view.findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addListener != null) {
                        addListener.onAdd(viewId, stackCount);
                    }
                }
            });

            Button removeButton = (Button) view.findViewById(R.id.remove_button);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (stackCount > 0 && removeListener != null) {
                        removeListener.onRemove(SimpleFragmentChildWithBackStack.this);
                    }
                }
            });
        }

        @Override
        public View getView() {
            return view;
        }
    }

    public interface OnAddListener {
        void onAdd(int viewId, int stackCount);
    }

    public interface OnRemoveListener {
        void onRemove(SimpleFragmentChildWithBackStack fragment);
    }
}