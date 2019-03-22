package com.apptimizm.mgs.presentation.utils;

import android.widget.EditText;
import com.jakewharton.rxbinding3.widget.RxTextView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class RxUtils {

    private RxUtils() {
    }

    /**
     * Returns an Observable that subscribes to TextView but observes the result on the computation thread.
     */
  public  static Observable<CharSequence> rxTextView(EditText editText) {
        return RxTextView.textChanges(editText)
                // Observe and process in the background.
                .observeOn(Schedulers.computation())
                // Avoid rx.exceptions.MissingBackpressureException.
                .debounce( charSequence -> {
                    if (charSequence.length() == 0) return Observable.just(0L);

                    // Otherwise, wait for 500 millis.
                    return Observable.timer(500, TimeUnit.MILLISECONDS);
                })
                // We need to create the Observable on the main thread.
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}