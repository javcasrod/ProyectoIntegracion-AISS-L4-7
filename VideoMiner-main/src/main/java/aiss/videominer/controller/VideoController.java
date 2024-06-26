package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.model.Video;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer/videos")
public class VideoController {

    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    VideoRepository videoRepository;

    // GET http://localhost:8080/videominer/videos
    @GetMapping
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    // GET http://localhost:8080/videominer/videos/{id}
    @GetMapping("/{id}")
    public Video findOne(@PathVariable String id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.get();
    }
}
