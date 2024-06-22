<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="com.proyectoweb.web.model.CuentaModel"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Wallet - ListaCuenta</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="../favicon.ico" type="image/x-icon" rel="icon" />

<link rel="stylesheet" href="../assets/css/menu.css">
<link rel="stylesheet" href="../assets/css/style.css">
</head>

<body class="fondo">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" title="HOME"
				href="home">
				<div class="home" alt="Volver al Men� Principal"
					style="background-image: url('../assets/img/home2.png') !important;"></div>
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link" aria-current="page" href="FormRealizarDeposito">Depositar
						saldo</a> <a class="nav-link" aria-current="page"
						href="FormRealizarRetiro">Retirar saldo</a> <a
						class="nav-link active" aria-current="page" href="viewAllCuentas">Listar
						Cuentas</a>
					<a class="nav-link" aria-current="page" href="/alkewallet_mvc/cuentas/nuevaCuentas.jsp">Crear Cuenta</a>								
				</div>
			</div>
			<span class="navbar-text"> <a style="float: right;"
				title="Logout" href="../../alkewallet_mvc/index.jsp"> <span
					style="float: left; margin-right: 10px;"> cerrar sesion </span>
					<div class="login" style="float: left;"></div>
			</a>
			</span>
		</div>
	</nav>
	<!-- nav-->

	<div class="container-fluid fondo">
		<div class="container">
			<br>

			<div class="card m-auto tamanoCardPrincipal">
				<div class="card-header">
					<h2 class="text-center">Listado Cuentas</h2>
				</div>

				<div class="card-body">

					<%
					List<CuentaModel> lista = (List) request.getAttribute("lista");
								out.print("Hay un total de " + lista.size() + " cuentas");
					%>
					
					    <select>
                         <% for (int j = 0; j < lista.size(); j++) { %>
                          <option name="listcuenta[]" value="<%=lista.get(j).getId() %>"><%=lista.get(j).getAlias() %></option>
                          <% }  %>  

                         </select>
					
					<table class="table table-striped table-hover">
					  <thead class="table-light">
					  	<tr>
							<th>ID</th>
							<th>RUN</th>
							<th>N� Cuenta</th>
							<th>Alias</th>
							<th>Banco</th>
							<th>Saldo</th>

						</tr>
				  </thead>
				  <tbody>
	
						<%
						for (int i = 0; i < lista.size(); i++) {
							out.print("<tr>" + "" 
							+ "<td>" + lista.get(i).getId() + "</td>" + "" 
							+ "<td>" + lista.get(i).getTitular().getRun() + "</td>" + ""
							+ "<td>" + lista.get(i).getNroCuenta() + "</td>" + "" 
							+ "<td>" + lista.get(i).getAlias() + "</td>" + ""
							+ "<td>" + lista.get(i).getBanco() + "</td>" + "" 
							+ "<td>" + lista.get(i).getSaldo() + "</td>"
							+"</tr>");
						}
						%>
						


				  </tbody>
				</table>

				</div>
				<!-- card body-->
			</div>
			<!-- card-->

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
	<script src="../assets/js/menu.js"></script>
</body>

</html>