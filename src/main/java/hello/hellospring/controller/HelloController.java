package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // static content 방식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Siwon!");
        return "hello"; // viewResolver가 templates/hello.html을 찾음
    }

    // MVC & template engine 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 1 (별로 쓸 일 없음)
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // API 방식 2 (핵심) => JSON으로 반환
    @GetMapping("hello-api")
    @ResponseBody // JSON 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // { name: "Spring" }
    }

    // 클래스 객체 생성
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
