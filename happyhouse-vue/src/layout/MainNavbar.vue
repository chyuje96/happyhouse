<template>
  <md-toolbar id="toolbar" md-elevation="0" class="md-dark md-absolute" :class="extraNavClasses">
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <router-link to="/"><h3 class="md-title">Happy House</h3></router-link>
      </div>
      <div class="md-toolbar-section-end">
        <md-list>
          <md-list-item @click="toQna" v-if="true" class="qna">
            <p>Q&A</p>
          </md-list-item>

          <md-list-item @click="toRegister" v-if="!loginuser" class="qna">
            <p>회원가입</p>
          </md-list-item>

          <md-list-item @click="toProfile" v-if="loginuser" class="qna">
            <p>회원정보</p>
          </md-list-item>

          <md-list-item @click="toLogin" v-if="!loginuser" class="qna">
            <p>로그인</p>
          </md-list-item>

          <md-list-item @click="logout" v-if="loginuser" class="qna">
            <p>로그아웃</p>
          </md-list-item>
        </md-list>
      </div>
    </div>
  </md-toolbar>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  data() {
    return {
      extraNavClasses: '',
      toggledClass: false,
    };
  },
  computed: {
    ...mapState(['loginuser']),
    // false면 로그인이 안 된 상태
  },
  methods: {
    ...mapActions(['logoutUser']),
    logout() {
      this.logoutUser();
      alert('로그아웃되었습니다.');
      this.$router.push({ name: 'index' });
    },
    toQna() {
      this.$router.push({ name: 'qna' });
    },
    toRegister(){
      this.$router.push({ name: 'register' });
    },
    toProfile(){
      this.$router.push({ name: 'profile' });
    },
    toLogin(){
      this.$router.push({ name: 'login' });
    },
    bodyClick() {
      let bodyClick = document.getElementById('bodyClick');

      if (bodyClick === null) {
        let body = document.querySelector('body');
        let elem = document.createElement('div');
        elem.setAttribute('id', 'bodyClick');
        body.appendChild(elem);

        let bodyClick = document.getElementById('bodyClick');
        bodyClick.addEventListener('click', this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
  },
};
</script>
<style>
.qna {
  margin-top: 5px;
}
</style>
