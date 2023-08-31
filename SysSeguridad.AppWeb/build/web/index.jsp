<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.appweb.utils.*"%>
<% if (SessionUser.isAuth(request) == false) {
         response.sendRedirect("Usuario?accion=login");
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Sistema de Facturación de la Librería</title>

    <!-- Stylesheet -->
    <link href="//themes.materializecss.com/cdn/shop/t/1/assets/startup-materialize.min.css?v=68013164329053048961511298916" rel="stylesheet">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
     <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  

    <style>
        body {
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        .jumbotron {
            background: linear-gradient(to bottom right, #4CAF50, #00BCD4);
            color: white;
            padding: 50px;
            border-radius: 20px;
            text-align: center;
        }

        .jumbotron h1 {
            font-size: 35px;
            margin-bottom: 10px;
        }

        .jumbotron p {
            font-size: 20px;
        }

        .product {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            margin-bottom: 20px;
            position: relative;
            overflow: hidden;
            transition: all 0.3s ease-in-out;
        }

        .product:hover {
          transform: scale(1.05);
          box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
          background-color: gray; /* Cambio a color gris en hover */
}


       .product .description {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            background-color: gray; /* Cambio a color gris en hover */
            color: white;
            padding: 10px;
            display: none;
        }

        .product:hover .description {
            display: block;
        }

        .product img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }

        .product h4 {
            font-size: 24px;
            margin: 10px 0;
            color: black;
        }

        .product p {
            color: #000000;
        }
        
         .team-section {
            background-color: #000000;
            color: #ffffff;
            padding: 60px 0;
            text-align: center;
        }

        .team-heading {
            font-size: 28px;
            margin-bottom: 20px;
        }

        .member-container {
            display: flex;
            justify-content: center;
            gap: 40px;
            margin-top: 40px;
        }

        .member {
            background-color: #ffffff;
            border-radius: 60%;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            padding: 25px;
            text-align: center;
            width: 120px;
            height: 290px;
            transition: transform 0.3s ease-in-out, background-color 0.3s ease-in-out;
        }

        .member img {
            max-width: 80px;
            border-radius: 55%;
            margin-bottom: 10px;
        }

        .member:hover {
            transform: translateY(-5px);
            background-color: #B2DFDB;
        }

        .member p {
            margin-top: 10px;
            color: black;
        }
    </style>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
         <div class="jumbotron">
        <h1>Sistema de Facturación de la Librería</h1>
        <p>Al sistema para aprender a cómo colocarle seguridad a sus aplicaciones web</p> 
    </div>
        <main class="container"> 
        <div class="row">
            <div class="col s12 m4">
                <div class="product">
                    <img src="imagenes/colores.jpeg" alt="Colores">
                    <h4>Colores</h4>
                    <p>Colores de todas las marcas</p>
                </div>
            </div>
            <div class="col s12 m4">
                <div class="product">
                    <img src="imagenes/Plumones.jpeg" alt="Plumones">
                    <h4>Plumones</h4>
                    <p>De todas las marcas de punta pincel normales</p>
                </div>
            </div>
             <div class="col s12 m4">
                    <div class="product">
                        <img src="imagenes/cuaderno .jpeg" alt="cuadernos">
                        <h4>Cuadernos</h4>
                        <p>Cuadernos de todos los tamaños</p>
                    </div>
                </div>
               </main>
                      <section class="library-info" style="padding: 40px 0; background-color: #f0f0f0;">
    <div class="container">
        <div class="row">
            <div class="col s12">
                <div class="info-container">
                    <div class="mission col s12 m6" style="background-color: #00BCD4; padding: 20px; border-radius: 10px; margin-bottom: 40px; color: white;">
                        <i class="material-icons" style="font-size: 36px;">assignment</i>
                        <h3>Misión</h3>
                        <p>Fomentar el amor por la lectura y el aprendizaje, proporcionando a nuestros clientes acceso a recursos culturales y educativos.</p>
                    </div>
                </div>
                <div class="info-container">
                    <div class="vision col s12 m6" style="background-color: #4CAF50; padding: 20px; border-radius: 10px; margin-bottom: 40px; color: white;">
                        <i class="material-icons" style="font-size: 36px;">visibility</i>
                        <h3>Visión</h3>
                        <p>Convertirnos en un centro de aprendizaje integral donde las personas puedan explorar, descubrir y conectarse a través de la literatura y el conocimiento.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="location" style="background-color: #00BCD4; padding: 40px 0; text-align: center;">
    <div class="container">
        <div class="row">
            <div class="col s12">
                <h3 style="color: white;">Ubicación</h3>
                <address>
                    <p style="color: white;">Estamos ubicados en:</p>
                    <p style="color: white;">Dirección: Calle Principal, Número 123</p>
                    <p style="color: white;">Ciudad: Ciudad Libro</p>
                    <p style="color: white;">País: País de los Libros</p>
                </address>
            </div>
        </div>
    </div>
</section>
           <section class="team-section">
        <div class="container">
            <h3 class="team-heading">Equipo de Desarrollo</h3>
            <div class="member-container">
                <div class="member">
                    <img src="ruta-imagen-1.jpg" alt="Miembro 1">
                    <p>Nombre Miembro 1</p>
                </div>
                <div class="member">
                    <img src="imagenes/David.jpeg" alt="Jose David Montoya">
                    <p>Jose David Montoya</p>
                </div>
                <div class="member">
                    <img src="imagenes/Luz.jpeg" alt="Luz Ortiz">
                    <p>Luz Elizabeth Ortiz</p>
                </div>
                <div class="member">
                    <img src="imagenes/melissa.jpeg" alt="Jackeline">
                    <p>Jackeline Melissa Cabrera</p>
                </div>
                <div class="member">
                    <img src="ruta-imagen-5.jpg" alt="Miembro 5">
                    <p>Nombre Miembro 5</p>
                </div>
            </div>
        </div>
    </section>

<footer style="background-color: #333; color: white; padding: 20px;">
    <div class="container">
        <div class="row">
            <div class="col s12">
                <p style="text-align: center;">&copy; 2023 Tu Librería. Todos los derechos reservados.</p>
            </div>
        </div>
    </div>
</footer>

        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>

