package pl.ania.notes.logowanie;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping
    int getValue(HttpSession session) {
        if (session.getAttribute("ania") == null) {
            session.setAttribute("ania", 0);
        }
        int count = (int) session.getAttribute("ania");
        count++;
        session.setAttribute("ania", count);
        return count;
    }

    @GetMapping("/cookie")
    int getFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request);
//        if(!cookie.isHttpOnly()) {
//            throw new IllegalStateException("Cookie nie jest httponly");
//        }
        int i = Integer.parseInt(cookie.getValue());
        i++;
            cookie.setValue(String.valueOf(i));
//        cookie.setValue("1000");
//        cookie.setMaxAge(15);
//        cookie.setDomain("allegro.pl");
//        cookie.setPath("/cookie");
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        response.addCookie(cookie);
        return i;
    }

    private Cookie getCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("counter")) {
                return cookie;
            }
        }
        return new Cookie("counter", "0");
    }
}