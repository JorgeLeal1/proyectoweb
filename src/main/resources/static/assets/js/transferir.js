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



	$("#btnAgregarContacto").on("click", function() {
		$("#mensaje").html("");
		document.getElementById("AgregarCtaTercero").reset();
	});


	$("#btn_consultar_run").on("click", function() {

		/*
				console.log("/proyectoweb/datos");
				$.ajax({
					url: "/proyectoweb/datos",
					method: "GET",
					success: function(response3) {
						console.log("Mensaje recibido: " + response3.mensaje);
					},
					error: function() {
						console.error("Error al obtener datos");
					}
				});
			
		*/

		let inputRun = $("#run").val();
		let inputNrocuenta = $("#nrocuenta").val();

		if (inputRun.length > 0) {

			if (validaRut(inputRun)) {

				$.ajax({
					url: '/proyectoweb/consultarClientePorRun',
					type: "POST",
					data: { Run: inputRun },
					success: function(response) {
						if (response > 0) {
							//console.log(response);
							$("#mensaje").html("");

							$.ajax({
								url: '/proyectoweb/obtenerCuentaCliente/' + inputRun,
								method: "GET",
								success: function(response) {
									//console.log("getBanco "+response.getBanco);

									$("#nombre1").val(response.nombre1);
									$("#nombre2").val(response.nombre2);
									$("#appaterno").val(response.appaterno);
									$("#apmaterno").val(response.apmaterno);

									$("#nrocuenta").val(response.nroCuenta);
									$("#alias").val(response.alias);
									$("#banco").val(response.banco);
								},
								error: function() {
									console.error("Error al obtener datos");
								}

							});//AJAX

						} else {
							alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
								+ "<p>Run ingresado No existe, favor ingrese otro !</p>"
								+ "</div>";
							//$("#mensaje").html(alert);
							//console.log(response);
							document.getElementById("AgregarCtaTercero").reset();
							$("#run").val(inputRun);
							document.getElementById("run").focus();
						}
					}

				});//AJAX


			} else {
				alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
					+ "<p>Run incorrecto!</p>"
					+ "</div>";
				$("#mensaje").html(alert);

				document.getElementById("AgregarCtaTercero").reset();
				$("#run").val(inputRun);
				document.getElementById("run").focus();
			}


		} else {
			alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
				+ "<p>Campo requerido!</p>"
				+ "</div>";
			$("#mensaje").html(alert);

			document.getElementById("AgregarCtaTercero").reset();
			$("#run").val(inputRun);
			document.getElementById("run").focus();
		}

	});


	$("#btn_agregar_tercero").on("click", function() {

		$("#mensaje").html("");

		const inputRun = $("#run").val();
		const inputNrocuenta = $("#nrocuenta").val();
		const runUsuario = $("#runUsuario").val();

		if ((inputRun.length > 0) && (inputNrocuenta.length > 0)) {

			if (inputRun === runUsuario) {
				alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
					+ "<p>El usuario no puede ser creado en cuentaTercero!</p>"
					+ "</div>";
				$("#mensaje").html(alert);
				document.getElementById("nrocuenta").focus();

			} else {

				$.ajax({
					url: '/proyectoweb/registrarCuentaTercero',
					type: "POST",
					data: { Run: inputRun, NroCuenta: inputNrocuenta },
					success: function(response) {

						//console.log(response);
						alert = "<div class='alert alert-success alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
							+ "<p>" + response + "</p>"
							+ "</div>";
						$("#mensaje").html(alert);
					}
				});
			}

		} else {
			alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
				+ "<p>Campos requeridos!</p>"
				+ "</div>";
			$("#mensaje").html(alert);
			document.getElementById("nrocuenta").focus();
		}

	});


	$("#TablaTercero tr:not(:first)").click(function() {
		// Obtiene el valor del primer <td> en la fila
		let run = $(this).find("td:first").text();
		let cuenta = $(this).find("td:eq(2)").text();

		let saldo_actual = $("#saldo_actual").val();

		$('#transferirModal').modal('show');
		$("#Trun").val(run);
		$("#Tnrocuenta").val(cuenta);
		$("#Tsaldo_actual").val(saldo_actual);

		$("#Tsaldo").val("");

	});


	$('#transferirSaldo').submit(function(e) {
		e.preventDefault();

		let run = $("#Trun").val();
		let cuenta = $("#Tnrocuenta").val();
		let saldo_actual = $("#Tsaldo_actual").val();
		let saldo = $("#Tsaldo").val();


		if(saldo_actual >= saldo){
			$.ajax({
				url: '/proyectoweb/TranferirSaldo',
				type: "POST",
				data: { Run: run, Saldo: saldo },
				success: function(response) {
	
					alert = "<div class='alert alert-success alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
						+ "<p>Transferencia realizada correctamente!.</p>"
						+ "</div>";
					$("#TranferirSaldoMensaje").html(alert);
					$("#Tsaldo").val("");
	
					$("#saldo_actual").val(saldo_actual - saldo);
					$("#Tsaldo_actual").val(saldo_actual - saldo);
	
				}
			});
		}else{
				alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
					+ "<p>Saldo insuficiente!.</p>"
					+ "</div>";
				$("#TranferirSaldoMensaje").html(alert);
		}

	});




})