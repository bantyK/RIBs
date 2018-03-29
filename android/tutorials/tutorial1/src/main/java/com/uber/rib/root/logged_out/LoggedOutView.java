package com.uber.rib.root.logged_out;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uber.rib.tutorial1.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoggedOutView extends LinearLayout implements LoggedOutInteractor.LoggedOutPresenter {

    private static final String TAG = "LoggedOutView";
    private TextView name;
    private Button loginButton;

    public LoggedOutView(Context context) {
        this(context, null);
    }

    public LoggedOutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoggedOutView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        Log.d(TAG, "onFinishInflate: ");
        super.onFinishInflate();
        name = (TextView) findViewById(R.id.edit_text);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    @Override
    public Observable<String> loginName() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) {
                LoggedOutView.this.loginButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emitter.onNext(name.getText().toString());
                    }
                });
            }
        });
    }
}
