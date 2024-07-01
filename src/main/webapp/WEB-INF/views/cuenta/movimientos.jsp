<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.proyectoweb.web.model.TransaccionModel"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Wallet - Menú Principal</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="/favicon.ico" type="image/x-icon" rel="icon" />

	<link rel="stylesheet" href="/assets/css/menu.css">
	<link rel="stylesheet" href="/assets/css/style.css">

</head>

<body>
    <%
	List<TransaccionModel> tabla = (List) request.getAttribute("movimientos");					
	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" title="HOME"
				href="home">
				<div class="home" alt="Volver al Menï¿½ Principal"
					style="background-image: url('/assets/img/home2.png') !important;"></div>
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" aria-current="page" href="depositar">Depositar saldo</a> 
					<a class="nav-link" aria-current="page" href="retirar">Retirar saldo</a> 
					<a class="nav-link" aria-current="page" href="transferir">Transferir</a>
					<a class="nav-link" aria-current="page" href="movimientos">Movimientos</a>			
				</div>
			</div>
			<span class="navbar-text"> 
				<span style="float: left; margin-right: 10px;">Bienvenido: </span>
				<a  title="Logout" href="/proyectoweb/logout" class="text-uppercase"> 
					<span style="float: left; margin-right: 10px;" class="fw-bold">${usuario} </span>
							<div class="login" style="float: left;"></div>
				</a>
			</span>
		</div>
	</nav>
	<!-- nav-->

	<div class="container-fluid fondo">
		<div class="container">
			<br>
					<h1 class="text-center">Ultimos Movimientos</h1><hr>
					<br>
					<table class="table table-hover  table-striped" id="TablaSendMoney">
						<thead class="table-light">
							<tr>
								<th scope="col">Usuario Envia</th>
								<th scope="col">Usuario Recibe</th>
								<th scope="col">Saldo</th>
								<th scope="col">Fecha</th>
								<th scope="col">Tipo Moneda</th>
								<th scope="col">Tipo Transaccion</th>
							</tr>
						</thead>
						<tbody>
						<%
							for (int i = 0; i < tabla.size(); i++) {
								out.print("<tr>" + ""
								+"<td>" + tabla.get(i).getUsuarioenvia().getNombre() + "</td>"		
								+"<td>" + tabla.get(i).getUsuariorecibe().getNombre() + "</td>"		
								+"<td>" + tabla.get(i).getSaldo() + "</td>"	
								+"<td>" + tabla.get(i).getDate() + "</td>"	
								+"<td>" + tabla.get(i).getMoneda().getNombre() + "</td>"	
								+"<td>" + tabla.get(i).getTipotransaccion().getTipotransaccion() + "</td>"											
								+"</tr>");
							}
						%>
						</tbody>
					</table>
					<div id="mensaje_sendmoney"></div>





		</div>
		<!-- container-->
	</div>
	<!-- container-fluid-->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
		
	<script src="/assets/js/menu.js"></script>
	<script src="/assets/js/deposit.js"></script>
</body>

</html>