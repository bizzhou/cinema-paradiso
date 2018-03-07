package controller;

import entity.JwtUser;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("/user")
@RestController
public class RegUserController {

    @Autowired
    @Qualifier("RegUserImpl")
    UserService userService;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JwtUser> userLogin(@RequestBody final User user) {
        return null;
    }

    @RequestMapping(value = "/logout", method = POST)
    public ResponseEntity<Boolean> userLogout() {
        return null;
    }

    @RequestMapping(value = "change_password", method = POST)
    public ResponseEntity<Boolean> changePassword(@RequestParam(value = "old_password", required = true) String oldPass,
                                                  @RequestParam(value = "new_password", required = true) String newPass) {
        return null;
    }

    // TODO: Sending image to the backend....
    @RequestMapping(value = "/change_proile_picture", method = POST)
    public ResponseEntity<List> changeProfilePicture() {
        return null;
    }

    @RequestMapping(value = "/forgot_password", method = POST)
    public ResponseEntity<User> verifyCritic(@RequestParam(value = "email", required = true) String email) {
        return null;
    }


    @RequestMapping(value = "/update_profile", method = POST)
    public ResponseEntity<User> updateProfile(@RequestBody final User user) {
        return null;
    }

    @RequestMapping(value = "/mark_private", method = POST)
    public ResponseEntity<Boolean> makeSummaryPrivate() {
        return null;
    }

}
