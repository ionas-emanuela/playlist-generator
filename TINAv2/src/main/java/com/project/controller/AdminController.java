package com.project.controller;

import com.project.dtos.SongDTO;
import com.project.dtos.UserDTO;
import com.project.entities.*;
import com.project.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final SongService songService;
    private final UserService userService;

    @GetMapping("/songs")
    public List<SongDTO> findAllSongs() {
        List<Song> songList = songService.findAllSongs();
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> {
            songDTOList.add(new SongDTO(song));
        });

        return songDTOList;
    }

    @PostMapping("/songs")
    public SongDTO saveSong(@RequestBody SongDTO toSave) {
        return new SongDTO(songService.saveSong(toSave.toEntity()));
    }

    @DeleteMapping("/songs")
    public void deleteSongById(int id) {
        songService.deleteSongById(id);
    }

    @PutMapping("/songs")
    public SongDTO updateSong(@RequestBody SongDTO toUpdate) {
        return new SongDTO(songService.updateSong(toUpdate.toEntity()));
    }

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();

        userList.forEach(user -> {
            userDTOList.add(new UserDTO(user));
        });

        return userDTOList;
    }

    @PostMapping("/users")
    public UserDTO saveUser(@RequestBody UserDTO toSave) {
        return new UserDTO(userService.saveUser(toSave.toEntity()));
    }

    @DeleteMapping("/users")
    public void deleteUserById(int id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/users")
    public UserDTO updateUser(@RequestBody UserDTO toUpdate) {
        return new UserDTO(userService.updateUser(toUpdate.toEntity()));
    }

    /*public void generateReport(ActionEvent actionEvent) {

        User user = usersTableView.getSelectionModel().getSelectedItem();
        if(null == user) {
            errorMessage.setText("please pick a user");
            return;
        }
        if(reportComboBox.getValue() == null) {
            errorMessage.setText("please choose the report file type");
            return;
        }
        if(reportName.getText().equals("")) {
            errorMessage.setText("please pick a name for the report");
            return;
        }
        errorMessage.setText("");

        ReportFactory reportFactory = new ReportFactory();
        reportFactory.getReport(reportComboBox.getValue()).generateReportFile(userService.getReport(user), reportName.getText());

    }*/
}
