package controller;


import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.CriticApplication;
import entity.JwtUser;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    @Qualifier("AdminServiceImpl")
    UserService userService;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JwtUser> adminLogin(@RequestBody final User user) {
        return null;
    }

    @RequestMapping(value = "/logout", method = POST)
    public ResponseEntity<Boolean> adminLogout() {
        return null;
    }

    @RequestMapping(value = "/suspend/{id}", method = POST)
    public ResponseEntity<Boolean> suspendUser(@PathVariable Integer id) {
        return null;
    }

    @RequestMapping(value = "/all_user", method = GET)
    public ResponseEntity<List> getUsers() {
        return null;
    }

    @RequestMapping(value = "/verify_critic", method = POST)
    public ResponseEntity<Boolean> verifyCritic(@RequestBody CriticApplication application) {
        return null;
    }

}
