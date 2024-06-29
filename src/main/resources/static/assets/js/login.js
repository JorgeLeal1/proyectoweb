$(document).ready(function() {

	function validaRut(rutCompleto) {
		if (!/^(\d{1,8}-[\dKk])$/.test(rutCompleto)) {
			return false;
		}
		const [rut, digv] = rutCompleto.split('-');
		const calculatedDigv = calculateDigv(rut);
		return calculatedDigv === digv.toLowerCase();
	}

	function calculateDigv(rut) {
		let M = 0;
		let S = 1;
		for (let T = rut; T; T = Math.floor(T / 10)) {
			S = (S + (T % 10) * (9 - M++ % 6)) % 11;
		}
		return S ? String(S - 1) : 'k';
	}



	$('#form_login').submit(function(e) {
		e.preventDefault();

		const inputRun = $("#run").val();
		const inputContrasena = $("#contrasena").val();

		//console.log('run:', inputRun);
		//console.log('contrasena:', inputContrasena);

		let alert = "";
		if (validaRut(inputRun)) {
			//console.log('Valido');

			$.ajax({
				url: '/proyectoweb/consultarClientePorRun',
				type: "POST",
				data: { Run: inputRun },
				success: function(response) {
					//console.log(response);
					if (response) {

						$.ajax({
							url: '/proyectoweb/validarLogin',
							type: "POST",
							data: { run: inputRun, contrasena: inputContrasena },
							success: function(response) {
								//console.log("Respuesta del servidor:", response);

								if (response > 0) {
									alert = "<div class='alert alert-success alert-dismissible fade show  text-center' role='alert'>"
										+ "<p>Redireccionando!</p>"
										+ "</div>";

									$("#mensaje").html(alert);
									setTimeout(() => { window.location.href = "home"; }, 2000);
								} else {

									 alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert'>"
										+ "<p>Datos Incorrectos!</p>"
										+ "</div>";
									$("#mensaje").html(alert);
								}
							},

							// Error handling 
							error: function(error) {
								console.log("Error en la solicitud:", error);
							}
						});//AJAX

					}else{
						alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
							+ "<p>Run no existe, debe registrarse!</p>"
							+ "</div>";
						$("#mensaje").html(alert);
					}
				},

				// Error handling 
				error: function(error) {
					console.log("Error en la solicitud:", error);
				}
			});//AJAX

		} else {
			let alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert'>"
				+ "<p>Rut incorrecto!</p>"
				+ "</div>";
			$("#mensaje").html(alert);
		
			
		}

	}); //form_login

})