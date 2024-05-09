package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.Channel;
import aiss.VimeoMiner.model.TextTrack;
import aiss.VimeoMiner.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "17f1fa3527765a7c2f5c6f3c1317aef0";

    public Channel getChannel(String id) {
        Channel res = null;
        String uri = "https://api.vimeo.com/channels/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<Channel> request = new HttpEntity<>(null, headers);

        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.GET, request, Channel.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<Channel> getAllChannels() {
        List<Channel> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/channels";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<Channel> request = new HttpEntity<>(null, headers);

        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.GET, request, Channel.class);

        if(response.getBody() != null){
            res.add(response.getBody());
        }
        return res;
    }




}
