package spring.interceptor.webtest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.utility.webtest.Utility;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	// 요청 주소 결과 생성전에 작동함
	//preHandle() - 컨트롤러 메소드 실행 직전에 수행됩니다. true 를 반환하면 계속 진행이 되고
	//false 를 리턴하면 실행 체인(다른 인터셉터, 컨트롤러 실행)이 중지되고 반환 됩니다. 
	//필터의 응답 처리가 있다면 그것은 실행이 됩니다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(true);
		System.out.println("session.getAttribute(\"grade\"): " + session.getAttribute("grade"));
		System.out.println("preHandle executed.");
		System.out.println("URL: " + request.getContextPath());

		// 관리자 로그인시 "grade" 세션 변수 생성
		String grade = Utility.checkNull((String) session.getAttribute("grade"));

		if (grade.equals("A") == true) { // 접근 가능, 요청 페이지 처리
			return true; // 요청 페이지로 계속 진행
		} else {
			// 에러 출력또는 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/login");
			return false;
		}
	}

	// 요청 주소 결과 생성후 작동함
	//postHandle() - 컨트롤러 메소드 실행 직후에 실행 됩니다.
	//View 페이지가 렌더링 되기전에   ModelAndView 객체를 조작 할 수 있습니다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle executed.");
		modelAndView.addObject("admin", "관리자 관련 기능을 출력합니다.");

	}

	// JSP등 View 페이지 출력전에 작동됨.
	//afterCompletion() -  View 페이지가 렌더링 되고 난 후 에 실행됩니다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion executed.");

	}

	//afterConcurrentHandlingStarted() - Servlet 3.0 부터 비동기 요청이 가능. 
	//비동기 요청시 postHandle와 afterCompletion 은 실행되지 않고, 이 메소드가 실행

	
	
}