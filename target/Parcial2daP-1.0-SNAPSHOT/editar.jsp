<%@page import="com.emergentes.modelo.Productos"%>
<% 
    Productos prod=(Productos)request.getAttribute("miobjprod");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar registros</h1>
        <form action="MainServlet" method="post">
             <table border="0">
                     <tr>
                         <td>ID</td>
                         <td><input type="text" name="id" 
                                    value="<%=prod.getId()%>" size="2" readonly=""></td>
                     </tr>
                     <tr>
                         <td>Descripcion</td>
                         <td><input type="text" name="descripcion" 
                                    value="<%= prod.getDescripcion()%>"></td>
                     </tr>
                     <tr>
                         <td>Cantidad</td>
                         <td><input type="text" name="cantidad" 
                                    value="<%= prod.getCantidad()%>"></td>
                     </tr>
                     <tr>
                         <td>Precio</td>
                         <td><input type="number" step="0.01" name="precio" 
                                    value="<%= prod.getPrecio()%>"></td>
                     </tr>
                     <tr>
                         <td>Categoria</td>
                         <td><input type="text" name="categoria" 
                                    value="<%= prod.getDescripcion()%>"></td>
                     </tr>
                     <tr>
                         <td></td>
                         <td><input type="submit" value="Actualizar"></td>
                     </tr>
                 </tbody>
             </table>
         </form>
    </body>
</html>
