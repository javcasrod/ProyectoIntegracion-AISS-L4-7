package aiss.videominer.controller;

import aiss.videominer.exceptions.CaptionNotFoundException;
import aiss.videominer.exceptions.ChannelNotFoundException;
import aiss.videominer.exceptions.CommentNotFoundException;
import aiss.videominer.exceptions.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.CommentRepository;
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
@RequestMapping("/videominer/comments")
public class CommentController {


    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CommentRepository commentRepository;

    // GET http://localhost:8080/videominer/comments
    @GetMapping
    public List<Comment> findAll() { return commentRepository.findAll(); }

    // GET http://localhost:8080/videominer/videos/{videoId}/comments
    @GetMapping("/{channelId}/videos/{videoId}/comments")
    public List<Comment> getAllCommentByVideoId(@PathVariable String videoId, @PathVariable String channelId)
            throws VideoNotFoundException, ChannelNotFoundException {
        List<Comment> res= new ArrayList<>();
        Optional<Channel> channel= channelRepository.findById(String.valueOf(channelId));
        if(channel.isPresent()) {
            Optional<Video> video = videoRepository.findById(String.valueOf(videoId));

            if (video.isPresent()) {
                res = video.get().getComments();
            } else {
                throw new VideoNotFoundException();
            }

        }else {
            throw new ChannelNotFoundException();
        }
        return res;
    }

    // GET http://localhost:8080/videominer/comments/{id}
    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return optionalComment.get();
    }
}
