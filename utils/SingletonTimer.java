
import android.os.CountDownTimer;
import android.util.Log;

public class SingletonTimer {
    private static final String         TAG      = "Singleton";
    private static       SingletonTimer instance = null;
    private static       int            lastTime = 0;
    private CountDownTimer mCountDownTimer;

    private SingletonTimer() {
    }

    public static SingletonTimer getInstance() {
        //避免不必要的同步
        if (instance == null) {
            synchronized (SingletonTimer.class) {
                //在null的情况下创建实例
                if (instance == null) {
                    instance = new SingletonTimer();
                }
            }
        }
        return instance;
    }

    public void startTime(int time) {
        if (lastTime == 0) {
            mCountDownTimer = new CountDownTimer(time * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    lastTime = (int) (millisUntilFinished / 1000);
                    Log.i(TAG, "seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                }
            };
            mCountDownTimer.start();
        }
    }

    public void stopTime() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
            lastTime = 0;
        }
    }

    public int getTime() {
        return lastTime;
    }

    public void setTime(int time) {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        mCountDownTimer = new CountDownTimer(time * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                lastTime = (int) (millisUntilFinished / 1000);
                Log.i(TAG, "seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
        };
        mCountDownTimer.start();
        lastTime = time;
    }
}
