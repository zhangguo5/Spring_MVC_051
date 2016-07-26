package com.zhangguo.springmvc51.controllers;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/up")
public class UpFileController {
	
	@RequestMapping("/file")
	public String file(Model model){
		return "up/upfile";
	}
	
	@RequestMapping(value="/fileSave",method=RequestMethod.POST)
	public String fileSave(Model model,MultipartFile[] files,HttpServletRequest request) throws Exception{
		
		//文件存放的位置
		String path=request.getServletContext().getRealPath("/files");
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getSize());
			System.out.println("--------------------------");
			File tempFile=new File(path, file.getOriginalFilename());
			file.transferTo(tempFile);
		}
		
		System.out.println(path);
		return "up/upfile";
	}
	
	
	@RequestMapping("/file3")
	public String file3(Model model){
		return "up/upfile3";
	}
	
	@RequestMapping(value="/file3Save",method=RequestMethod.POST)
	public String file3Save(Model model,MultipartFile[] files,HttpServletRequest request) throws Exception{
		
		//文件存放的位置
		String path=request.getSession().getServletContext().getRealPath("/files");
		System.out.println(path);
		String msg="";
		for (MultipartFile file : files) {
		    //保存文件
			File tempFile=new File(path, file.getOriginalFilename());
			file.transferTo(tempFile);
			msg+="<img src='../files/"+file.getOriginalFilename()+"' width='200' />";
		}
		model.addAttribute("message", msg);
		return "up/upfile3";
	}

	
}
