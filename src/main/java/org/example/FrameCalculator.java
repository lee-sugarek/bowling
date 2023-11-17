package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculates the frame scores
 *
 */
public class FrameCalculator
{
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String GUTTER_BALL = "-";

    /**
     * Application entry point
     *
     * @param args Passes arguments from the command line to the applicaiton.
     */
    public static void main( String[] args )
    {
        FrameCalculator frameCalculator = new FrameCalculator();
        frameCalculator.calculateFrames(args);
    }

    /**
     * Calculates the score for each frame.
     *
     * @param rolls String array of rolls ranging from 0-9, "/" and "X".
     * @return Integer array of frame scores.
     */
    public Integer[] calculateFrames(String[] rolls) {
        int frameCounterIndex = 1;
        Map<Integer, FrameData> incompleteFrames = new HashMap<>();
        List<Integer> completedFrameScores = new ArrayList<>();
        for (String roll: rolls) {
            //Add the score to the existing incomplete frames that need this roll.
            incompleteFrames.values().forEach(frameData -> {
                if (SPARE.equals(roll)) {
                    //Spare following a strike
                    if (frameData.rollsExpected == 3) {
                        frameData.frameScore = 20;
                    } else {
                        frameData.frameScore = 10;
                        frameData.rollsExpected = 3;
                    }
                } else {
                    frameData.frameScore += PinConverter(roll);
                }

                frameData.rollsCounted++;

                //If the frame has acquired all the rolls needed, then add it to the completed frames list.
                if (frameData.rollsCounted == frameData.rollsExpected) {
                    completedFrameScores.add(frameData.frameScore);
                }
            });

            //Add new frame if it does not already exist. Otherwise, move onto the next frame.
            if (incompleteFrames.get(frameCounterIndex) == null) {
                incompleteFrames.put(frameCounterIndex, new FrameData(PinConverter(roll), 1, STRIKE.equals(roll) ? 3 : 2));

                //Move to next frame if a strike was bowled.
                if (STRIKE.equals(roll)) {
                    frameCounterIndex++;
                }
            } else {
                frameCounterIndex++;
            }

            //Remove frames that have been completed.
            incompleteFrames.values().removeIf(frameData -> frameData.rollsExpected == frameData.rollsCounted);
        }

        return completedFrameScores.toArray(new Integer[0]);
    }

    private int PinConverter(String roll) {
        int pins = 0;
        if (STRIKE.equals(roll) || SPARE.equals(roll)) {
            pins = 10;
        } else if (GUTTER_BALL.equals(roll)) {
            pins = 0;
        }
        else {
            pins = Integer.parseInt(roll);
        }

        return pins;
    }
}
