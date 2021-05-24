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
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a81d222dada7747717a0d92f55a53644&libraries=services"></script>

    <!-- jQuery -->
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>

<body>

<div id="wrapper" style="height: 100%">
    <div id="page-wrapper" style="padding: 0">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header" style="text-align: center">TrashCans</h1>
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
                level: 1
            };

            let map = new kakao.maps.Map(container, options);

            map.setLevel(4);
            map.relayout();

            map.setLevel(1);
            map.relayout();

            let count = 0;
            // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new kakao.maps.services.Geocoder();

            // 주소로 좌표를 검색합니다
            <c:forEach items="${trashCans}" var="trashCan">
            geocoder.addressSearch('<c:out value="${trashCan.detailLocation}"/>' , function (result, status) {

                // 정상적으로 검색이 완료됐으면
                if (status === kakao.maps.services.Status.OK) {

                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                    console.log(coords);

                    count++;
                    console.log(count);


                    // 결과값으로 받은 위치를 마커로 표시합니다
                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords,
                        text: 'trash can'
                    });

                    marker.setMap(map);

                }
            });
            </c:forEach>

        });

    });
</script>

<script>

</script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

<!-- DataTables JavaScript -->
<script src="/resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="/resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="/resources/vendor/datatables-responsive/dataTables.responsive.js"></script>
<!-- Custom Theme JavaScript -->
<script src="/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>
