<!DOCTYPE html>
<html>
    <head>
        <title>Detecção Facial</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@700&display=swap" rel="stylesheet">    
        <script type="text/javascript">
		    function processImage() {
		        var subscriptionKey = document.getElementById("inputKey").value;
		        var aplicationUrl = document.getElementById("inputUrl").value;
		        //uriBase = url da sua aplicação  + serviço selecionado
		        var uriBase = aplicationUrl + "/face/v1.0/detect";
		    
		        // Parametros da requisição:
		        // idade, genero, posição da cabeça, sorriso, pelos faciais, oculos, etc.
				var params = {
				    "detectionModel": "detection_01",
				    "returnFaceAttributes": "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise",
				    "returnFaceId": "true"
				};
						    
		        // Mostra a imagem passada pelo usuario.
		        var sourceImageUrl = document.getElementById("inputImage").value;
		        document.querySelector("#sourceImage").src = sourceImageUrl;
		    
		        // Faz a chamada da API.
		        $.ajax({
		        	//Cria a url para qual a chamada sera feita a partir da uriBase e dos parametros
		            url: uriBase + "?" + $.param(params),
		    
		            /// Corpo da requisição (contem o link da imagem disponibilizada).
		            beforeSend: function(xhrObj){
		                xhrObj.setRequestHeader("Content-Type","application/json");
		                xhrObj.setRequestHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
		            },
		    
		            type: "POST",
		    
		            // Corpo da requisição (contem o link da imagem disponibilizada).
		            data: '{"url": ' + '"' + sourceImageUrl + '"}',
		        })
		    	//Caso tudo ocorra corretamente formata o JSON e o mostra na tela.
		        .done(function(data) {
		            
		            $("#responseTextArea").val(JSON.stringify(data, null, 2));
		        })
		    	//Caso aconteça algum problema o erro printa na tela.
		        .fail(function(jqXHR, textStatus, errorThrown) {
		            var errorString = (errorThrown === "") ?
		                "Error. " : errorThrown + " (" + jqXHR.status + "): ";
		            errorString += (jqXHR.responseText === "") ?
		                "" : (jQuery.parseJSON(jqXHR.responseText).message) ?
		                    jQuery.parseJSON(jqXHR.responseText).message :
		                        jQuery.parseJSON(jqXHR.responseText).error.message;
		            alert(errorString);
		        });
		    };
		</script>
		<!-- Estilização da pagina -->
		<style>
           
			html, body {
			    max-width: 100%;
			    overflow-x: hidden;
			}
			html{
				width: 100%;
				padding: 0;
				margin: 0;
			}
			body{
				background-color: #01161e;
				width: 100%;
				padding: 0;
				margin: 0;
			}
			#titulo_princial{
				font-family: 'Ubuntu', sans-serif;
                
				font-size: 40pt;
				width: 100%;
				background-color:#aec3b0;
				padding: 30pt;
				margin: 0;
				color: #01161e;
			}
            
			#corpo{
				max-width: 1000px;
  				margin-left: 40px;
                margin-top: 10px;
  				font-family: 'Open Sans', sans-serif;
  				padding-top: 10pt;
                color: white;
			}
            #titulos{
                background-color:#aec3b0 ;
                color: #01161e;
                padding: 5px;
                border-radius: 5px;
                margin-top: 1000px;
                
            }
           
			#entradas{
				display: flex;
				flex-direction: column;
				width: 50%;
				margin-left: 60px;
			}
			.entCampo{
				display: flex;
				flex-direction: row;
				align-items: center;
				margin: 5pt 0;
			}
			.entTexto{
				width: 50%;
				margin: 0;
			}
			.entInput{
				width: 50%;
				height: 25pt;
				margin-left: 0;
				box-sizing: border-box;
    			border: 1px solid #4c4c51;
   				color: #4c4c51;
    			line-height: 1.2;
    			font-family: 'Roboto', sans-serif;
			}
			#entButton{
				background-color: #aec3b0;
    			border: 2   px solid black;
    			border-radius: 4px;
    			color: black;
    			padding: 5px 8px;
    			max-width: 120pt;
    			margin: auto;
    			margin-top: 5pt;
			}
			textarea{
				background-color: rgba(174,195 ,176 , 12);
			}
            p{
                color: #fff;
            }
		</style>
    </head>
    <!-- Estilização corpo da pagina -->
    <body>
    	<h1 id = "titulo_princial">Análise Facial com Azure </h1>
	    	<div id = "corpo">
			Entre com os dados do Azure e uma imagem a ser analisada. Quando estiver pronto clique em "Analisar Imagem" :0 .<br><br>
			<div id = "entradas">
				<div class="entCampo">
					<p class="entTexto">URL da aplicação no Azure:</p> 
					<input class="entInput" type="text" name="inputUrl" id="inputUrl"/>
				</div>
				<div class="entCampo">
					<p class="entTexto">Chave da aplicação:</p> 
					<input class="entInput" type="password" name="inputKey" id="inputKey"/>
				</div>
				<div class="entCampo">
					<p class="entTexto">URL da Imagem para ser analisada:</p> 
					<input class="entInput" type="text" name="inputImage" id="inputImage"/>
				</div>
				<button id="entButton" onclick="processImage()">Analisar Imagem</button><br><br>
			</div>
			<div id="wrapper" style="width:1020px; display:table;">
	
			    <div id="imageDiv" style="width:420px; display:table-cell;">
			        <span id="titulos">Imagem fonte:</span>
                    <br><br>
			        <img id="sourceImage" width="400" />
			    </div>
                <div id="jsonOutput" style="width:600px; display:table-cell;">
			        <span id="titulos">Resposta:</span>
                    <br><br>
			        <textarea id="responseTextArea" class="UIInput"
			            style="width:580px; height:400px;"></textarea>
			    </div>
			</div>
		</div>
    </body>
</html>