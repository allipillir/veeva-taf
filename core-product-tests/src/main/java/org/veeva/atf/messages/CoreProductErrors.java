package org.veeva.atf.messages;

public class CoreProductErrors  extends AssertionError {

    public static final String TOTAL_VIDEO_COUNT_NEW_FEATURE = "Verify total video feed count in New & Feature page ";

    public static final String TOTAL_VIDEO_FEED_PRESENT = "Verify total video feed exist more 2 days  in New & Feature page ";


    public CoreProductErrors(String message, Throwable error){
        super(message,error);
    }
}
