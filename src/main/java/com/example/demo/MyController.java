package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	@RequestMapping("home")
	public String hello() {

		System.out.println("hello hi");
		return "home";
	}

	@RequestMapping("new")
	public String newFile() {

		System.out.println("newFile hi");
		return "NewFile";
	}

	@RequestMapping("names")
	public String getNameFromURL(HttpServletRequest req) {

		// TODO by HttpServletRequest object we can get data from request
//		request should be like this 
//		http://localhost:8082/names?name=chait
		String name = req.getParameter("name");

		System.out.println("hello ...." + name);

		return "home";
	}

	@RequestMapping("getname")
	public String getNameFromURLAndSentToHomeJSP(HttpServletRequest req) {

		// http://localhost:8091/getname?name=ChaitanyaSilawat
		String name = req.getParameter("name");
		System.out.println("hello ...." + name);

//		TODO. By HttpSession object we can set Attribute inside Session 
//		and we can get it on return page by ${AttributeName}to session

		HttpSession session = req.getSession();
		session.setAttribute("name", name);

		return "getName";
	}

	@RequestMapping("sname")
	public String getNameFromUrl(String name, HttpSession session) {

		// TODO can get name without HttpServletRequest req just pass String name
		// can pass it to jsp page by HttpSession session inside method
		// Stirng name and this ------name! should be same. If not same so won't send
		// data to JSP page
//										 ^
//		http://localhost:8082/sname?name=chait

//		(Stirng myName,HttpSession session)
//		and   http://localhost:8082/sname?name=chait so wont work 

		session.setAttribute("name", name);
		System.out.println("hello ..inside getNameFromUrl.." + name);

		return "getName";
	}

	/*
	 * TODO if we want to use other name inside method public String
	 * getNameInside(String myName, HttpSession session), to get name attribute from
	 * http://localhost:8082/sname?name=chait request. So we use @RequestParam()
	 * inside method signature getNameInside(@RequestParam("name")String myName,
	 * HttpSession session) now any String referance name can take inside method
	 * getNameInside(String myName, HttpSession session) while request have name
	 * attribute Need HttpSession session to send data in JSP page
	 */

	@RequestMapping("diff")
	public String getNameInside(@RequestParam("name") String myName, HttpSession session) {

//	   http://localhost:8082/diffname?name=chait

		session.setAttribute("name", myName);
		System.out.println("hello ..inside getNameInside.." + myName);

		return "getName";
	}

	/*
	 * We can remove HttpSession session. getName,home,newFile is VIEW and name,
	 * myName is data DispatcherServlet need a Data and View name to send response
	 * We have ModelAndView class for this. we add data and view inside ModelAndView
	 * class and return it
	 * 
	 */

	@RequestMapping("model")
	public ModelAndView modelView(@RequestParam("name") String myName) {

		ModelAndView mv = new ModelAndView();
		// inside ModelAndView object add Data and View name

		mv.addObject("name", myName);
		mv.setViewName("getName");

		return mv;
	}

	/*
	 * If we want to get hole Object value on View rather than individual like name
	 * ,id etc Create Alien Class and get this object from request and send it to SP
	 * page
	 */

	@RequestMapping("alien")
	public ModelAndView getAlienObject(Aliens alien) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("obj", alien);

		// get this object value on JSP page

		mv.setViewName("aliens");

		return mv;
	}

}
