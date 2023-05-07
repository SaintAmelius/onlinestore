package onlinestore.repository;

import onlinestore.Channel;

import java.util.List;

public interface ChannelRepository {
    void create(Channel channel);

    void follow(Channel channel, Long userId);

    List<Channel> findALl();

    List<Channel> findOwnChannelsByUserId(Long userId);

    List<Channel> findFollowedChannelsByUserId(Long userId);

    Channel findById(Long id);
}
