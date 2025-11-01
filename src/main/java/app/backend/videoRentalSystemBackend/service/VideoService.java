package app.backend.videoRentalSystemBackend.service;

import org.springframework.stereotype.Service;
import app.backend.videoRentalSystemBackend.dto.VideoDTO;
import app.backend.videoRentalSystemBackend.exceptions.VideoNotFoundException;
import app.backend.videoRentalSystemBackend.model.Video;
import app.backend.videoRentalSystemBackend.repository.VideoRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {
    // ALl our business logic goes here, clean and readable.
    private final VideoRepo videoRepo;

    public VideoService(VideoRepo videoRepo){
        this.videoRepo = videoRepo;
    }

    // Get ALL Videos
    public List<VideoDTO> allVideos(){
        List<Video> videoList = videoRepo.findAll();
        return videoList.stream()
                .map(this::convertToDTO).collect(Collectors.toList()); // Or just use .map((video)->convertToDTO(video).collect(Collectors.toList());
    }

    // Add a video
    public VideoDTO addVideo(VideoDTO videoDTO){
        Video video = convertToVideo(videoDTO);
        videoRepo.save(video);
        return convertToDTO(video);
    }


    // Get a Video by its Title
    public VideoDTO getVideoByTitle(String title){
           Video video = videoRepo.findByTitleIgnoreCase(title)
                   .orElseThrow(VideoNotFoundException::new);
           return convertToDTO(video);
    }

    // Getting the Video by ID (It's primary key)
    public VideoDTO getVideoByID(Long id){
        Video video = videoRepo.findById(id)
                .orElseThrow(VideoNotFoundException::new);
        return convertToDTO(video);
    }


    public VideoDTO updateRating(float rating, Long id){
        Video video = videoRepo.findById(id) // Storing the video we got so that we can perform the update operation using the setter.
                .orElseThrow(VideoNotFoundException::new); // Exception Handling.
        video.updateRating(rating); // Updating the Video's rating.
        videoRepo.save(video);// Saving the updated rating in the memory.
        return convertToDTO(video);
    }

    public VideoDTO checked(Long id, boolean currentState){
        Video video = videoRepo.findById(id) // Finding video by Title
                .orElseThrow(VideoNotFoundException::new);
        video.toggleChecked(currentState); // Flipping the check In/Out as method is called.
        videoRepo.save(video);
        return convertToDTO(video);
    }

    // Deletion of a Video
    public void deleteVideo(Long id){
        if(!videoRepo.existsById(id)){
            throw new VideoNotFoundException();
        }

        videoRepo.deleteById(id);
    }

    // Helper Methods for DTO conversion (for saving purpose)
    private VideoDTO convertToDTO(Video video){
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setRating(video.getRating());
        dto.setTitle(video.getTitle());
        dto.setChecked(video.isChecked());
        return dto;
    }

    private Video convertToVideo(VideoDTO dto){
        Video video = new Video();
        video.setTitle(dto.getTitle());
        video.setChecked(dto.isChecked()); // Lombok created this "getChecked" method as isChecked().
        video.setRating(dto.getRating());
        return video;
    }






}
