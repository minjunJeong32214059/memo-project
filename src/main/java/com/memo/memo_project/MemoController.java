package com.memo.memo_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoController {

    @Autowired
    private MemoRepository memoRepository;

    @GetMapping("/")
    public String index(org.springframework.ui.Model model) {
        // DB에서 모든 메모를 가져와 "memos"라는 이름으로 HTML에 전달
        model.addAttribute("memos", memoRepository.findAll()); 
        return "index";
    }

    // 저장
    @PostMapping("/save")
    public String saveMemo(@RequestParam("title") String title, @RequestParam("content") String content) {
        Memo memo = new Memo();
        memo.setTitle(title);
        memo.setContent(content);
        
        memoRepository.save(memo); // DB에 저장
        
        return "redirect:/"; // 저장이 끝나면 다시 첫 화면으로
    }
    
    // 삭제
    @GetMapping("/delete/{id}")
    public String deleteMemo(@PathVariable("id") Long id) {
        memoRepository.deleteById(id); // ID로 메모를 찾아 삭제
        return "redirect:/"; // 삭제 후 다시 메인 화면으로
    }
    
    // 수정
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, org.springframework.ui.Model model) {
        Memo memo = memoRepository.findById(id).orElseThrow(); // ID로 메모를 찾기
        model.addAttribute("memo", memo); // 찾은 메모를 "memo"라는 이름으로 화면에 전달
        return "edit"; // edit.html 파일을 보여줌
    }

    // 수정 내용을 DB에 저장
    @PostMapping("/update")
    public String updateMemo(@RequestParam("id") Long id, 
                             @RequestParam("title") String title, 
                             @RequestParam("content") String content) {
        Memo memo = memoRepository.findById(id).orElseThrow();
        memo.setTitle(title); // 새로운 제목으로 변경
        memo.setContent(content); // 새로운 내용으로 변경
        
        memoRepository.save(memo); // 이미 있는 ID라면 JPA가 덮어쓰기(Update)를 해줌
        return "redirect:/";
    }
    
    // login 주소로 오면 HTML을 보여줌
    @GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }
    
    
    
}