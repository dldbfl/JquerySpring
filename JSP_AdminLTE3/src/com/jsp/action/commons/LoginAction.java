package com.jsp.action.commons;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.exception.NullidException;
import com.jsp.exception.NullpasswordException;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class LoginAction implements Action {

	private MemberService memberService = MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="redirect:/member/list.do";
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		HttpSession session = request.getSession();
		
		try {
			memberService.login(id, pwd);
			
			MemberVO loginUser=memberService.getMember(id);
			if(loginUser.getEnabled()==0) {
				//url="commons/loginForm";
				session.setAttribute("loginUser", loginUser);
				request.setAttribute("msg", "비활성화된 유저에요.");
				session.setMaxInactiveInterval(100*1); // 세션 유지시간
			}else {
				session.setAttribute("loginUser", loginUser);
				session.setMaxInactiveInterval(100*1); // 세션 유지시간
			}
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			request.setAttribute("exception", e);
			
		} catch (NotFoundIDException | InvalidPasswordException | NullidException | NullpasswordException e) {
			e.printStackTrace();
			url="redirect:/commons/loginForm.do";
			request.setAttribute("msg", e.getMessage());
		}
				
		return url;
	}

}
