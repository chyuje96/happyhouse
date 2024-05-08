const path = "/deal";

// 창이 열리면
$(function () {
  // sido option 추가 (AJAX)
  $.ajax({
    url: path + "/sido",
    type: "GET",

    success: function (resJson, status) {
      $.each(resJson, function (key, value) {
        $("#si-select").append(fn_option(value.dongCode, value.sidoName));
      });
    },
    error: function (error) {
      console.log(error);
    },
  });

  // 거래 데이터를 표시하는 div를 숨긴 상태로 시작
  $(".deal-box").hide();

  // show, hide 옵션을 이벤트로 사용
  $.each(["show", "hide"], function (i, ev) {
    var el = $.fn[ev];
    $.fn[ev] = function () {
      this.trigger(ev);
      return el.apply(this, arguments);
    };
  });
});

// 광역시 / 도를 선택하면 구 / 군 선택지 갱신
$(document).on("change", "#si-select", function () {
  // html 수정
  $("#goo-select").html("<option>시 / 구 / 군</option>");
  $("#dong-select").html("<option>읍 / 면 / 동</option>");

  let sido = $("#si-select option:selected").val();

  // gugun option 추가 (AJAX)
  $.ajax({
    url: path + "/gugun",
    type: "GET",
    data: { sido },

    success: function (resJson, status) {
      $.each(resJson, function (key, value) {
        $("#goo-select").append(fn_option(value.dongCode, value.gugunName));
      });

      $("#goo-select").show();
    },
    error: function (error) {
      console.log(error);
    },
  });
});

// 위에서 사용하는 html 추가 코드
function fn_option(code, name) {
  return '<option value="' + code + '">' + name + "</option>";
}

// 동 선택지 갱신
$(document).on("change", "#goo-select", getDeal);
function getDeal() {
  // 거래 데이터를 표시하는 div를 숨겨줌
  $(".deal-box").hide();

  // 시와 구를 모두 선택했을 때만 동작
  let sido = $("#si-select option:selected").val();
  let gugun = $("#goo-select option:selected").val();

  if (sido == "광역시 / 도" || gugun == "시 / 구 / 군") return;

  // 동 코드 초기화
  $("#dong-select").html("<option>읍 / 면 / 동</option>");

  // dong option 추가 (AJAX)
  $.ajax({
    url: path + "/dong",
    type: "GET",
    data: { sido, gugun },

    success: function (resJson, status) {
      $.each(resJson, function (key, value) {
        $("#dong-select").append(fn_option(value.dongCode, value.dongName));
      });
    },
    error: function (error) {
      console.log(error);
    },
  });
}

// 동을 선택하면 아파트 거래 정보를 div로 만들어서 html에 뿌리는 기능
$(document).on("change", "#dong-select", function () {
  showDongDeal("make");
});
function showDongDeal(str) {
  // 시와 구를 모두 선택했을 때만 동작
  let sido = $("#si-select option:selected").val();
  let gugun = $("#goo-select option:selected").val();
  let dong = $("#dong-select option:selected").val();

  // 지역을 모두 선택했을 때만 동작
  if (sido == "광역시 / 도" || gugun == "시 / 구 / 군" || dong == "읍 / 면 / 동") return;
  // 동을 새로 선택하면 기존에 있는 데이터를 지워줌
  $(".deal-box").empty();

  // 거래 데이터 AJAX
  $.ajax({
    url: path + "/apt",
    type: "GET",
    data: { dong },

    success: function (resJson, status) {
      if (resJson.length == 0) {
        $(".deal-box").append(
          `<div class="deal-content">
                  <p> 거래 데이터가 없습니다 </p>
          </div>`
        );
      } else {
        $.each(resJson, function (key, value) {
          $(".deal-box").append(
            `<div class="deal-content">
                    <a class="deal-content-title" href="javascript:void(0);" onClick="getAptDeal(this);">${value.aptName}</a>
                    <p>최근 거래금액 : ${value.recentPrice} 만원</p>
                    </div>`
          );
        });
      }

      // 아파트 선택 결과에서 돌아가기 버튼으로 호출된 경우에는 show를 하지 않음 => 지도의 마커를 재성성하지 않음
      if (str != "back") {
        $(".deal-box").show();
      }
    },
    error: function (error) {
      console.log(error);
    },
  });
}

// 아파트 이름을 클릭한 경우
function getAptDeal(obj) {
  // 출력 div 비워주기
  $(".deal-box").empty();
  // 함수를 호출한 Element의 텍스트를 얻어옴
  let apt = $(obj).text();
  // 아파트 이름만 따로 출력
  $(".deal-box").append(
    `<div class="deal-content-apt-name">
	  <h5>${apt}</h5>
	  </div>`
  );

  // 동코드 저장
  let dong = $("#dong-select option:selected").val();
  // 아파트 실거래 데이터 AJAX
  $.ajax({
    url: path + "/aptDeal",
    type: "GET",
    data: { dong, aptName: apt },

    success: function (resJsonArr) {
      $.each(resJsonArr, function (key, value) {
        $(".deal-box").append(
          `<div class="deal-content">
        		  <p>면적 : ${value.area} m<sup>2</sup></p>
        		  <p>층 : ${value.floor} 층</p>
                  <p>거래금액 : ${value.dealAmount} 만원</p>
                  <p>거래일자 : ${value.dealYear}.${value.dealMonth}.${value.dealDay}</p>
                  </div>`
        );
      });

      // 다시 동네 보기로 돌아가는 버튼
      $(".deal-box").append(
        `<div class="deal-content-back">
    	  <button class="btn btn-outline-primary">돌아가기</button>
    	  </div>`
      );
    },
    error: function (error) {
      console.log(error);
    },
  });
}
// 동일 아파트 출력 화면에서 돌아가기 버튼을 눌렀을 때
$(document).on("click", ".deal-content-back button", function () {
  showDongDeal("back");
});

// ===================================지도======================================
// 기본 지도 표시 + GEOCODING 세팅
function myMap() {
  // 구글지도 콜백함수
  const mapOptions = {
    center: new google.maps.LatLng(36.7, 128.25),
    zoom: 8,
    disableDefaultUI: true,
    zoomControl: true,
  };

  let map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);

  // 아파트 거래내역이 다 처리가 되면 .deal-box가 show()로 바뀌기 때문에 그 상태를 확인
  $(document).on("show", ".deal-box", function () {
    // 동코드 저장
    let dong = $("#dong-select option:selected").val();
    // 선택한 동의 중심 좌표 가져오기
    $.ajax({
      url: path + "/base",
      type: "GET",
      data: { dong },

      success: function (resJson) {
        sessionStorage.setItem("dongLat", resJson.lat);
        sessionStorage.setItem("dongLng", resJson.lng);
        // Position 설정
        const pos = {
          lat: parseFloat(resJson.lat),
          lng: parseFloat(resJson.lng),
        };
        // 맵을 동의 중심으로 이동
        map.setZoom(14);
        map.setCenter(pos);

        // 거래 데이터 AJAX
        $.ajax({
          url: path + "/apt",
          type: "GET",
          data: { dong },

          success: function (resJson, status) {
            // 각 아파트의 좌표를 불러오고 마커 설정
            $.each(resJson, function (key, value) {
              // 해당 좌표 마커 생성
              var marker = new google.maps.Marker({
                label: value.aptName,
                map: map,
                position: new google.maps.LatLng(value.lat, value.lng),
              });
              // 마커 추가
              marker.setMap(map);
            });
          },
          error: function (error) {
            console.log(error);
          },
        });
      },
      error: function (error) {
        console.log(error);
      },
    }); // ajax
  }); // 좌표 저장 끝

  // 아파트 이름을 클릭한 경우
  $(document).on("click", ".deal-content-title", function () {
    // 동코드 저장
    let dong = $("#dong-select option:selected").val();

    $.ajax({
      url: path + "/infoOne",
      type: "GET",
      data: { dong, aptName: $(this).text() },

      success: function (resJson) {
        // Position 설정
        const cenPos = {
          lat: parseFloat(resJson.lat),
          lng: parseFloat(resJson.lng),
        };
        map.setCenter(cenPos);
      },
      error: function (error) {
        console.log(error);
      },
    });
  });

  // 동일 아파트 거래 화면에서 돌아가기 버튼을 누른 경우
  $(document).on("click", ".deal-content-back button", function () {
    const cenPos = {
      // sessionStorage에서 데이터를 불러오면 String이기 때문에 숫자로 인식을 못해서 parseFloat을 사용
      lat: parseFloat(sessionStorage.getItem("dongLat")),
      lng: parseFloat(sessionStorage.getItem("dongLng")),
    };
    map.setCenter(cenPos);
  });
}
