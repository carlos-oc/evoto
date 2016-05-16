<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page language="java" import="es.upm.dit.isst.evoto.dao.CRVDAO" %>
<%@ page language="java" import="es.upm.dit.isst.evoto.dao.CRVDAOImpl" %>
<%@ page language="java" import="es.upm.dit.isst.evoto.model.CRV" %>

<!DOCTYPE html>
<html>
  <head>
  
    <%@include file="inc/head.html" %>
    
    <style type="text/css">
      .chart-legend li span{
      display: inline-block;
      width: 12px;
      height: 12px;
      margin-right: 5px;
      }
      .chart-legend li {
        display: inline;
        margin-right: 10px;
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
          <h1>
            Elecciones al senado
            <small>Centro de recuento de votos</small>
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-md-6">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Recuento de votos</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                <h2 class="text-center"><strong>ELECCIONES GENERALES 2015</strong></h2>
                    <h4 class="text-center"><strong>Votos por senadores total en Madrid</strong></h4>
                    <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                          <th class="info">Candidato</th>
                          <th class="info">Partido</th>
                          <th class="info">Esca&ntilde;os</th>
                          <th class="info">Votos</th>
                        </tr>
                    </thead>
                        <tbody>
                        <% CRVDAO dao = CRVDAOImpl.getInstance();
                        String[] esc = {"NO", "SÍ"};
                        int cpp = 0, cpsoe = 0, cpodemos = 0, cciudadanos = 0, cciu = 0, cupyd = 0, cpacma = 0, cvox = 0;
                        int contador = 0;
                        for (CRV res : dao.todosCRV()) {
                            int escano = 0;
                        	contador++;
                        	if(contador <=4 && res.getnVotos() > 0){
                        		escano = 1;
                        	}
                        %>
                        	<tr>
                        		<td> <jsp:expression>res.getNombreCompleto()</jsp:expression> </td>
                        		<td> <jsp:expression>res.getPartido()</jsp:expression> </td>
                        		<td> <jsp:expression>esc[escano]</jsp:expression> </td>
                        		<td> <jsp:expression>res.getnVotos()</jsp:expression> </td>
                        	</tr>	
                        <%
                        if(escano == 1){
	                        if(res.getPartido().equals("pp")){
	                        	cpp++;
	                        }else if(res.getPartido().equals("psoe")){
	                        	cpsoe++;
	                        }else if(res.getPartido().equals("podemos")){
	                        	cpodemos++;
	                        }else if(res.getPartido().equals("ciudadanos")){
	                        	cciudadanos++;
	                        }else if(res.getPartido().equals("ciu")){
	                        	cciu++;
	                        }else if(res.getPartido().equals("upyd")){
	                        	cupyd++;
	                        }else if(res.getPartido().equals("pacma")){
	                        	cpacma++;
	                        }else if(res.getPartido().equals("vox")){
	                        	cvox++;
	                        }
        				}
                        }
                        %>
                      </tbody>
                    </table>
            	</div>
            </div>
            </div>
                      <!--  Gráfica de resultados -->
            <!-- left column -->
            <div class="col-md-6">
              <!-- general form elements -->
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Gráfica de resultados</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    
                    <h3 class="text-center">Escaños</h3>
                    <canvas id="pieChart"></canvas>
                    <p>&nbsp; </p>
                    <div id="js-legend" class="chart-legend"></div>
                    <p>&nbsp; </p>
                </div>
              </div><!-- /.box -->
            </div><!--/.col -->
            <!-- /gráfica resultados -->
            
          </div>   <!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	<%@include file="inc/footer.html" %>
	
    </div><!-- ./wrapper -->
	<%@include file="inc/scripts.html" %>
<!-- ChartJS 1.0.1 -->
    <script src="plugins/chartjs/Chart.min.js"></script>
<script>
          //-------------
        //- PIE CHART -
        //-------------
        // Get context with jQuery - using jQuery's .get() method.
        var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
        var pieChart = new Chart(pieChartCanvas);
        var PieData = [
          {
            value: <jsp:expression>cpp</jsp:expression>,
            color: "#0BB2FF",
            highlight: "#0BB2FF",
            label: "PP"
          },
          {
            value: <jsp:expression>cpsoe</jsp:expression>,
            color: "#ED0A00",
            highlight: "#ED0A00",
            label: "PSOE"
          },
          {
            value: <jsp:expression>cpodemos</jsp:expression>,
            color: "#9A559A",
            highlight: "#9A559A",
            label: "PODEMOS"
          },
          {
            value: <jsp:expression>cvox</jsp:expression>,
            color: "#6DAF3C",
            highlight: "##6DAF3C",
            label: "VOX"
          },
          {
            value: <jsp:expression>cpacma</jsp:expression>,
            color: "#93A607",
            highlight: "#93A607",
            label: "PACMA"
          },
          {
            value: <jsp:expression>cupyd</jsp:expression>,
            color: "#EC008C",
            highlight: "#EC008C",
            label: "UPYD"
          },
          {
            value: <jsp:expression>cciudadanos</jsp:expression>,
            color: "#F07A36",
            highlight: "#F07A36",
            label: "CIUDADANOS"
          },
          {
            value: <jsp:expression>cciu</jsp:expression>,
            color: "#FFF200",
            highlight: "#FFF200",
            label: "CIU"
          },
          {
              value: <jsp:expression>cciu</jsp:expression>,
              color: "#02311F ",
              highlight: "#02311F ",
              label: "IU"
            }
        ];
        var pieOptions = {
          //Boolean - Whether we should show a stroke on each segment
          segmentShowStroke: true,
          //String - The colour of each segment stroke
          segmentStrokeColor: "#fff",
          //Number - The width of each segment stroke
          segmentStrokeWidth: 2,
          //Number - The percentage of the chart that we cut out of the middle
          percentageInnerCutout: 50, // This is 0 for Pie charts
          //Number - Amount of animation steps
          animationSteps: 100,
          //String - Animation easing effect
          animationEasing: "easeOutBounce",
          //Boolean - Whether we animate the rotation of the Doughnut
          animateRotate: true,
          //Boolean - Whether we animate scaling the Doughnut from the centre
          animateScale: false,
          //Boolean - whether to make the chart responsive to window resizing
          responsive: true,
          // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
          maintainAspectRatio: true,
          //String - A legend template
          legendTemplate: "<ul class=\"<\%=name.toLowerCase()%>-legend\"><\% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<\%=segments[i].fillColor%>\"></span><\%if(segments[i].label){%><\%=segments[i].label%><\%}%><\%=' - '%><\%=segments[i].value%></li><\%}%></ul>"
        };
        //Create pie or douhnut chart
        // You can switch between pie and douhnut using the method below.
        pieChart = pieChart.Doughnut(PieData, pieOptions);
        var legend = pieChart.generateLegend();
        document.getElementById('js-legend').innerHTML = legend;
</script>  </body>
</html>
