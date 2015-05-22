package pe.seti222.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model, @RequestParam(value="error", required=false) String error) {
    	if(error != null) {
    		model.addAttribute("error",error);
    		LOGGER.debug("Getting login page, error={}", error);
    	}
        
        return "login";// ModelAndView("login", "error", error);
    }

}
