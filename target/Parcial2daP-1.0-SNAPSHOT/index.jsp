<%@page import="com.emergentes.modelo.Productos"%>
<%@page import="java.util.ArrayList"%>
<% 
  if(session.getAttribute("listaprod")==null){
       ArrayList<Productos> listaux = new ArrayList<Productos>();
       session.setAttribute("listaprod", listaux);
  }
  ArrayList<Productos> lista = (ArrayList<Productos>)session.getAttribute("listaprod");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>PRIMER PARCIAL TEM-742</p>
        <p>NOMBRE: NOE ELMER QUISPE LIPA</p>
        <p>CARNET: 9885320</p>
        
        <h1>Gestion de Productos</h1>
         <a href="MainServlet?op=nuevo">Agregar una Nuevo Producto</a>
        <form action="MainServlet" method="POST"></form>
        <table border = "1">
            <tr>
                    <th>ID</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Categoria</th>
                    <th></th>
                    <th></th>
                </tr><br>
                    <%
                        if(lista !=null){
                            for(Productos item:lista){
                     %>
                     <td><%= item.getId() %></td>
                    <td><%=item.getDescripcion() %></td>
                    <td><%= item.getCantidad()%></td>
                    <td><%= item.getPrecio()%></td>
                    <td><%= item.getCategoria()%></td>
                    <td>
                        <a href="MainServlet?op=editar&id=<%= item.getId() %>
                           ">Editar</a>
                    </td><td>
                        <a href="MainServlet?op=eliminar&id=<%= item.getId() %>"
                           onclick="return(confirm('esta seguro de eliminar??..')
                                       )">Eliminar</a>
                    </td>  
                    <tr>
                    <% 
                            }
                        }
                     %>
                 </tr>
        </table>
    </body>
</html>
