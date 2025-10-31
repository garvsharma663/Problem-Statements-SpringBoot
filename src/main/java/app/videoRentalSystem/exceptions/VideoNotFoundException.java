package app.videoRentalSystem.exceptions;

public class VideoNotFoundException extends RuntimeException{
    public String VideoNotFound(){
        return "Video not found";
    }
}
