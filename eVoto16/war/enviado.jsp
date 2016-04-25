<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
  <head>
  <%@include file="inc/head.html" %>
  </head>
  <body class="hold-transition skin-purple sidebar-mini">
    <div class="wrapper">

      	<%@include file="inc/header.html" %>

		<%@include file="inc/sidebar.html" %>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Elecciones al senado
            <small>Autoridad de Certificación</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <!-- left column -->
            <div class="col-md-12">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Solicitud de certificado electrónico</h3>
                </div><!-- /.box-header -->
                <br/>
                <div class="row">
                	<div class="col-md-10 col-md-offset-1">
                		<div class="alert alert-success"> Su solicitud se ha enviado correctamente. </div>
                	</div>
                </div>
                <!-- form start -->
              </div><!-- /.box --> 
            </div><!--/.col -->
          </div>   <!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	<%@include file="inc/footer.html" %>
    </div><!-- ./wrapper -->

	<%@include file="inc/scripts.html" %>

  </body>
</html>
