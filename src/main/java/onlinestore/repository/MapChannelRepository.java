package onlinestore.repository;

import onlinestore.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapChannelRepository implements ChannelRepository {
    Map<Long, Channel> channelMap = new HashMap<>();
    Long idGenerator = 0L;

    public Long generateID() {
        return idGenerator++;
    }

    @Override
    public void create(Channel channel) {
        Long id = generateID();
        channel.setId(id);
        channelMap.put(id, channel);
    }

    @Override
    public void follow(Channel channel, Long userId) {
        channel.getFollowers().add(userId);
    }

    @Override
    public List<Channel> findALl() {

        return new ArrayList<>(channelMap.values());
    }

    @Override
    public List<Channel> findOwnChannelsByUserId(Long userId) {
        return channelMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getUserId().equals(userId))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<Channel> findFollowedChannelsByUserId(Long userId) {
        return channelMap.values()
                .stream()
                .filter(channel -> channel.getFollowers().contains(userId))
                .collect(Collectors.toList());

    }

    @Override
    public Channel findById(Long id) {
        return channelMap.get(id);
    }
}

