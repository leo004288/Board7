package com.green.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.green.config.WebMvcConfig;
import com.green.user.dto.UserDto;
import com.green.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Users")
public class UserController {

    private final WebMvcConfig webMvcConfig;
	
	@Autowired
	private UserMapper userMapper;

    UserController(WebMvcConfig webMvcConfig) {
        this.webMvcConfig = webMvcConfig;
    }
	
    // /Users/WriteForm
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users/write");
		mv.addObject("msg", "user");
		
		return mv;
	}
	
	// /Users/Write?userid=&passwd=&username=&email=
	@RequestMapping("/Write")
	public ModelAndView write(UserDto userdto) {
		
		//db 넘기기
		userMapper.insertUser(userdto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		
		return mv;
	}
	
	// /Users/List
	@RequestMapping("/List")
	public ModelAndView list( ) {
		
		//db 넘기기
		List<UserDto> userList = userMapper.getUserList();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/users/list");
		mv.addObject("userList", userList);
		
		return mv;
	}
	
	// /Users/Delete?userid=
	@RequestMapping("/Delete")
	public ModelAndView delete(UserDto userdto) {
		
		//db 넘기기
		userMapper.deleteUser(userdto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		
		return mv;
	}
	
	// /Users/Update
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(UserDto userdto) {
		
		//db 조회
		UserDto user = userMapper.getUser(userdto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/users/update");
		mv.addObject("user", user);
		
		return mv;
	}
	
	// /Users/Update
	// Controller 에서 map 으로 인자를 받을 때는 반드시 @RequestParam 
	@RequestMapping("/Update")
	public ModelAndView update( @RequestParam Map<String, Object> map ) {
		System.out.println(map);
		userMapper.updateUser2(map);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return mv;
	}
	
//	@RequestMapping("/Update")
//	public ModelAndView update(UserDto userdto, String oldpwd) {
//		
//		userMapper.updateUser(userdto, oldpwd);
//		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("redirect:/Users/List");
//		
//		return mv;
//	}
	
	// 아이디 중복확인 - 결과문자열을 리턴
	// <b class="green">사용가능한 아이디입니다</b>
	// <b class="red">사용 할 수 없는 아이디입니다</b>
	// /Users/IdDupCheck2?userid=aaa
	@GetMapping("/IdDupCheck2")
	@ResponseBody                 // 리턴되는 글자는 jsp가 아니다
	public UserDto idDupCheck2(UserDto userdto) {
		
		// String  userid = userdto.getUserid();             // 넘어온 userid
		UserDto user   = userMapper.getIdDupCheck2(userdto); // 조회한 userid
		if(user == null)
			user = new UserDto(); 
		return user; 
	}
	
	// 아이디 중복체크 새창만들기
	// /Users/DupCheckWindow?first=true
	@GetMapping("/DupCheckWindow")
	public ModelAndView dupCheckWindow( boolean first, HttpSession session ) {
		ModelAndView mv = new ModelAndView();
		
		// ?frist=true 활용
		if(first)
				mv.addObject("first", first);
		
//		session.setAttribute("first", "true");
		
		mv.setViewName("users/idcheck");
		return mv;
	}
	
	// 새창 중복확인
	// /Users/DupCheck?userid=
	@RequestMapping("/DupCheck")
	public ModelAndView dupCheck(UserDto userdto, HttpSession session) {
//		String userid = userdto.getUserid(); // ${param.userid}
		
//		session.setAttribute("first", "");
		
		UserDto user  = userMapper.getUser(userdto);
		
		String  msg  = "<b class='red'>사용 할 수 없는 아이디</b>";
		if(user == null)
			msg = "<b class='green'>사용사능한 아이디</b>";
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/users/idcheck");
		mv.addObject("msg", msg);
//		mv.addObject("userid", userid); // ${param.userid}
		return mv;
	}
	//-----------------------------------------------------------------------------------
	// 로그인폼
	@RequestMapping("/LoginForm")
	public String loginForm () {
		return "users/login";
	}
	
	// 로그인
	@RequestMapping("/Login")
	public String login (UserDto userdto, HttpServletRequest request) {
		
		UserDto     user    = userMapper.getLogin(userdto);
		
		HttpSession session = request.getSession();
		session.setAttribute("login", user);
		
		String      loc     = "";  
		// 주소가 "/" 일때는 session.getAttribute("loc") -> null 이다
		if(session.getAttribute("loc") == null )
			loc = "redirect:/";
		else
			loc = "redirect:" + session.getAttribute("loc").toString();
		
		return loc;
//		return "redirect:/Board/List?menu_id=MENU01";
	}
	
	// 로그아웃
	@RequestMapping("/Logout")
	public String logout (HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
} //

//@RequestMapping("/List")
//public String list(Model model) {
//	return "/user/list";
//}
//@RequestMapping("/List")
//public String list(Model model) {
//	return "redirect:/Users/List";
//}
//
//@RequestMapping("/List")
//public ModelAndView list() {
//	ModelAndView mv = new ModelAndView();
//	return mv;
//}
