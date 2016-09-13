package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/*")
public class FiltroDeAuditoria implements javax.servlet.Filter{

	final int MINUTOS = 1;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		Cookie cookie = new Cookies(req.getCookies()).getUsuarioLogado();
		
		String usuario = "<deslogado>";
		
		if (cookie != null){
			cookie.setMaxAge(MINUTOS * 60);
			usuario = cookie.getValue();
			res.addCookie(cookie);
		}
		
		System.out.println("Usuário " + usuario + " acessando a URI: " + req.getRequestURI());
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
