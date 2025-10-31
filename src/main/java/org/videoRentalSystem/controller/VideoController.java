package org.videoRentalSystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.videoRentalSystem.exceptions.VideoNotFoundException;
import org.videoRentalSystem.model.Video;
import org.videoRentalSystem.service.VideoService;

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






}
