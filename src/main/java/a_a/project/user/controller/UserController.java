package a_a.project.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"유저"})
public class UserController {

    @ApiOperation(value = "asd")
    @RequestMapping("")
    public void Str() {

    }


}
