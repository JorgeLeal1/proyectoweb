<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Wallet - Deposito</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<link href="/favicon.ico" type="image/x-icon" rel="icon" />

<link rel="stylesheet" href="/assets/css/registrar.css">
</head>

<body class="fondo" style="font-size: 14px !important">

	<div class="container">

		<div class="card m-auto tamanoCardPrincipal">
			<div class="card-header">
				<h2 class="text-center">REGISTRAR</h2>
			</div>
			<form id="form_registrar">
				<div class="card-body">

					<div class="card m-auto ">
						<div class="card-body">

							<h5 class="card-title text-decoration-underline fw-bold">CLIENTE</h5>
							<div class="form-group row" style="margin-bottom: 5px">
								<label for="run"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Run<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4 ">
									<input type="text" class="form-control" id="run" name="run"
										placeholder="11111111-1" maxlength="12" required="required">
								</div>
							</div>
							<div class="form-group row" style="margin-bottom: 5px">
								<label for="nombre1"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Nombre1<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="nombre1"
										name="nombre1" maxlength="45" placeholder="Ingrese nombre1"
										required="required">
								</div>

								<label for="nombre2"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Nombre2<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="nombre2"
										name="nombre2"  maxlength="45" placeholder="Ingrese Nombre2"
										required="required">
								</div>
							</div>

							<div class="form-group row" style="margin-bottom: 5px">
								<label for="appaterno"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Ap.Paterno<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="appaterno"
										name="appaterno"  maxlength="45" placeholder="Ingrese Apellido Paterno"
										required="required">
								</div>

								<label for="apmaterno"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Ap.Materno<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="apmaterno"
										name="apmaterno"  maxlength="45" placeholder="Ingrese Apellido Paterno"
										required="required">
								</div>
							</div>

						</div>
					</div>


					<br>
					<div class="card m-auto">
						<div class="card-body">

							<h5 class="card-title text-decoration-underline fw-bold">BANCO</h5>
							<div class="form-group row" style="margin-bottom: 5px">
								<label for="nrocuenta"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">N°
									Cuenta<span class="span_required">*</span>
								</label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="number" class="form-control" id="nrocuenta"
										name="nrocuenta"  min="1" max="20" placeholder="Ingrese nrocuenta"
										required="required" oninput="if(this.value.length > 20) this.value = this.value.slice(0, 20);">
								</div>
							</div>

							<div class="form-group row" style="margin-bottom: 5px">
								<label for="alias"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Alias<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="alias" name="alias"
										placeholder="Ingrese alias"  maxlength="45" required="required">
								</div>

								<label for="banco"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Banco<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="banco" name="banco"
										placeholder="Ingrese banco"  maxlength="45" required="required">
								</div>
							</div>
						</div>
					</div>


					<br>
					<div class="card m-auto">
						<div class="card-body">

							<h5 class="card-title text-decoration-underline fw-bold">USUARIO</h5>
							<div class="form-group row" style="margin-bottom: 5px">
								<label for="nombreUsuario"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">nombre<span
									class="span_required">*</span></label>
								<div class="ccol-xxl-4 col-xl-4 col-md-4">
									<input type="text" class="form-control" id="nombreUsuario"
										name="nombreUsuario"  maxlength="45" placeholder="Ingrese nombreUsuario"
										required="required">
								</div>
							</div>

							<div class="form-group row" style="margin-bottom: 5px">
								<label for="email"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Correo<span
									class="span_required">*</span></label>
								<div class="ccol-xxl-4 col-xl-4 col-md-4">
									<input type="email" class="form-control" id="email"
										name="email" placeholder="Ingrese email"  maxlength="60" required="required">
								</div>

								<label for="contrasena"
									class="padding_left_right_5 col-xxl-2 col-xl-2 col-md-2 col-form-label fw-bold">Contraseña<span
									class="span_required">*</span></label>
								<div class="col-xxl-4 col-xl-4 col-md-4">
									<input type="password" class="form-control" id="contrasena"
										name="contrasena"  maxlength="50" placeholder="Ingrese Contraseña"
										required="required" autocomplete="off">
								</div>
							</div>
						</div>
					</div>


				</div>
				<!-- car body-->

				<div class="card-footer text-center">
					<div class="form-group row" style="margin-bottom: 5px">
						<div class="col-xxl-10 col-xl-10 col-md-10">
							<div id="mensaje"></div>
						</div>
						<div class="col-xxl-2 col-xl-2 col-md-2">
							<button type="submit" class="btn btn-primary" id="Enviar">Registrar</button>
						</div>

					</div>
				</div>

			</form>
		</div>
		<!-- card -->

	</div>
	<!-- container-->


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script src="/assets/js/registrar.js"></script>
</body>

</html>
