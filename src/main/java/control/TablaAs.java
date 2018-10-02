/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;


import net.daw.bean.Resultado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.daw.bean.Errores;

/**
 *
 * @author Alejandro
 */
public class TablaAs extends HttpServlet {

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
            throws ServletException, IOException, InterruptedException {
        response.setContentType("application/jSon");
        PrintWriter out = response.getWriter();

        String operacion="mult";
        Double oper1 = null;
        Double oper2 = null;
        Double res = null;
        Gson gSon = new Gson();
        Boolean fallo = false;

        //operacion = request.getParameter("operador");

        try {
            oper1 = Double.parseDouble(request.getParameter("op1"));
            oper2 = Double.parseDouble(request.getParameter("op2"));

        } catch (NumberFormatException nfex) {
            response.setStatus(500);
            Errores error = new Errores();
            error.setOper1(oper1);
            error.setOper2(oper2);
            error.setOperacion(operacion);
            error.setMensaje("Por favor, ingrese dos numeros en el formulario");

            response.getWriter().append(gSon.toJson(error));

        }

        try {


            switch (operacion) {
                case "sum":
                    res = oper1 + oper2;
                    break;
                case "rest":
                    res = oper1 - oper2;
                    break;
                case "mult":
                    res = oper1 * oper2;
                    break;
                case "div":
                    if (oper2 == 0) {
                        response.setStatus(500);
                        Errores error = new Errores();
                        error.setOper1(oper1);//No hace falta que envíe tanto, con el mensaje vale
                        error.setOper2(oper2);
                        error.setOperacion(operacion);
                        error.setMensaje("No se puede dividir por cero");
                        response.getWriter().append(gSon.toJson(error));
                        fallo = true;
                    } else {
                        res = oper1 / oper2;
                    }

                    break;
                default:
                    response.setStatus(500);
                    Errores error = new Errores();
                    error.setOper1(oper1);
                    error.setOper2(oper2);
                    error.setOperacion(operacion);
                    error.setMensaje("No ha especificado una operación");
                    response.getWriter().append(gSon.toJson(error));
                    fallo = true;
            }
            if (!fallo) {
                Resultado r = new Resultado();
                r.setOp1(oper1);
                r.setOp2(oper2);
                r.setResultado(res);
                
                Thread.sleep((long)(Math.random() * 2000));
                response.getWriter().append(gSon.toJson(r));
            }

        } finally {
            out.close();
        }
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(TablaAs.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(TablaAs.class.getName()).log(Level.SEVERE, null, ex);
        }
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
