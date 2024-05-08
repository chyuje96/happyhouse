<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand" v-if="!ismodify">
              <h1>회원정보</h1>
            </div>
            <div class="brand" v-else-if="ismodify">
              <h1>회원정보 수정</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          
          <div>
            <div class="registerbox">
              <md-field>
                <label>ID</label>
                <md-input v-model="userId" disabled="true"></md-input>
              </md-field>

              <md-field>
                <label>Password</label>
                <md-input v-model="userPassword" type="password" :disabled="!ismodify"></md-input>
                <span class="md-helper-text">비밀번호는 8자리 이상 영문/숫자</span>
              </md-field>

              <md-field>
                <label>Username</label>
                <md-input v-model="userName" :disabled="!ismodify"></md-input>
              </md-field>

              <md-field>
                <label>Useraddress</label>
                <md-input v-model="userAddress" :disabled="!ismodify"></md-input>
              </md-field>

              <md-field>
                <label>Phonenumber</label>
                <md-input v-model="userPhoneNumber" :disabled="!ismodify"></md-input>
              </md-field>
              <div class="md-raised btn-margin">
                <div v-if="!ismodify">
                  <md-button class="modify-btn" @click="activeModify">정보수정</md-button>
                  <md-button @click="deleteUser" type="button">회원탈퇴</md-button>
                </div>
                <div v-else-if="ismodify" class="register-btn">
                  <md-button @click="updateUser" class="modify-btn">수정</md-button>
                  <md-button @click="deactiveModify">취소</md-button>
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
import { mapState, mapActions } from 'vuex';

export default {
  name: 'ProfileView',
  bodyClass: 'profile-page',
  data() {
    return {
      ismodify: false,
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
    ...mapState(['loginuser']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },
  created() {
    this.setUser();
  },
  methods: {
    ...mapActions(['setLoginUser', 'logoutUser']),
    setUser() {
      this.userId = this.loginuser.userId;
      this.userPassword = this.loginuser.userPassword;
      this.userName = this.loginuser.userName;
      this.userAddress = this.loginuser.userAddress;
      this.userPhoneNumber = this.loginuser.userPhoneNumber;
    },
    activeModify() {
      this.ismodify = true;
    },
    deactiveModify() {
      this.setUser();
      this.ismodify = false;
    },
    updateUser() {
      http
        .put('/user/update', {
          userId: this.userId,
          userPassword: this.userPassword,
          userName: this.userName,
          userAddress: this.userAddress,
          userPhoneNumber: this.userPhoneNumber,
        })
        .then(({ data }) => {
          alert('수정 완료');
          this.setLoginUser(data);
          this.ismodify = false;
        });
    },
    deleteUser() {
      http.delete(`/user/delete/${this.userId}`).then(({ data }) => {
        let msg = '삭제 처리시 문제가 발생했습니다.';
        if (data === 'delete_success') {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);

        this.logoutUser();

        this.$router.push({ name: 'index' });
      })
      .catch(()=>{
        alert("서버 에러가 발생하였습니다.");
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
  margin: 10px 6px;
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

.register-btn {
  margin-left: 2px;
}
.modify-btn {
  margin-right: 4px;
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
