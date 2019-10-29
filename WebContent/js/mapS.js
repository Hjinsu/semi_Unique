
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}, true);

// 마커를 담을 배열입니다
	var markers = [];

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places();  

	// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});

	// 키워드로 장소를 검색합니다
	searchPlaces();

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 키워드 검색을 요청하는 함수입니다
	function searchPlaces() {

	    var keyword = document.getElementById('keyword').value;

	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('키워드를 입력해주세요!');
	        return false;
	    }

	    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	    ps.keywordSearch( keyword, placesSearchCB); 
	}

	// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {

	        // 정상적으로 검색이 완료됐으면
	        // 검색 목록과 마커를 표출합니다
	        displayPlaces(data);

	        // 페이지 번호를 표출합니다
	        displayPagination(pagination);

	    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

	        alert('검색 결과가 존재하지 않습니다.');
	        return;

	    } else if (status === kakao.maps.services.Status.ERROR) {

	        alert('검색 결과 중 오류가 발생했습니다.');
	        return;

	    }
	}

	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

	    var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
	    
	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	    removeAllChildNods(listEl);

	    // 지도에 표시되고 있는 마커를 제거합니다
	    removeMarker();
	    
	    for ( var i=0; i<places.length; i++ ) {

	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
	            marker = addMarker(placePosition, i), 
	            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);

	        // 마커와 검색결과 항목에 mouseover 했을때
	        // 해당 장소에 인포윈도우에 장소명을 표시합니다
	        // mouseout 했을 때는 인포윈도우를 닫습니다
	        (function(marker, title) {
	            kakao.maps.event.addListener(marker, 'mouseover', function() {
	                displayInfowindow(marker, title);
	            });

	            kakao.maps.event.addListener(marker, 'mouseout', function() {
	                infowindow.close();
	            });
	            
	            //마커에 클릭이벤트를 등록합니다
	            kakao.maps.event.addListener(marker, 'click', function() {
	            	
	            	
	            	// API 마커 선택 시 사용자 선택 마커 감추기
					//userMarker.setVisible(false);
					// alert(marker.getPosition());// 마커 찍었을때 좌표
					// 송출(LatLng)
					// alert(title); //마커 찍었을때 명칭 송출
					//document.getElementById('userMarkerText').style.display = 'none';
	            	
					var lat = marker.getPosition().getLat();
					var lng = marker.getPosition().getLng();
					
					document.frm.lat.value = lat;
					document.frm.lng.value = lng;
			
		       		userMarker.setVisible(true);
		       		
		       	//----------------------------------날씨 
		       		const url = "weather.do";
		       		const x = new Date();
		    		const year = x.getFullYear() + '';
		    		let month = x.getMonth() + 1 + '';
		    		let date =  x.getDate() + '';
		    		let time = x.getHours() + '';
		    		
		    		//const xy = dfs_xy_conv("toXY",$("#latitiude").val(),$("#longitude").val());
		    		if(month.length < 2)
		    			month = '0' + month;
		    		if(date.length < 2)
		    			date = '0' + date;
		    		if(time.lenght < 2)
		    			time = '0' + time;
		    		const fulldate = year + month + date;
		    		$.ajax({
		    			url : url + "?command=tlqkf&"+"&lat="+ lat +"&long=" + lng + "&date=" + fulldate + "&time=" + time,
		    			dataType:"text",
		    		}).done(
		    			function(data){
		    				const value = JSON.parse(data);
		    				const temp = Math.floor(value.temp - 272.15);
		    				const temp_min = Math.floor(value.temp - 272.15);
		    				const temp_max = Math.floor(value.temp - 272.15);				
		    				
		    				$("#reh").html(value.humidity + "%");
		    				$("#t3h").html(temp + "℃");
		    				$("#tmn").html(temp_min + "℃");
		    				$("#tmx").html(temp_max + "℃");
		    				

		    				$("#weather_reh").val(value.humidity + "℃");
		    				$("#weather_t3h").val(temp + "℃");
		    				$("#weather_tmn").val(temp_min + "℃");
		    				$("#weather_tmx").val(temp_max + "℃");
		    				$("#weather_sky").val(value.weather);
		    				
		    				if(value.weather === 'clear sky'){
		    					$("#weather").attr("src","img/weather/sun.png");
		    				}else if (value.weather === 'drizzle' || 'light intensity drizzle' || 'heavy intensity drizzle' ||
		    											'drizzle rain' || 'shower rain and drizzle' || 'heavy shower rain and drizzle' ||
		    											'shower drizzle' || 'light intensity drizzle rain' || 'heavy intensity drizzle rain'){
		    					$("#weather").attr("src","img/weather/Drizzle.png");
		    				}else if (value.weather === 'light rain' || 'moderate rain' || 'heavy intensity rain' || 'very heavy rain' || 'extreme rain'){
		    					$("#weather").attr("src","img/weather/rain.png");
		    				}else if (value.weather === 'thunderstorm with light rain' || 'thunderstorm with rain' || 'thunderstorm with heavy rain' ||
		    											'light thunderstorm' || 'thunderstorm' || 'heavy thunderstorm' || 'ragged thunderstorm' ||
		    											'thunderstorm with light drizzle' || 'thunderstorm with drizzle' || 'thunderstorm with heavy drizzle'){
		    					$("#weather").attr("src","img/weather/rain_thunder.png");
		    				}else if (value.weather === 'few clouds' || 'scattered clouds' || 'broken clouds' || 'overcast clouds'){
		    					$("#weather").attr("src","img/weather/broken_cloud.png");
		     				}else if (value.weather === 'mist' || 'smoke' || 'haze' || 'fog'){
		     					$("#weather").attr("src","img/weather/mist.png");
		    				}else{
		    					$("#weather_span").html(value.weather);	
		    				}
		    			}		
		    		)
	            });

	            itemEl.onmouseover =  function () {
	                displayInfowindow(marker, title);
	            };

	            itemEl.onmouseout =  function () {
	                infowindow.close();
	            };
	        })(marker, places[i].place_name);

	        fragment.appendChild(itemEl);
	    }

	    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
	    listEl.appendChild(fragment);
	    menuEl.scrollTop = 0;

	    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	    map.setBounds(bounds);
	}

	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {

	    var el = document.createElement('li'),
	    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
	                '<div class="info">' +
	                '   <h5>' + places.place_name + '</h5>';

	    if (places.road_address_name) {
	        itemStr += '    <span>' + places.road_address_name + '</span>' +
	                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
	    } else {
	        itemStr += '    <span>' +  places.address_name  + '</span>'; 
	    }
	            
	      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
	                '</div>';           
	      
	    el.innerHTML = itemStr;
	    el.className = 'item';

	    return el;
	}

	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
	    var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
	        imgOptions =  {
	            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
	            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	        },
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });

	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

	    return marker;
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}

	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {
	    var paginationEl = document.getElementById('pagination'),
	        fragment = document.createDocumentFragment(),
	        i; 

	    // 기존에 추가된 페이지번호를 삭제합니다
	    while (paginationEl.hasChildNodes()) {
	        paginationEl.removeChild (paginationEl.lastChild);
	    }

	    for (i=1; i<=pagination.last; i++) {
	        var el = document.createElement('a');
	        el.href = "#";
	        el.innerHTML = i;

	        if (i===pagination.current) {
	            el.className = 'on';
	        } else {
	            el.onclick = (function(i) {
	                return function() {
	                    pagination.gotoPage(i);
	                }
	            })(i);
	        }

	        fragment.appendChild(el);
	    }
	    paginationEl.appendChild(fragment);
	}

	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker, title) {
	    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

	    infowindow.setContent(content);
	
	    infowindow.open(map, marker);
	}

	 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {   
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}

	//지도를 클릭한 위치에 표출할 마커입니다
	var userMarker = new kakao.maps.Marker({ 
	  // 지도 중심좌표에 마커를 생성합니다 
	  position: map.getCenter() 
	}); 

	//지도에 마커를 표시합니다
	userMarker.setMap(map);
	//userMarker.setVisible(false);

	//마커에 클릭이벤트를 등록합니다
	kakao.maps.event.addListener(userMarker, 'click', function() {
	      alert(marker.getPosition());
	      
	});

	
	
	// 지도에 클릭 이벤트를 등록합니다
	//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {   
		 searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
		        if (status === kakao.maps.services.Status.OK) {
		            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
		            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
		           
		            var content = '<div class="bAddr">' +
                    '<span class="title">법정동 주소정보</span>' + 
                    detailAddr + '</div>';
		            // 클릭한 위도, 경도 정보를 가져옵니다 
		            var latlng = mouseEvent.latLng; 
		            var cont = !!result[0].road_address ? '도로명주소 : ' + result[0].road_address.address_name : '';
		            cont += (cont == '') ? '' : ', ';
		            cont += '지번 주소 : ' + result[0].address.address_name;
		            //location.href="meetingboard.do?command=latlng&lng="+lng+"&lat="+lat+"&det="+cont;
		       	 	// 마커 위치를 클릭한 위치로 옮깁니다
		       	 	userMarker.setPosition(latlng);
		       
		       	 	var lat = latlng.getLat();
		       	 	var lng = latlng.getLng();
				
		       		document.frm.lat.value = lat;
		       		document.frm.lng.value = lng;
		       		document.frm.map_addr.value = cont;
		       		userMarker.setVisible(true);
		       		// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
		       		infowindow.setContent(content);
		       		infowindow.open(map, userMarker);
		       		//----------------------------------날씨 
		       		const url = "weather.do";
		       		const x = new Date();
		    		const year = x.getFullYear() + '';
		    		let month = x.getMonth() + 1 + '';
		    		let date =  x.getDate() + '';
		    		let time = x.getHours() + '';
		    		
		    		//const xy = dfs_xy_conv("toXY",$("#latitiude").val(),$("#longitude").val());
		    		if(month.length < 2)
		    			month = '0' + month;
		    		if(date.length < 2)
		    			date = '0' + date;
		    		if(time.lenght < 2)
		    			time = '0' + time;
		    		const fulldate = year + month + date;
		    		$.ajax({
		    			url : url + "?command=tlqkf&"+"&lat="+ lat +"&long=" + lng + "&date=" + fulldate + "&time=" + time,
		    			dataType:"text",
		    		}).done(
		    			function(data){
		    				const value = JSON.parse(data);
		    				const temp = Math.floor(value.temp - 272.15);
		    				const temp_min = Math.floor(value.temp - 272.15);
		    				const temp_max = Math.floor(value.temp - 272.15);				
		    				
		    				$("#reh").html(value.humidity + "%");
		    				$("#t3h").html(temp + "℃");
		    				$("#tmn").html(temp_min + "℃");
		    				$("#tmx").html(temp_max + "℃");
		    				
		    				$("#weather_reh").val(value.humidity + "℃");
		    				$("#weather_t3h").val(temp + "℃");
		    				$("#weather_tmn").val(temp_min + "℃");
		    				$("#weather_tmx").val(temp_max + "℃");
		    				$("#weather_sky").val(value.weather);
		    				
		    				if(value.weather === 'clear sky'){
		    					$("#weather").attr("src","img/weather/sun.png");
		    				}else if (value.weather === 'drizzle' || 'light intensity drizzle' || 'heavy intensity drizzle' ||
		    											'drizzle rain' || 'shower rain and drizzle' || 'heavy shower rain and drizzle' ||
		    											'shower drizzle' || 'light intensity drizzle rain' || 'heavy intensity drizzle rain'){
		    					$("#weather").attr("src","img/weather/Drizzle.png");
		    				}else if (value.weather === 'light rain' || 'moderate rain' || 'heavy intensity rain' || 'very heavy rain' || 'extreme rain'){
		    					$("#weather").attr("src","img/weather/rain.png");
		    				}else if (value.weather === 'thunderstorm with light rain' || 'thunderstorm with rain' || 'thunderstorm with heavy rain' ||
		    											'light thunderstorm' || 'thunderstorm' || 'heavy thunderstorm' || 'ragged thunderstorm' ||
		    											'thunderstorm with light drizzle' || 'thunderstorm with drizzle' || 'thunderstorm with heavy drizzle'){
		    					$("#weather").attr("src","img/weather/rain_thunder.png");
		    				}else if (value.weather === 'few clouds' || 'scattered clouds' || 'broken clouds' || 'overcast clouds'){
		    					$("#weather").attr("src","img/weather/broken_cloud.png");
		     				}else if (value.weather === 'mist' || 'smoke' || 'haze' || 'fog'){
		     					$("#weather").attr("src","img/weather/mist.png");
		    				}else{
		    					$("#weather_span").html(value.weather);	
		    				}
		    			}		
		    		)
		        }
		 });
	 //document.getElementById("userMarkerText").style.display = "block";
	});
	
	 
	kakao.maps.event.addListener(map, 'idle', function() {
	   
	});
	
	function searchDetailAddrFromCoords(coords, callback) {
	    // 좌표로 법정동 상세 주소 정보를 요청합니다
	    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}
	
	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	        var infoDiv = document.getElementById('centerAddr');

	        for(var i = 0; i < result.length; i++) {
	            // 행정동의 region_type 값은 'H' 이므로
	            if (result[i].region_type === 'H') {
	                infoDiv.innerHTML = result[i].address_name;
	                break;
	            }
	        }
	    }    
	}
	
	