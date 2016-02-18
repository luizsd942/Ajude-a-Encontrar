/***************************LOGIN****************************/
var jqy=jQuery.noConflict();
jqy(document).ready(function() { // prevent the facebook buttons to appears on mobiles, if they can´t make the facebook communiation
	if(navigator.userAgent.match(/Windows\sPhone/i)) {
		jqy("#cadastroFB").hide();
		jqy("#form-signup-facebook").hide();
		jqy("#loginFB").hide();
	}
});
jqy(document).ready(function(){
	jqy("#cadastroFB").on("click", this, function(){
		jqy("#cadastroFB").removeClass("fade");
		jqy("#cadastroEMAIL").addClass("fade");
		jqy("#form-login-cadastro").closest(".mail-form").slideUp();
		jqy("#facebook-phone").slideDown();
		jqy("#facebook-phone .error-message").hide();
	});

	jqy("#cadastroEMAIL").on("click", this, function(){
		jqy("#cadastroEMAIL").removeClass("fade");
		jqy("#cadastroFB").addClass("fade");
		jqy("#facebook-phone").slideUp();
		jqy("#errosDeValidacaoCadastroLogin").hide();
		jqy("#form-login-cadastro").closest(".mail-form").slideDown();
		jqy("#name").removeClass("disabled").prop('disabled', false);
		jqy("#email").removeClass("disabled").prop('disabled', false);
		jqy("#password").removeClass("disabled").prop('disabled', false);
	});
});

function isValidEmailAddress(emailAddress) {
	var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
	return pattern.test(emailAddress);
};

function verificaDadosLogin(){
	clearErrors();

	var email = jqy("#email_login").val();
	var password = jqy("#password_login").val();
	var errorMsg = "";
	var FB = jqy("#form-login #fb_id").val();

	if( jqy("#fb_id").val() != null && jqy("#fb_id").val() != ""){
		return true;
	}

	if(email == ""){
		errorMsg = "Informe seu e-mail...";
		errorInput = "email_login";
	} else if(!isValidEmailAddress(email)){
		errorMsg = "E-mail Inválido.";
		errorInput = "email_login";
	} else if(FB == null || FB == "") {
		if(password == "" || password == "Digite sua senha"){
			errorMsg = "Informe sua senha."
			errorInput = "password_login";
		}
	}
	jqy("#form-login input[type=submit]").removeClass("loadingDots");
	if (errorMsg != ''){
		jqy("#errosDeValidacaoLogin").html(errorMsg);
		jqy("#errosDeValidacaoLogin").show();
		jqy("#" + errorInput).attr('class', 'error');
		return false;
	}

	return true;
};

function onlyNumbersValue(e){
	var tecla=(window.event)?event.keyCode:e.which;
	if((tecla>47 && tecla<58)) return true;
	else{
		if (tecla==8 || tecla==0) return true;
		else  return false;
	}
}

//
//function clearErrors(){
//	jqy('input[class=error]').attr("class", "");
//	jqy("#errosDeValidacaoLogin").hide();
//}
//
//jqy(document).ready(function() {
//	jqy.smartbanner({
//		title: 'iFood',
//		author: 'http://www.ifood.com.br',
//		icon: _ctxCDN+'/css/images/ifood_app.png',
//		daysHidden: 15, // Duration to hide the banner after being closed (0 = always show banner)
//		daysReminder: 90 // Duration to hide the banner after "VIEW" is clicked (0 = always show banner)
//	});
//
//	var bannerHeight = jqy("#smartbanner").height();
//	if(jqy("#smartbanner").length > 0){
//		jqy(".header").css("top", bannerHeight);
//		jqy(".mobile-nav").css("top", bannerHeight);
//		jqy(".mobile-nav").css("height", "-="+bannerHeight);
//
//
//		jqy("#smartbanner").css("position", "fixed");
//		jqy("#smartbanner .sb-close").click(function(){
//			jqy(".header").css("top", "0");
//			jqy(".mobile-nav").css("top", "0");
//		});
//		jqy("#smartbanner .sb-button").click(function(){
//			jqy(".header").css("top", "0");
//			jqy(".mobile-nav").css("top", "0");
//		});
//	}else{
//		jqy(".header").css("top", "0");
//		jqy(".mobile-nav").css("top", "0");
//		jqy(".mobile-nav").css("height", "+="+bannerHeight);
//	}
//
//	if(jqy('.select_cadastro').length >0){
//		jqy('.filtro_cadastro').hide();
//		jqy('.select_cadastro').click(function(event){
//			jqy('.button_email').hide();
//			event.stopPropagation();
//			jqy('.filtro_cadastro').slideToggle('fast');
//		});
//	}
//
//	jqy("#form-login").submit(function(event) {
//		var ok = verificaDadosLogin();
//		var btn = jqy("#form-login input[type=submit]");
//		initLoadingAnimationDots(btn);
//		if (!ok) {
//			btn.removeClass("loadingDots");
//			event.preventDefault();
//		}
//	});
//
//	var url = window.location.search.substring(1);
//	if (url == "erro=1"){
//		if (_gaq) {
//			if (jqy("#fb_id").val()) {
//				_gaq.push(['_trackEvent', 'Acesso', 'Facebook', 'Falha']);
//			} else {
//				_gaq.push(['_trackEvent', 'Acesso', 'Email', 'Falha']);
//			}
//		}
//
//		jqy("#errosDeValidacaoLogin").html("Login/Senha Inválidos.");
//		jqy("#errosDeValidacaoLogin").show();
//	} else {
//		jqy("#errosDeValidacaoLogin").hide();
//	}
//
//});
//
///**************************CADASTRO*****************************/
//
//function verificaClienteCadastro(){
//	var name = jqy("#name").val();
//	var email = jqy("#email").val();
//	var password = jqy("#password").val();
//	var check_password = jqy('#password_check').val();
//	var FB = jqy("#facebook-phone").data("value");
//	var termos = jqy("#termos");
//	var termosFb = jqy("#termosFb");
//
//	var errorMsg = "";
//	var errorInput = "";
//
//	if (!termos.hasClass("checked")) {
//		errorMsg = "É necessário aceitar os termos de uso";
//	}else if(name == "" || name == 'Digite seu nome'){
//		errorMsg = "Nome completo é obrigatório"
//		errorInput = "name";
//	}else if(!isValidEmailAddress(email)){
//		errorMsg = "E-mail inválido.";
//		errorInput = "email";
//	}else if(FB == null || FB == ""){
//		if(password=="" || password=="Digite sua senha"){
//			errorMsg = "Senha é obrigatório";
//			errorInput = "password";
//		}else if(password.length<6){
//			errorMsg = "Senha deve conter, no mínimo, 6 caracteres."
//			errorInput = "password";
//		}else if(password !== check_password){
//			errorMsg = "Senha e confirmação devem ser iguais."
//			errorInput = "password";
//		}
//	}
//
//	var intRegex = /^\d+$/;
//
//	if (errorMsg == ""){
//		var encontrado = false;
//		jqy(".DDD").each(function(){
//			var dddsValidos = [11,12,13,14,15,16,17,18,19,21,22,24,27,28,31,32,33,34,35,37,38,41,42,43,44,45,46,47,48,49,51,53,54,55,61,62,63,64,65,66,67,68,69,71,73,74,75,77,79,81,82,83,84,85,86,87,88,89,91,92,93,94,95,96,97,98,99];
//			var ddd = parseInt(jqy(this).val());
//			for(var i = 0; i < dddsValidos.length; i++){
//				if(ddd == dddsValidos[i]){
//					encontrado = true;
//					break;
//				}
//			}
//		});
//		if(!encontrado){
//			errorMsg = "DDD inválido.";
//			errorInput = "DDD, #DDDFb";
//		}
//		jqy(".telefone").each(function(){
//			if(jqy(this).val().length < 8 || jqy(this).val().length > 9 || jqy(this).val() == "Digite seu telefone" || !intRegex.test(jqy(this).val())){
//				errorMsg = "Telefone inválido.";
//				errorInput = "phone";
//			}
//		});
//	}
//
//	jqy('.error').removeClass("error");
//	if (errorMsg != ""){
//		jqy("#errosDeValidacaoCadastroLogin").html(errorMsg);
//		jqy("#facebook-phone .error-message").html(errorMsg);
//		jqy("#facebook-phone .error-message").show();
//		jqy("#errosDeValidacaoCadastroLogin").show();
//		jqy("#" + errorInput).addClass('error');
//		if(errorMsg === "Senha e confirmação devem ser iguais."){
//			jqy('#password_check').addClass('error');
//		}
//		return false;
//	}
//
//	return true;
//};
//
//jqy(document).ready(function() {
//	if( jqy("#FB_id").val() == ""){
//		jqy("#FB_id").remove();
//	}
//
//	jqy("#form-login-cadastro").submit(function( event ) {
//		event.preventDefault();
//		var btn =jqy("#form-login-cadastro input[type=submit]");
//		var ok = verificaClienteCadastro();
//		if (ok) {
//			var form = jqy("#form-login-cadastro").serialize();
//			jqy.ajax({
//				type : "POST",
//				data : form,
//				dataType : "json",
//				url : _ctx + URL_ENTER + URL_SAVE,
//				beforeSend: initLoadingAnimationDots(btn)
//			}).done(function(data) {
//				if (data.code != "00"){
//					if (_gaq) {
//						if (jqy("#fb_id").val()) {
//							_gaq.push(['_trackEvent', 'Cadastro', 'Facebook', 'Falha']);
//						} else {
//							_gaq.push(['_trackEvent', 'Cadastro', 'Email', 'Falha']);
//						}
//					}
//
//					jqy("#facebook-phone .error-message").show();
//					jqy("#errosDeValidacaoCadastroLogin").show();
//					jqy("#errosDeValidacaoCadastroLogin").html(data.message);
//					jqy("#facebook-phone .error-message").html(data.message);
//					//jqy("#errosDeValidacaoCadastroLogin").show();
//					if (data.code = "100") {
//						var faceBookMsg = "Esse facebook já está cadastrado";
//						console.log(faceBookMsg);
//						jqy("#facebook-phone .error-message").html(faceBookMsg);
//					}
//				} else {
//					if (_gaq) {
//						if (jqy("#fb_id").val()) {
//							_gaq.push(['_trackEvent', 'Cadastro', 'Facebook', 'Sucesso']);
//						} else {
//							_gaq.push(['_trackEvent', 'Cadastro', 'Email', 'Sucesso']);
//						}
//					}
//
//					var formLogin = jqy("#form-login");
//					jqy("#email_login", formLogin).val(jqy("#email","#form-login-cadastro").val());
//					jqy("#password_login", formLogin).val(jqy("#password","#form-login-cadastro").val());
//					formLogin.submit();
//				}
//			}).fail(function(data){
//				jqy("#errosDeValidacaoCadastroLogin").html("Erro: "+data.message);
//				jqy("#facebook-phone .error-message").html("Erro: "+data.message);
//			}).always(function(){btn.removeClass("loadingDots");});
//		}
//	});
//	//Exibe o pedido de dar preferencia pelo numero de celular
//	jqy(".medium").focus(function(){
//		jqy(this).closest(".row").find(".warningCel").slideDown();
//	});
//});
//
///**********************FACEBOOK**************************/
///**
// * Carrega o sdk do facebook
// */
//(function(d, s, id) {
//	var js, fjs = d.getElementsByTagName(s)[0];
//	if (d.getElementById(id)) return;
//	js = d.createElement(s); js.id = id;
//	js.src = "//connect.facebook.net/en_US/sdk.js";
//	fjs.parentNode.insertBefore(js, fjs);
//}(document, 'script', 'facebook-jssdk'));
//
//window.fbAsyncInit = function() {
//	facebookAppId = jqy('#loginFB').attr('data-facebookId');
//
//	if(!facebookAppId || facebookAppId == "") {
//		facebookAppId = FACEBOOK_APP_ID;
//	}
//
//	FB.init({
//		appId      : facebookAppId,
//		cookie     : true,  // enable cookies to allow the server to access
//		// the session
//		xfbml      : true,  // parse social plugins on this page
//		version    : 'v2.0' // use version 2.0
//	});
//	FB.getLoginStatus(fb_status);
//};
jqy(document).ready(function(){
	jqy(".facebook-access").addClass("loadingDots");

	if (window.location.href.indexOf("fbConnect=true") > 0) {
		FB.getLoginStatus(fb_connect);
	} else if (window.location.href.indexOf("fbSignUp=true") > 0) {

		if (localStorage) {
			jqy("#DDDFb").val(localStorage.getItem("DDDFb"));
			jqy("#phoneFb").val(localStorage.getItem("phoneFb"));
			if (localStorage.getItem("newsEmailFb") == "true") jqy("#newsEmailFb").addClass("checked");
			if (localStorage.getItem("termosFb") == "true") jqy("#termosFb").addClass("checked");

			updateFbSignup();
		}

		jqy("#cadastroFB").removeClass("fade");
		jqy("#cadastroEMAIL").addClass("fade");
		jqy("#form-login-cadastro").closest(".mail-form").slideUp();
		jqy("#facebook-phone").slideDown();

		FB.getLoginStatus(fb_signup);
	}
});
//
///**
// * Chamado quando o js do facebook é inicializado.
// * Prepara a página para caso o usuário já esteja logado
// * ou para caso ele ainda precise logar no facebook.
// * @param response - dados recebidos do facebook
// */
//function fb_status(response){
//	if(response.status !== 'connected'){
//		if( navigator.userAgent.match('CriOS') ) {
//			jqy('#loginFB').attr('href', "https://www.facebook.com/dialog/oauth?client_id=" + facebookAppId + "&redirect_uri=" + encodeURIComponent(document.location.href + "?fbConnect=true") + "&scope=email,public_profile");
//			jqy('#cadastrarFB').attr('onclick', "fb_signup_crios(event); return false;");
//		} else {
//			jqy('#loginFB').attr('onclick', "FB.login(fb_connect, {scope: 'public_profile,email'})");
//			jqy('#cadastrarFB').attr('onclick', "FB.login(fb_signup, {scope: 'public_profile,email'}); return false;");
//		}
//	}else{
//		jqy('#loginFB').click(function(event) {
//			event.preventDefault();
//			FB.getLoginStatus(fb_connect);
//		});
//		jqy('#cadastrarFB').click(function(event) {
//			event.preventDefault();
//			FB.getLoginStatus(fb_signup);
//		});
//	}
//	jqy('#cadastrarFB').click(updateFbSignup);
//	jqy(".facebook-access").removeClass("loadingDots");
//}
//
//function updateFbSignup(){
//	var fbddd = jqy("#DDDFb").val();
//	var fbtel = jqy("#phoneFb").val();
//
//	jqy("#fb_ddd").val(fbddd);
//	jqy("#fb_tel").val(fbtel);
//	jqy("#form-login-cadastro #DDD").val(fbddd);
//	jqy("#form-login-cadastro #phone").val(fbtel);
//
//	var newsFb = jqy("#newsEmailFb");
//	if (newsFb.hasClass("checked")) {
//		jqy("#newsEmail").addClass("checked");
//		jqy("input[name=newsEmail]").attr("checked","checked");
//	}else{
//		jqy("#newsEmail").removeClass("checked");
//		jqy("input[name=newsEmail]").removeAttr("checked");
//	}
//
//	var termosFb = jqy("#termosFb");
//	if (termosFb.hasClass("checked")) {
//		jqy("#termos").addClass("checked");
//		jqy("input[name=termos]").attr("checked","checked");
//	}else{
//		jqy("#termos").removeClass("checked");
//		jqy("input[name=termos]").removeAttr("checked");
//	}
//}
//
//function fb_signup_crios(event){
//
//	event.preventDefault();
//
//	if (localStorage) {
//		localStorage.setItem("DDDFb",jqy("#DDDFb").val());
//		localStorage.setItem("phoneFb",jqy("#phoneFb").val());
//		localStorage.setItem("newsEmailFb",jqy("#newsEmailFb").hasClass("checked"));
//		localStorage.setItem("termosFb",jqy("#termosFb").hasClass("checked"));
//	}
//
//	window.location.href = "https://www.facebook.com/dialog/oauth?client_id=" + facebookAppId + "&redirect_uri=" + encodeURIComponent(document.location.href + "?fbSignUp=true") + "&scope=email,public_profile";
//}
//
//function fb_data_connect(response){
//	jqy('#loginFB').addClass("loadingDots");
//	jqy('#email_login_fb').val(response.email);
//
//	jqy("#form-login-fb").submit();
//}
//
//function fb_data_signup(response){
//	jqy("#fb_username").val(response.username);
//	jqy("[name='j_username']").val(response.email);
//	jqy("#fb_email").val(response.email);
//	jqy("#fb_id").val(response.id);
//	jqy("#fb_gender").val(response.gender);
//
//	jqy("#form-login-cadastro #name").val(response.name);
//	jqy("#form-login-cadastro #email").val(response.email);
//
//	jqy("#form-login-cadastro").submit();
//
//	jqy("#form-signup-facebook input[type=submit]").removeClass("loadingDots");
//}
//
//function fb_connect(response){
//	if(response.status === 'connected'){
//		jqy('#password_login_fb').val(response.authResponse.accessToken);
//		jqy("[name='fb_access_token']").val(response.authResponse.accessToken);
//		FB.api('/me', {fields: 'id,email,name'}, fb_data_connect);
//	} else if (_gaq) {
//		_gaq.push(['_trackEvent', 'Acesso', 'Facebook', 'Cancela']);
//	}
//}
//
//function fb_signup(response){
//	if(response.status === 'connected'){
//		jqy("#form-login-cadastro #password").val(response.authResponse.accessToken);
//		jqy("#form-login-cadastro #password_check").val(response.authResponse.accessToken);
//		jqy("[name='fb_access_token']").val(response.authResponse.accessToken);
//		FB.api('/me', {fields: 'id,email,name'}, fb_data_signup);
//	} else if (_gaq) {
//		_gaq.push(['_trackEvent', 'Cadastro', 'Facebook', 'Cancela']);
//	}
//}
//
///*********************Esqueci minha senha********************/
//jqy(document).ready(function(){
//	jqy("#esqueceuSenha").on("click", function(){
//		senha.popinEsqueceu();
//	});
//});