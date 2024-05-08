<template>
  <div class="wrapper">
    <parallax-comp class="section page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>로그인</h1>
            </div>
          </div>
        </div>
      </div>
    </parallax-comp>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div>
            <div class="registerbox">
              <md-field>
                <label>ID</label>
                <md-input v-model="userId"></md-input>
              </md-field>

              <md-field>
                <label>Password</label>
                <md-input v-model="userPassword" type="password"></md-input>
                <span class="md-helper-text">비밀번호는 8자리 이상 영문/숫자</span>
              </md-field>

              <div class="md-raised btn-margin">
                <div>
                  <md-button class="md-raised qna-btn" @click="login">로그인</md-button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/api/http';
import { mapActions } from 'vuex';

export default {
  name: 'RegisterView',
  bodyClass: 'profile-page',
  data() {
    return {
      userId: '',
      userPassword: '',
    };
  },

  props: {
    image: {
      type: String,
      default: require('@/assets/img/architecture2.jpg'),
    },
  },

  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
  methods: {
    ...mapActions(['setLoginUser']),
    login() {
      http
        .post('/user/login', {
          userId: this.userId,
          userPassword: this.userPassword,
        })
        .then(({ data }) => {
          if (data == 'success') {
            alert('로그인 되었습니다.');
            return this.userId;
          } else {
            alert('로그인 실패!');
          }
        })
        .then((userId) => {
          if (!userId) {
            this.$router.go(this.$router.currentRoute);
          } else {
            http.get(`/user/getmember/${userId}`).then(({ data }) => {
              this.setLoginUser(data);
              this.$router.push({ name: 'index' });
            });
          }
        })
        .catch(()=>{
          alert("서버 에러가 발생하였습니다.")
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
}

#qnaTitle {
  color: black !important;
  font-weight: 600;
  font-size: 24px;
  text-align: center;
  display: initial;
}

.profile-tabs::v-deep {
  .md-card-tabs .md-list {
    justify-content: center;
  }

  [class*='tab-pane-'] {
    margin-top: 3.213rem;
    padding-bottom: 50px;

    img {
      margin-bottom: 2.142rem;
    }
  }
}

.qna-btn {
  margin: 10px 6px !important;
  width: 94%;
  display: flex;
  justify-content: center;
}
.btn-margin {
  margin-left: 40%;
  margin-right: 40%;
  margin-bottom: 2%;
  margin-top: 1%;
}

.registerbox {
  margin-top: 3%;
}

.main {
  width: 50% !important;
  margin: auto;
  margin-top: -2.3%;
}

.brand {
  color: #ffffff;
}

h1 {
  font-weight: 550 !important;
}
</style>
