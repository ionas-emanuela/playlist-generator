package com.project.controller;

import com.project.dtos.SongDTO;
import com.project.dtos.UserDTO;
import com.project.entities.Song;
import com.project.entities.User;
import com.project.services.SongService;
import com.project.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final SongService songService;
    private final UserService userService;

    @MessageMapping("save-song")
    public SongDTO saveSong(SongDTO toSave) {
        return new SongDTO(songService.saveSong(toSave.toEntity()));
    }

    @MessageMapping("delete-song-by-id")
    public void deleteSongById(int id) {
        songService.deleteSongById(id);
    }

    @MessageMapping("update-song")
    public SongDTO updateSong(SongDTO toUpdate) {
        return new SongDTO(songService.updateSong(toUpdate.toEntity()));
    }

    @MessageMapping("find-all-users")
    public Flux<UserDTO> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();

        userList.forEach(user -> {
            userDTOList.add(new UserDTO(user));
        });

        return Flux.fromIterable(userDTOList);
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
