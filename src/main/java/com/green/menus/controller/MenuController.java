package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

@Controller
public class MenuController {
	
	// @Autowired : spring container 에 미리 new된 Componnent 를 찾아서 menuMapper 변수에 저장 
	// @Autowired : 객체 타입으로 찾아서 연결 
	// @Qulified  : 객체 이름으로 찾아서 연결 
	@Autowired
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:9090/Menus/List
	@RequestMapping("/Menus/List")   
	public   String   list( Model model ) {
		// 조회한결과를 ArrayList 로 돌려준다
		List<MenuDTO> menuList = menuMapper.getMenuList(); // ArrayList 결과를 받음
		System.out.println(menuList);
		
		model.addAttribute("msg", "하하");
		model.addAttribute("menuList", menuList);
		
		return "menus/list";
	}
	
	// /Menus/WriteForm
	@RequestMapping("/Menus/WriteForm")
	public String writeForm() {
		return "menus/write";  // write.jsp 로 이동
	}

	// /Menus/Write
	// ?menu_id=MENU09 &menu_name=git &menu_seq=4
	@RequestMapping("/Menus/Write")
	public String write(MenuDTO menuDTO, Model model) {
		
		// 넘어온 값
		System.out.println(menuDTO);
				
		// db에 저장
		menuMapper.insertMenu(menuDTO);
		
		return "redirect:/Menus/List";
		
		// 다시 조회 -> menulist
		/*
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		model.addAttribute("menuList", menuList);
		
		return "menus/list";
		*/
	}
	
	/*
	@RequestMapping("/Menus/Delete")
	public String delete(String menu_id) {
		
		System.out.println(menu_id);
		
		MenuDTO menuDTO = new MenuDTO(menu_id, null, 0);
		
		// DB에서 삭제
		menuMapper.deleteMenu(menuDTO); // mybatis Mapper에는 DTO 전달
		
		return "redirect:/Menus/List";
	}
	*/
	
	@RequestMapping("/Menus/Delete")
	public String delete(MenuDTO menuDTO) {
		
		System.out.println(menuDTO);
		
		// DB에서 삭제
		menuMapper.deleteMenu(menuDTO); // mybatis Mapper에는 DTO 전달
		
		return "redirect:/Menus/List";
	}
	
	@RequestMapping("/Menus/UpdateForm")
	public String updateform(MenuDTO menuDTO, Model model) {
		
		System.out.println(menuDTO);
		
		// DB에서 수정할 정보 담긴 menu
		MenuDTO  menu = menuMapper.getMenu(menuDTO);
		model.addAttribute("menu", menu);
		System.out.println(menu);
		
		
		return "menus/update";
	}
	
	// /Menus/Update
	@RequestMapping("/Menus/Update")
	public String update(MenuDTO menuDTO) {
		
		// 넘어온 정보로 DB 수정
		menuMapper.updateMenu(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	// 메뉴이름으로만 추가하기
	@RequestMapping ("/Menus/WriteForm2")
	public String writeform2() {
		return "menus/write2";
	}
	
//	/Menus/Write<ㅁ;2
	@RequestMapping ("/Menus/Write2")
	public String write2(MenuDTO menuDTO) {
		
		menuMapper.insertMenu2(menuDTO);
		
		return "redirect:/Menus/List";
	}
	
	
} //













