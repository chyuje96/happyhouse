<template>
  <div class="wrapper">
    <parallax-comp class="section page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand">
              <h1>회원 가입</h1>
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
                <label>아이디</label>
                <md-input v-model="userId"></md-input>
              </md-field>

              <md-field>
                <label>비밀번호</label>
                <md-input v-model="userPassword" type="password"></md-input>
                <span class="md-helper-text">비밀번호는 8자리 이상 영문/숫자</span>
              </md-field>

              <md-field>
                <label>이름</label>
                <md-input v-model="userName"></md-input>
              </md-field>

              <md-field>
                <label>주소</label>
                <md-input v-model="userAddress"></md-input>
              </md-field>

              <md-field>
                <label>전화번호</label>
                <md-input v-model="userPhoneNumber"></md-input>
              </md-field>
              <div class="md-raised btn-margin">
                <div>
                  <md-button class="md-raised qna-btn" @click="registerUser">회원가입</md-button>
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

export default {
  name: 'RegisterView',
  bodyClass: 'profile-page',
  data() {
    return {
      userId: '',
      userPassword: '',
      userName: '',
      userAddress: '',
      userPhoneNumber: '',
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
    registerUser() {
      if (this.userId.length < 1) {
        alert('아이디를 입력해주세요!');
        this.userId = '';
        return;
      } else if (this.userPassword.length < 1) {
        alert('비밀번호를 입력해주세요!');
        this.userPassword = '';
        return;
      } else if (this.userPassword.length < 8) {
        alert('비밀번호는 8자리 이상 입니다. 다시 입력해주세요!');
        this.userPassword = '';
        return;
      } else if (this.userName.length < 1) {
        alert('이름을 입력해주세요!');
        this.userName = '';
        return;
      } else if (this.userAddress.length < 1) {
        alert('주소를 입력해주세요!');
        this.userAddress = '';
        return;
      } else if (this.userPhoneNumber.length < 1) {
        alert('전화번호를 입력해주세요!');
        this.userPhoneNumber = '';
        return;
      } else {
        http
          .post('/user/register', {
            // userInfo : {
            userId: this.userId,
            userPassword: this.userPassword,
            userName: this.userName,
            userAddress: this.userAddress,
            userPhoneNumber: this.userPhoneNumber,
            // }
          })
          .then(({ data }) => {
            alert('등록 완료');
            this.$router.push({ name: 'index' });
          })
          .catch(()=>{
            alert('아이디가 중복되었습니다.');
          });
      }
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
  width: 75% !important;
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
