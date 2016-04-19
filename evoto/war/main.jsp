<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
  <head>
    <%@include file="inc/head.html" %>
    <style type="text/css">
      .banner {
           position: relative;
           overflow: auto;
           width: 100% !important
       }
       .banner ul {
           margin: 0px;
           padding: 0px
       }
       .banner li {
           list-style: none
       }
       .banner li.row {
           margin-right: 0px;
           margin-left: 0px
       }
       .banner ul li {
           float: left;
           display: block;
           width: 100%;
           min-height: 400px;
           background-position: center center;
           background-repeat: no-repeat;
           background-size: cover
       }

       .banner li#banner-img {
           background-image: url('images/senado.jpeg')
       }
            
    </style>
  </head>
  <body class="hold-transition skin-purple sidebar-mini">
    <div class="wrapper">

      	<%@include file="inc/header.html" %>

		<%@include file="inc/sidebar.html" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
        <h1>Ingeniería de Servicios y Sistemas de Telecomunicación</h1>
          <h2>Sistema de voto electrónico <small>Elecciones al senado</small></h2>
        </section>
        <section class="content">
          <div class="row">
        <div class="banner">
        <ul>
            <li class="row" id="banner-img">
            </li>
            </ul>
            </div>
        <!-- Main content -->

          </div>   <!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      
	<%@include file="inc/footer.html" %>

    </div><!-- ./wrapper -->
    
	<%@include file="inc/scripts.html" %>
  </body>
</html>
