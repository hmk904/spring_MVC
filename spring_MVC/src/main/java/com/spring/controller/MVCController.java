package com.spring.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.command.SearchCriteria;

@Controller
public class MVCController {
	
	@GetMapping("/login")
	public void loginForm() {}
	
	@PostMapping("/login")
	public String loginPost(String id, String pwd) {
		String url = "redirect:/member/list";
		
		System.out.println("id : " +id);
		System.out.println("pwd : " +pwd);
		
		return url;
	}
	
	@GetMapping("/member/list")
	public void memberList(SearchCriteria cri) throws Exception {
		
		System.out.println("page : "+cri.getPage());
		System.out.println("perPageNum : "+cri.getPerPageNum());
		System.out.println("SearchType : "+cri.getSearchType());
		System.out.println("keyword : "+cri.getKeyword());
		
	}
	@GetMapping("/fileUploadForm")
	public void fileUploadForm() {}
	
	@PostMapping(value="multipartFile",produces = "text/plain;charset=utf-8")
	public String uploadByMultipartFile(String title, MultipartFile file, HttpServletRequest request) throws Exception {
		String url = "fileUpload";
		
		/* 파일저장폴더생성*/
		String uploadPath = "c:\\upload\\file";
		
		/* 파일명 중복방지*/
		String fileName = file.getOriginalFilename();
		
		/* 파일 경로확인 및 생성*/
		File dest = new File(uploadPath, fileName);
		dest.mkdirs();
		
		/* 파일 저장*/
		file.transferTo(dest);
		
		request.setAttribute("title", title);
		request.setAttribute("originalFilename", file.getOriginalFilename());
		request.setAttribute("uploadedFilename", dest.getName());
		request.setAttribute("uploadPath", dest.getAbsoluteFile());
		return url;
	}
}
