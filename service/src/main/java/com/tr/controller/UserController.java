package com.tr.controller;

import com.tr.pojo.LoginVo;
import com.tr.pojo.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping ("/login")
    public Result login(@RequestBody LoginVo loginVo, HttpSession session){
        if("laohei".equals(loginVo.getUsername())||"laoge".equals(loginVo.getUsername())){
            session.setAttribute("user",loginVo.getUsername());
            Result success = Result.ok("success");
            success.setData(loginVo);
            return success;
        }
        return Result.error("error");
    }
}
