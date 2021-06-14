<%-- 
    Document   : disciplina
    Created on : 14 de jun. de 2021, 08:15:03
    Author     : Thiago
--%>

<%@page import="db.Disciplina"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String requestError = null;
    ArrayList<Disciplina> disciplinaLista = null;
    
    try{
        if(request.getParameter("inserirDisciplina") != null){
            String nomeDisciplina = request.getParameter("nomeDisciplina");
            String diaDisciplina = request.getParameter("diaDisciplina");
            String horaDisciplina = request.getParameter("horaDisciplina");
            int qtAulasDisciplina = Integer.parseInt(request.getParameter("qtAulasDisciplina"));
            double P1Disciplina = Double.parseDouble(request.getParameter("P1Disciplina"));
            double P2Disciplina = Double.parseDouble(request.getParameter("P2Disciplina"));
            
            Disciplina.insertDisciplina(nomeDisciplina, diaDisciplina, horaDisciplina, qtAulasDisciplina, P1Disciplina, P2Disciplina);
            response.sendRedirect(request.getRequestURI());
            
        } else if(request.getParameter("deletarDisciplina") != null){
            String nomeDisciplina = request.getParameter("nomeDisciplina");
            
            Disciplina.deleteDisciplina(nomeDisciplina);
            response.sendRedirect(request.getRequestURI());
        } else if (request.getParameter("atualizarDisciplina") != null){
            String nomeDisciplina = request.getParameter("nomeDisciplina");
            double P1Disciplina = Double.parseDouble(request.getParameter("P1Disciplina"));
            double P2Disciplina = Double.parseDouble(request.getParameter("P2Disciplina"));
            
            Disciplina.updateDisciplina(nomeDisciplina, P1Disciplina, P2Disciplina);
            response.sendRedirect(request.getRequestURI());
        }
        
        disciplinaLista = Disciplina.getDisciplinaList();

    } catch(Exception ex){
        requestError = ex.getMessage();
    }
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POO P2</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        
        <% if(requestError != null){%>
            <div style="color:red"><%=requestError %></div>
        <%}%>
        
        <% if(session.getAttribute("user.login") != null) {%>
            <br>
            <form method="POST">
                Nome: <input type="text" name="nomeDisciplina"> <br>
                Dia da Semana: <input type="text" name="diaDisciplina"> <br>
                Horário de Aula: <input type="text" name="horaDisciplina"> <br> 
                Quantidade de Aulas: <input type="text" name="qtAulasDisciplina"> <br>
                Nota P1: <input type="text" name="P1Disciplina"> <br>
                Nota P2: <input type="text" name="P2Disciplina"> <br><br>
                <input type="submit" name="inserirDisciplina"> <br>
                
                <h2>Lista de Disiciplinas Atuais</h2>
                
                <table border="1">
                    <tr>
                        <th>Nome</th>
                        <th>Dia da Semana</th>
                        <th>Horário de Aula</th>
                        <th>Quantidade de Aulas</th>
                        <th>Nota P1</th>
                        <th>Nota P2</th>
                    </tr>
                    <% for(Disciplina disciplina: disciplinaLista){%>
                        <tr>
                            <td><%= disciplina.getNome() %></td>
                            <td><%= disciplina.getDiaAula() %></td>
                            <td><%= disciplina.getHoraAula() %></td>
                            <td><%= disciplina.getQtAulas() %></td>
                            <form method="POST">
                               Nome: <input type="hidden" name="nomeDisciplina" value="<%= disciplina.getNome() %>">
                               <td><input type="text" name="P1Disciplina" value="<%=disciplina.getNotaP1() %>"></td>
                               <td><input type="text" name="P2Disciplina" value="<%=disciplina.getNotaP2() %>"></td>
                               <td><input type="submit" name="atualizarDisciplina" value="Alterar"></td>
                            </form>
                            <td>
                                <form method="POST">
                                    <input type="hidden" name="nomeDisciplina" value="<%= disciplina.getNome() %>">
                                    <input type="submit" name="deletarDisciplina" value="Apagar">
                                 </form>
                            </td>
                        </tr>
                    <%}%>
                </table>
            </form>
        <%} else{%>
            <h2>Realize seu aceso apra visualizar o contéudo.</h2>
        <%}%>
    </body>
</html>
