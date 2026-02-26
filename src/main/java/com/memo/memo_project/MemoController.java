package com.memo.memo_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;


@Controller
public class MemoController {
	
	@Value("${file.upload-dir:/memo_files}")
	private String uploadDir;
	
	@Autowired
	private UserService userService;

    @Autowired
    private MemoRepository memoRepository;

    
    // 조회
    @GetMapping("/")
    public String index(org.springframework.ui.Model model, 
                        java.security.Principal principal,
                        @RequestParam(value = "keyword", required = false) String keyword) {
        
        if (principal != null) {
            SiteUser siteUser = userService.getUser(principal.getName());
            List<Memo> memoList;
            
            if (keyword != null && !keyword.isEmpty()) {
                // 검색어가 있으면 검색 결과 최신순
                memoList = memoRepository.findByAuthorAndTitleContainingOrderByCreatedAtDesc(siteUser, keyword);
            } else {
                // 검색어 없으면 그냥 내 글 전체 최신순
                memoList = memoRepository.findByAuthorOrderByCreatedAtDesc(siteUser);
            }
            
            model.addAttribute("memos", memoList);
        } else {
            model.addAttribute("memos", new java.util.ArrayList<Memo>());
        }
        return "index";
    }

    // 저장
    @PostMapping("/save")
    public String saveMemo(@RequestParam("title") String title, 
                           @RequestParam("content") String content,
                           @RequestParam("file") org.springframework.web.multipart.MultipartFile file, // ✅ 파일 받기
                           java.security.Principal principal) throws java.io.IOException {
        
        Memo memo = new Memo();
        memo.setTitle(title);
        memo.setContent(content);
        
        
        if (!file.isEmpty()) {
            // 저장 경로를 서버 내부가 아닌 외부 폴더(C:/memo_files/)로 설정
            String projectPath = uploadDir;

            // 폴더가 없는 경우를 대비해 자동으로 생성하는 로직을 추가하여 더욱 안전하게
            java.io.File dir = new java.io.File(projectPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            java.util.UUID uuid = java.util.UUID.randomUUID(); 
            String fileName = uuid + "_" + file.getOriginalFilename();
            
            // 설정한 외부 경로에 파일을 저장
            java.io.File saveFile = new java.io.File(projectPath, fileName);
            file.transferTo(saveFile); 
            
            memo.setFileName(fileName); 
        }

        SiteUser siteUser = userService.getUser(principal.getName());
        memo.setAuthor(siteUser);
        
        memoRepository.save(memo);
        return "redirect:/";
    }
    
    // 삭제
    @GetMapping("/delete/{id}")
    public String deleteMemo(@PathVariable("id") Long id, java.security.Principal principal) {
        Memo memo = memoRepository.findById(id).orElseThrow();
        
        // 로그인한 사용자와 작성자가 같은지 확인
        if (!memo.getAuthor().getUsername().equals(principal.getName())) {
            return "redirect:/"; // 다르면 삭제 안 하고 메인으로 튕겨냄
        }
        
        memoRepository.deleteById(id);
        return "redirect:/";
    }
    
    // 수정
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, org.springframework.ui.Model model, java.security.Principal principal) {
        Memo memo = memoRepository.findById(id).orElseThrow();
        
        // 작성자가 아니면 수정 페이지 못 들어가게 막기
        if (!memo.getAuthor().getUsername().equals(principal.getName())) {
            return "redirect:/";
        }
        
        model.addAttribute("memo", memo);
        return "edit";
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