<template>
  <div class="wrapper">
    <div class="section page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand" v-if="this.type === 'regist'">
              <h1>Q&A 등록</h1>
            </div>
            <div class="brand" v-else-if="this.type === 'modify'">
              <h1>Q&A 수정</h1>
            </div>
            <div class="brand" v-else-if="this.type === 'detail'">
              <h1>Q&A</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="main main-raised">
      <div class="section profile-content">
        <div class="container">
          <div class="profile">
            <div class="name"></div>
          </div>
          <div>
            <md-field class="md-theme-default1">
              <label for="reg-title">제목</label>
              <md-input
                name="reg-title"
                id="reg-title"
                v-model="article.title"
                maxlength="50"
                :disabled="isDisabled"
              />
            </md-field>

            <md-field class="md-theme-default2" v-if="this.type === 'regist'">
              <label for="reg-userId">작성자</label>
              <md-input
                name="reg-userId"
                id="reg-userId"
                v-model="article.userId"
                :disabled="true"
              />
            </md-field>

            <md-field
              class="md-theme-default2"
              v-if="this.type === 'detail' || this.type === 'modify'"
            >
              <label for="reg-userId">작성자</label>
              <md-input
                name="reg-userId"
                id="reg-userId"
                v-model="article.userId"
                :disabled="true"
              />
            </md-field>

            <md-field class="md-theme-default3">
              <label for="reg-content">내용</label>
              <md-textarea
                id="reg-content"
                name="reg-content"
                v-model="article.content"
                maxlength="1000"
                :disabled="isDisabled"
              />
            </md-field>

            <comment-list :no="article.no" v-if="this.type === 'detail'"></comment-list>
            <div>
              <md-card-actions class="btn-margin">
                <div>
                  <md-button @click="resetqna">목록으로 돌아가기</md-button>
                </div>
                <div v-if="this.type === 'regist' || this.type === 'modify'">
                  <md-button @click="registerqna">등록</md-button>
                </div>
                <div v-else-if="isloginuser">
                  <md-button @click="moveModifyArticle" type="button">수정</md-button>
                  <md-button @click="deleteArticle" type="button">삭제</md-button>
                </div>
              </md-card-actions>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/api/http';
import { mapState } from 'vuex';
import CommentList from './CommentInputItem.vue';

export default {
  bodyClass: 'profile-page',
  name: 'QnaInputItem',
  components: {
    CommentList,
  },
  data() {
    return {
      article: {
        no: 0,
        userId: '',
        title: '',
        content: '',
        regtime: '',
      },
      isDisabled: false,
    };
  },

  computed: {
    ...mapState(['loginuser']),
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
  },

  props: {
    type: { type: String },
    image: {
      type: String,
      default: require('@/assets/img/architecture2.jpg'),
    },
  },

  created() {
    if (this.type === 'regist') {
      this.article.userId = this.$store.state.loginuser.userId;
    }
    if (this.type === 'modify') {
      http.get(`/qna/${this.$route.params.no}`).then(({ data }) => {
        this.article = data;
      });
    }

    if (this.type === 'detail') {
      http.get(`/qna/${this.$route.params.no}`).then(({ data }) => {
        this.article = data;
        if (this.article.userId === this.$store.state.loginuser.userId) {
          this.isloginuser = true;
        }
      });
      this.isDisabled = !this.isDisabled;
    }
  },

  methods: {
    registerqna(event) {
      event.preventDefault();

      let err = true;
      let msg = '';
      !this.article.title && ((msg = '제목 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = '내용 입력해주세요'), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === 'regist' ? this.registArticle() : this.$emit('doModify', this.article);
    },
    resetqna(event) {
      event.preventDefault();
      this.article.title = '';
      this.article.content = '';
      this.$router.push({ name: 'QnaList' });
    },
    registArticle() {
      http
        .post(`/qna`, {
          userId: this.article.userId,
          // userId: this.loginuser.userId,
          title: this.article.title,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '등록이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        });
    },
    moveModifyArticle() {
      this.$router.push({ name: 'QnaModify', params: { no: this.article.no } });
    },
    deleteArticle() {
      http.delete(`/qna/${this.$route.params.no}`).then(({ data }) => {
        let msg = '삭제 처리시 문제가 발생했습니다.';
        if (data === 'success') {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: 'QnaList' });
      });
    },
    moveList() {
      this.$router.push({ name: 'QnaList' });
    },
  },
};
</script>

<style lang="scss" scoped>
.section {
  padding: 0;
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
  margin-top: 10px;
  margin-bottom: 10px;
}

.comment-btn {
  margin-top: 25px;
  margin-bottom: 25px;
}

#qnaTitle {
  color: black !important;
  font-weight: 600;
  font-size: 24px;
  text-align: center;
  display: initial;
  margin-bottom: 5%;
}

.md-title {
  font-weight: 600;
}

.md-card {
  font-weight: 600;
  margin-top: 0px;
  box-shadow: 0 2px 2px 0 rgb(0 0 0 / 14%), 0 3px 1px -2px rgb(0 0 0 / 20%),
    0 1px 5px 0 rgb(0 0 0 / 12%); // 마지막에 수정해야함
}

.md-fab-bottom-right {
  margin: 10px 6px;
  width: 94%;
}

.text-center {
  padding-left: 3%;
  padding-right: 3%;
}

.md-table-head-label {
  height: 50px;
}

.md-theme-default1 {
  margin-top: 30px;
}

.md-theme-default2 {
  margin-top: 50px;
}

.btn-margin {
  margin-bottom: 1%;
  margin-top: 1%;
  justify-content: space-between;
}

.elevation-demo {
  display: contents;
}

#card1 {
  width: 20%;
  margin-left: 1px;
  margin-right: 1px;
}

#card2 {
  width: 60%;
  margin-left: 1px;
  margin-right: 1px;
}

#card3 {
  width: 20%;
  margin-left: 1px;
  margin-right: 1px;
}

#card4 {
  box-shadow: 0 0px 0px 0 rgb(0 0 0 / 14%), 0 0px 0px 0px rgb(0 0 0 / 20%),
    0 0px 0px 0 rgb(0 0 0 / 12%);
  font-weight: 400;
}

.brand {
  color: #ffffff;
}

h1 {
  font-weight: 550 !important;
}
</style>
