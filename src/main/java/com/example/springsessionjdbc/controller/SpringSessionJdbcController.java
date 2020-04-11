package com.example.springsessionjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpringSessionJdbcController {

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String index(Model model) {
        List<String> favoriteColors = getFavColors();
        model.addAttribute("favoriteColors", favoriteColors);
        model.addAttribute("sessionId", session.getId());
        return "index";
    }

    @GetMapping("/saveColor")
    public String saveMessage(@RequestParam("color") String color) {
        List<String> favoriteColors = getFavColors();
        if (!StringUtils.isEmpty(color)) {
            favoriteColors.add(color);
            session.setAttribute("favoriteColors", favoriteColors);
        }
        return "redirect:/";
    }

    private List<String> getFavColors() {
        List<String> favoriteColors = (List<String>) session.getAttribute("favoriteColors");
        if (favoriteColors == null) {
            favoriteColors = new ArrayList<>();
        }
        return favoriteColors;
    }
}
