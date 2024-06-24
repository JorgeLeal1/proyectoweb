<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Wallet - Login</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
		integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link href="/favicon.ico" type="image/x-icon" rel="icon" />
	<link rel="stylesheet" href="/assets/css/style.css">
</head>

<body class="fondo">
	<div class="container">
	<br>
		<div class="card m-auto shadow-lg p-3 mb-5 bg-white rounded" style="width: 25rem; ">
			<h2 class="text-center">Bienvenido</h2>
			<h5 class="text-center">AlkeWallet</h5>

			<div class="card-body">
				<form id="form_login">
					<div class="form-group has-validation">
						<label for="run" class="form-label">Ingresa tu Run:</label>
						<input type="text" class="form-control form-control-lg " id="run" name="run"
							placeholder="11111111-1" maxlength="12" required="required" >
					</div>
					<br>
					<div class="form-group has-validation">
						<label for="contrasena" class="form-label">Ingresa tu Contraseña:</label>
						<input type="password" class="form-control form-control-lg" id="contrasena" name="contrasena"
							placeholder="Ingrese su contraseña" autocomplete="off" maxlength="50" required="required">
					</div>
					<br>
					<button type="submit" class="btn btn-primary w-100 py-2" value="submit">Iniciar sesión</button>
				</form>
			</div>
			<div class="card-footer  text-center">
				<div>
					   <a href="registrar" class="alert-link">¿No tienes cuenta? Registrate</a>
				</div>
				<br>
				
				<div id="mensaje" > </div>

			</div>
		</div><!-- Card -->
	</div><!-- Container -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script src="/assets/js/login.js"></script>
</body>

</html>