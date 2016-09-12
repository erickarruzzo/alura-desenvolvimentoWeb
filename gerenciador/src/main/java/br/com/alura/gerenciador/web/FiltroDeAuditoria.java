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

@WebFilter(urlPatterns="/*")
public class FiltroDeAuditoria implements javax.servlet.Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String usuario = getUsuario(req);
		
		System.out.println("Usuário" + usuario + " acessando a URI: " + req.getRequestURI());
		chain.doFilter(request, response);
	}

	private String getUsuario(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies==null) return "<deslogado>";
		for (Cookie cookie : cookies){
			if (cookie.getName().equals("usuario.logado")){
				return cookie.getValue();
			}
		}
		return "<deslogado>";
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
