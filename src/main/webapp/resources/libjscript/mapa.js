var geocoder;
var map;
var marker;
var circle;

function initialize() {
	var latlng = new google.maps.LatLng(-18.8800397, -47.05878999999999);
	var options = {
		zoom: 5,
		center: latlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	
	map = new google.maps.Map(document.getElementById("mapa"), options);
	
	geocoder = new google.maps.Geocoder();
	
	marker = new google.maps.Marker({
		map: map,
		draggable: false,
	});
	
	marker.setPosition(latlng);

	/*var populationOptions = {
      strokeColor: '#FF0000',
      strokeOpacity: 0.8,
      strokeWeight: 2,
      fillColor: '#FF0000',
      fillOpacity: 0.35,
      map: map,
      center: latlng,
      radius: 25000
    };

    // Add the circle for this city to the map.
    circle = new google.maps.Circle(populationOptions);*/
}

var jqy=jQuery.noConflict();
jqy(document).ready(function () {

	initialize();
	
	function carregarNoMapa(endereco) {
		geocoder.geocode({ 'address': endereco + ', Brasil', 'region': 'BR' }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();
					var rad = 50000;
		
					jqy('.txtEndereco').val(results[0].formatted_address);
					//var ender = jqy('#txtEndereco').val();
					jqy('.txtLatitude').val(latitude);
                   	jqy('.txtLongitude').val(longitude);
                   	jqy('.txtPegaEndereco').val(jqy('.txtEndereco').val());
                   	
                   	//Verifica se o componente existe na pagina.
                   	if(jqy('.valRadius').length){
                   		rad = jqy('.valRadius').val() * 1000;
                   		jqy('#txtKm').text(rad);
                   	}
                   	               	
					var location = new google.maps.LatLng(latitude, longitude);
					marker.setPosition(location);
					map.setCenter(location);
					circle = new google.maps.Circle({
			   		 	strokeColor: '#FF0000',
						strokeOpacity: 0.8,
						strokeWeight: 2,
						fillColor: '#FF0000',
						fillOpacity: 0.35,
						map: map,
						center: location,
						radius: rad
					});
					map.setZoom(8);
				}
			}
		});
	}
	//Ao clicar no botão (Mostrar no mapa) btnEndereco
	jqy("#btnEndereco").click(function() {
		if(jqy(this).val() != "")
			carregarNoMapa(jqy(".txtEndereco").val());
	});
	//Ao perder o foco do campo endereco
	jqy(".txtEndereco").blur(function() {
		if(jqy(this).val() != "")
			carregarNoMapa(jqy(this).val());
	});
	// Ao arrastar o marcador
	google.maps.event.addListener(marker, 'drag', function () {
		geocoder.geocode({ 'latLng': marker.getPosition() }, function (results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0]) {  
					var latitude = results[0].geometry.location.lat();
					var longitude = results[0].geometry.location.lng();

					jqy('.txtEndereco').val(results[0].formatted_address);
					jqy('#txtLatitude').val(latitude);
                   	jqy('#txtLongitude').val(longitude);

					/*var location = new google.maps.LatLng(latitude, longitude);
					circle = new google.maps.Circle({
			   		 	strokeColor: '#FF0000',
						strokeOpacity: 0.8,
						strokeWeight: 2,
						fillColor: '#FF0000',
						fillOpacity: 0.35,
						map: map,
						center: location,
						radius: 25000
					});
					*/
				}
			}
		});
	});
	// Ao usar o auto complete e dar enter;
	jqy(".txtEndereco").autocomplete({
		source: function (request, response) {
			geocoder.geocode({ 'address': request.term + ', Brasil', 'region': 'BR' }, function (results, status) {
				response(jqy.map(results, function (item) {
					return {
						label: item.formatted_address,
						value: item.formatted_address,
						latitude: item.geometry.location.lat(),
          				longitude: item.geometry.location.lng()
					};
				}));
			});
		},
		select: function (event, ui) {
			jqy(".txtLatitude").val(ui.item.latitude);
    		jqy(".txtLongitude").val(ui.item.longitude);
			var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
			var rad = 50000;
			
           	if(jqy('.valRadius').length){
           		rad = jqy('.valRadius').val() * 1000;
           		jqy('.txtKm').text(rad);
           	}
           	
			marker.setPosition(location);
			map.setCenter(location);
			circle = new google.maps.Circle({
			 	strokeColor: '#FF0000',
				strokeOpacity: 0.8,
				strokeWeight: 2,
				fillColor: '#FF0000',
				fillOpacity: 0.35,
				map: map,
				center: location,
				radius: rad
			});			
			map.setZoom(8);
		}
	});
	
	jqy(".txtEndereco").blur(function(event) {
		event.preventDefault();
		
		var endereco = jqy(".txtEndereco").val();
		var latitude = jqy(".txtLatitude").val();
		var longitude = jqy(".txtLongitude").val();
		
		//alert("Endereço: " + endereco + "\nLatitude: " + latitude + "\nLongitude: " + longitude);
	});

});