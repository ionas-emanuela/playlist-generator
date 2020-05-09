package com.project.services;

import com.project.entities.Playlist;
import com.project.entities.Song;
import com.project.entities.User;
import com.project.services.repositories.UserRepository;
import com.project.services.utils.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SongService songService;

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(String username, String password) {
        return userRepository.save(User.builder().username(username).password(password).build());
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User loginUser(String username, String password) {

        User user = userRepository.findByUsername(username);

        if(null != user) {
            if(user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public String getReport(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("User: " + user.getUsername());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(System.lineSeparator());

        List<Playlist> playlists = user.getPlaylistList();

        stringBuilder.append(playlists.size() + " playlists:");
        stringBuilder.append(System.lineSeparator());

        for(Playlist playlist: playlists) {

            List<Song> songs = songService.findByPlaylist(playlist);

            stringBuilder.append(playlist.getName() + ", " + songs.size() + " songs");
            stringBuilder.append(System.lineSeparator());

            for(int i = 0; i < songs.size(); i ++) {
                stringBuilder.append("     " + (i+1) + ". " + songs.get(i).toString());
                stringBuilder.append(System.lineSeparator());
            }

        }

        return stringBuilder.toString();
    }

    public User saveUser(User toSave) {
        return userRepository.save(toSave);
    }

    public User updateUser(User toUpdate) {

        User oldUser = userRepository.findById(toUpdate.getId()).orElse(null);

        if(null == oldUser) {
            throw new EntityNotFoundException(toUpdate.getId(), "user");
        }
        if(null == toUpdate.getUsername()) {
            toUpdate.setUsername(oldUser.getUsername());
        }
        if(null == toUpdate.getPassword()) {
            toUpdate.setPassword(oldUser.getPassword());
        }

        return userRepository.save(toUpdate);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public User addFriend(int userId, int friendId) {

        User user = userRepository.findById(userId).orElse(null);

        if(null == user) {
            throw new EntityNotFoundException(userId, "user");
        }

        User friend = userRepository.findById(friendId).orElse(null);

        if(null == friend) {
            throw new EntityNotFoundException(friendId, "user");
        }

        user.getFriendOf().add(friend);
        friend.getFriends().add(user);

        return userRepository.save(user);

    }

}
