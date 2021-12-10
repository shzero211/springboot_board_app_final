package com.board.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;

@Controller
public class UiUtils {
	//메세지랑 리다이렉트하는 html을 실행하는 메소드(params는 페이지 데이터를 기록함)
	public String showMessageWithRedirect(@RequestParam(value="message",required = false)String message,@RequestParam(value="redirectUri",required = false)String redirectUri,@RequestParam(value="method",required = false)Method method,@RequestParam(value="params",required = false)Map<String,Object> params,Model model) {
		model.addAttribute("message",message);
		model.addAttribute("redirectUri",redirectUri);
		model.addAttribute("method",method);
		model.addAttribute("params",params);
		
		
		
		return"utils/message-redirect";
		
	}
}
