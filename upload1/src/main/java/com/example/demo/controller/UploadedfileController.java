package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Uploadedfile;
import com.example.demo.mapper.UploadedfileMapper;

@Controller
public class UploadedfileController {
	
	@Autowired UploadedfileMapper uploadedfileMapper;
	
	@RequestMapping({"/", "file/list"})
	public String list(Model model) {
		model.addAttribute("files", uploadedfileMapper.findAll());
		return "file/list";
	}
	
	@RequestMapping(value="file/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("fileUpload") MultipartFile[] files) throws IOException {
		for(MultipartFile uploadFile : files) {
			System.out.println("저장??");
			if(uploadFile.getSize() <= 0) continue;
			System.out.println("저장");
			String fileName = Paths.get(uploadFile.getOriginalFilename()).getFileName().toString();
			Uploadedfile uploadedFile = new Uploadedfile();
			uploadedFile.setFileName(fileName);
			uploadedFile.setFileSize((int)uploadFile.getSize());
			uploadedFile.setFileTime(new Date());
			uploadedFile.setData(uploadFile.getBytes());
			uploadedfileMapper.insert(uploadedFile);
		}
		return "redirect:list";
	}
	
	@RequestMapping("file/download")
	public void download(@RequestParam("id") int id, HttpServletResponse response) throws Exception {
		Uploadedfile uploadedFile = uploadedfileMapper.findOne(id);
		if(uploadedFile == null) return;
		String fileName = URLEncoder.encode(uploadedFile.getFileName(), "UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
		try (BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
			output.write(uploadedFile.getData());
		}
	}
	
	@RequestMapping("file/delete")
	public String delete(@RequestParam("id") int id) throws Exception {
		uploadedfileMapper.delete(id);
		return "redirect:list";
	}
}
