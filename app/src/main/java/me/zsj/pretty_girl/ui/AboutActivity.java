package me.zsj.pretty_girl.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import me.zsj.pretty_girl.R;
import me.zsj.pretty_girl.databinding.AboutActivityBinding;

/**
 * Created by zsj on 2015/11/24 0024.
 */
public class AboutActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AboutActivityBinding binding =
                DataBindingUtil.setContentView(this, R.layout.about_activity);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RxToolbar.navigationClicks(binding.toolbar)
                .compose(bindToLifecycle())
                .subscribe(aVoid -> {
                    AboutActivity.this.onBackPressed();
                });

        RxView.clicks(binding.cardView)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .subscribe(aVoid -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://github.com/Assassinss/pretty-girl"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                });

        RxView.clicks(binding.cardGankio)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .compose(bindToLifecycle())
                .subscribe(aVoid -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://gank.io"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                });
    }

}