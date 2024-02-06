package hello.hellospring.controller;

import org.springframework.ui.Model;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Spring bean 등록: component scan 방식 - 싱글톤 방식 (딱 layer 하나만 등록)
public class MemberController {
    private final MemberService memberService;

    // private final MemberService memberService; = new MemberService();를 해주면 다른 곳에서 사용할 때 계속 새로운 객체를 생성해서 유용x
    // spring bean에 등록되어 있는 service를 생성자에 넣어준다 => DI: controller - service 연결
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.signUp(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
