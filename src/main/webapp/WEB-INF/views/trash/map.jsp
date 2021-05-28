<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>쓰래기통 위치</title>

    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

	<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


    <!--     <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
     -->
    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- kakaoMap api -->
    <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a81d222dada7747717a0d92f55a53644&libraries=services,clusterer"></script>
    <!-- jQuery -->
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>

<body>

<div id="wrapper" style="height: 100%">
    <div id="page-wrapper" style="padding: 0">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header" style="text-align: center">Trash Cans</h1>
                <ul class="nav nav-tabs" style="width: 100%">
                    <li role="presentation" class="active" style="width: 33%; text-align: center"><a href="#">Map</a></li>
                    <li role="presentation" style="width: 33%; text-align: center"><a href="#" >AI Separate Collection</a></li>
                    <li role="presentation" style="width: 33%; text-align: center"><a href="#" >Notice</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div id="map" style="width:100%;height:95%;padding-top: 30px; margin: 0 auto"></div>
            <!-- /#page-wrapper -->
            </div>
        </div>
    </div>
</div>
<!-- /#wrapper -->

<script>
    $(document).ready(function () {
        navigator.geolocation.getCurrentPosition(function (pos) {
            let latitude = pos.coords.latitude;
            let longitude = pos.coords.longitude;

            let container = document.getElementById('map');
            let options = {
                center: new kakao.maps.LatLng(latitude, longitude),
                level: 14
            };

            let count = 0;
            let map = new kakao.maps.Map(container, options);
            let trashLat;
            let trashLng;

            <c:forEach items="${trashCans}" var="trashCan">

            trashLat = "<c:out value='${trashCan.lat}'/> ";
            trashLng = "<c:out value='${trashCan.lng}'/> ";

            if(trashLng !== " "){
                count++;

                // 마커 클러스터러를 생성합니다
                var clusterer = new kakao.maps.MarkerClusterer({
                    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
                    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
                    minLevel: 3 // 클러스터 할 최소 지도 레벨
                });

                // 데이터를 가져오기 위해 jQuery를 사용합니다
                // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
                $.get("/Users/zzun_ho9/Downloads/chicken.json", function(data) {
                    // 데이터에서 좌표 값을 가지고 마커를 표시합니다
                    // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
                    var markers = new kakao.maps.Marker({
                        position : new kakao.maps.LatLng(37.0, 127.0)
                    });

                    // 클러스터러에 마커들을 추가합니다
                    clusterer.addMarkers(markers);
                });
            }

            </c:forEach>

        });
    });

</script>



</body>

</html>
