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
								url: '/proyectoweb/obtenerCuentaCliente/'+inputRun,
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
								} ,           
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
								
						}
					}

				});//AJAX


			} else {
				alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
					+ "<p>Run incorrecto!</p>"
					+ "</div>";
				$("#mensaje").html(alert);
				document.getElementById("run").focus();
			}


		} else {
			alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
				+ "<p>Campo requerido!</p>"
				+ "</div>";
			$("#mensaje").html(alert);
			document.getElementById("run").focus();
		}

	});


	$("#btn_agregar_tercero").on("click", function() {

		$("#mensaje").html("");
		
		const inputRun = $("#run").val(); 
		const inputNrocuenta = $("#nrocuenta").val(); 
		
		if (inputRun.length > 0 && inputNrocuenta.length > 0) {
			console.log(inputRun.length);
			console.log(inputNrocuenta.length);
			
			$.ajax({
				url: '/proyectoweb/registrarCuentaTercero',
				type: "POST",
				data: { Run: inputRun, NroCuenta:inputNrocuenta },
				success: function(response) {
					if (response > 0) {
						//console.log(response);
						$("#mensaje").html("");
					}
				}
			});
			
		}else{
			alert = "<div class='alert alert-danger alert-dismissible fade show  text-center' role='alert' style='padding-top: 0px; padding-bottom: 0px; '>"
				+ "<p>Campos requeridos!</p>"
				+ "</div>";
			$("#mensaje").html(alert);
			document.getElementById("nrocuenta").focus();

		}

		
	});



})