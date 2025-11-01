package app.backend.videoRentalSystemBackend.exceptions;

public class VideoNotFoundException extends RuntimeException{
    public String VideoNotFound(){
        return "Video not found";
    }
}
