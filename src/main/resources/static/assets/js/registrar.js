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


	$('#form_registrar').submit(function(e) {
		e.preventDefault();

		const inputRun = $("#run").val();
		const inputNombre1 = $("#nombre1").val();
		const inputNombre2 = $("#nombre2").val();
		const inputAppaterno = $("#appaterno").val();
		const inputApmaterno = $("#apmaterno").val();

		const inputNrocuenta = $("#nrocuenta").val();
		const inputAlias = $("#alias").val();
		const inputBanco = $("#banco").val();

		const inputNombreUsuario = $("#nombreUsuario").val();
		const inputEmail = $("#email").val();
		const inputContrasena = $("#contrasena").val();

		let alert = "";

		/* console.log('run:', inputRun); */
		$("#mensaje").html("");
		//console.log(inputRun);

		if (validaRut(inputRun)) {
			//console.log('Valido');

			$.ajax({
				url: '/proyectoweb/consultarClientePorRun',
				type: "POST",
				data: { Run: inputRun },
				success: function(response) {
					console.log(response);
					if (response > 0) {
						alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
							+ "<p>Run ingresado ya existe, favor ingrese otro !</p>"
							+ "</div>";
						$("#mensaje").html(alert);
					} else {

						$.ajax({
							url: '/proyectoweb/registrarCuenta',
							type: "POST",
							data: {
								Run: inputRun, Nombre1: inputNombre1, Nombre2: inputNombre2, Appaterno: inputAppaterno, Apmaterno: inputApmaterno,
								Nrocuenta: inputNrocuenta, Alias: inputAlias, Banco: inputBanco,
								NombreUsuario: inputNombreUsuario, Email: inputEmail, Contrasena: inputContrasena,
							},
							success: function(response) {
								if (response > 0) {
									alert = "<div class='alert alert-success alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
										+ "<p>Usuario registrado correctamente, redireccionando a login.</p>"
										+ "</div>";
									$("#mensaje").html(alert);
									setTimeout(() => { window.location.href = "index"; }, 4000);
								} else {
									alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
										+ "<p>Error en registrar usuario, favor contactar administrador</p>"
										+ "</div>";
									$("#mensaje").html(alert);									
									
								}
							},

							// Error handling 
							error: function(error) {
								console.log("Error en la solicitud:", error);
							}
						});//AJAX

					}
				},
				// Error handling 
				error: function(error) {
					console.log("Error en la solicitud:", error);
				}
			});//AJAX


		} else {
			alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
				+ "<p>Rut incorrecto!</p>"
				+ "</div>";
			$("#mensaje").html(alert);
		}

	}); //form_login

})