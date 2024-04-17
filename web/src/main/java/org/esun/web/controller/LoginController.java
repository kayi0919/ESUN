package org.esun.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.esun.database.entity.User;
import org.esun.database.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@Log4j2
public class LoginController {
    @Autowired
    LocaleResolver localeResolver;

    private final RegisterRepository registerRepository;

    public LoginController(RegisterRepository registerRepository){
        this.registerRepository = registerRepository;
    }


    @GetMapping("/login")
    public String login() throws JsonProcessingException {
        return "login";
    }

    @GetMapping("/register")
    public String register() throws JsonProcessingException{
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(String username,
                           String realname,
                           String password,
                           String email,
                           String action,
                           Model model) {
        if (username == null || username.isEmpty() || realname == null || realname.isEmpty() ||
                password == null || password.isEmpty() || email == null || email.isEmpty()) {
            model.addAttribute("error", "請輸入資料");
            return "/register";
        }
        User user = new User();
        user.setUsername(username);
        user.setRealname(realname);
        user.setPassword(password);
        user.setEmail(email);
        user.setEnabled("1");
        registerRepository.save(user);
        return "redirect:/login";
    }
}
