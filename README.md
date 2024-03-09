![1709979815106](https://github.com/ShivaPradhyumna/Cibilscoreindicator/assets/93043998/cb491e41-5e94-451e-ad2d-987231b578b9)
![cibil score indicator gif](https://github.com/ShivaPradhyumna/Cibilscoreindicator/assets/93043998/c25bd5b5-e62d-4f0e-8997-ce0943140c5d)
```
score = findViewById(R.id.score);
        smoothArcProgressBar = findViewById(R.id.arcProgressBar);
        smoothArcProgressBar.setSmoothProgress(0.68f, 1000, new SmoothArcProgressBar.OnProgressUpdateListener() {
            @Override
            public void onProgressUpdate(float progress) {
                int prg = (int) (progress * 100);
                score.setText(String.valueOf(prg));
            }
        });
```
where 1000 milli seconds represents delay in animation. you have to use `OnProgressUpdateListener` since it is included in function body of `setSmoothProgress` or just leave it blank if you don't want to use it.

```
<FrameLayout
                android:id="@+id/layout_cibil_score"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:visibility="visible"
                android:paddingTop="20dp">
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:orientation="horizontal">

                    <com.dont_code.cibil_score_indicator_library.SmoothArcProgressBar
                        android:id="@+id/arcProgressBar"
                        android:layout_width="170dp"
                        android:layout_height="170dp" />
                </LinearLayout>
                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:orientation="vertical">
                    <TextView
                        android:id="@+id/score"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:textColor="@color/black"
                        android:textSize="20sp"
                        android:text="800" />
                    <TextView
                        android:id="@+id/score_category_tv"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center"
                        android:textColor="@color/black"
                        android:textSize="12sp"
                        android:text="Good Score" />
                </LinearLayout>
            </FrameLayout>
```

adjust the width and height of the progress bar accordingly.

Step 1. Add the JitPack repository to your build file if exists already ignore

    repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}

Step 2. Add the dependency:

    implementation 'com.github.ShivaPradhyumna:Cibilscoreindicator:1.0'
