<template>
  <div class="main main-raised">
    <div>
      <div class="md-layout md-gutter">
        <div class="dropbox">
          <md-field>
            <label for="sido" class="drop-text">시/도</label>
            <md-select v-model="selsido" name="sido" id="sido">
              <md-option v-for="sido in sidos" :key="sido.dongCode" :value="sido.dongCode">{{
                sido.sidoName
              }}</md-option>
            </md-select>
          </md-field>
        </div>

        <div class="dropbox">
          <md-field>
            <label for="gugun">구/군</label>
            <md-select v-model="selgugun" name="gugun" id="gugun">
              <md-option v-for="gugun in guguns" :key="gugun.dongCode" :value="gugun.dongCode">{{
                gugun.gugunName
              }}</md-option>
            </md-select>
          </md-field>
        </div>

        <div class="dropbox">
          <md-field>
            <label for="dong">동</label>
            <md-select v-model="seldong" name="dong" id="dong">
              <md-option v-for="dong in dongs" :key="dong.dongCode" :value="dong.dongCode">{{
                dong.dongName
              }}</md-option>
            </md-select>
          </md-field>
        </div>

        <div class="search">
          <md-field>
            <label>아파트명</label>
            <md-input v-model="searchName"></md-input>
          </md-field>
        </div>
        <div class="search-btn">
          <md-button class="md-raised" @click="searchapt">검색</md-button>
        </div>
      </div>
      <!-- 잘려질부분 -->

      <div class="">
        <div class="elevation-demo">
          <md-content class="md-elevation-1" id="list1">
            <div class="map_wrap" id="map_wrap">
              <div
                id="map"
                style="width: 100%; height: 100%; position: relative; overflow: hidden"
              ></div>
              <ul id="category">
                <li id="BK9" data-order="0">
                  <span id="BK9" class="category_bg bank"></span>
                  은행
                </li>
                <li id="MT1" data-order="1">
                  <span id="MT1" class="category_bg mart"></span>
                  마트
                </li>
                <li id="PM9" data-order="2">
                  <span id="PM9" class="category_bg pharmacy"></span>
                  약국
                </li>
                <li id="OL7" data-order="3">
                  <span id="OL7" class="category_bg oil"></span>
                  주유소
                </li>
                <li id="CE7" data-order="4">
                  <span id="CE7" class="category_bg cafe"></span>
                  카페
                </li>
                <li id="CS2" data-order="5">
                  <span id="CS2" class="category_bg store"></span>
                  편의점
                </li>
                <li id="SW8" data-order="6">
                  <span id="SW8" class="subway"></span>
                  지하철
                </li>
              </ul>
            </div>
          </md-content>
          <div class="margin1">
            <md-content class="md-elevation-1" id="list3" v-if="loginuser">
              <prefer-apt-list :selpre.sync="selprefer"></prefer-apt-list>
            </md-content>
            <md-content class="md-elevation-1" :id="style_aptlist">
              <div class="md-scrollbar">
                <md-list-item>
                  <div class="md-list-item-content md-ripple md-disabled">
                    <md-radio v-model="sortby" value="date" class="radio-btn" name="sortradio"
                      >최근순</md-radio
                    >
                    <md-radio v-model="sortby" value="price" class="radio-btn" name="sortradio"
                      >가격순</md-radio
                    >
                    <md-radio v-model="sortby" value="area" class="radio-btn" name="sortradio"
                      >면적순</md-radio
                    >
                  </div>
                </md-list-item>
                <md-list :md-expand-single="expandSingle">
                  <md-list-item
                    md-expand
                    v-for="houseInfo in houseInfos"
                    :key="houseInfo.apartmentName"
                    @click="makeaptdeal(houseInfo)"
                  >
                    <md-card class="card">
                      <md-card-content :md-expand-single="expandSingle">{{
                        houseInfo.apartmentName
                      }}</md-card-content>
                    </md-card>

                    <div class="check-btn" v-if="loginuser">
                      <md-button class="md-raised" @click="addPrefer(houseInfo)">관심</md-button>
                    </div>
                    <md-list slot="md-expand">
                      <md-list-item
                        class="md-elevation-1"
                        v-for="houseDeal in houseDeals"
                        :key="houseDeal.no"
                      >
                        계약일 : {{ houseDeal.dealYear }}년 {{ houseDeal.dealMonth }}월
                        {{ houseDeal.dealDay }}일<br />
                        매매가 : {{ houseDeal.dealAmount }} 만원<br />
                        {{ houseDeal.area }} ㎡ / {{ houseDeal.floor }} 층</md-list-item
                      >
                    </md-list>
                  </md-list-item>
                </md-list>
              </div>
            </md-content>
          </div>
        </div>
      </div>

      <div class="section profile-content"></div>
    </div>
  </div>
</template>

<script>
import http from '@/api/http';
import { mapState, mapActions } from 'vuex';
import preferAptList from './item/PreferAptList.vue';
export default {
  name: 'mainmap',
  components: {
    preferAptList,
  },
  data() {
    return {
      selsido: '',
      selgugun: '',
      seldong: '',
      searchName: '',
      sidos: [],
      guguns: [],
      dongs: [],
      houseInfos: [],
      houseDeals: [],
      sortby: 'date',
      expandSingle: true,

      style_aptlist: 'list4',
      selprefer: null,
      selpreferflag: false,

      curmap: null,
      curmarker: [],

      placeOverlay: null,
      contentNode: null,
      placemarkers: [],
      currCategory: '',
      ps: null,
    };
  },
  watch: {
    selsido: function () {
      this.selgugun = '';
      this.seldong = '';

      http.get(`/deal/gugun?sido=${this.selsido}`).then(({ data }) => {
        this.guguns = data;
      });
    },
    selgugun: function () {
      this.seldong = '';
      if (this.selgugun) {
        http.get(`/deal/dong?sido=${this.selsido}&gugun=${this.selgugun}`).then(({ data }) => {
          this.dongs = data;
        });
      }
    },
    seldong: function () {
      if (this.seldong && !this.selpreferflag) {
        http.get(`/deal/apt?dong=${this.seldong}`).then(({ data }) => {
          this.houseInfos = data;
          this.makemarker();
        });
      }
    },
    sortby: function () {
      this.sortaptdeal();
    },
    selprefer: function () {
      if (this.selprefer) {
        this.searchPrefer();
      }
    },
  },

  computed: {
    ...mapState(['loginuser']),
    // false면 로그인이 안 된 상태
  },
  created() {
    http.get('/deal/sido').then(({ data }) => {
      this.sidos = data;
    });
    if (this.loginuser) {
      this.style_aptlist = 'list4';
    } else {
      this.style_aptlist = 'list2';
    }
  },

  mounted() {
    this.makemap();
  },
  methods: {
    ...mapActions(['setPreferList']),

    //관심지역 등록
    addPrefer(houseInfo) {
      if (this.selsido && this.selgugun && this.seldong) {
        this.setPreferList();
        http
          .post(`/deal/prefer`, {
            sidoName: this.selsido,
            gugunName: this.selgugun,
            dongName: this.seldong,
            dongCode: houseInfo.dongCode,
            userId: this.loginuser.userId,
            aptCode: houseInfo.aptCode,
          })
          .then(({ data }) => {
            this.setPreferList();
          });
      }
    },
    //관심지역찾기
    searchPrefer() {
      this.selpreferflag = true;
      this.seldong = this.selprefer.dongCode;
      this.searchName = this.selprefer.apartmentName;
      this.searchapt();
    },
    //아파트 거래 정보 불러오기
    makeaptdeal(houseInfo) {
      this.houseDeals = [];
      this.markerhighlight(houseInfo.apartmentName);
      http.get(`/deal/apt/${houseInfo.aptCode}`).then(({ data }) => {
        this.houseDeals = data;
      });
    },
    //아파트거래정보 정렬하기
    sortaptdeal() {
      if (this.houseDeals) {
        if (this.sortby == 'price') {
          this.houseDeals.sort(function (a, b) {
            return Number(a.dealAmount.replace(/,/g, '')) < Number(b.dealAmount.replace(/,/g, ''))
              ? 1
              : -1;
          });
        } else if (this.sortby == 'area') {
          this.houseDeals.sort(function (a, b) {
            return Number(a.area) < Number(b.area) ? 1 : -1;
          });
        } else if(this.sortby == 'date') {
          this.houseDeals.sort(function (a, b) {
            if(a.dealYear == b.dealYear){
              if(a.dealMonth == b.dealMonth){
                  return a.dealDay < b.dealDay ? 1 : -1;
                }
              return a.dealMonth < b.dealMonth ? 1 : -1;
            }
            return a.dealYear < b.dealYear ? 1 : -1;
          });
        }
      }
    },
    //아파트 이름으로 검색
    searchapt() {
      if (this.searchName && this.seldong) {
        http.get(`/deal/aptsearch/${this.seldong}/${this.searchName}`).then(({ data }) => {
          this.houseInfos = data;
          this.makemarker();

          this.selpreferflag = false;
          this.searchName = '';
        });
      }
    },
    //카카오 api 지도 만들기
    makemap() {
      var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.555946, 126.972317), //지도의 중심좌표.
        level: 6, //지도의 레벨(확대, 축소 정도)
      };
      this.curmap = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      (this.contentNode = document.createElement('div')), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        (this.placemarkers = []), // 마커를 담을 배열입니다
        (this.currCategory = ''); // 현재 선택된 카테고리를 가지고 있을 변수입니다
      // 장소 검색 객체를 생성합니다
      this.ps = new kakao.maps.services.Places(this.curmap);
      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.curmap, 'idle', this.searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      this.contentNode.className = 'placeinfo_wrap';

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(this.contentNode, 'mousedown', kakao.maps.event.preventMap);
      this.addEventHandle(this.contentNode, 'touchstart', kakao.maps.event.preventMap);
      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      this.addCategoryClickEvent();
    },
    // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent('on' + type, callback);
      }
    },
    //장소 검색
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      this.ps.categorySearch(this.currCategory, this.placesSearchCB, { useMapBounds: true });
    }, // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },

    // 지도에 마커를 표출하는 함수입니다
    displayPlaces(places) {
      let self = this;
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      let order = document.getElementById(this.currCategory).getAttribute('data-order');
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        let marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        (function (marker, place) {
          kakao.maps.event.addListener(marker, 'click', function () {
            self.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, order) {
      if (order == 6) {
        var imageSrc = 'https://i1.daumcdn.net/dmaps/apis/n_local_blit_04.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions = {
            offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
          });

        marker.setMap(this.curmap); // 지도 위에 마커를 표출합니다
        this.placemarkers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;
      }
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.curmap); // 지도 위에 마커를 표출합니다
      this.placemarkers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (let i = 0; i < this.placemarkers.length; i++) {
        this.placemarkers[i].setMap(null);
      }
      this.placemarkers = [];
    },

    // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
    displayPlaceInfo(place) {
      let content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        '</a>';

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          '</span>' +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ')</span>';
      } else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
      }

      content +=
        '    <span class="tel">' + place.phone + '</span>' + '</div>' + '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.curmap);
    },

    // 각 카테고리에 클릭 이벤트를 등록합니다
    addCategoryClickEvent() {
      let category = document.getElementById('category'),
        children = category.children;
      for (let i = 0; i < children.length; i++) {
        // console.log(children[i]);
        children[i].onclick = this.onClickCategory;
      }
    },

    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(event) {
      let id = event.target.id,
        className = event.target.className;
      this.placeOverlay.setMap(null);
      // console.log(event.target);

      if (className === 'on') {
        this.currCategory = '';
        this.changeCategoryClass();
        this.removeMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(this);
        this.searchPlaces();
      }
    },

    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(el) {
      let category = document.getElementById('category'),
        children = category.children,
        i;

      for (i = 0; i < children.length; i++) {
        children[i].className = '';
      }

      if (el) {
        el.className = 'on';
      }
    },
    //카카오 api 마커 이미지 바꾸기
    markerhighlight(apartmentName) {
      // 마커 이미지의 이미지 주소입니다
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
      var imageSize = new kakao.maps.Size(24, 35);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      for (let marker of this.curmarker) {
        if (marker.getTitle() == apartmentName) {
          marker.setImage(markerImage);
          this.curmap.setCenter(marker.getPosition());
        }
        if (marker.getImage() && marker.getTitle() != apartmentName) {
          marker.setImage(null);
        }
      }
    },
    //마커 만들기
    makemarker() {
      for (let marker of this.curmarker) {
        marker.setMap(null);
      }

      var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.555946, 126.972317), //지도의 중심좌표.
        level: 6, //지도의 레벨(확대, 축소 정도)
      };

      let positions = [];
      let avglat = 0;
      let avglng = 0;
      for (const houseInfo of this.houseInfos) {
        avglat = avglat + Number(houseInfo.lat);
        avglng = avglng + Number(houseInfo.lng);
        positions.push({
          title: houseInfo.apartmentName,
          latlng: new kakao.maps.LatLng(houseInfo.lat, houseInfo.lng),
        });
      }
      avglat = avglat / positions.length;
      avglng = avglng / positions.length;

      for (let i = 0; i < positions.length; i++) {

        // 마커를 생성합니다
        this.curmarker.push(
          new kakao.maps.Marker({
            map: this.curmap, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            // image : markerImage // 마커 이미지
          })
        );
      }
      if (avglat) {
        this.curmap.setCenter(new kakao.maps.LatLng(avglat, avglng));
      }
    },
  },
};
</script>

<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 1000px;
}
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li .subway {
  background: url('../../assets/img/subway.png');
}

#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: '';
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png');
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
/* #map_wrap {
  width: 97.5%;
  height: 97.5%;
  margin: auto;
} */
/* #map {
  width: 97.5%;
  height: 97.5%;
  margin: auto;
} */

.md-layout {
  justify-content: center;
}

.dropbox {
  margin: 10px;
  margin-top: 20px;
}

.search {
  margin: 10px;
  margin-top: 20px;
}

.search-btn {
  margin: 10px;
  margin-top: 20px;
}

.section {
  padding: 10px 0 !important;
}

.md-list-item-text {
  align-items: center !important;
  margin-top: 27px;
}

.md-ripple > span {
  width: 50%;
}

.card {
  text-align: center;
  margin-top: 10px !important;
  margin-bottom: 10px !important;
}

.elevation-demo {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

/* .md-content {
  height: 1000px;
  margin: 30px;
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: auto;
} */

#list1 {
  height: 1000px;
  margin: 30px;
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
  overflow: auto;
  width: 72.5%;
  margin-right: 0px !important;
}

#list2 {
  height: 1000px;
  margin: 30px;
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
  overflow: auto;
  width: 100%;
  margin-left: 0px !important;
}

#list3 {
  height: 250px;
  margin: 30px;
  margin-top: 20px;
  margin-bottom: 24px;
  display: flex;
  justify-content: center;
  overflow: auto;
  width: 100%;
  margin-left: 0px !important;
}

#list4 {
  height: 725px;
  margin: 30px;
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: center;
  overflow: auto;
  width: 100%;
  margin-left: 0px !important;
}

.margin1 {
  margin-right: 30px;
  width: 22.5%;
}

.md-scrollbar {
  width: 95%;
}

.md-icon {
  display: none !important;
}

.check-btn {
  margin-left: 10px;
}
.radio-btn {
  margin-right: 10px !important;
  justify-content: center !important;
}

.md-list-item-content md-ripple md-disabled {
  justify-content: center !important;
}

.md-theme-default a:not(.md-button) {
  color: #fff !important;
  font-family: Arial, Helvetica, sans-serif;
}
</style>
