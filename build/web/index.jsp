<%-- 
    Document   : index
    Created on : 14 de jun. de 2021, 08:14:57
    Author     : Thiago
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="db.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Disciplina> disciplinaLista = null; 
    disciplinaLista = Disciplina.getDisciplinaList();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POO P2</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h1>Inicio (Index)</h1>
        
        <% if(session.getAttribute("user.login") != null) {%>
        <table border="1">
            <tr>
                <th>RA</th>
                <th>Nome</th>
                <th>Semestre</th>
                <th>GitHub</th>
            </tr>
            <tr>
                <td>1290481923047</td>
                <td>Thiago Mathias dos Santos</td>
                <td>2º/2019</td>
                <td><a href="github.com/ThiagoMSantos">Meu GitHub</a></td>
            </tr>
        </table>
        
        <h2>Lista de Disiciplinas Atuais</h2>
        
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Media</th>
            </tr>
            <tr>
            <% for(Disciplina disciplina: disciplinaLista){%>
                <tr>
                    <td><%= disciplina.getNome() %></td>
                    <td><%= (Double)disciplina.getNotaP1()+disciplina.getNotaP2() / 2 %></td>
                </tr>
            <%}%>
        </table>
    <%} else{%>
        <h2>Realize seu aceso apra visualizar o contéudo.</h2>
    <%}%>
        
    </body>
</html>
