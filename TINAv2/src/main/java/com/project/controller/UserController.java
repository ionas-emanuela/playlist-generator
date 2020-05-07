package com.project.controller;

import com.project.dtos.SongDTO;
import com.project.entities.*;
import com.project.services.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserController {

    private final SongService songService;
    private final GenreService genreService;
    private final PlaylistService playlistService;
    private final ArtistService artistService;
    private final UserService userService;

    private String currentPlaylistString;

    @GetMapping("/songs")
    public List<SongDTO> findAllSongs() {
        List<Song> songList = songService.findAllSongs();
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> {
            songDTOList.add(new SongDTO(song));
        });

        return songDTOList;
    }

    @GetMapping("/songs/playlist/{id}")
    public List<SongDTO> findAllSongsByPlaylistId(@PathVariable int id) {
        List<Song> songList = songService.findAllSongsByPlaylistId(id);
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> {
            songDTOList.add(new SongDTO(song));
        });

        return songDTOList;
    }

    /*public void addSongToPlaylist(ActionEvent actionEvent) {

        Song song = songsTableView.getSelectionModel().getSelectedItem();
        Playlist playlist = playlistComboBox.getSelectionModel().getSelectedItem();

        if(playlist.getSongSet().contains(song)) {
            errorMessage.setText("song is already in playlist");
            return;
        }

        errorMessage.setText("");

        playlist.getSongSet().add(song);
        song.getPlaylistSet().add(playlist);

        playlistService.update(playlist);
        songService.update(song);
        refreshPlaylistComboBox();

    }

    public void removeSongFromPlaylist(ActionEvent actionEvent) {

        if(currentPlaylist.getText().equals("PLAYING FROM LIBRARY")) {
            errorMessage.setText("please select a playlist first");
            return;
        }

        errorMessage.setText("");

        Song song = songsTableView.getSelectionModel().getSelectedItem();
        Playlist playlist = playlistService.findByName(currentPlaylistString);

        Set<Song> songs = playlist.getSongSet();
        Iterator<Song> it = songs.iterator();

        while(it.hasNext()) {
            Song songIt = it.next();
            if(songIt.getName().equals(song.getName())) {
                it.remove();
                break;
            }
        }

        Set<Playlist> playlists = song.getPlaylistSet();
        Iterator<Playlist> it2 = playlists.iterator();

        while(it2.hasNext()) {
            Playlist playlistIt = it2.next();
            if(playlistIt.getName().equals(playlist.getName())) {
                it2.remove();
                break;
            }
        }

        songService.update(song);
        playlistService.update(playlist);

    }

    public void playSong(ActionEvent actionEvent) {

        Song song = songsTableView.getSelectionModel().getSelectedItem();
        song.setViewCount(song.getViewCount() + 1);

        currentlyPlaying.setText("CURRENTLY PLAYING " + song.getName() + " - " + song.getArtist().getName());

        songService.update(song);
        refreshViewCountComboBox();
    }

    public void createPlaylist(ActionEvent actionEvent) {

        String name = newPlaylistName.getText();

        Playlist playlist = playlistService.findByName(name);

        if(!(null == playlist)) {
            errorMessage.setText("playlist " + name + " already exists");
            return;
        }

        errorMessage.setText("");

        User user = userService.findByUsername(usernameLabel.getText());

        playlistService.save(name, user);

        refreshPlaylistComboBox();
    }

    public void searchByArtist(ActionEvent actionEvent) {

        Artist searchedArtist = artistService.findByName(searchByArtist.getText());

        if(null == searchedArtist) {
            errorMessage.setText("cannot find the artist named " + searchByArtist.getText());
            return;
        }

        errorMessage.setText("");

        List<Song> songs = songService.findByArtist(searchedArtist.getName());
        ObservableList<Song> songObservableList = FXCollections.observableArrayList();

        for(Song song: songs) {
            songObservableList.add(song);
        }

        refreshSongs(songObservableList);

    }

    public void searchByGenre(ActionEvent actionEvent) {

        List<Song> songs = songService.findByGenre(genreComboBox.getSelectionModel().getSelectedItem().getName());
        ObservableList<Song> songObservableList = FXCollections.observableArrayList();

        for(Song song: songs) {
            songObservableList.add(song);
        }

        refreshSongs(songObservableList);
    }

    public void searchByViewCount(ActionEvent actionEvent) {

        List<Song> songs = songService.findByViewCount(viewCountComboBox.getSelectionModel().getSelectedItem());
        ObservableList<Song> songObservableList = FXCollections.observableArrayList();

        for(Song song: songs) {
            songObservableList.add(song);
        }

        refreshSongs(songObservableList);

    }

    public void searchByPlaylist(ActionEvent actionEvent) {
        try {

            List<Song> songs = songService.findByPlaylist(playlistComboBoxSearch.getSelectionModel().getSelectedItem());

            ObservableList<Song> songObservableList = FXCollections.observableArrayList();

            for (Song song : songs) {
                songObservableList.add(song);
            }

            currentPlaylistString = playlistComboBoxSearch.getSelectionModel().getSelectedItem().getName();

            currentPlaylist.setText("PLAYING FROM " + currentPlaylistString);
            refreshSongs(songObservableList);
            refreshPlaylistComboBox();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void refreshPlaylistComboBox() {

        List<Playlist> playlists = playlistService.findByUsername(usernameLabel.getText());
        ObservableList<Playlist> playlistObservableList = FXCollections.observableArrayList();

        for(Playlist playlist: playlists) {
            playlistObservableList.add(playlist);
        }

        playlistComboBox.setItems(playlistObservableList);
        playlistComboBoxSearch.setItems(playlistObservableList);
    }

    private void refreshViewCountComboBox() {

        List<Song> songs = songService.findAllSongs();
        ObservableList<Integer> viewCounts = FXCollections.observableArrayList();

        for(Song song: songs) {
            if(!viewCounts.contains(song.getViewCount())) {
                viewCounts.add(song.getViewCount());
            }
        }

        viewCountComboBox.setItems(viewCounts);
    }

    private void refreshGenreComboBox() {

        List<Genre> genres = genreService.findAll();
        ObservableList<Genre> genreObservableList = FXCollections.observableArrayList();

        for(Genre genre: genres) {
            genreObservableList.add(genre);
        }

        genreComboBox.setItems(genreObservableList);
    }

    public void showAllSongs() {

        List<Song> songs = songService.findAllSongs();
        ObservableList<Song> songObservableList = FXCollections.observableArrayList();

        for(Song song: songs) {
            songObservableList.add(song);
        }

        currentPlaylistString = "";
        currentPlaylist.setText("PLAYING FROM LIBRARY");
        refreshSongs(songObservableList);
    }

    private void refreshSongs(ObservableList<Song> songObservableList) {
        songId.setCellValueFactory(new PropertyValueFactory<Song, Integer>("id"));
        songName.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
        artistName.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        genre.setCellValueFactory(new PropertyValueFactory<Song, String>("genre"));
        viewCount.setCellValueFactory(new PropertyValueFactory<Song, Integer>("viewCount"));
        songsTableView.setItems(songObservableList);
    }

*/
}

