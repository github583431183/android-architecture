/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zl.weilu.saber.viewmodel;

import android.app.Application;
import android.os.SystemClock;

import com.zl.weilu.saber.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A ViewModel used for the {@link MainActivity}.
 */
public class LiveDataTimerViewModel extends TimerViewModel {

    private static final int ONE_SECOND = 1000;

    private long mInitialTime;

    public LiveDataTimerViewModel(Application application) {
        super(application);

        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                // setValue() cannot be called from a background thread so post to main thread.
                postTime(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

}
