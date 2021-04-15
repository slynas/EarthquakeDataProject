package com.example.earthquakedataproject.util;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.earthquakedataproject.R;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Map;

public class ColourCheck {

    private final Range<Double> greenRange = Range.between(0.0, 3.3);
    private final Range<Double> amberRange = Range.between(3.4, 6.6);
    private final Range<Double> redRange = Range.between(6.7, 10.0);

    private final Map<Range<Double>, Integer> colourMap = new HashMap<Range<Double>, Integer>() {{
        put(greenRange, R.color.green);
        put(amberRange, R.color.amber);
        put(redRange, R.color.red);
    }};

    @SuppressLint("NewApi")
    public int getColourForMagnitude(double magnitude) {
            return colourMap.entrySet().stream()
                    .filter(entry -> entry.getKey().contains(magnitude))
                    .map(Map.Entry::getValue)
                    .findFirst().orElse(R.color.purple_200);
    }
}
