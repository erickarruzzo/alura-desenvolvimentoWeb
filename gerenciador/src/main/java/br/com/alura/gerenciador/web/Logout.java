package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

	    session.removeAttribute("usuario.logado");

	    //resp.sendRedirect("logout.html");  Para direcionar à pagina de forma direta
	    
	    //Direcionando para a página logout.html de forma "escondida"
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/pages/logout.html");
	    dispatcher.forward(req, resp);
	}
}
