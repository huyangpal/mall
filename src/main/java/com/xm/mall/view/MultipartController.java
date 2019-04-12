package com.xm.mall.view;

import com.xm.mall.bean.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 多视图解析
 *
 * @author huyan
 * @date 2019-04-10 15:21
 */
@Controller
public class MultipartController {

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String view(Model model){
        user user = new user();
        user.setName("蔡徐坤");
        user.setSex("女");
        user.setAge(18);
        user.setTelephone("120");
        user.setAddress("天上人间");

        model.addAttribute("user",user);
        return "/view";
    }

}
