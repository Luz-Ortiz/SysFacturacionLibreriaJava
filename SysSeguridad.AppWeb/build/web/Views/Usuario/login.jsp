<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Pacifico', cursive;
            background: linear-gradient(45deg, #ff6b6b, #ffb88c, #ff6b6b, #ffb88c); /* Cambio de colores en el fondo */
            background-size: 400% 400%;
            animation: gradient 15s ease infinite;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            overflow: hidden;
        }

        .login-container {
            background-color: rgba(255, 255, 255, 0.15); /* Cambio de tono de color */
            border-radius: 10px;
            padding: 20px;
            text-align: center;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            position: relative;
            max-width: 600px; /* Aumento el ancho máximo del contenedor de registro */
            width: 100%; /* Ajusto el ancho al 100% para que sea responsivo */
            display: flex; /* Cambio la disposición a flexbox */
            align-items: center; /* Centrar verticalmente */
            justify-content: center; /* Centrar horizontalmente */
            margin: 20px; /* Espacio alrededor del cuadro */
        }

        .left-section {
            flex: 1;
            margin-right: 20px; /* Separación de la imagen respecto al cuadro de ingreso de datos */
        }

        .right-section {
            flex: 1;
            color: white;
        }

        h2 {
            color: white;
            margin-bottom: 20px;
            font-size: 48px;
            font-weight: bold;
            animation: neon 1s linear infinite alternate, enlarge 2s ease-in-out infinite alternate, swing 2s ease infinite;
            font-family: 'Adreena Script Demo Regular', cursive;
        }

        /* Estilo de los campos de entrada */
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 20px;
            font-size: 24px;
            border: 1px solid #ccc;
            border-radius: 3px;
            outline: none;
        }

        /* Estilo del botón */
        button {
            background-color: transparent;
            color: #ffffff; /* Cambio el color del texto del botón a blanco */
            border: 2px solid transparent; /* Borde blanco */
            padding: 15px 30px;
            border-radius: 3px;
            cursor: pointer;
            transition: border-color 0.3s, color 0.3s, box-shadow 0.3s;
            font-size: 24px;
            font-weight: bold;
            letter-spacing: 1px;
            text-transform: uppercase;
            position: relative;
        }

        /* Estilo del botón al pasar el cursor por encima */
        button:hover {
            border-color: #fff; /* Cambia el color del borde al pasar el cursor */
            color: #ff6b6b; /* Cambio el color del texto al pasar el cursor a un color más cálido */
            box-shadow: 0 0 20px rgba(255, 107, 107, 0.6); /* Agrega sombra al pasar el cursor */
            transform: scale(1.05);
            animation: neon-border 0.5s linear infinite alternate;
        }

        /* Animación de borde neon */
        @keyframes neon-border {
            0% {
                border-color: #fff;
            }
            100% {
                border-color: #ffb88c;
            }
        }

        /* Animación de balanceo (swing) para el h2 */
        @keyframes swing {
            0% {
                transform: rotate(0deg);
            }
            50% {
                transform: rotate(3deg);
            }
            100% {
                transform: rotate(0deg);
            }
        }

        /* Animación de la imagen */
        .image-container {
            flex: 1;
            overflow: hidden;
        }

        .image-container img {
            width: 100%;
            max-height: 100%;
            object-fit: cover;
            transition: filter 0.3s ease; /* Transición de filtro para el cambio de tono */
        }

        /* Cambio de tono de la imagen al pasar el cursor */
        .image-container:hover img {
            filter: grayscale(100%) brightness(70%); /* Cambio de tono de la imagen */
        }

        @keyframes gradient {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }
    </style>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container login-container">
        <div class="left-section image-container">
            <img src="imagenes/papeleria.jpg" alt="Imagen de fondo">
        </div>
        <div class="right-section">
            <h2>Bienvenidos al Sistema de Facturación</h2>
            <form action="Usuario?accion=login" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="input-container">
                    <label for="txtLogin">Login</label>
                    <input id="txtLogin" type="text" name="login" required class="validate" maxlength="25">  
                </div>
                <div class="input-container">
                    <label for="txtPassword">Password</label>
                    <input id="txtPassword" type="password" name="password" required class="validate" minlength="5" maxlength="32">  
                </div>
                <!-- Botón de ingresar -->
                <button type="submit">Ingresar</button>
                <% if (request.getAttribute("error") != null) { %>
                <div class="row">
                    <div class="col l12 s12">
                        <span style="color:red"><%= request.getAttribute("error") %></span>                                              
                    </div>
                </div>
                <%}%>
            </form>          
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />  
</body>
</html>
