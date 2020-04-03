package com.jsp.service;

import java.util.List;

import com.jsp.vo.MemberVO;

public interface LoginService {
	
	public List<MemberVO> loginMember(MemberVO mv);

}
