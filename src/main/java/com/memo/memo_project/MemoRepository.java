package com.memo.memo_project;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//MemoRepository.java
public interface MemoRepository extends JpaRepository<Memo, Long> {
 // 1. 작성자별로 가져오되 + 2. 제목에 검색어가 포함되고 + 3. 날짜 내림차순(최신순) 정렬
 List<Memo> findByAuthorAndTitleContainingOrderByCreatedAtDesc(SiteUser author, String title);
 
 // 검색어가 없을 때를 위한 기본 최신순 정렬
 List<Memo> findByAuthorOrderByCreatedAtDesc(SiteUser author);
}