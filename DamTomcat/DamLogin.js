function enviarGet(){
    let http = new XMLHttpRequest();

    let email = document.getElementById("email").value;
    let pass = document.getElementById("pass").value;

    
// Validar campos vacíos
  if (email === "" || pass === "") {
    document.getElementById("Resultat").innerHTML = 'Por favor, complete todos los campos.';
    return;
  }

  http.open("GET","http://localhost:3006/Dam_tomcat/Login?email="+email+"&pass="+pass,true);
    /**metodo ,direccion y asincrono/ */
    
    
  http.onreadystatechange = function() {
      if (this.readyState === 4 && this.status === 200){
        var response = http.responseText;

        if (response == "Error: No se pudo iniciar sesión") {
          // El login no ha sido correcto
          document.getElementById("Resultat").innerHTML = 'Login erróneo';
        } else {
          // El login ha sido exitoso, obtener el código de sesión
          var sessionCode = response;

          // Almacenar el código de sesión en sessionStorage
          sessionStorage.setItem('session', sessionCode);
          sessionStorage.setItem('email', email);

          // Redireccionar a la página "Gestio.html"
          window.location.href='Gestio.html';
        }
      } else {
        console.error('Error en la petición al backend:', http.status);
      }
    }

    http.send();
}




function enviarPost(){
    let http = new XMLHttpRequest();

    let email = document.getElementById("email").value;
    let pass = document.getElementById("pass").value;
    http.open("POST","http://localhost:3006/Dam_tomcat/Login",true);
    http.setRequestHeader("content-Type","application/x-www-form-urlencoded");
    http.send("email="+email+"&pass="+pass);
    

}



/** funcion islogged(email,session)   lastlog*/