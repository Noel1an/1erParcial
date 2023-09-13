
package com.emergentes.controlador;

import com.emergentes.modelo.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op=request.getParameter("op");
        Productos objprod =new Productos();
        int id,pos;
        
        HttpSession ses=request.getSession();
        ArrayList<Productos> lista =(ArrayList<Productos>)ses.getAttribute("listaprod");
        switch (op) {
            case "nuevo":
                //enviar un objeto vacio a editar dato
                request.setAttribute("miobjprod", objprod);
                request.getRequestDispatcher("editar.jsp").
                        forward(request,response);
                break;
            case "editar":
                //envia un objeto a editar pero con contenido
                id=Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request,id);
                //obtener el objeto
                objprod = lista.get(pos);
                request.setAttribute("miobjprod", objprod);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                //enviar el registro de laa eliminar
                id=Integer.parseInt(request.getParameter("id"));
                //averiguar el objeto
                
                pos = buscarIndice(request,id);
                if(pos  >=0){
                    lista.remove(pos);
                }
                //actualizar la lista
                request.setAttribute("listaprod", lista);
                //enviamos al index
                response.sendRedirect("index.jsp");
                break;
            default:
       
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses=request.getSession();
        ArrayList<Productos> lista =(ArrayList<Productos>)ses.getAttribute("listaprod");
        Productos objprod = new Productos();
        objprod.setId(id);
        objprod.setDescripcion(request.getParameter("descripcion"));
        objprod.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        objprod.setPrecio(Double.parseDouble(request.getParameter("precio")));
        objprod.setCategoria(request.getParameter("categoria"));
        if(id==0){
            //nuevo
            int idNuevo = obtenerId(request);
            objprod.setId(idNuevo);
            lista.add(objprod);
        }else{
            //edicion
            int pos = buscarIndice(request,id);
            lista.set(pos, objprod);
        }
        request.setAttribute("listaprod", lista);//actualisa la lista
        response.sendRedirect("index.jsp");//redireccion el control aindex
    }
    public int buscarIndice(HttpServletRequest request,int id){
       HttpSession ses=request.getSession();
       ArrayList<Productos> lista=(ArrayList<Productos>)ses.getAttribute("listaprod");
       
       int pos =-1;
       if(lista != null){
           for(Productos ele : lista){
               ++pos;
               if(ele.getId()==id){
                   break;
               }
           }
       }
       return pos;
   }
   
   public int obtenerId(HttpServletRequest request){
       HttpSession ses = request.getSession();
       ArrayList<Productos> lista=(ArrayList<Productos>)ses.getAttribute("listaprod");
       //busca el ultimo id
       int idn=0;
       for(Productos ele : lista){
           idn = ele.getId();
       }
       return idn + 1;
   }
    }
