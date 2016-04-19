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
                <!-- form start -->
                <form role="form">
                  <div class="box-body">
                    <div class="form-group">
                      <label for="nombre">Nombre</label>
                      <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduzca su nombre">
                    </div>
                    <div class="form-group">
                      <label for="apellidos">Primer apellido</label>
                      <input type="text" class="form-control" id="apellido1" name="apellido1" placeholder="Introduzca su primer apellido">
                    </div>
                    <div class="form-group">
                      <label for="apellidos">Segundo apellido</label>
                      <input type="text" class="form-control" id="apellido2" name="apellido2" placeholder="Introduzca su segundo apellido">
                    </div>
                    <div class="form-group">
                      <label for="dni">DNI</label>
                      <input type="text" class="form-control" id="dni" name="dni" placeholder="Introduzca su DNI">
                    </div>
                    <div class="form-group">
                      <label for="email">Email</label>
                      <input type="email" class="form-control" id="email" name="email" placeholder="Intoruzca su email">
                    </div>
                    <div class="form-group">
                      <label for="email">Provincia</label>
                      <select class="form-control" id="provincia" name="provincia">
                      	<option value="0">Seleccione...</option>
                      	<option value="1">Madrid</option>
                      </select>
                    </div>
                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary">Solicitar certificado</button>
                  </div>
                </form>
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