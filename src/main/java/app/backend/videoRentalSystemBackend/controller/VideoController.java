package app.backend.videoRentalSystemBackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.backend.videoRentalSystemBackend.dto.VideoDTO;
import app.backend.videoRentalSystemBackend.service.VideoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/videos") // CLass level mapping, now, every method for http will start with api/videos
public class VideoController
{
    private final VideoService videoService; // Lombok's @RequiredArgsConstructor made the constructor for final field itself.

//    public VideoController(VideoService videoService){
//        this.videoService = videoService;
//    }

    // We'll be using DTO from now on
    @GetMapping
    public ResponseEntity<List<VideoDTO>> getAllVideos(){
        List<VideoDTO> videoDTOList = videoService.allVideos();
        return ResponseEntity.ok(videoDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDTO> getVideoById(@PathVariable Long id){
        VideoDTO videoDTO = videoService.getVideoByID(id);
        return ResponseEntity.ok(videoDTO);
    }

    @PostMapping()
    public ResponseEntity<VideoDTO> addVideo(@Valid @RequestBody VideoDTO videoDTO){
        VideoDTO newVideo = videoService.addVideo(videoDTO);
        return ResponseEntity.ok(newVideo);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<VideoDTO> getVideoByTitle(@PathVariable String title){
        VideoDTO videoDTO = videoService.getVideoByTitle(title);
        return ResponseEntity.ok(videoDTO);
    }

    @PutMapping("/{id}/rating/{rating}")
    public ResponseEntity<VideoDTO> updateVideoRating(@Valid @PathVariable float rating,@Valid @PathVariable Long id){
        VideoDTO videoDTO = videoService.updateRating(rating, id);
        return ResponseEntity.ok(videoDTO);
    }

    @PostMapping("/{id}/toggle/{currentState}")
    public ResponseEntity<VideoDTO> toggleChecked(@PathVariable Long id,@PathVariable boolean currentState){
        VideoDTO videoDTO = videoService.checked(id, currentState);
        return ResponseEntity.ok(videoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id){
        videoService.deleteVideo(id);
        return ResponseEntity.ok("Video deletion SUCCESS !!!");
    }
}
