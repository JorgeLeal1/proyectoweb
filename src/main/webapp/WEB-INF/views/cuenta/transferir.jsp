<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="com.proyectoweb.web.model.CuentaTerceroModel"%>
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
	List<CuentaTerceroModel> tabla = (List) request.getAttribute("cuentaTercero");					
	%>
		
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" title="HOME" href="home">
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
					<a class="nav-link" aria-current="page" href="depositar">Depositar
						saldo</a> <a class="nav-link" aria-current="page" href="retirar">Retirar
						saldo</a> <a class="nav-link" aria-current="page" href="transferir">Transferir</a>
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

			<div class="card m-auto tamanoCardPrincipal">
				<div class="card-header">
					<h2 class="text-center">Enviar Dinero</h2>
				</div>

				<div class="card-body">
					<form id="Form_BuscarContacto"  action="buscarCuentaTercero" method="post">
						<div class="form-group row" style="margin-bottom: 5px;">

							<label for="saldo_actual" class="col-sm-2 col-form-label fw-bold">Saldo
								actual:</label>
							<div class="col-sm-8">
								<input type="number" class="form-control" id="saldo_actual"
									name="saldo_actual" step="0.01"
									value="<%out.print(request.getAttribute("saldo"));%>" readonly>
							</div>
						</div>

						<div class="form-group row">
							<label for="inputBuscarContacto"
								class="col-sm-2 col-form-label fw-bold">Nombre:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="inputBuscarContacto"
									name="inputBuscarContacto" maxlength="45" placeholder="Ingrese nombre"
									required="required">
							</div>
							<div class="col-sm-2">
								<button type="submit" class="btn btn-primary mb-2"
									style="float: right;" id="btnBuscar">Buscar</button>
							</div>
						</div>

					</form>

					<hr>
					<!-- Button trigger modal -->
					<button style="float: left;" type="button" class="btn btn-primary"
						id="btnAgregarContacto" data-bs-toggle="modal"
						data-bs-target="#exampleModal">Agregar cuenta tercero</button>

					<a href="transferir" class="btn btn-success"
						style="float: right">Actualizar Listado</a>

					<table class="table table-hover  table-striped" id="TablaTercero">
						<thead class="table-light">
							<tr>
								<th scope="col">Run</th>
								<th scope="col">Nombre</th>
								<th scope="col">N° Cuenta</th>
								<th scope="col">Alias</th>
								<th scope="col">Banco</th>
							</tr>
						</thead>
						<tbody>
						<%
							for (int i = 0; i < tabla.size(); i++) {
								out.print("<tr>" + ""
								+"<td>" + tabla.get(i).getCliente().getRun()+ "</td>"	
								+"<td>" + tabla.get(i).getCliente().getNombre1()+ " " + tabla.get(i).getCliente().getAppaterno() + "</td>"			
								+"<td>" + tabla.get(i).getNrocuentatercero()+ "</td>"		
								+"<td>" + tabla.get(i).getCuenta().getAlias()+ "</td>"	
								+"<td>" + tabla.get(i).getCuenta().getBanco()+ "</td>"								
								+"</tr>");
							}
						%>
						</tbody>
					</table>
					<div id="mensaje_sendmoney"></div>
				</div>
				<!-- card body-->
			</div>
			<!-- card-->

			<!-- MODAL CUENTA TERCERO ------------------------------------------------------------------------------>
			<div class="modal fade modal-lg" id="exampleModal" tablogin="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<form id="AgregarCtaTercero">
							<div class="modal-header">

								<div class="text-center col-sm-11">
									<h3 class="text-center col-sm-11" id="exampleModalLabel">Agregar
										cuenta tercero</h3>
									<h6 class="text-start fst-italic col-sm-11">
										<span style="color:#3447e4">El usuario debe tener una
											cuenta en el sistema para ser ingresado a sus contactos.</span>
									</h6>
								</div>
								<button type="button" class="btn-close col-sm-1"
									data-bs-dismiss="modal" aria-label="Close"></button>
							</div>

							<div class="modal-body">

								<div class="row mb-3">
									<div class="col-sm-5">
										<input type="text" class="form-control" id="runUsuario" name="runUsuario" value="${run}" readonly hidden>
									</div>
								</div>

								<div class="row mb-3">
									<label for="run" class="col-sm-2 col-form-label">Run:<span
										class="span_required">*</span></label>
									<div class="col-sm-5">
										<input type="text" class="form-control" required="required"
											id="run" name="run" maxlength="12" placeholder="11111111-1">
									</div>
									<div class="col-sm-5">
										<button type="button" class="btn btn-primary"
											id="btn_consultar_run">Verificar run</button>
									</div>
								</div>

								<div class="row mb-3">
									<label for="nombre1" class="col-sm-2 col-form-label">Nombres<span
										class="span_required"></span></label>
									<div class="col-sm-5">
										<input type="text" class="form-control" maxlength="45"
											placeholder="Nombre1" required="required" id="nombre1"
											name="nombre1" disabled>
									</div>
									<div class="col-sm-5">
										<input type="text" class="form-control" maxlength="45"
											placeholder="Nombre2" required="required" id="nombre2"
											name="nombre2" disabled>
									</div>
								</div>

								<div class="row mb-3">
									<label for="appaterno" class="col-sm-2 col-form-label">Apellidos<span
										class="span_required"></span></label>
									<div class="col-sm-5">
										<input type="text" class="form-control" maxlength="45"
											placeholder="Apellido Paterno" required="required"
											id="appaterno" name="appaterno" disabled>
									</div>
									<div class="col-sm-5">
										<input type="text" class="form-control" maxlength="45"
											placeholder="Apellido Paterno" required="required"
											id="apmaterno" name="apmaterno" disabled>
									</div>
								</div>

								<div class="row mb-3">
									<label for="nrocuenta" class="col-sm-2 col-form-label">N°
										Cuenta<span class="span_required"></span>
									</label>
									<div class="col-sm-10">
										<input type="number" class="form-control" max="20"
											oninput="if(this.value.length > 20) this.value = this.value.slice(0, 20);"
											placeholder="Nrocuenta" min="1" required="required"
											id="nrocuenta" name="nrocuenta" readonly>
									</div>
								</div>

								<div class="row mb-3">
									<label for="alias" class="col-sm-2 col-form-label">Alias<span
										class="span_required"></span></label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="Alias"
											required="required" maxlength="45" id="alias" name="alias"
											disabled>
									</div>
								</div>

								<div class="row mb-3">
									<label for="banco" class="col-sm-2 col-form-label">Banco<span
										class="span_required"></span></label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="Banco"
											required="required" maxlength="45" id="banco" name="banco"
											disabled>
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<div class="form-group row col-md-12" style="margin-bottom: 5px">
									<div class="col-md-8">
										<div id="mensaje"></div>
									</div>
									<div class="col-md-4">
										<button type="button" class="btn btn-secondary "
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary"
											id="btn_agregar_tercero">Agregar</button>
									</div>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
			<!-- Modal --------------------------------------------------------------------------------------------->

			<!-- MODAL TRANSFERIR ------------------------------------------------------------------------------>
			<div class="modal fade modal-lg" id="transferirModal" tablogin="-1"
				aria-labelledby="transferirModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<form id="transferirSaldo">
						
							<div class="modal-header">
								<div class="text-center col-sm-11">
									<h3 class="text-center col-sm-11" id="exampleModalLabel">TRANSFERIR MONTO</h3>
								</div>
								<button type="button" class="btn-close col-sm-1"
									data-bs-dismiss="modal" aria-label="Close"></button>
							</div>

							<div class="modal-body">
								<div class="row mb-3">
									<label for="Trun" class="col-sm-2 col-form-label">Run</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="Trun" name="Trun" readonly>
									</div>
								</div>

								<div class="row mb-3">
									<label for="nrocuenta" class="col-sm-2 col-form-label">N°
										Cuenta<span class="span_required"></span>
									</label>
									<div class="col-sm-10">
										<input type="number" class="form-control" id="Tnrocuenta" name="Tnrocuenta" readonly>
									</div>
								</div>

								<div class="row mb-3">
									<label for="Tsaldo_actual" class="col-sm-2 col-form-label">Saldo actual:</label>
									<div class="col-sm-10">
										<input type="number" class="form-control" id="Tsaldo_actual" name="Tsaldo_actual" readonly>
									</div>
								</div>

								<div class="row mb-3">
									<label for="Tsaldo" class="col-sm-2 col-form-label">Monto<span class="span_required">*</span>
									</label>
									<div class="col-sm-10">
										<input type="number" class="form-control" 
											placeholder="Ingresar Monto" min="1" required="required" id="Tsaldo" name="Tsaldo">
									</div>
								</div>
							</div>
							
							<div class="modal-footer">
								<div class="form-group row col-md-12" style="margin-bottom: 5px">
									<div class="col-md-8">
										<div id="TranferirSaldoMensaje"></div>
									</div>
									<div class="col-md-4">
										<button type="button" class="btn btn-secondary "
											data-bs-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary"
											id="btn_agregar_tercero">Transferir</button>
									</div>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
			<!-- Modal --------------------------------------------------------------------------------------------->




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
	<script src="/assets/js/transferir.js"></script>
</body>

</html>
