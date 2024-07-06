package service.backend_spring3.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.backend_spring3.domain.User;
import service.backend_spring3.service.LoginService;

@Controller
public class LoginController {

    private LoginService loginService;


    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    @GetMapping("/loginPage")
    public String navigateToPage() {
        // Replace "targetPage" with the name of the HTML file you want to navigate to
        System.out.println("asdasd");
        return "login/loginPopUp";
    }




    @PostMapping("/login/new")
    public String create(UserForm form){
        User user = new User();
        user.setName(form.getName());
        user.setPassword(form.getPassword());

        loginService.join(user);

        return "redirect:/";
    }
//    @Controller
//    public class NavigationController {
//
//        @GetMapping("/navigate")
//        public String navigateToPage() {
//            // Replace "targetPage" with the name of the HTML file you want to navigate to
//            return "targetPage";
//        }
//    }
}
