package org.videoRentalSystem.service;

import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.processing.Suppress;
import org.springframework.stereotype.Service;
import org.videoRentalSystem.exceptions.VideoNotFoundException;
import org.videoRentalSystem.model.Video;
import org.videoRentalSystem.repository.VideoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    // ALl our business logic goes here, clean and readable.
    private final VideoRepo videoRepo;

    public VideoService(VideoRepo videoRepo){
        this.videoRepo = videoRepo;
    }

    // Get ALL Videos
    public List<Video> allVideos(){
        return videoRepo.findAll(); // Returns in JSON format as it's not plain String or Integer.

    }

    // Add a video
    public void addVideo(Video video){
        videoRepo.save(video);
    }


    // Get a Video by its Title
    public Video getVideoByTitle(String title){
        return videoRepo.findByTitleIgnoreCase(title)
                .orElseThrow(VideoNotFoundException::new); // Or just use lambda ()-> new VideoNotFoundException().
    }

    // Getting the Video by ID (It's primary key)
    public Optional<Video> getVideoByID(Long id){
        return videoRepo.findById(id);
    }


    public Video updateRating(float rating, Long id){
        Video video = videoRepo.findById(id) // Storing the video we got so that we can perform the update operation using the setter.
                .orElseThrow(VideoNotFoundException::new); // Exception Handling.
        video.updateRating(rating); // Updating the Video's rating.
        return videoRepo.save(video); // Saving the updated rating in the memory.
    }

    public Video checked(String title, boolean currentState){
        Video video = videoRepo.findByTitleIgnoreCase(title) // Finding video by Title
                .orElseThrow(VideoNotFoundException::new);
        video.toggleChecked(currentState); // Flipping the check In/Out as method is called.
        return videoRepo.save(video);
    }

    // Deletion of a Video
    public void deleteVideo(Long id){
        if(videoRepo.existsById(id)){
            throw new VideoNotFoundException();
        }

        videoRepo.deleteById(id);
    }





}
