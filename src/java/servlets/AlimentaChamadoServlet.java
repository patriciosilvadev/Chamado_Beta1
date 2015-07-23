/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dados.Atendente;
import dados.Chamado;
import dados.Status;
import dados.Usuario;
import dao.ChamadoDao;
import dao.UsuarioDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author evandro
 */
@WebServlet(name = "AlimentaChamadoServlet", urlPatterns = {"/AlimentaChamadoServlet"})
public class AlimentaChamadoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlimentaChamado</title>");
            out.println("</head>");
            out.println("<body>");

            Chamado chamado = new Chamado();
            Atendente atendente = new Atendente();
           // Status status = new Status();
            //Usuario usuario = new Usuario();
            UsuarioDao userDao = new UsuarioDao();

            String login = request.getParameter("login");
            //String verLod = userDao.verificaLogin(usuario);
            if (login == userDao.verificaLogin(login)) {

                    String tipo = request.getParameter("tipo");
                    if (tipo.equalsIgnoreCase("TI-Desenvolvimento")) {
                        chamado.setTipo(tipo);
                        //atendente.setCodigo(1);
                        atendente.setNome(atendente.getNome());

                    } else {
                        if (tipo.equalsIgnoreCase("TI-Suporte")) {
                            chamado.setTipo(tipo);
                            atendente.setCodigo(2);
                        }
                    }

                    String descricao = request.getParameter("descricao");
                    chamado.setDescricao(descricao);

                    int temp = atendente.getCodigo();
                    Atendente aten = new Atendente();
                    aten.setCodigo(temp);
                    chamado.setAtendente(aten);

                    Status stat = new Status();
                    stat.setCodigo(Integer.parseInt(request.getParameter("status")));
                    chamado.setStatus(stat);

                    //buscar usuarios banco.
                    Usuario usu = new Usuario();
                    usu.setCodigo(Integer.parseInt(login));
                    chamado.setUsuario(usu);

                    ChamadoDao chamadoDao = new ChamadoDao();
                    String msg = chamadoDao.addChamado(chamado);

                    if (msg.equals("sucesso")) {
                        response.sendRedirect("mostraChamado.jsp?chamadoIncluido = " + chamado.getStatus());

                    } else {
                        out.print("Erro ao registrar Chamado!");
                    }

                    out.println("</body>");
                    out.println("</html>");
             
            }
            System.out.println("Usuario ou senha incorretos");
        }
        System.out.println("Usuario ou senha incorretos");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
