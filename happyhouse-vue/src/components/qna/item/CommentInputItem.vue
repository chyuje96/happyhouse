<template>
  <md-list :md-expand-single="expandSingle" @click="getcomment()">
    <md-list-item md-expand>
      <md-card class="card">
        <md-card-content>댓글</md-card-content>
      </md-card>

      <md-list slot="md-expand">
        <md-list-item v-for="(comment, index) in commentlist" :key="comment.no">
          <div class="elevation-demo">
            <md-content class="md-elevation-1" id="card1">
              <md-card class="card" id="card4">
                <md-card-content>{{ comment.userId }}</md-card-content>
              </md-card>
            </md-content>
            <md-content class="md-elevation-1" id="card2">
              <md-card class="card" id="card4">
                <md-card-content>{{ comment.comment }}</md-card-content>
              </md-card>
            </md-content>
            <md-content class="md-elevation-1" id="card3">
              <md-card class="card" id="card4">
                <md-card-content>{{ comment.regtime }}</md-card-content>
              </md-card>
            </md-content>
          </div>
          <md-content>
            <md-button class="comment-btn1" v-if="issame[index]" @click="deletecomment(comment)"
              >삭제</md-button
            >
          </md-content>
        </md-list-item>

        <md-list-item v-if="loginuser">
          <md-field>
            <label>댓글</label>
            <md-textarea v-model="commentwrite" md-counter="500"></md-textarea>
            <md-button class="comment-btn" @click="registercomment">댓글 등록</md-button>
          </md-field>
        </md-list-item>
      </md-list>
    </md-list-item>
  </md-list>
</template>

<script>
import http from '@/api/http';
import { mapState } from 'vuex';

export default {
  name: 'Comment',

  computed: {
    ...mapState(['loginuser']),
  },

  props: {
    no: Number,
  },

  data() {
    return {
      commentlist: [],
      commentwrite: '',
      issame: [],
      ismodify: [],
      isDisabled: false,
      // comment: {
      //   no: 0,
      //   userId: '',
      //   comment: '',
      //   regtime: '',
      //   qnaboardno: 0,
      // },
    };
  },

  created() {
    this.getcomment();
  },

  methods: {
    getcomment() {
      http.get(`/comment/${this.no}`).then(({ data }) => {
        this.commentlist = data;

        this.issame = [];
        this.ismodify = [];

        for (const comment of this.commentlist) {
          if (comment.userId === this.$store.state.loginuser.userId) {
            this.issame.push(true);
          } else {
            this.issame.push(false);
          }
          this.ismodify.push(false);
        }
      });
    },
    registercomment() {
      http
        .post(`/comment`, {
          userId: this.loginuser.userId,
          comment: this.commentwrite,
          qnaboardno: this.no,
        })
        .then(({ data }) => {
          // this.comments = data;
          this.commentwrite = '';
          this.getcomment();
        });
    },
    deletecomment(comment) {
      http.delete(`/comment/${comment.no}`).then(({ data }) => {
        // this.comments = data;
        this.getcomment();
      });
    },
    activatemodify(index) {
      this.ismodify[index] = true;
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
  margin-top: 0px !important;
  margin-bottom: 0px !important;
}

#card1 {
  width: 17.5%;
  margin-left: 1px;
  margin-right: 1px;
}

#card2 {
  width: 65%;
  margin-left: 1px;
  margin-right: 1px;
}

#card3 {
  width: 17.5%;
  margin-left: 1px;
  margin-right: 1px;
}

#card4 {
  box-shadow: 0 0px 0px 0 rgb(0 0 0 / 14%), 0 0px 0px 0px rgb(0 0 0 / 20%),
    0 0px 0px 0 rgb(0 0 0 / 12%);
  font-weight: 400;
}

.card {
  margin-top: 0px !important;
  margin-bottom: 0px !important;
}

.comment-btn1 {
  width: 25px;
  margin: 0px 0px;
}
</style>
